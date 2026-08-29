package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.block.ATBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;

public class ATLootTableProvider extends BlockLootSubProvider {
    public ATLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ATBlocks.ACACIA_PLANTER.get());
        dropSelf(ATBlocks.BAMBOO_PLANTER.get());
        dropSelf(ATBlocks.BIRCH_PLANTER.get());
        dropSelf(ATBlocks.CHERRY_PLANTER.get());
        dropSelf(ATBlocks.CRIMSON_PLANTER.get());
        dropSelf(ATBlocks.DARK_OAK_PLANTER.get());
        dropSelf(ATBlocks.JUNGLE_PLANTER.get());
        dropSelf(ATBlocks.MANGROVE_PLANTER.get());
        dropSelf(ATBlocks.OAK_PLANTER.get());
        dropSelf(ATBlocks.SPRUCE_PLANTER.get());
        dropSelf(ATBlocks.WARPED_PLANTER.get());
        dropSelf(ATBlocks.TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.BLACK_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.BLUE_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.BROWN_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.CYAN_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.GRAY_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.GREEN_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.LIME_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.ORANGE_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.PINK_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.PURPLE_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.RED_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.WHITE_TERRACOTTA_PLANTER.get());
        dropSelf(ATBlocks.YELLOW_TERRACOTTA_PLANTER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ATBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
