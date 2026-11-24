package com.misterd.agritechtwo.block;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.item.ATItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ATBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AgritechTwo.MODID);

    public static final RegistryObject<Block> ACACIA_PLANTER = registerBlock("acacia_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> BAMBOO_PLANTER = registerBlock("bamboo_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> BIRCH_PLANTER = registerBlock("birch_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> CHERRY_PLANTER = registerBlock("cherry_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> CRIMSON_PLANTER = registerBlock("crimson_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> DARK_OAK_PLANTER = registerBlock("dark_oak_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> JUNGLE_PLANTER = registerBlock("jungle_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> MANGROVE_PLANTER = registerBlock("mangrove_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> OAK_PLANTER = registerBlock("oak_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> SPRUCE_PLANTER = registerBlock("spruce_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> WARPED_PLANTER = registerBlock("warped_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));

    public static final RegistryObject<Block> TERRACOTTA_PLANTER = registerBlock("terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLACK_TERRACOTTA_PLANTER = registerBlock("black_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLUE_TERRACOTTA_PLANTER = registerBlock("blue_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BROWN_TERRACOTTA_PLANTER = registerBlock("brown_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CYAN_TERRACOTTA_PLANTER = registerBlock("cyan_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GRAY_TERRACOTTA_PLANTER = registerBlock("gray_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GREEN_TERRACOTTA_PLANTER = registerBlock("green_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_PLANTER = registerBlock("light_blue_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_PLANTER = registerBlock("light_gray_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LIME_TERRACOTTA_PLANTER = registerBlock("lime_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_PLANTER = registerBlock("magenta_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ORANGE_TERRACOTTA_PLANTER = registerBlock("orange_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PINK_TERRACOTTA_PLANTER = registerBlock("pink_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> PURPLE_TERRACOTTA_PLANTER = registerBlock("purple_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RED_TERRACOTTA_PLANTER = registerBlock("red_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> WHITE_TERRACOTTA_PLANTER = registerBlock("white_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> YELLOW_TERRACOTTA_PLANTER = registerBlock("yellow_terracotta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ATItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}