package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ATBlockTagProvider extends BlockTagsProvider {
    public ATBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, AgritechTwo.MODID);
    }

    private static ResourceKey<Block> key(Block block) {
        return block.builtInRegistryHolder().key();
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(key(ATBlocks.ACACIA_PLANTER.get()))
                .add(key(ATBlocks.BAMBOO_PLANTER.get()))
                .add(key(ATBlocks.BIRCH_PLANTER.get()))
                .add(key(ATBlocks.CHERRY_PLANTER.get()))
                .add(key(ATBlocks.CRIMSON_PLANTER.get()))
                .add(key(ATBlocks.DARK_OAK_PLANTER.get()))
                .add(key(ATBlocks.JUNGLE_PLANTER.get()))
                .add(key(ATBlocks.MANGROVE_PLANTER.get()))
                .add(key(ATBlocks.OAK_PLANTER.get()))
                .add(key(ATBlocks.SPRUCE_PLANTER.get()))
                .add(key(ATBlocks.WARPED_PLANTER.get()))
                .add(key(ATBlocks.PALE_OAK_PLANTER.get()))

                .add(key(ATBlocks.ACACIA_RAISED_BED.get()))
                .add(key(ATBlocks.BAMBOO_RAISED_BED.get()))
                .add(key(ATBlocks.BIRCH_RAISED_BED.get()))
                .add(key(ATBlocks.CHERRY_RAISED_BED.get()))
                .add(key(ATBlocks.CRIMSON_RAISED_BED.get()))
                .add(key(ATBlocks.DARK_OAK_RAISED_BED.get()))
                .add(key(ATBlocks.JUNGLE_RAISED_BED.get()))
                .add(key(ATBlocks.MANGROVE_RAISED_BED.get()))
                .add(key(ATBlocks.OAK_RAISED_BED.get()))
                .add(key(ATBlocks.SPRUCE_RAISED_BED.get()))
                .add(key(ATBlocks.WARPED_RAISED_BED.get()))
                .add(key(ATBlocks.PALE_OAK_RAISED_BED.get()))

                .add(key(ATBlocks.ACACIA_CRATE.get()))
                .add(key(ATBlocks.BAMBOO_CRATE.get()))
                .add(key(ATBlocks.BIRCH_CRATE.get()))
                .add(key(ATBlocks.CHERRY_CRATE.get()))
                .add(key(ATBlocks.CRIMSON_CRATE.get()))
                .add(key(ATBlocks.DARK_OAK_CRATE.get()))
                .add(key(ATBlocks.JUNGLE_CRATE.get()))
                .add(key(ATBlocks.MANGROVE_CRATE.get()))
                .add(key(ATBlocks.OAK_CRATE.get()))
                .add(key(ATBlocks.PALE_OAK_CRATE.get()))
                .add(key(ATBlocks.SPRUCE_CRATE.get()))
                .add(key(ATBlocks.WARPED_CRATE.get()));
    }
}
