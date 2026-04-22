package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ATBlockTagProvider extends BlockTagsProvider {
    public ATBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, AgritechTwo.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ATBlocks.ACACIA_PLANTER.get())
                .add(ATBlocks.BAMBOO_PLANTER.get())
                .add(ATBlocks.BIRCH_PLANTER.get())
                .add(ATBlocks.CHERRY_PLANTER.get())
                .add(ATBlocks.CRIMSON_PLANTER.get())
                .add(ATBlocks.DARK_OAK_PLANTER.get())
                .add(ATBlocks.JUNGLE_PLANTER.get())
                .add(ATBlocks.MANGROVE_PLANTER.get())
                .add(ATBlocks.OAK_PLANTER.get())
                .add(ATBlocks.SPRUCE_PLANTER.get())
                .add(ATBlocks.WARPED_PLANTER.get())
                .add(ATBlocks.PALE_OAK_PLANTER.get());
    }
}
