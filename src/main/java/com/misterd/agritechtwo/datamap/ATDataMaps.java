package com.misterd.agritechtwo.datamap;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.network.ATNetwork;
import com.misterd.agritechtwo.network.SyncDataMapsPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.Map;

public final class ATDataMaps {
    public static final ResourceLocation FERTILIZERS_ID = ResourceLocation.fromNamespaceAndPath(AgritechTwo.MODID, "fertilizers");
    public static final ResourceLocation SOIL_MODIFIERS_ID = ResourceLocation.fromNamespaceAndPath(AgritechTwo.MODID, "soil_modifiers");

    private static volatile Map<Item, FertilizerData> fertilizers = Map.of();
    private static volatile Map<Item, SoilModifierData> soilModifiers = Map.of();

    private ATDataMaps() {}

    @Nullable
    public static FertilizerData getFertilizer(Item item) {
        return fertilizers.get(item);
    }

    @Nullable
    public static SoilModifierData getSoilModifier(Item item) {
        return soilModifiers.get(item);
    }

    public static Map<Item, FertilizerData> getFertilizers() {
        return fertilizers;
    }

    public static Map<Item, SoilModifierData> getSoilModifiers() {
        return soilModifiers;
    }

    public static void setFertilizers(Map<Item, FertilizerData> values) {
        fertilizers = Map.copyOf(values);
    }

    public static void setSoilModifiers(Map<Item, SoilModifierData> values) {
        soilModifiers = Map.copyOf(values);
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(ATDataMaps::onAddReloadListeners);
        MinecraftForge.EVENT_BUS.addListener(ATDataMaps::onDatapackSync);
    }

    private static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new DataMapLoader(event.getConditionContext()));
    }

    private static void onDatapackSync(OnDatapackSyncEvent event) {
        SyncDataMapsPacket packet = new SyncDataMapsPacket(fertilizers, soilModifiers);
        if (event.getPlayer() != null) {
            ATNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(event::getPlayer), packet);
        } else {
            ATNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), packet);
        }
    }
}