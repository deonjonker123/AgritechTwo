package com.misterd.agritechtwo.compat.jade;

import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.util.RegistryHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
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

        String cropName      = data.getString("cropName");
        int currentStage     = data.getInt("currentStage");
        int maxStage         = data.getInt("maxStage");
        float progressPercent = data.getFloat("progressPercent");
        String soilName      = data.getString("soilName");
        float growthModifier = data.getFloat("growthModifier");

        if (progressPercent >= 100.0F) {
            tooltip.add(Component.translatable("jade.agritechtwo.crop_ready", cropName)
                    .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        } else {
            tooltip.add(Component.translatable("jade.agritechtwo.crop_progress", cropName, currentStage, maxStage, Math.round(progressPercent))
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        tooltip.add(Component.translatable("jade.agritechtwo.soil_info", soilName, String.format("%.2fx", growthModifier))
                .withStyle(ChatFormatting.GRAY));

        if (data.contains("hasModules") && data.getBoolean("hasModules")) {
            tooltip.add(Component.translatable("jade.agritechtwo.modules_installed", data.getString("moduleInfo"))
                    .withStyle(ChatFormatting.BLUE));
        }

        if (data.contains("hasFertilizer") && data.getBoolean("hasFertilizer")) {
            tooltip.add(Component.translatable("jade.agritechtwo.fertilizer_info", data.getString("fertilizerName"))
                    .withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof PlanterBlockEntity basicPlanter) {
            appendBasicPlanterData(data, basicPlanter);
        }
    }


    private void appendBasicPlanterData(CompoundTag data, PlanterBlockEntity planter) {
        ItemStack seedStack = planter.inventory.getStackInSlot(0);
        ItemStack soilStack = planter.inventory.getStackInSlot(1);
        if (seedStack.isEmpty() || soilStack.isEmpty()) {
            data.putBoolean("hasCrop", false);
            return;
        }
        data.putBoolean("hasCrop", true);
        data.putString("cropName", seedStack.getDisplayName().getString());
        data.putInt("currentStage", planter.getGrowthStage());
        data.putInt("maxStage", getMaxStage(seedStack));
        data.putFloat("progressPercent", planter.getGrowthProgress() * 100.0F);
        data.putString("soilName", soilStack.getDisplayName().getString());
        data.putFloat("growthModifier", planter.getSoilGrowthModifier(soilStack));
        ItemStack fertilizerStack = planter.inventory.getStackInSlot(2);
        if (!fertilizerStack.isEmpty()) {
            data.putBoolean("hasFertilizer", true);
            data.putString("fertilizerName", fertilizerStack.getDisplayName().getString());
        } else {
            data.putBoolean("hasFertilizer", false);
        }
    }

    private int getMaxStage(ItemStack plantStack) {
        return PlantablesConfig.isValidSapling(RegistryHelper.getItemId(plantStack)) ? 1 : 8;
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }
}
