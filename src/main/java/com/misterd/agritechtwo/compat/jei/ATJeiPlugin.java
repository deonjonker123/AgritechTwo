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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class ATJeiPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath("agritechtwo", "jei_plugin");
    private static IJeiRuntime jeiRuntime;

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new PlanterRecipeCategory(registration.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(PlanterRecipeCategory.PLANTER_RECIPE_TYPE, generatePlanterRecipes());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ATBlocks.OAK_PLANTER.get()), PlanterRecipeCategory.PLANTER_RECIPE_TYPE);
    }

    private List<PlanterRecipe> generatePlanterRecipes() {
        List<PlanterRecipe> recipes = new ArrayList<>();
        RecipeManager rm = Minecraft.getInstance().level.getRecipeManager();

        for (RecipeHolder<CropRecipe> holder : rm.getAllRecipesFor(ATRecipeTypes.CROP_TYPE.get())) {
            try {
                PlanterRecipe recipe = PlanterRecipe.fromCropRecipe(holder.value());
                if (!recipe.getOutputs().isEmpty()) recipes.add(recipe);
            } catch (Exception e) {
                LogUtils.getLogger().error("Error creating JEI recipe from CropRecipe: {}", e.getMessage(), e);
            }
        }

        for (RecipeHolder<TreeRecipe> holder : rm.getAllRecipesFor(ATRecipeTypes.TREE_TYPE.get())) {
            try {
                PlanterRecipe recipe = PlanterRecipe.fromTreeRecipe(holder.value());
                if (!recipe.getOutputs().isEmpty()) recipes.add(recipe);
            } catch (Exception e) {
                LogUtils.getLogger().error("Error creating JEI recipe from TreeRecipe: {}", e.getMessage(), e);
            }
        }

        LogUtils.getLogger().info("Generated {} total planter recipes for JEI", recipes.size());
        return recipes;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        ATJeiPlugin.jeiRuntime = runtime;
    }

    public static IJeiRuntime getJeiRuntime() {
        return jeiRuntime;
    }
}