package com.misterd.agritechtwo.datamap;

import com.misterd.agritechtwo.AgritechTwo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class ATDataMaps {
    public static final DataMapType<Item, FertilizerData> FERTILIZERS = DataMapType
            .builder(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "fertilizers"), Registries.ITEM, FertilizerData.CODEC)
            .synced(FertilizerData.CODEC, false)
            .build();

    public static final DataMapType<Item, SoilModifierData> SOIL_MODIFIERS = DataMapType
            .builder(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "soil_modifiers"), Registries.ITEM, SoilModifierData.CODEC)
            .synced(SoilModifierData.CODEC, false)
            .build();

    public static void register(IEventBus eventBus) {
        eventBus.addListener(ATDataMaps::onRegisterDataMaps);
    }

    private static void onRegisterDataMaps(RegisterDataMapTypesEvent event) {
        event.register(FERTILIZERS);
        event.register(SOIL_MODIFIERS);
    }
}