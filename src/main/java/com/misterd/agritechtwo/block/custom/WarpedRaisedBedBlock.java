package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class WarpedRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<WarpedRaisedBedBlock> CODEC = simpleCodec(WarpedRaisedBedBlock::new);

    public WarpedRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
