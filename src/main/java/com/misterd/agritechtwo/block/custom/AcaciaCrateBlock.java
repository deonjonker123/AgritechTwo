package com.misterd.agritechtwo.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BaseEntityBlock;

public class AcaciaCrateBlock extends CrateBlock {
    public static final MapCodec<AcaciaCrateBlock> CODEC = simpleCodec(AcaciaCrateBlock::new);

    public AcaciaCrateBlock(Properties properties) {
        super(properties);
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
