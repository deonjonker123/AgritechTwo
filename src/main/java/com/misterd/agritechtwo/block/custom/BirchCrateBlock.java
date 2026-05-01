package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class BirchCrateBlock extends CrateBlock {
    public static final MapCodec<BirchCrateBlock> CODEC = simpleCodec(BirchCrateBlock::new);

    public BirchCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
