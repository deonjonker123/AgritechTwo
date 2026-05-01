package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class CherryCrateBlock extends CrateBlock {
    public static final MapCodec<CherryCrateBlock> CODEC = simpleCodec(CherryCrateBlock::new);

    public CherryCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
