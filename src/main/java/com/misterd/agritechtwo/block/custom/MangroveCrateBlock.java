package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class MangroveCrateBlock extends CrateBlock {
    public static final MapCodec<MangroveCrateBlock> CODEC = simpleCodec(MangroveCrateBlock::new);

    public MangroveCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
