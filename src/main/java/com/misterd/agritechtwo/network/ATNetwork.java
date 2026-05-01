package com.misterd.agritechtwo.network;

import com.misterd.agritechtwo.AgritechTwo;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ATNetwork {
    public static void register(IEventBus eventBus) {
        eventBus.addListener(ATNetwork::registerPayloads);
    }

    private static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(AgritechTwo.MODID);

        registrar.playToServer(
                CrateCollectionTogglePacket.TYPE,
                CrateCollectionTogglePacket.STREAM_CODEC,
                CrateCollectionTogglePacket::handle);
    }
}