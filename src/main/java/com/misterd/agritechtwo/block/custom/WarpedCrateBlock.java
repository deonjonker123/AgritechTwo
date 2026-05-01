package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class WarpedCrateBlock extends CrateBlock {
    public static final MapCodec<WarpedCrateBlock> CODEC = simpleCodec(WarpedCrateBlock::new);

    public WarpedCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
