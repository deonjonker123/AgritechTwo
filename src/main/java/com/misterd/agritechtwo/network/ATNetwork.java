package com.misterd.agritechtwo.network;

import com.misterd.agritechtwo.AgritechTwo;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class ATNetwork {
    private static final String PROTOCOL = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(AgritechTwo.MODID, "main"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    private ATNetwork() {}

    public static void register() {
        CHANNEL.messageBuilder(SyncDataMapsPacket.class, 0, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(SyncDataMapsPacket::encode)
                .decoder(SyncDataMapsPacket::decode)
                .consumerMainThread(SyncDataMapsPacket::handle)
                .add();
    }
}