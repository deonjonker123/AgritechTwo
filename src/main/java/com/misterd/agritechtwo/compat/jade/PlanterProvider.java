package com.misterd.agritechtwo.compat.jade;

import com.misterd.agritechtwo.Config;
import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.datamap.ATDataMaps;
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

        data.putBoolean("hasCrop", true);
        data.putString("cropName", seedStack.getDisplayName().getString());
        data.putInt("currentStage", planter.getGrowthStage());
        data.putInt("maxStage", planter.isTree() ? 1 : 8);
        data.putFloat("progressPercent", planter.getGrowthProgress() * 100.0F);
        data.putString("soilName", soilStack.getDisplayName().getString());
        data.putFloat("growthModifier", planter.getSoilGrowthModifier(soilStack));

        ItemStack fertStack = planter.getStack(2);
        if (!fertStack.isEmpty()) {
            var fertData = fertStack.getItem().builtInRegistryHolder().getData(ATDataMaps.FERTILIZERS);
            data.putBoolean("hasFertilizer", true);
            data.putString("fertilizerName", fertStack.getDisplayName().getString());
            data.putFloat("fertilizerSpeedModifier", fertData != null ? fertData.speedMultiplier() : 1.0F);
            data.putFloat("fertilizerYieldModifier", fertData != null ? fertData.yieldMultiplier() : 1.0F);
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
}