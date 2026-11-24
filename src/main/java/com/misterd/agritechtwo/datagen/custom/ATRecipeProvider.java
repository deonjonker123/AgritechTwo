package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.item.ATItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ATRecipeProvider extends RecipeProvider {
    public ATRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {
        woodPlanter(recipeOutput, ATBlocks.ACACIA_PLANTER.get(), Items.ACACIA_PLANKS, "has_acaia_log", Items.ACACIA_LOG);
        woodPlanter(recipeOutput, ATBlocks.BAMBOO_PLANTER.get(), Items.BAMBOO_PLANKS, "has_bamboo_block", Items.BAMBOO_BLOCK);
        woodPlanter(recipeOutput, ATBlocks.BIRCH_PLANTER.get(), Items.BIRCH_PLANKS, "has_birch_log", Items.BIRCH_LOG);
        woodPlanter(recipeOutput, ATBlocks.CHERRY_PLANTER.get(), Items.CHERRY_PLANKS, "has_cherry_log", Items.CHERRY_LOG);
        woodPlanter(recipeOutput, ATBlocks.CRIMSON_PLANTER.get(), Items.CRIMSON_PLANKS, "has_crimson_stem", Items.CRIMSON_STEM);
        woodPlanter(recipeOutput, ATBlocks.DARK_OAK_PLANTER.get(), Items.DARK_OAK_PLANKS, "has_dark_oak_log", Items.DARK_OAK_LOG);
        woodPlanter(recipeOutput, ATBlocks.JUNGLE_PLANTER.get(), Items.JUNGLE_PLANKS, "has_jungle_log", Items.JUNGLE_LOG);
        woodPlanter(recipeOutput, ATBlocks.MANGROVE_PLANTER.get(), Items.MANGROVE_PLANKS, "has_mangrove_log", Items.MANGROVE_LOG);
        woodPlanter(recipeOutput, ATBlocks.OAK_PLANTER.get(), Items.OAK_PLANKS, "has_oak_log", Items.OAK_LOG);
        woodPlanter(recipeOutput, ATBlocks.SPRUCE_PLANTER.get(), Items.SPRUCE_PLANKS, "has_spruce_log", Items.SPRUCE_LOG);
        woodPlanter(recipeOutput, ATBlocks.WARPED_PLANTER.get(), Items.WARPED_PLANKS, "has_warped_stem", Items.WARPED_STEM);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ATItems.CLOCHE.get(), 4)
                .pattern("III")
                .pattern("IPI")
                .pattern("III")
                .define('P', Tags.Items.GLASS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        terracottaPlanter(recipeOutput, ATBlocks.TERRACOTTA_PLANTER.get(), Items.TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.BLACK_TERRACOTTA_PLANTER.get(), Items.BLACK_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.BLUE_TERRACOTTA_PLANTER.get(), Items.BLUE_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.BROWN_TERRACOTTA_PLANTER.get(), Items.BROWN_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.CYAN_TERRACOTTA_PLANTER.get(), Items.CYAN_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.GRAY_TERRACOTTA_PLANTER.get(), Items.GRAY_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.GREEN_TERRACOTTA_PLANTER.get(), Items.GREEN_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get(), Items.LIGHT_BLUE_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get(), Items.LIGHT_GRAY_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.LIME_TERRACOTTA_PLANTER.get(), Items.LIME_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get(), Items.MAGENTA_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.ORANGE_TERRACOTTA_PLANTER.get(), Items.ORANGE_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.PINK_TERRACOTTA_PLANTER.get(), Items.PINK_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.PURPLE_TERRACOTTA_PLANTER.get(), Items.PURPLE_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.RED_TERRACOTTA_PLANTER.get(), Items.RED_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.WHITE_TERRACOTTA_PLANTER.get(), Items.WHITE_TERRACOTTA);
        terracottaPlanter(recipeOutput, ATBlocks.YELLOW_TERRACOTTA_PLANTER.get(), Items.YELLOW_TERRACOTTA);

        dyedPlanter(recipeOutput, ATBlocks.BLACK_TERRACOTTA_PLANTER.get(), Items.BLACK_DYE, "black");
        dyedPlanter(recipeOutput, ATBlocks.BLUE_TERRACOTTA_PLANTER.get(), Items.BLUE_DYE, "blue");
        dyedPlanter(recipeOutput, ATBlocks.BROWN_TERRACOTTA_PLANTER.get(), Items.BROWN_DYE, "brown");
        dyedPlanter(recipeOutput, ATBlocks.CYAN_TERRACOTTA_PLANTER.get(), Items.CYAN_DYE, "cyan");
        dyedPlanter(recipeOutput, ATBlocks.GRAY_TERRACOTTA_PLANTER.get(), Items.GRAY_DYE, "gray");
        dyedPlanter(recipeOutput, ATBlocks.GREEN_TERRACOTTA_PLANTER.get(), Items.GREEN_DYE, "green");
        dyedPlanter(recipeOutput, ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get(), Items.LIGHT_BLUE_DYE, "light_blue");
        dyedPlanter(recipeOutput, ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get(), Items.LIGHT_GRAY_DYE, "light_gray");
        dyedPlanter(recipeOutput, ATBlocks.LIME_TERRACOTTA_PLANTER.get(), Items.LIME_DYE, "lime");
        dyedPlanter(recipeOutput, ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get(), Items.MAGENTA_DYE, "magenta");
        dyedPlanter(recipeOutput, ATBlocks.ORANGE_TERRACOTTA_PLANTER.get(), Items.ORANGE_DYE, "orange");
        dyedPlanter(recipeOutput, ATBlocks.PINK_TERRACOTTA_PLANTER.get(), Items.PINK_DYE, "pink");
        dyedPlanter(recipeOutput, ATBlocks.PURPLE_TERRACOTTA_PLANTER.get(), Items.PURPLE_DYE, "purple");
        dyedPlanter(recipeOutput, ATBlocks.RED_TERRACOTTA_PLANTER.get(), Items.RED_DYE, "red");
        dyedPlanter(recipeOutput, ATBlocks.WHITE_TERRACOTTA_PLANTER.get(), Items.WHITE_DYE, "white");
        dyedPlanter(recipeOutput, ATBlocks.YELLOW_TERRACOTTA_PLANTER.get(), Items.YELLOW_DYE, "yellow");
    }

    private void woodPlanter(Consumer<FinishedRecipe> recipeOutput, ItemLike planter, Item planks, String criterion, ItemLike unlock) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, planter)
                .pattern("PHP")
                .pattern("PPP")
                .define('P', planks)
                .define('H', Items.HOPPER)
                .unlockedBy(criterion, has(unlock))
                .save(recipeOutput);
    }

    private void terracottaPlanter(Consumer<FinishedRecipe> recipeOutput, ItemLike planter, Item terracotta) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, planter)
                .pattern("PHP")
                .pattern("PPP")
                .define('P', terracotta)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(recipeOutput);
    }

    private void dyedPlanter(Consumer<FinishedRecipe> recipeOutput, ItemLike planter, Item dye, String color) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, planter)
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(dye)
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(recipeOutput, "agritechtwo:" + color + "_terracotta_planter_from_dye");
    }
}