package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class MangroveRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<MangroveRaisedBedBlock> CODEC = simpleCodec(MangroveRaisedBedBlock::new);

    public MangroveRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
