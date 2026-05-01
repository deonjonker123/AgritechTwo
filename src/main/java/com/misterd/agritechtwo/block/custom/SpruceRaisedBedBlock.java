package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class SpruceRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<SpruceRaisedBedBlock> CODEC = simpleCodec(SpruceRaisedBedBlock::new);

    public SpruceRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
