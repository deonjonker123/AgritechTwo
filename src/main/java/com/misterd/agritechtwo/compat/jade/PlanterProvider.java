package com.misterd.agritechtwo.compat.jade;

import com.misterd.agritechtwo.Config;
import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.util.RegistryHelper;
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

        String cropName       = data.getString("cropName");
        int currentStage      = data.getInt("currentStage");
        int maxStage          = data.getInt("maxStage");
        float progressPercent = data.getFloat("progressPercent");
        String soilName       = data.getString("soilName");
        float growthModifier  = data.getFloat("growthModifier");

        if (progressPercent >= 100.0F) {
            tooltip.add(Component.translatable("jade.agritechtwo.crop_ready", cropName)
                    .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        } else {
            tooltip.add(Component.translatable("jade.agritechtwo.crop_progress", cropName, currentStage, maxStage, Math.round(progressPercent))
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        tooltip.add(Component.translatable("jade.agritechtwo.soil_info", soilName, String.format("%.2fx", growthModifier))
                .withStyle(ChatFormatting.GRAY));

        if (data.contains("hasFertilizer") && data.getBoolean("hasFertilizer")) {
            float fertSpeed = data.getFloat("fertilizerSpeedModifier");
            float fertYield = data.getFloat("fertilizerYieldModifier");
            tooltip.add(Component.translatable("jade.agritechtwo.fertilizer_info",
                            data.getString("fertilizerName"),
                            String.format("%.2fx", fertSpeed),
                            String.format("%.2fx", fertYield))
                    .withStyle(ChatFormatting.YELLOW));
        }

        if (data.contains("isCloched") && data.getBoolean("isCloched")) {
            float clocheSpeed = data.getFloat("clocheSpeedModifier");
            float clocheYield = data.getFloat("clocheYieldModifier");
            tooltip.add(Component.translatable("jade.agritechtwo.cloche_installed",
                            String.format("%.2fx", clocheSpeed),
                            String.format("%.2fx", clocheYield))
                    .withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof PlanterBlockEntity planter) {
            appendBasicPlanterData(data, planter, accessor.getBlockState());
        }
    }

    private void appendBasicPlanterData(CompoundTag data, PlanterBlockEntity planter, BlockState state) {
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
            String fertilizerId = RegistryHelper.getItemId(fertilizerStack);
            data.putBoolean("hasFertilizer", true);
            data.putString("fertilizerName", fertilizerStack.getDisplayName().getString());
            data.putFloat("fertilizerSpeedModifier", getFertilizerSpeedModifier(fertilizerId));
            data.putFloat("fertilizerYieldModifier", getFertilizerYieldModifier(fertilizerId));
        } else {
            data.putBoolean("hasFertilizer", false);
        }

        boolean cloched = state.getValue(PlanterBlock.CLOCHED);
        data.putBoolean("isCloched", cloched);
        if (cloched) {
            data.putFloat("clocheSpeedModifier", (float) Config.getClocheSpeedMultiplier());
            data.putFloat("clocheYieldModifier", (float) Config.getClocheYieldMultiplier());
        }
    }

    private float getFertilizerSpeedModifier(String fertilizerId) {
        return switch (fertilizerId) {
            case "minecraft:bone_meal"                        -> (float) Config.getFertilizerBoneMealSpeedMultiplier();
            case "immersiveengineering:fertilizer"            -> (float) Config.getFertilizerImmersiveFertilizerSpeedMultiplier();
            case "mysticalagriculture:fertilized_essence"     -> (float) Config.getFertilizerFertilizedEssenceSpeedMultiplier();
            case "mysticalagriculture:mystical_fertilizer"    -> (float) Config.getFertilizerMysticalFertilizerSpeedMultiplier();
            case "forbidden_arcanus:arcane_bone_meal"         -> (float) Config.getFertilizerArcaneBoneMealSpeedMultiplier();
            default -> {
                PlantablesConfig.FertilizerInfo info = PlantablesConfig.getFertilizerInfo(fertilizerId);
                yield info != null ? info.speedMultiplier : 1.0F;
            }
        };
    }

    private float getFertilizerYieldModifier(String fertilizerId) {
        return switch (fertilizerId) {
            case "minecraft:bone_meal"                        -> (float) Config.getFertilizerBoneMealYieldMultiplier();
            case "immersiveengineering:fertilizer"            -> (float) Config.getFertilizerImmersiveFertilizerYieldMultiplier();
            case "mysticalagriculture:fertilized_essence"     -> (float) Config.getFertilizerFertilizedEssenceYieldMultiplier();
            case "mysticalagriculture:mystical_fertilizer"    -> (float) Config.getFertilizerMysticalFertilizerYieldMultiplier();
            case "forbidden_arcanus:arcane_bone_meal"         -> (float) Config.getFertilizerArcaneBoneMealYieldMultiplier();
            default -> {
                PlantablesConfig.FertilizerInfo info = PlantablesConfig.getFertilizerInfo(fertilizerId);
                yield info != null ? info.yieldMultiplier : 1.0F;
            }
        };
    }

    private int getMaxStage(ItemStack plantStack) {
        return PlantablesConfig.isValidSapling(RegistryHelper.getItemId(plantStack)) ? 1 : 8;
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }
}