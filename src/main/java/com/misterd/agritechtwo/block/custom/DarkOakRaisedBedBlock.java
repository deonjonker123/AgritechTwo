package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class DarkOakRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<DarkOakRaisedBedBlock> CODEC = simpleCodec(DarkOakRaisedBedBlock::new);

    public DarkOakRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
