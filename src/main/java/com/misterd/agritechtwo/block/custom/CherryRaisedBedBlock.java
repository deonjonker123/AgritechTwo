package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class CherryRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<CherryRaisedBedBlock> CODEC = simpleCodec(CherryRaisedBedBlock::new);

    public CherryRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
