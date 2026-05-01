package com.misterd.agritechtwo.network;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.blockentity.custom.CrateBlockEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CrateCollectionTogglePacket(BlockPos pos, boolean collecting) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<CrateCollectionTogglePacket> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "crate_collection_toggle"));

    public static final StreamCodec<ByteBuf, CrateCollectionTogglePacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, CrateCollectionTogglePacket::pos,
                    ByteBufCodecs.BOOL, CrateCollectionTogglePacket::collecting,
                    CrateCollectionTogglePacket::new);

    @Override
    public CustomPacketPayload.Type<CrateCollectionTogglePacket> type() {
        return TYPE;
    }

    public static void handle(CrateCollectionTogglePacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            if (!(ctx.player() instanceof ServerPlayer player)) return;
            if (player.distanceToSqr(packet.pos().getCenter()) > 64) return;
            if (!(player.level().getBlockEntity(packet.pos()) instanceof CrateBlockEntity crate)) return;
            crate.setCollecting(packet.collecting());
        });
    }
}