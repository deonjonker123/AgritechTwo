package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.util.ATTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ATItemTagProvider extends ItemTagsProvider {
    public ATItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, AgritechTwo.MODID);
    }

    protected void addTags(HolderLookup.Provider provider) {
        tag(ATTags.Items.FARMLAND_SOILS)
                .add(Blocks.FARMLAND.asItem());

        tag(ATTags.Items.DIRT_SOILS)
                .add(Blocks.GRASS_BLOCK.asItem())
                .add(Blocks.DIRT.asItem())
                .add(Blocks.COARSE_DIRT.asItem())
                .add(Blocks.ROOTED_DIRT.asItem())
                .add(Blocks.MYCELIUM.asItem())
                .add(Blocks.PODZOL.asItem())
                .add(Blocks.MUD.asItem())
                .add(Blocks.MUDDY_MANGROVE_ROOTS.asItem());

        tag(ATTags.Items.TREE_SOILS)
                .add(Blocks.FARMLAND.asItem())
                .add(Blocks.GRASS_BLOCK.asItem())
                .add(Blocks.DIRT.asItem())
                .add(Blocks.COARSE_DIRT.asItem())
                .add(Blocks.ROOTED_DIRT.asItem())
                .add(Blocks.MYCELIUM.asItem())
                .add(Blocks.PODZOL.asItem())
                .add(Blocks.MUD.asItem())
                .add(Blocks.MUDDY_MANGROVE_ROOTS.asItem())
                .add(Blocks.MOSS_BLOCK.asItem())
                .add(Blocks.PALE_MOSS_BLOCK.asItem());

        tag(ATTags.Items.SAND_SOILS)
                .add(Blocks.SAND.asItem())
                .add(Blocks.RED_SAND.asItem());

        tag(ATTags.Items.SOUL_SAND_SOILS)
                .add(Blocks.SOUL_SAND.asItem())
                .add(Blocks.SOUL_SOIL.asItem());

        tag(ATTags.Items.MOSS_SOILS)
                .add(Blocks.MOSS_BLOCK.asItem())
                .add(Blocks.PALE_MOSS_BLOCK.asItem());

        tag(ATTags.Items.WATER_SOILS)
                .add(Items.WATER_BUCKET);

        tag(ATTags.Items.MUSHROOM_SOILS)
                .add(Blocks.MYCELIUM.asItem())
                .add(Blocks.PODZOL.asItem());

        tag(ATTags.Items.NETHER_SOILS)
                .add(Blocks.CRIMSON_NYLIUM.asItem())
                .add(Blocks.WARPED_NYLIUM.asItem());

        tag(ATTags.Items.JUNGLE_SOILS)
                .add(Blocks.JUNGLE_LOG.asItem())
                .add(Blocks.JUNGLE_WOOD.asItem())
                .add(Blocks.STRIPPED_JUNGLE_LOG.asItem())
                .add(Blocks.STRIPPED_JUNGLE_WOOD.asItem());

        tag(ATTags.Items.STONE_SOILS)
                .add(Blocks.STONE.asItem())
                .add(Blocks.END_STONE.asItem());

        tag(ATTags.Items.END_SOILS)
                .add(Blocks.END_STONE.asItem())
                .add(Blocks.END_STONE_BRICKS.asItem());
    }
}
