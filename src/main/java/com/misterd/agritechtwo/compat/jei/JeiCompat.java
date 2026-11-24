package com.misterd.agritechtwo.compat.jei;

import net.neoforged.fml.ModList;

public final class JeiCompat {

    private JeiCompat() {}

    public static boolean isLoaded() {
        return ModList.get().isLoaded("jei");
    }

    public static void showPlanterRecipes() {
        if (isLoaded()) JeiRecipeOpener.showPlanterRecipes();
    }

    public static void showRaisedBedRecipes() {
        if (isLoaded()) JeiRecipeOpener.showRaisedBedRecipes();
    }
}