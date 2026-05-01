package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class CrimsonCrateBlock extends CrateBlock {
    public static final MapCodec<CrimsonCrateBlock> CODEC = simpleCodec(CrimsonCrateBlock::new);

    public CrimsonCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
