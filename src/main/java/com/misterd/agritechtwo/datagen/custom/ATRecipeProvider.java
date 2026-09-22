package com.misterd.agritechtwo.datagen.custom;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.item.ATItems;
import com.misterd.agritechtwo.recipe.CropRecipe;
import com.misterd.agritechtwo.recipe.DropEntry;
import com.misterd.agritechtwo.recipe.DurabilityShapelessRecipe;
import com.misterd.agritechtwo.recipe.TreeRecipe;
import com.misterd.agritechtwo.util.ATTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.MultiRegistryBootstrap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
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
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ATRecipeProvider extends RecipeProvider {
    private final BootstrapContext<Recipe<?>> recipeContext;

    public ATRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
        this.recipeContext = recipeOutput;
    }

    public static MultiRegistryBootstrap create() {
        return new MultiRegistryBootstrap() {
            @Override
            public Set<ResourceKey<? extends Registry<?>>> requestedRegistries() {
                return Set.of(Registries.RECIPE, Registries.ADVANCEMENT);
            }

            @Override
            public void run(MultiRegistryBootstrap.BootstrapGetter registries) {
                new ATRecipeProvider(
                        registries.get(Registries.RECIPE),
                        registries.get(Registries.ADVANCEMENT)
                ).buildRecipes();
            }
        };
    }

    @Override
    protected void buildRecipes() {
        RecipeOutput noEvolved = output.withConditions(
                new NotCondition(new ModLoadedCondition("agritechtwo"))
        );

        shaped(RecipeCategory.MISC, ATBlocks.ACACIA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.ACACIA_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_acacia_log", has(Items.ACACIA_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.BAMBOO_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.BAMBOO_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_bamboo_block", has(Items.BAMBOO_BLOCK))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.BIRCH_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.BIRCH_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_birch_log", has(Items.BIRCH_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.CHERRY_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.CHERRY_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_cherry_log", has(Items.CHERRY_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.CRIMSON_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.CRIMSON_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_crimson_stem", has(Items.CRIMSON_STEM))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.DARK_OAK_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DARK_OAK_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.JUNGLE_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.JUNGLE_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.MANGROVE_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.MANGROVE_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.OAK_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.OAK_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', ItemTags.PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(noEvolved, "agritechtwo:zzz_basic_planter_from_any_wood");

        shaped(RecipeCategory.MISC, ATBlocks.SPRUCE_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.SPRUCE_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.WARPED_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.WARPED_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_warped_stem", has(Items.WARPED_STEM))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.PALE_OAK_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.PALE_OAK_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_pale_oak_log", has(Items.PALE_OAK_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.POPLAR_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.POPLAR_PLANKS)
                .define('H', Items.HOPPER)
                .unlockedBy("has_poplar_log", has(Items.POPLAR_LOG))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.TERRACOTTA)
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.BLACK_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.black())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.BLUE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.blue())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.BROWN_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.brown())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.CYAN_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.cyan())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.GRAY_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.gray())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.GREEN_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.green())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.lightBlue())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.lightGray())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.LIME_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.lime())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.magenta())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.ORANGE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.orange())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.PINK_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.pink())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.PURPLE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.purple())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.RED_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.red())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.WHITE_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.white())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shaped(RecipeCategory.MISC, ATBlocks.YELLOW_TERRACOTTA_PLANTER.get())
                .pattern("PHP")
                .pattern("PPP")
                .define('P', Items.DYED_TERRACOTTA.yellow())
                .define('H', Items.HOPPER)
                .unlockedBy("has_hopper", has(Items.HOPPER))
                .save(noEvolved);

        shapeless(RecipeCategory.MISC, ATBlocks.BLACK_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.black())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:black_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.BLUE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.blue())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:blue_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.BROWN_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.brown())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:brown_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.CYAN_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.cyan())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:cyan_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.GRAY_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.gray())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:gray_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.GREEN_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.green())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:green_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.lightBlue())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:light_blue_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.lightGray())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:light_gray_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.LIME_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.lime())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:lime_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.magenta())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:magenta_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.ORANGE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.orange())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:orange_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.PINK_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.pink())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:pink_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.PURPLE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.purple())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:purple_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.RED_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.red())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:red_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.WHITE_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.white())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:white_terracotta_planter_from_dye");

        shapeless(RecipeCategory.MISC, ATBlocks.YELLOW_TERRACOTTA_PLANTER.get())
                .requires(ATBlocks.TERRACOTTA_PLANTER.get())
                .requires(Items.DYE.yellow())
                .unlockedBy("has_terracotta_planter", has(ATBlocks.TERRACOTTA_PLANTER.get()))
                .save(noEvolved, "agritechtwo:yellow_terracotta_planter_from_dye");

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

        shaped(RecipeCategory.MISC, ATBlocks.POPLAR_RAISED_BED.get())
                .pattern("PDP").define('P', Items.POPLAR_PLANKS).define('D', Items.POPLAR_SLAB)
                .unlockedBy("has_poplar_planks", has(Items.POPLAR_PLANKS)).save(output);

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

        shaped(RecipeCategory.MISC, ATBlocks.POPLAR_CRATE.get())
                .pattern("P P").pattern("PCP").pattern("PSP")
                .define('P', Items.POPLAR_PLANKS).define('C', Tags.Items.CHESTS).define('S', Items.POPLAR_SLAB)
                .unlockedBy("has_poplar_planks", has(Items.POPLAR_PLANKS)).save(output);

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
    }

    private void saveTillingRecipe(String name, Item input, Item result, RecipeOutput out) {
        HolderSet<Item> hoeTag = recipeContext
                .lookup(Registries.ITEM)
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