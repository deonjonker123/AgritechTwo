package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class DarkOakCrateBlock extends CrateBlock {
    public static final MapCodec<DarkOakCrateBlock> CODEC = simpleCodec(DarkOakCrateBlock::new);

    public DarkOakCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
