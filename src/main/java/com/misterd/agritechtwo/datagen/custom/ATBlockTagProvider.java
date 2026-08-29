package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ATBlockTagProvider extends BlockTagsProvider {
    public ATBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AgritechTwo.MODID, existingFileHelper);
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
                .add(ATBlocks.WARPED_PLANTER.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ATBlocks.TERRACOTTA_PLANTER.get())
                .add(ATBlocks.BLACK_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.BLUE_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.BROWN_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.CYAN_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.GRAY_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.GREEN_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.LIME_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.ORANGE_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.PINK_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.PURPLE_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.RED_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.WHITE_TERRACOTTA_PLANTER.get())
                .add(ATBlocks.YELLOW_TERRACOTTA_PLANTER.get());
    }
}
