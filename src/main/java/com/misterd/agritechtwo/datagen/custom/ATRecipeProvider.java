package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.item.ATItems;
import com.misterd.agritechtwo.recipe.CropRecipe;
import com.misterd.agritechtwo.recipe.DropEntry;
import com.misterd.agritechtwo.recipe.DurabilityShapelessRecipe;
import com.misterd.agritechtwo.recipe.TreeRecipe;
import com.misterd.agritechtwo.util.ATTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;

import java.util.List;
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
            return "Agritech Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        RecipeOutput noEvolved = output.withConditions(
                new NotCondition(new ModLoadedCondition("agritechtwo"))
        );

        shaped(RecipeCategory.MISC, ATBlocks.ACACIA_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.ACACIA_PLANKS).define('L', Items.ACACIA_LOG)
                .define('H', Items.HOPPER).define('D', Items.ACACIA_SLAB)
                .unlockedBy("has_acacia_log", has(Items.ACACIA_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.BAMBOO_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.BAMBOO_PLANKS).define('L', Items.BAMBOO_BLOCK)
                .define('H', Items.HOPPER).define('D', Items.BAMBOO_SLAB)
                .unlockedBy("has_bamboo_block", has(Items.BAMBOO_BLOCK))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.BIRCH_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.BIRCH_PLANKS).define('L', Items.BIRCH_LOG)
                .define('H', Items.HOPPER).define('D', Items.BIRCH_SLAB)
                .unlockedBy("has_birch_log", has(Items.BIRCH_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.CHERRY_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.CHERRY_PLANKS).define('L', Items.CHERRY_LOG)
                .define('H', Items.HOPPER).define('D', Items.CHERRY_SLAB)
                .unlockedBy("has_cherry_log", has(Items.CHERRY_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.CRIMSON_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.CRIMSON_PLANKS).define('L', Items.CRIMSON_STEM)
                .define('H', Items.HOPPER).define('D', Items.CRIMSON_SLAB)
                .unlockedBy("has_crimson_stem", has(Items.CRIMSON_STEM))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.DARK_OAK_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.DARK_OAK_PLANKS).define('L', Items.DARK_OAK_LOG)
                .define('H', Items.HOPPER).define('D', Items.DARK_OAK_SLAB)
                .unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.JUNGLE_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.JUNGLE_PLANKS).define('L', Items.JUNGLE_LOG)
                .define('H', Items.HOPPER).define('D', Items.JUNGLE_SLAB)
                .unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.MANGROVE_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.MANGROVE_PLANKS).define('L', Items.MANGROVE_LOG)
                .define('H', Items.HOPPER).define('D', Items.MANGROVE_SLAB)
                .unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.OAK_PLANKS).define('L', Items.OAK_LOG)
                .define('H', Items.HOPPER).define('D', Items.OAK_SLAB)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', ItemTags.PLANKS).define('L', ItemTags.LOGS)
                .define('H', Items.HOPPER).define('D', ItemTags.WOODEN_SLABS)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(noEvolved, "agritechtwo:zzz_oak_planter_from_any_wood");

        shaped(RecipeCategory.MISC, ATBlocks.SPRUCE_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.SPRUCE_PLANKS).define('L', Items.SPRUCE_LOG)
                .define('H', Items.HOPPER).define('D', Items.SPRUCE_SLAB)
                .unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.WARPED_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.WARPED_PLANKS).define('L', Items.WARPED_STEM)
                .define('H', Items.HOPPER).define('D', Items.WARPED_SLAB)
                .unlockedBy("has_warped_stem", has(Items.WARPED_STEM))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.PALE_OAK_PLANTER.get())
                .pattern("P P").pattern("PDP").pattern("LHL")
                .define('P', Items.PALE_OAK_PLANKS).define('L', Items.PALE_OAK_LOG)
                .define('H', Items.HOPPER).define('D', Items.PALE_OAK_SLAB)
                .unlockedBy("has_pale_oak_log", has(Items.PALE_OAK_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATItems.CLOCHE.get(), 4)
                .pattern("III").pattern("IPI").pattern("III")
                .define('P', Tags.Items.GLASS_BLOCKS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.ACACIA_RAISED_BED.get())
                .pattern("PDP").define('P', Items.ACACIA_PLANKS).define('D', Items.ACACIA_SLAB)
                .unlockedBy("has_acacia_planks", has(Items.ACACIA_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.BAMBOO_RAISED_BED.get())
                .pattern("PDP").define('P', Items.BAMBOO_PLANKS).define('D', Items.BAMBOO_SLAB)
                .unlockedBy("has_bamboo_planks", has(Items.BAMBOO_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.BIRCH_RAISED_BED.get())
                .pattern("PDP").define('P', Items.BIRCH_PLANKS).define('D', Items.BIRCH_SLAB)
                .unlockedBy("has_birch_planks", has(Items.BIRCH_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.CHERRY_RAISED_BED.get())
                .pattern("PDP").define('P', Items.CHERRY_PLANKS).define('D', Items.CHERRY_SLAB)
                .unlockedBy("has_cherry_planks", has(Items.CHERRY_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.CRIMSON_RAISED_BED.get())
                .pattern("PDP").define('P', Items.CRIMSON_PLANKS).define('D', Items.CRIMSON_SLAB)
                .unlockedBy("has_crimson_planks", has(Items.CRIMSON_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.DARK_OAK_RAISED_BED.get())
                .pattern("PDP").define('P', Items.DARK_OAK_PLANKS).define('D', Items.DARK_OAK_SLAB)
                .unlockedBy("has_dark_oak_planks", has(Items.DARK_OAK_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.JUNGLE_RAISED_BED.get())
                .pattern("PDP").define('P', Items.JUNGLE_PLANKS).define('D', Items.JUNGLE_SLAB)
                .unlockedBy("has_jungle_planks", has(Items.JUNGLE_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.MANGROVE_RAISED_BED.get())
                .pattern("PDP").define('P', Items.MANGROVE_PLANKS).define('D', Items.MANGROVE_SLAB)
                .unlockedBy("has_mangrove_planks", has(Items.MANGROVE_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_RAISED_BED.get())
                .pattern("PDP").define('P', Items.OAK_PLANKS).define('D', Items.OAK_SLAB)
                .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_RAISED_BED.get())
                .pattern("PDP").define('P', ItemTags.PLANKS).define('D', ItemTags.WOODEN_SLABS)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(output, "agritechtwo:zzz_oak_raised_bed_from_any_wood");

        shaped(RecipeCategory.MISC, ATBlocks.PALE_OAK_RAISED_BED.get())
                .pattern("PDP").define('P', Items.PALE_OAK_PLANKS).define('D', Items.PALE_OAK_SLAB)
                .unlockedBy("has_pale_oak_planks", has(Items.PALE_OAK_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.SPRUCE_RAISED_BED.get())
                .pattern("PDP").define('P', Items.SPRUCE_PLANKS).define('D', Items.SPRUCE_SLAB)
                .unlockedBy("has_spruce_planks", has(Items.SPRUCE_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.WARPED_RAISED_BED.get())
                .pattern("PDP").define('P', Items.WARPED_PLANKS).define('D', Items.WARPED_SLAB)
                .unlockedBy("has_warped_planks", has(Items.WARPED_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.ACACIA_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.ACACIA_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.ACACIA_SLAB)
                .unlockedBy("has_acacia_planks", has(Items.ACACIA_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.BAMBOO_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.BAMBOO_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.BAMBOO_SLAB)
                .unlockedBy("has_bamboo_planks", has(Items.BAMBOO_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.BIRCH_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.BIRCH_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.BIRCH_SLAB)
                .unlockedBy("has_birch_planks", has(Items.BIRCH_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.CHERRY_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.CHERRY_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.CHERRY_SLAB)
                .unlockedBy("has_cherry_planks", has(Items.CHERRY_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.CRIMSON_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.CRIMSON_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.CRIMSON_SLAB)
                .unlockedBy("has_crimson_planks", has(Items.CRIMSON_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.DARK_OAK_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.DARK_OAK_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.DARK_OAK_SLAB)
                .unlockedBy("has_dark_oak_planks", has(Items.DARK_OAK_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.JUNGLE_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.JUNGLE_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.JUNGLE_SLAB)
                .unlockedBy("has_jungle_planks", has(Items.JUNGLE_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.MANGROVE_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.MANGROVE_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.MANGROVE_SLAB)
                .unlockedBy("has_mangrove_planks", has(Items.MANGROVE_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.OAK_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.OAK_SLAB)
                .unlockedBy("has_oak_planks", has(Items.OAK_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', ItemTags.PLANKS).define('C', Tags.Items.CHESTS).define('S', ItemTags.WOODEN_SLABS)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(output, "agritechtwo:zzz_oak_crate_from_any_wood");

        shaped(RecipeCategory.MISC, ATBlocks.PALE_OAK_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.PALE_OAK_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.PALE_OAK_SLAB)
                .unlockedBy("has_pale_oak_planks", has(Items.PALE_OAK_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.SPRUCE_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.SPRUCE_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.SPRUCE_SLAB)
                .unlockedBy("has_spruce_planks", has(Items.SPRUCE_PLANKS)).save(output);

        shaped(RecipeCategory.MISC, ATBlocks.WARPED_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.WARPED_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.WARPED_SLAB)
                .unlockedBy("has_warped_planks", has(Items.WARPED_PLANKS)).save(output);

        saveTillingRecipe("dirt_to_farmland", Items.DIRT, Items.FARMLAND, noEvolved);
        saveTillingRecipe("rooted_dirt_to_farmland", Items.ROOTED_DIRT, Items.FARMLAND, noEvolved);
        saveTillingRecipe("coarse_dirt_to_farmland", Items.COARSE_DIRT, Items.FARMLAND, noEvolved);
        saveTillingRecipe("grass_to_farmland", Items.GRASS_BLOCK, Items.FARMLAND, noEvolved);

        generateCropRecipes();
        generateTreeRecipes();
    }

    private void generateCropRecipes() {
        Ingredient farmlandSoils = tagIngredient(ATTags.Items.FARMLAND_SOILS);
        Ingredient dirtSoils = tagIngredient(ATTags.Items.DIRT_SOILS);
        Ingredient sandSoils = tagIngredient(ATTags.Items.SAND_SOILS);
        Ingredient mossSoils = tagIngredient(ATTags.Items.MOSS_SOILS);
        Ingredient waterSoils = tagIngredient(ATTags.Items.WATER_SOILS);
        Ingredient soulSandSoils = tagIngredient(ATTags.Items.SOUL_SAND_SOILS);
        Ingredient mushroomSoils = tagIngredient(ATTags.Items.MUSHROOM_SOILS);
        Ingredient jungleSoils = tagIngredient(ATTags.Items.JUNGLE_SOILS);
        Ingredient stoneSoils = tagIngredient(ATTags.Items.STONE_SOILS);
        Ingredient endSoils = tagIngredient(ATTags.Items.END_SOILS);

        saveCropRecipe("wheat", Items.WHEAT_SEEDS, List.of(farmlandSoils),
                drop(Items.WHEAT, 1, 1),
                drop(Items.WHEAT_SEEDS, 1, 2, 0.5f));

        saveCropRecipe("beetroot", Items.BEETROOT_SEEDS, List.of(farmlandSoils),
                drop(Items.BEETROOT, 1, 1),
                drop(Items.BEETROOT_SEEDS, 1, 2, 0.5f));

        saveCropRecipe("carrot", Items.CARROT, List.of(farmlandSoils),
                drop(Items.CARROT, 2, 5));

        saveCropRecipe("potato", Items.POTATO, List.of(farmlandSoils),
                drop(Items.POTATO, 2, 5),
                drop(Items.POISONOUS_POTATO, 1, 1, 0.02f));

        saveCropRecipe("melon", Items.MELON_SEEDS, List.of(farmlandSoils),
                drop(Items.MELON_SLICE, 3, 7));

        saveCropRecipe("pumpkin", Items.PUMPKIN_SEEDS, List.of(farmlandSoils),
                drop(Items.PUMPKIN, 1, 1));

        saveCropRecipe("pitcher_pod", Items.PITCHER_POD, List.of(farmlandSoils),
                drop(Items.PITCHER_PLANT, 1, 1));

        saveCropRecipe("torchflower", Items.TORCHFLOWER_SEEDS, List.of(farmlandSoils),
                drop(Items.TORCHFLOWER, 1, 1));

        saveCropRecipe("sweet_berries", Items.SWEET_BERRIES, List.of(dirtSoils),
                drop(Items.SWEET_BERRIES, 2, 4));

        saveCropRecipe("firefly_bush", Items.FIREFLY_BUSH, List.of(dirtSoils),
                drop(Items.FIREFLY_BUSH, 1, 2));

        saveCropRecipe("bamboo", Items.BAMBOO, List.of(dirtSoils),
                drop(Items.BAMBOO, 2, 4));

        saveCropRecipe("sugar_cane", Items.SUGAR_CANE, List.of(dirtSoils, sandSoils),
                drop(Items.SUGAR_CANE, 1, 3));

        saveCropRecipe("cactus", Items.CACTUS, List.of(sandSoils),
                drop(Items.CACTUS, 1, 3),
                drop(Items.CACTUS_FLOWER, 1, 1, 0.25f));

        saveCropRecipe("nether_wart", Items.NETHER_WART, List.of(soulSandSoils),
                drop(Items.NETHER_WART, 1, 3));

        saveCropRecipe("kelp", Items.KELP, List.of(waterSoils),
                drop(Items.KELP, 1, 2));

        saveCropRecipe("lily_pad", Items.LILY_PAD, List.of(waterSoils),
                drop(Items.LILY_PAD, 1, 1));

        saveCropRecipe("sea_pickle", Items.SEA_PICKLE, List.of(waterSoils),
                drop(Items.SEA_PICKLE, 1, 1));

        saveCropRecipe("glow_berries", Items.GLOW_BERRIES, List.of(mossSoils),
                drop(Items.GLOW_BERRIES, 2, 4));

        saveCropRecipe("spore_blossom", Items.SPORE_BLOSSOM, List.of(mossSoils),
                drop(Items.SPORE_BLOSSOM, 1, 1));

        saveCropRecipe("chorus_flower", Items.CHORUS_FLOWER, List.of(endSoils),
                drop(Items.CHORUS_FRUIT, 1, 3),
                drop(Items.CHORUS_FLOWER, 1, 1, 0.02f));

        saveCropRecipe("moss_block", Items.MOSS_BLOCK, List.of(stoneSoils),
                drop(Items.MOSS_BLOCK, 1, 2));

        saveCropRecipe("pale_moss_block", Items.PALE_MOSS_BLOCK, List.of(stoneSoils),
                drop(Items.PALE_MOSS_BLOCK, 1, 2));

        saveCropRecipe("brown_mushroom", Items.BROWN_MUSHROOM, List.of(mushroomSoils),
                drop(Items.BROWN_MUSHROOM, 1, 1));

        saveCropRecipe("red_mushroom", Items.RED_MUSHROOM, List.of(mushroomSoils),
                drop(Items.RED_MUSHROOM, 1, 1));

        saveCropRecipe("cocoa_beans", Items.COCOA_BEANS, List.of(jungleSoils),
                drop(Items.COCOA_BEANS, 1, 3));

        for (Item flower : new Item[]{
                Items.ALLIUM, Items.AZURE_BLUET, Items.BLUE_ORCHID,
                Items.CORNFLOWER, Items.DANDELION, Items.LILY_OF_THE_VALLEY,
                Items.OXEYE_DAISY, Items.POPPY, Items.RED_TULIP,
                Items.ORANGE_TULIP, Items.WHITE_TULIP, Items.PINK_TULIP,
                Items.WITHER_ROSE, Items.LILAC, Items.PEONY,
                Items.ROSE_BUSH, Items.SUNFLOWER,
                Items.CLOSED_EYEBLOSSOM, Items.OPEN_EYEBLOSSOM
        }) {
            String name = BuiltInRegistries.ITEM.getKey(flower).getPath();
            saveCropRecipe(name, flower, List.of(dirtSoils), drop(flower, 1, 1));
        }
    }

    private void generateTreeRecipes() {
        Ingredient treeSoils = tagIngredient(ATTags.Items.TREE_SOILS);
        Ingredient netherSoils = tagIngredient(ATTags.Items.NETHER_SOILS);

        saveTreeRecipe("oak", Items.OAK_SAPLING, List.of(treeSoils),
                drop(Items.OAK_LOG, 2, 6),
                drop(Items.OAK_SAPLING, 1, 2, 0.5f),
                drop(Items.STICK, 1, 2, 0.5f),
                drop(Items.APPLE, 1, 1, 0.4f));

        saveTreeRecipe("birch", Items.BIRCH_SAPLING, List.of(treeSoils),
                drop(Items.BIRCH_LOG, 2, 6),
                drop(Items.BIRCH_SAPLING, 1, 2, 0.5f),
                drop(Items.STICK, 1, 2, 0.5f));

        saveTreeRecipe("spruce", Items.SPRUCE_SAPLING, List.of(treeSoils),
                drop(Items.SPRUCE_LOG, 4, 8),
                drop(Items.SPRUCE_SAPLING, 1, 2, 0.5f),
                drop(Items.STICK, 1, 2, 0.5f));

        saveTreeRecipe("jungle", Items.JUNGLE_SAPLING, List.of(treeSoils),
                drop(Items.JUNGLE_LOG, 2, 6),
                drop(Items.JUNGLE_SAPLING, 1, 2, 0.4f),
                drop(Items.STICK, 1, 2, 0.5f),
                drop(Items.COCOA_BEANS, 1, 2, 0.2f));

        saveTreeRecipe("acacia", Items.ACACIA_SAPLING, List.of(treeSoils),
                drop(Items.ACACIA_LOG, 2, 6),
                drop(Items.ACACIA_SAPLING, 1, 2, 0.5f),
                drop(Items.STICK, 1, 2, 0.5f));

        saveTreeRecipe("dark_oak", Items.DARK_OAK_SAPLING, List.of(treeSoils),
                drop(Items.DARK_OAK_LOG, 4, 8),
                drop(Items.DARK_OAK_SAPLING, 1, 2, 0.5f),
                drop(Items.STICK, 1, 2, 0.5f),
                drop(Items.APPLE, 1, 2, 0.3f));

        saveTreeRecipe("cherry", Items.CHERRY_SAPLING, List.of(treeSoils),
                drop(Items.CHERRY_LOG, 2, 6),
                drop(Items.CHERRY_SAPLING, 1, 2, 0.5f),
                drop(Items.STICK, 1, 2, 0.5f));

        saveTreeRecipe("pale_oak", Items.PALE_OAK_SAPLING, List.of(treeSoils),
                drop(Items.PALE_OAK_LOG, 4, 8),
                drop(Items.PALE_OAK_SAPLING, 1, 2, 0.5f),
                drop(Items.STICK, 1, 2, 0.5f),
                drop(Items.PALE_HANGING_MOSS, 1, 2, 0.3f));

        saveTreeRecipe("mangrove", Items.MANGROVE_PROPAGULE, List.of(treeSoils),
                drop(Items.MANGROVE_LOG, 2, 6),
                drop(Items.MANGROVE_PROPAGULE, 1, 2, 0.5f),
                drop(Items.STICK, 1, 2, 0.5f),
                drop(Items.MANGROVE_ROOTS, 1, 1, 0.3f));

        saveTreeRecipe("azalea", Items.AZALEA, List.of(treeSoils),
                drop(Items.OAK_LOG, 1, 4),
                drop(Items.AZALEA, 1, 1, 0.3f),
                drop(Items.FLOWERING_AZALEA, 1, 1, 0.1f),
                drop(Items.STICK, 1, 2, 0.5f));

        saveTreeRecipe("flowering_azalea", Items.FLOWERING_AZALEA, List.of(treeSoils),
                drop(Items.OAK_LOG, 1, 4),
                drop(Items.AZALEA, 1, 1, 0.2f),
                drop(Items.FLOWERING_AZALEA, 1, 1, 0.2f),
                drop(Items.STICK, 1, 2, 0.5f));

        saveTreeRecipe("crimson_fungus", Items.CRIMSON_FUNGUS, List.of(netherSoils),
                drop(Items.CRIMSON_STEM, 2, 6),
                drop(Items.NETHER_WART_BLOCK, 2, 6, 1f),
                drop(Items.WEEPING_VINES, 1, 2, 0.5f),
                drop(Items.SHROOMLIGHT, 1, 2, 0.5f));

        saveTreeRecipe("warped_fungus", Items.WARPED_FUNGUS, List.of(netherSoils),
                drop(Items.WARPED_STEM, 2, 6),
                drop(Items.WARPED_WART_BLOCK, 2, 6, 1f),
                drop(Items.TWISTING_VINES, 1, 2, 0.5f),
                drop(Items.SHROOMLIGHT, 1, 2, 0.5f));
    }

    private Ingredient tagIngredient(TagKey<Item> tag) {
        HolderSet<Item> holders = registries.lookupOrThrow(Registries.ITEM).getOrThrow(tag);
        return Ingredient.of(holders);
    }

    private DropEntry drop(Item item, int min, int max) {
        return new DropEntry(item, min, max, 1.0f);
    }

    private DropEntry drop(Item item, int min, int max, float chance) {
        return new DropEntry(item, min, max, chance);
    }

    private void saveCropRecipe(String name, Item seed, List<Ingredient> soils, DropEntry... drops) {
        String namespace = BuiltInRegistries.ITEM.getKey(seed).getNamespace();
        CropRecipe recipe = new CropRecipe(Ingredient.of(seed), soils, List.of(drops));
        ResourceKey<Recipe<?>> key = ResourceKey.create(
                Registries.RECIPE,
                Identifier.fromNamespaceAndPath("agritechtwo", "planter/crop/" + namespace + "/" + name)
        );
        output.accept(key, recipe, null);
    }

    private void saveTreeRecipe(String name, Item sapling, List<Ingredient> soils, DropEntry... drops) {
        String namespace = BuiltInRegistries.ITEM.getKey(sapling).getNamespace();
        TreeRecipe recipe = new TreeRecipe(Ingredient.of(sapling), soils, List.of(drops));
        ResourceKey<Recipe<?>> key = ResourceKey.create(
                Registries.RECIPE,
                Identifier.fromNamespaceAndPath("agritechtwo", "planter/tree/" + namespace + "/" + name)
        );
        output.accept(key, recipe, null);
    }

    private void saveTillingRecipe(String name, Item input, Item result, RecipeOutput out) {
        HolderSet<Item> hoeTag = registries
                .lookupOrThrow(Registries.ITEM)
                .getOrThrow(ItemTags.HOES);

        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.of(input));

        DurabilityShapelessRecipe recipe = new DurabilityShapelessRecipe(
                CraftingBookCategory.MISC,
                new ItemStackTemplate(result),
                ingredients,
                Ingredient.of(hoeTag),
                1
        );

        ResourceKey<Recipe<?>> key = ResourceKey.create(
                Registries.RECIPE,
                Identifier.fromNamespaceAndPath("agritechtwo", name)
        );
        out.accept(key, recipe, null);
    }
}