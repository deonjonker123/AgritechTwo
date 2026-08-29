package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.item.ATItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ATRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ATRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.ACACIA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.ACACIA_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_acaia_log", has(Items.ACACIA_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.BAMBOO_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.BAMBOO_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_bamboo_block", has(Items.BAMBOO_BLOCK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.BIRCH_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.BIRCH_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_birch_log", has(Items.BIRCH_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.CHERRY_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.CHERRY_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_cherry_log", has(Items.CHERRY_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.CRIMSON_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.CRIMSON_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_crimson_stem", has(Items.CRIMSON_STEM))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.DARK_OAK_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DARK_OAK_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.JUNGLE_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.JUNGLE_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.MANGROVE_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.MANGROVE_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.OAK_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.OAK_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.OAK_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', ItemTags.PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(recipeOutput, "agritechtwo:basic_planter_from_any_wood");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.SPRUCE_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.SPRUCE_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.WARPED_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.WARPED_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_warped_stem", has(Items.WARPED_STEM))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATItems.CLOCHE.get(), 4)
                .pattern("III")
                .pattern("IPI")
                .pattern("III")
                .define('P', Tags.Items.GLASS_BLOCKS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.BLACK_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.BLACK_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.BLUE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.BLUE_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.BROWN_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.BROWN_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.CYAN_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.CYAN_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.GRAY_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.GRAY_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.GREEN_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.GREEN_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.LIGHT_BLUE_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.LIGHT_GRAY_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.LIME_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.LIME_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.MAGENTA_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.ORANGE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.ORANGE_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.PINK_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.PINK_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.PURPLE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.PURPLE_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.RED_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.RED_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.WHITE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.WHITE_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATBlocks.YELLOW_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.YELLOW_TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.BLACK_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:black_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.BLUE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:blue_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.BROWN_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.BROWN_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:brown_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.CYAN_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.CYAN_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:cyan_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.GRAY_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.GRAY_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:gray_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.GREEN_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:green_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.LIGHT_BLUE_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:light_blue_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.LIGHT_GRAY_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:light_gray_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.LIME_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.LIME_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:lime_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.MAGENTA_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:magenta_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.ORANGE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.ORANGE_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:orange_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.PINK_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.PINK_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:pink_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.PURPLE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.PURPLE_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:purple_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.RED_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.RED_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:red_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.WHITE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:white_terracotta_planter_from_dye");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ATBlocks.YELLOW_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.YELLOW_DYE)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:yellow_terracotta_planter_from_dye");
    }
}
