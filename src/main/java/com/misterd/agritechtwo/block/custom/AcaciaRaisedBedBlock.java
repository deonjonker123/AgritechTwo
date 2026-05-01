package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class AcaciaRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<AcaciaRaisedBedBlock> CODEC = simpleCodec(AcaciaRaisedBedBlock::new);

    public AcaciaRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
