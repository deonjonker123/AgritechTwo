package com.misterd.agritechtwo.datagen;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.datagen.custom.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = AgritechTwo.MODID)
public class DataGenerators {

    private static RegistrySetBuilder reloadableRegistries() {
        return new RegistrySetBuilder()
                .add(Registries.LOOT_TABLE, new LootTableProvider(
                        Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(ATLootTableProvider::new, LootContextParamSets.BLOCK))
                ));
        // .add(Registries.RECIPE, ...) — pending, see below
    }

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> worldLookup = event.getWorldLookupProvider();

        event.createReloadableRegistryObjects(reloadableRegistries());

        BlockTagsProvider blockTagsProvider = new ATBlockTagProvider(packOutput, worldLookup);
        generator.addProvider(true, blockTagsProvider);

        generator.addProvider(true, new ATModelProvider(packOutput));
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> worldLookup = event.getWorldLookupProvider();

        event.createReloadableRegistryObjects(reloadableRegistries());

        BlockTagsProvider blockTagsProvider = new ATBlockTagProvider(packOutput, worldLookup);
        generator.addProvider(true, blockTagsProvider);
    }
}