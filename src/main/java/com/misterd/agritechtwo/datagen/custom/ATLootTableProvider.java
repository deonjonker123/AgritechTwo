package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.block.ATBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

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
        dropSelf(ATBlocks.PALE_OAK_PLANTER.get());

        dropSelf(ATBlocks.ACACIA_RAISED_BED.get());
        dropSelf(ATBlocks.BAMBOO_RAISED_BED.get());
        dropSelf(ATBlocks.BIRCH_RAISED_BED.get());
        dropSelf(ATBlocks.CHERRY_RAISED_BED.get());
        dropSelf(ATBlocks.CRIMSON_RAISED_BED.get());
        dropSelf(ATBlocks.DARK_OAK_RAISED_BED.get());
        dropSelf(ATBlocks.JUNGLE_RAISED_BED.get());
        dropSelf(ATBlocks.MANGROVE_RAISED_BED.get());
        dropSelf(ATBlocks.OAK_RAISED_BED.get());
        dropSelf(ATBlocks.SPRUCE_RAISED_BED.get());
        dropSelf(ATBlocks.WARPED_RAISED_BED.get());
        dropSelf(ATBlocks.PALE_OAK_RAISED_BED.get());

        dropSelf(ATBlocks.ACACIA_CRATE.get());
        dropSelf(ATBlocks.BAMBOO_CRATE.get());
        dropSelf(ATBlocks.BIRCH_CRATE.get());
        dropSelf(ATBlocks.CHERRY_CRATE.get());
        dropSelf(ATBlocks.CRIMSON_CRATE.get());
        dropSelf(ATBlocks.DARK_OAK_CRATE.get());
        dropSelf(ATBlocks.JUNGLE_CRATE.get());
        dropSelf(ATBlocks.MANGROVE_CRATE.get());
        dropSelf(ATBlocks.OAK_CRATE.get());
        dropSelf(ATBlocks.PALE_OAK_CRATE.get());
        dropSelf(ATBlocks.SPRUCE_CRATE.get());
        dropSelf(ATBlocks.WARPED_CRATE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ATBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
