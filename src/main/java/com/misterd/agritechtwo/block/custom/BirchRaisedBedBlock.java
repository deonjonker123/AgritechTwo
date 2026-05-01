package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class BirchRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<BirchRaisedBedBlock> CODEC = simpleCodec(BirchRaisedBedBlock::new);

    public BirchRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
