package com.misterd.agritechtwo.compat.jei;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.recipe.ATRecipeTypes;
import com.misterd.agritechtwo.recipe.CropRecipe;
import com.misterd.agritechtwo.recipe.TreeRecipe;
import com.mojang.logging.LogUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.fml.ModList;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class ATJeiPlugin implements IModPlugin {
    private static final Identifier PLUGIN_ID = Identifier.fromNamespaceAndPath("agritechtwo", "jei_plugin");
    private static IJeiRuntime jeiRuntime;

    private static boolean evolvedLoaded() {
        return ModList.get().isLoaded("agritechevolved");
    }

    @Override
    public Identifier getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (!evolvedLoaded()) {
            registration.addRecipeCategories(new PlanterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        }
        registration.addRecipeCategories(new RaisedBedRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (!evolvedLoaded()) {
            registration.addRecipes(PlanterRecipeCategory.PLANTER_RECIPE_TYPE, generatePlanterRecipes());
        }
        registration.addRecipes(RaisedBedRecipeCategory.RAISED_BED_RECIPE_TYPE, generateRaisedBedRecipes());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        if (!evolvedLoaded()) {
            registration.addCraftingStation(PlanterRecipeCategory.PLANTER_RECIPE_TYPE, ATBlocks.OAK_PLANTER);
        }
        registration.addCraftingStation(RaisedBedRecipeCategory.RAISED_BED_RECIPE_TYPE, ATBlocks.OAK_RAISED_BED);
    }

    private RecipeManager getRecipeManager() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return null;
        if (mc.level.recipeAccess() instanceof RecipeManager rm) return rm;
        return null;
    }

    private List<PlanterRecipe> generatePlanterRecipes() {
        List<PlanterRecipe> recipes = new ArrayList<>();
        recipes.addAll(generateCropPlanterRecipes());
        recipes.addAll(generateTreePlanterRecipes());
        LogUtils.getLogger().info("Generated {} total planter recipes for JEI", recipes.size());
        return recipes;
    }

    private List<PlanterRecipe> generateCropPlanterRecipes() {
        List<PlanterRecipe> recipes = new ArrayList<>();
        RecipeManager rm = getRecipeManager();
        if (rm == null) {
            LogUtils.getLogger().warn("RecipeManager unavailable during JEI crop recipe generation");
            return recipes;
        }
        for (RecipeHolder<?> holder : rm.getRecipes()) {
            if (holder.value().getType() != ATRecipeTypes.CROP_TYPE.get()) continue;
            try {
                recipes.add(PlanterRecipe.fromCrop((CropRecipe) holder.value()));
            } catch (Exception e) {
                LogUtils.getLogger().error("Error creating JEI planter crop recipe for {}: {}", holder.id(), e.getMessage());
            }
        }
        return recipes;
    }

    private List<PlanterRecipe> generateTreePlanterRecipes() {
        List<PlanterRecipe> recipes = new ArrayList<>();
        RecipeManager rm = getRecipeManager();
        if (rm == null) {
            LogUtils.getLogger().warn("RecipeManager unavailable during JEI tree recipe generation");
            return recipes;
        }
        for (RecipeHolder<?> holder : rm.getRecipes()) {
            if (holder.value().getType() != ATRecipeTypes.TREE_TYPE.get()) continue;
            try {
                recipes.add(PlanterRecipe.fromTree((TreeRecipe) holder.value()));
            } catch (Exception e) {
                LogUtils.getLogger().error("Error creating JEI planter tree recipe for {}: {}", holder.id(), e.getMessage());
            }
        }
        return recipes;
    }

    private List<RaisedBedRecipe> generateRaisedBedRecipes() {
        List<RaisedBedRecipe> recipes = new ArrayList<>();
        recipes.addAll(generateCropRaisedBedRecipes());
        recipes.addAll(generateTreeRaisedBedRecipes());
        LogUtils.getLogger().info("Generated {} total raised bed recipes for JEI", recipes.size());
        return recipes;
    }

    private List<RaisedBedRecipe> generateCropRaisedBedRecipes() {
        List<RaisedBedRecipe> recipes = new ArrayList<>();
        RecipeManager rm = getRecipeManager();
        if (rm == null) {
            LogUtils.getLogger().warn("RecipeManager unavailable during JEI raised bed crop recipe generation");
            return recipes;
        }
        for (RecipeHolder<?> holder : rm.getRecipes()) {
            if (holder.value().getType() != ATRecipeTypes.CROP_TYPE.get()) continue;
            try {
                recipes.add(RaisedBedRecipe.fromCrop((CropRecipe) holder.value()));
            } catch (Exception e) {
                LogUtils.getLogger().error("Error creating JEI raised bed crop recipe for {}: {}", holder.id(), e.getMessage());
            }
        }
        return recipes;
    }

    private List<RaisedBedRecipe> generateTreeRaisedBedRecipes() {
        List<RaisedBedRecipe> recipes = new ArrayList<>();
        RecipeManager rm = getRecipeManager();
        if (rm == null) {
            LogUtils.getLogger().warn("RecipeManager unavailable during JEI raised bed tree recipe generation");
            return recipes;
        }
        for (RecipeHolder<?> holder : rm.getRecipes()) {
            if (holder.value().getType() != ATRecipeTypes.TREE_TYPE.get()) continue;
            try {
                recipes.add(RaisedBedRecipe.fromTree((TreeRecipe) holder.value()));
            } catch (Exception e) {
                LogUtils.getLogger().error("Error creating JEI raised bed tree recipe for {}: {}", holder.id(), e.getMessage());
            }
        }
        return recipes;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        jeiRuntime = runtime;
    }

    public static IJeiRuntime getJeiRuntime() {
        return jeiRuntime;
    }
}