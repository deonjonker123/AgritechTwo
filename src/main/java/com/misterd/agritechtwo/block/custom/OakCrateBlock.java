package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class OakCrateBlock extends CrateBlock {
    public static final MapCodec<OakCrateBlock> CODEC = simpleCodec(OakCrateBlock::new);

    public OakCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
