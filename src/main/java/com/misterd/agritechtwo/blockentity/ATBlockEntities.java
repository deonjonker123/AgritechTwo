package com.misterd.agritechtwo.blockentity;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.blockentity.custom.CrateBlockEntity;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.blockentity.custom.RaisedBedBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.transfer.item.ItemResource;

import java.util.function.Supplier;

public class ATBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AgritechTwo.MODID);

    public static final Supplier<BlockEntityType<PlanterBlockEntity>> PLANTER_BLOCK_BE =
            BLOCK_ENTITIES.register("planter_block_be", () -> new BlockEntityType<>(
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
                    ATBlocks.WARPED_PLANTER.get(),
                    ATBlocks.PALE_OAK_PLANTER.get()
            ));

    public static final Supplier<BlockEntityType<RaisedBedBlockEntity>> RAISED_BED_BLOCK_BE =
            BLOCK_ENTITIES.register("raised_bed_block_be", () -> new BlockEntityType<>(
                    RaisedBedBlockEntity::new,
                    ATBlocks.ACACIA_RAISED_BED.get(),
                    ATBlocks.BAMBOO_RAISED_BED.get(),
                    ATBlocks.BIRCH_RAISED_BED.get(),
                    ATBlocks.CHERRY_RAISED_BED.get(),
                    ATBlocks.CRIMSON_RAISED_BED.get(),
                    ATBlocks.DARK_OAK_RAISED_BED.get(),
                    ATBlocks.JUNGLE_RAISED_BED.get(),
                    ATBlocks.MANGROVE_RAISED_BED.get(),
                    ATBlocks.OAK_RAISED_BED.get(),
                    ATBlocks.SPRUCE_RAISED_BED.get(),
                    ATBlocks.WARPED_RAISED_BED.get(),
                    ATBlocks.PALE_OAK_RAISED_BED.get()
            ));

    public static final Supplier<BlockEntityType<CrateBlockEntity>> CRATE_BLOCK_BE =
            BLOCK_ENTITIES.register("crate_block_be", () -> new BlockEntityType<>(
                    CrateBlockEntity::new,
                    ATBlocks.ACACIA_CRATE.get(),
                    ATBlocks.BAMBOO_CRATE.get(),
                    ATBlocks.BIRCH_CRATE.get(),
                    ATBlocks.CHERRY_CRATE.get(),
                    ATBlocks.CRIMSON_CRATE.get(),
                    ATBlocks.DARK_OAK_CRATE.get(),
                    ATBlocks.JUNGLE_CRATE.get(),
                    ATBlocks.MANGROVE_CRATE.get(),
                    ATBlocks.OAK_CRATE.get(),
                    ATBlocks.PALE_OAK_CRATE.get(),
                    ATBlocks.SPRUCE_CRATE.get(),
                    ATBlocks.WARPED_CRATE.get()
            ));

    private static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, PLANTER_BLOCK_BE.get(),
                (blockEntity, direction) -> {
                    if (!(blockEntity instanceof PlanterBlockEntity planter)) return null;
                    if (direction == null) return null;
                    if (direction.getAxis().isHorizontal()) return planter.getInsertHandler();
                    if (direction == Direction.DOWN) return planter.getExtractHandler();
                    return null;
                });

        event.registerBlockEntity(Capabilities.Item.BLOCK, CRATE_BLOCK_BE.get(),
                (blockEntity, direction) -> {
                    if (!(blockEntity instanceof CrateBlockEntity crate)) return null;
                    return crate.inventory;
                });
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
        eventBus.addListener(ATBlockEntities::registerCapabilities);
    }
}