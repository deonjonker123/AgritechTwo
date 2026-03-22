package com.misterd.agritechtwo.blockentity;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ATBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AgritechTwo.MODID);

    public static final Supplier<BlockEntityType<PlanterBlockEntity>> PLANTER_BLOCK_BE =
            BLOCK_ENTITIES.register("planter_block_be", () -> BlockEntityType.Builder.of(
                    PlanterBlockEntity::new,
                    ATBlocks.ACACIA_PLANTER.get(),
                    ATBlocks.BAMBOO_PLANTER.get(),
                    ATBlocks.BIRCH_PLANTER.get(),
                    ATBlocks.CHERRY_PLANTER.get(),
                    ATBlocks.CRIMSON_PLANTER.get(),
                    ATBlocks.DARK_OAK_PLANTER.get(),
                    ATBlocks.JUNGLE_PLANTER.get(),
                    ATBlocks.MANGROVE_PLANTER.get(),
                    ATBlocks.OAK_PLANTER.get(),
                    ATBlocks.SPRUCE_PLANTER.get(),
                    ATBlocks.WARPED_PLANTER.get()
            ).build(null));

    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PLANTER_BLOCK_BE.get(),
                (blockEntity, direction) -> {
                    if (!(blockEntity instanceof PlanterBlockEntity planter) || direction == null) {
                        return null;
                    }

                    if (direction.getAxis().isHorizontal()) {
                        return planter.getCapabilityHandler();
                    }

                    if (direction == Direction.DOWN) {
                        return planter.getExtractHandler();
                    }

                    return null;
                });
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
        eventBus.addListener(ATBlockEntities::registerCapabilities);
    }
}
