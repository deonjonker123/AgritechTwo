package com.misterd.agritechtwo.compat.jade;

import com.misterd.agritechtwo.Config;
import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.datamap.ATDataMaps;
import com.misterd.agritechtwo.datamap.FertilizerData;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum PlanterProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath("agritechtwo", "planter_info");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        if (!data.contains("hasCrop") || !data.getBoolean("hasCrop")) return;
        String cropName = data.getString("cropName");
        int currentStage = data.getInt("currentStage");
        int maxStage = data.getInt("maxStage");
        float progressPercent = data.getFloat("progressPercent");
        String soilName = data.getString("soilName");
        float growthModifier = data.getFloat("growthModifier");
        if (progressPercent >= 100.0F) {
            tooltip.add(Component.translatable("jade.agritechtwo.crop_ready", cropName).withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        } else {
            tooltip.add(Component.translatable("jade.agritechtwo.crop_progress", cropName, currentStage, maxStage, Math.round(progressPercent)).withStyle(ChatFormatting.DARK_GREEN));
        }
        tooltip.add(Component.translatable("jade.agritechtwo.soil_info", soilName, String.format("%.2fx", growthModifier)).withStyle(ChatFormatting.GRAY));
        if (data.getBoolean("hasFertilizer")) {
            tooltip.add(Component.translatable("jade.agritechtwo.fertilizer_info",
                    data.getString("fertilizerName"),
                    String.format("%.2fx", data.getFloat("fertilizerSpeedModifier")),
                    String.format("%.2fx", data.getFloat("fertilizerYieldModifier"))).withStyle(ChatFormatting.YELLOW));
        }
        if (data.getBoolean("isCloched")) {
            tooltip.add(Component.translatable("jade.agritechtwo.cloche_installed",
                    String.format("%.2fx", data.getFloat("clocheSpeedModifier")),
                    String.format("%.2fx", data.getFloat("clocheYieldModifier"))).withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof PlanterBlockEntity planter) {
            appendPlanterData(data, planter, accessor.getBlockState());
        }
    }

    private void appendPlanterData(CompoundTag data, PlanterBlockEntity planter, BlockState state) {
        ItemStack seedStack = planter.inventory.getStackInSlot(0);
        ItemStack soilStack = planter.inventory.getStackInSlot(1);
        if (seedStack.isEmpty() || soilStack.isEmpty()) { data.putBoolean("hasCrop", false); return; }
        data.putBoolean("hasCrop", true);
        data.putString("cropName", seedStack.getHoverName().getString());
        data.putInt("currentStage", planter.getGrowthStage());
        data.putInt("maxStage", planter.isTree() ? 1 : 8);
        data.putFloat("progressPercent", planter.getGrowthProgress() * 100.0F);
        data.putString("soilName", soilStack.getHoverName().getString());
        data.putFloat("growthModifier", planter.getSoilGrowthModifier(soilStack));
        appendFertilizerData(data, planter.inventory.getStackInSlot(2));
        appendClocheData(data, state.getValue(PlanterBlock.CLOCHED));
    }

    private void appendFertilizerData(CompoundTag data, ItemStack fertilizerStack) {
        if (fertilizerStack.isEmpty()) { data.putBoolean("hasFertilizer", false); return; }
        FertilizerData fertData = ATDataMaps.getFertilizer(fertilizerStack.getItem());
        data.putBoolean("hasFertilizer", fertData != null);
        if (fertData == null) return;
        data.putString("fertilizerName", fertilizerStack.getHoverName().getString());
        data.putFloat("fertilizerSpeedModifier", fertData.speedMultiplier());
        data.putFloat("fertilizerYieldModifier", fertData.yieldMultiplier());
    }

    private void appendClocheData(CompoundTag data, boolean cloched) {
        data.putBoolean("isCloched", cloched);
        if (cloched) {
            data.putFloat("clocheSpeedModifier", (float) Config.getClocheSpeedMultiplier());
            data.putFloat("clocheYieldModifier", (float) Config.getClocheYieldMultiplier());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }
}