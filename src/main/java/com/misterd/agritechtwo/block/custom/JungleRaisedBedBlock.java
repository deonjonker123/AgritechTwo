package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class JungleRaisedBedBlock extends RaisedBedBlock {
    public static final MapCodec<JungleRaisedBedBlock> CODEC = simpleCodec(JungleRaisedBedBlock::new);

    public JungleRaisedBedBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
