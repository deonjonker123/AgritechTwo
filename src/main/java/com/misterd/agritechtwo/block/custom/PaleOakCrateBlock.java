package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class PaleOakCrateBlock extends CrateBlock {
    public static final MapCodec<PaleOakCrateBlock> CODEC = simpleCodec(PaleOakCrateBlock::new);

    public PaleOakCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
