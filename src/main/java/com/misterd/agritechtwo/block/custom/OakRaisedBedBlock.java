package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class OakRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<OakRaisedBedBlock> CODEC = simpleCodec(OakRaisedBedBlock::new);

    public OakRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
