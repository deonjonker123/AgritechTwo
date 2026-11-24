package com.misterd.agritechtwo.blockentity;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ATBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AgritechTwo.MODID);

    public static final RegistryObject<BlockEntityType<PlanterBlockEntity>> PLANTER_BLOCK_BE =
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
                    ATBlocks.WARPED_PLANTER.get(),
                    ATBlocks.TERRACOTTA_PLANTER.get(),
                    ATBlocks.BLACK_TERRACOTTA_PLANTER.get(),
                    ATBlocks.BLUE_TERRACOTTA_PLANTER.get(),
                    ATBlocks.BROWN_TERRACOTTA_PLANTER.get(),
                    ATBlocks.CYAN_TERRACOTTA_PLANTER.get(),
                    ATBlocks.GRAY_TERRACOTTA_PLANTER.get(),
                    ATBlocks.GREEN_TERRACOTTA_PLANTER.get(),
                    ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get(),
                    ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get(),
                    ATBlocks.LIME_TERRACOTTA_PLANTER.get(),
                    ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get(),
                    ATBlocks.ORANGE_TERRACOTTA_PLANTER.get(),
                    ATBlocks.PINK_TERRACOTTA_PLANTER.get(),
                    ATBlocks.PURPLE_TERRACOTTA_PLANTER.get(),
                    ATBlocks.RED_TERRACOTTA_PLANTER.get(),
                    ATBlocks.WHITE_TERRACOTTA_PLANTER.get(),
                    ATBlocks.YELLOW_TERRACOTTA_PLANTER.get()
            ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}