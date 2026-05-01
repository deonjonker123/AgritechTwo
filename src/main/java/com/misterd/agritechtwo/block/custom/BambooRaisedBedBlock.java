package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class BambooRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<BambooRaisedBedBlock> CODEC = simpleCodec(BambooRaisedBedBlock::new);

    public BambooRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
