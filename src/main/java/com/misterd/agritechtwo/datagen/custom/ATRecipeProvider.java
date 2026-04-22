package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.item.ATItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class ATRecipeProvider extends RecipeProvider {
    public ATRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ATRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.MISC, ATBlocks.ACACIA_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.ACACIA_PLANKS)
                .define('L', Items.ACACIA_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.ACACIA_SLAB)
                .unlockedBy("has_acaia_log", has(Items.ACACIA_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.BAMBOO_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.BAMBOO_PLANKS)
                .define('L', Items.BAMBOO_BLOCK)
                .define('H', Items.HOPPER)
                .define('D', Items.BAMBOO_SLAB)
                .unlockedBy("has_bamboo_block", has(Items.BAMBOO_BLOCK))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.BIRCH_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.BIRCH_PLANKS)
                .define('L', Items.BIRCH_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.BIRCH_SLAB)
                .unlockedBy("has_birch_log", has(Items.BIRCH_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.CHERRY_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.CHERRY_PLANKS)
                .define('L', Items.CHERRY_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.CHERRY_SLAB)
                .unlockedBy("has_cherry_log", has(Items.CHERRY_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.CRIMSON_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.CRIMSON_PLANKS)
                .define('L', Items.CRIMSON_STEM)
                .define('H', Items.HOPPER)
                .define('D', Items.CRIMSON_SLAB)
                .unlockedBy("has_crimson_stem", has(Items.CRIMSON_STEM))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.DARK_OAK_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.DARK_OAK_PLANKS)
                .define('L', Items.DARK_OAK_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.DARK_OAK_SLAB)
                .unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.JUNGLE_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.JUNGLE_PLANKS)
                .define('L', Items.JUNGLE_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.JUNGLE_SLAB)
                .unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.MANGROVE_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.MANGROVE_PLANKS)
                .define('L', Items.MANGROVE_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.MANGROVE_SLAB)
                .unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.OAK_PLANKS)
                .define('L', Items.OAK_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.OAK_SLAB)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', ItemTags.PLANKS)
                .define('L', ItemTags.LOGS)
                .define('H', Items.HOPPER)
                .define('D', ItemTags.WOODEN_SLABS)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(output, "agritechtwo:oak_planter_from_any_wood");

        shaped(RecipeCategory.MISC, ATBlocks.SPRUCE_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.SPRUCE_PLANKS)
                .define('L', Items.SPRUCE_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.SPRUCE_SLAB)
                .unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.WARPED_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.WARPED_PLANKS)
                .define('L', Items.WARPED_STEM)
                .define('H', Items.HOPPER)
                .define('D', Items.WARPED_SLAB)
                .unlockedBy("has_warped_stem", has(Items.WARPED_STEM))
                .save(output);

        shaped(RecipeCategory.MISC, ATBlocks.PALE_OAK_PLANTER.get())
                .pattern("P P")
                .pattern("PDP")
                .pattern("LHL")
                .define('P', Items.PALE_OAK_PLANKS)
                .define('L', Items.PALE_OAK_LOG)
                .define('H', Items.HOPPER)
                .define('D', Items.PALE_OAK_SLAB)
                .unlockedBy("has_pale_oak_log", has(Items.PALE_OAK_LOG))
                .save(output);

        shaped(RecipeCategory.MISC, ATItems.CLOCHE.get(), 4)
                .pattern("III")
                .pattern("IPI")
                .pattern("III")
                .define('P', Tags.Items.GLASS_BLOCKS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(output);
    }
}
