package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class CrimsonRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<CrimsonRaisedBedBlock> CODEC = simpleCodec(CrimsonRaisedBedBlock::new);

    public CrimsonRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
