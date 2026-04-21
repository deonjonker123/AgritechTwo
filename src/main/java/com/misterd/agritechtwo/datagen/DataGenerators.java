package com.misterd.agritechtwo.datagen;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.datagen.custom.ATBlockTagProvider;
import com.misterd.agritechtwo.datagen.custom.ATLootTableProvider;
import com.misterd.agritechtwo.datagen.custom.ATRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = AgritechTwo.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(ATLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(true, new ATRecipeProvider.Runner(packOutput, lookupProvider));

        BlockTagsProvider blockTagsProvider = new ATBlockTagProvider(packOutput, lookupProvider);
        generator.addProvider(true, blockTagsProvider);
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(ATLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(true, new ATRecipeProvider.Runner(packOutput, lookupProvider));

        BlockTagsProvider blockTagsProvider = new ATBlockTagProvider(packOutput, lookupProvider);
        generator.addProvider(true, blockTagsProvider);
    }
}
