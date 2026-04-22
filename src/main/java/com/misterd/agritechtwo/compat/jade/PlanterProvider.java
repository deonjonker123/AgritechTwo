package com.misterd.agritechtwo.compat.jade;

import com.misterd.agritechtwo.Config;
import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.util.RegistryHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IServerDataProvider;

public enum PlanterProvider implements IServerDataProvider<BlockAccessor> {
    INSTANCE;

    static final Identifier UID =
            Identifier.fromNamespaceAndPath("agritechtwo", "planter_info");

    @Override
    public Identifier getUid() {
        return UID;
    }

    @Override
    public void appendServerData(CompoundTag data, BlockAccessor accessor) {
        BlockEntity be = accessor.getBlockEntity();
        if (be instanceof PlanterBlockEntity planter) {
            appendBasicPlanterData(data, planter, accessor.getBlockState());
        }
    }

    private void appendBasicPlanterData(CompoundTag data, PlanterBlockEntity planter, BlockState state) {
        ItemStack seedStack = planter.getStack(0);
        ItemStack soilStack = planter.getStack(1);
        if (seedStack.isEmpty() || soilStack.isEmpty()) {
            data.putBoolean("hasCrop", false);
            return;
        }

        data.putBoolean("hasCrop",true);
        data.putString("cropName", seedStack.getDisplayName().getString());
        data.putInt("currentStage", planter.getGrowthStage());
        data.putInt("maxStage", getMaxStage(seedStack));
        data.putFloat("progressPercent", planter.getGrowthProgress() * 100.0F);
        data.putString("soilName", soilStack.getDisplayName().getString());
        data.putFloat("growthModifier", planter.getSoilGrowthModifier(soilStack));

        ItemStack fertStack = planter.getStack(2);
        if (!fertStack.isEmpty()) {
            String fertId = RegistryHelper.getItemId(fertStack);
            data.putBoolean("hasFertilizer",true);
            data.putString("fertilizerName", fertStack.getDisplayName().getString());
            data.putFloat("fertilizerSpeedModifier", getFertilizerSpeedModifier(fertId));
            data.putFloat("fertilizerYieldModifier", getFertilizerYieldModifier(fertId));
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
        PlantablesConfig.FertilizerInfo info = PlantablesConfig.getFertilizerInfo(fertilizerId);
        return info != null ? info.speedMultiplier : 1.0F;
    }

    private float getFertilizerYieldModifier(String fertilizerId) {
        PlantablesConfig.FertilizerInfo info = PlantablesConfig.getFertilizerInfo(fertilizerId);
        return info != null ? info.yieldMultiplier : 1.0F;
    }

    private int getMaxStage(ItemStack plantStack) {
        return PlantablesConfig.isValidSapling(RegistryHelper.getItemId(plantStack)) ? 1 : 8;
    }
}