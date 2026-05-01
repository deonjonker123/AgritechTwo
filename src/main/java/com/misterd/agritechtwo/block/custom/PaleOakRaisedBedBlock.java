package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class PaleOakRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<PaleOakRaisedBedBlock> CODEC = simpleCodec(PaleOakRaisedBedBlock::new);

    public PaleOakRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
