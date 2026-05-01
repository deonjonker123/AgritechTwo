package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class JungleCrateBlock extends CrateBlock {
    public static final MapCodec<JungleCrateBlock> CODEC = simpleCodec(JungleCrateBlock::new);

    public JungleCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
