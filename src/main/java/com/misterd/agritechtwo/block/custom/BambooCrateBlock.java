package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class BambooCrateBlock extends CrateBlock {
    public static final MapCodec<BambooCrateBlock> CODEC = simpleCodec(BambooCrateBlock::new);

    public BambooCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
