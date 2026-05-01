package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class SpruceCrateBlock extends CrateBlock {
    public static final MapCodec<SpruceCrateBlock> CODEC = simpleCodec(SpruceCrateBlock::new);

    public SpruceCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
