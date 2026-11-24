package com.misterd.agritechtwo.compat.jei;

import mezz.jei.api.runtime.IJeiRuntime;

import java.util.List;

final class JeiRecipeOpener {

    private JeiRecipeOpener() {}

    static void showPlanterRecipes() {
        IJeiRuntime runtime = ATJeiPlugin.getJeiRuntime();
        if (runtime != null) runtime.getRecipesGui().showTypes(List.of(PlanterRecipeCategory.PLANTER_RECIPE_TYPE));
    }

    static void showRaisedBedRecipes() {
        IJeiRuntime runtime = ATJeiPlugin.getJeiRuntime();
        if (runtime != null) runtime.getRecipesGui().showTypes(List.of(RaisedBedRecipeCategory.RAISED_BED_RECIPE_TYPE));
    }
}