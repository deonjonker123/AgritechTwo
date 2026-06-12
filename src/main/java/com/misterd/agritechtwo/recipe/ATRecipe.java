package com.misterd.agritechtwo.recipe;

import com.misterd.agritechtwo.AgritechTwo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ATRecipe {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, AgritechTwo.MODID);

    public static final Supplier<RecipeSerializer<DurabilityShapelessRecipe>> DURABILITY_SHAPELESS_SERIALIZER =
            RECIPE_SERIALIZERS.register("durability_shapeless", () -> DurabilityShapelessRecipeSerializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        ATRecipeTypes.register(eventBus);
    }
}