package com.misterd.agritechtwo.compat.jei;

import net.minecraftforge.fml.ModList;

public final class JeiCompat {

    private JeiCompat() {}

    public static boolean isLoaded() {
        return ModList.get().isLoaded("jei");
    }

    public static void showPlanterRecipes() {
        if (isLoaded()) JeiRecipeOpener.showPlanterRecipes();
    }
}