package com.misterd.agritechtwo.block;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.custom.*;
import com.misterd.agritechtwo.item.ATItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;
import java.util.function.Function;

public class ATBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AgritechTwo.MODID);

    public static final DeferredBlock<Block> ACACIA_PLANTER = registerBlock("acacia_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> BAMBOO_PLANTER = registerBlock("bamboo_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> BIRCH_PLANTER = registerBlock("birch_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> CHERRY_PLANTER = registerBlock("cherry_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> CRIMSON_PLANTER = registerBlock("crimson_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> DARK_OAK_PLANTER = registerBlock("dark_oak_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> JUNGLE_PLANTER = registerBlock("jungle_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> MANGROVE_PLANTER = registerBlock("mangrove_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> OAK_PLANTER = registerBlock("oak_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> SPRUCE_PLANTER = registerBlock("spruce_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> WARPED_PLANTER = registerBlock("warped_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> PALE_OAK_PLANTER = registerBlock("pale_oak_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> POPLAR_PLANTER = registerBlock("poplar_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> ACACIA_RAISED_BED = registerBlock("acacia_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> BAMBOO_RAISED_BED = registerBlock("bamboo_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> BIRCH_RAISED_BED = registerBlock("birch_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> CHERRY_RAISED_BED = registerBlock("cherry_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> CRIMSON_RAISED_BED = registerBlock("crimson_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> DARK_OAK_RAISED_BED = registerBlock("dark_oak_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> JUNGLE_RAISED_BED = registerBlock("jungle_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> MANGROVE_RAISED_BED = registerBlock("mangrove_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> OAK_RAISED_BED = registerBlock("oak_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> SPRUCE_RAISED_BED = registerBlock("spruce_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> WARPED_RAISED_BED = registerBlock("warped_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> PALE_OAK_RAISED_BED = registerBlock("pale_oak_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> POPLAR_RAISED_BED = registerBlock("poplar_raised_bed",
            regName -> new RaisedBedBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> ACACIA_CRATE = registerBlock("acacia_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> BAMBOO_CRATE = registerBlock("bamboo_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> BIRCH_CRATE = registerBlock("birch_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> CHERRY_CRATE = registerBlock("cherry_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> CRIMSON_CRATE = registerBlock("crimson_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> DARK_OAK_CRATE = registerBlock("dark_oak_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> JUNGLE_CRATE = registerBlock("jungle_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> MANGROVE_CRATE = registerBlock("mangrove_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> OAK_CRATE = registerBlock("oak_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> PALE_OAK_CRATE = registerBlock("pale_oak_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> POPLAR_CRATE = registerBlock("poplar_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> SPRUCE_CRATE = registerBlock("spruce_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> WARPED_CRATE = registerBlock("warped_crate",
            regName -> new CrateBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));

    public static final DeferredBlock<Block> TERRACOTTA_PLANTER = registerBlock("terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BLACK_TERRACOTTA_PLANTER = registerBlock("black_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BLUE_TERRACOTTA_PLANTER = registerBlock("blue_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BROWN_TERRACOTTA_PLANTER = registerBlock("brown_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CYAN_TERRACOTTA_PLANTER = registerBlock("cyan_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GRAY_TERRACOTTA_PLANTER = registerBlock("gray_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GREEN_TERRACOTTA_PLANTER = registerBlock("green_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LIGHT_BLUE_TERRACOTTA_PLANTER = registerBlock("light_blue_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LIGHT_GRAY_TERRACOTTA_PLANTER = registerBlock("light_gray_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LIME_TERRACOTTA_PLANTER = registerBlock("lime_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> MAGENTA_TERRACOTTA_PLANTER = registerBlock("magenta_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ORANGE_TERRACOTTA_PLANTER = registerBlock("orange_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> PINK_TERRACOTTA_PLANTER = registerBlock("pink_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> PURPLE_TERRACOTTA_PLANTER = registerBlock("purple_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> RED_TERRACOTTA_PLANTER = registerBlock("red_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> WHITE_TERRACOTTA_PLANTER = registerBlock("white_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> YELLOW_TERRACOTTA_PLANTER = registerBlock("yellow_terracotta_planter",
            regName -> new PlanterBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, regName))
                    .strength(2.0F, 3.0F).sound(SoundType.STONE).noOcclusion().requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<Identifier, T> factory) {
        DeferredBlock<T> toReturn = BLOCKS.register(name,
                factory);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ATItems.ITEMS.register(name, regName -> {
            if (name.endsWith("_planter")) {
                return new BlockItem(block.get(),
                        new Item.Properties()
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AgritechTwo.MODID, name)))
                                .useBlockDescriptionPrefix()) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
                        adder.accept(Component.translatable("tooltip.agritechtwo.planter.till").withStyle(ChatFormatting.GRAY));
                        adder.accept(Component.translatable("tooltip.agritechtwo.planter.fertilize").withStyle(ChatFormatting.GRAY));
                    }
                };
            }
            if (name.endsWith("_raised_bed")) {
                return new BlockItem(block.get(),
                        new Item.Properties()
                                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AgritechTwo.MODID, name)))
                                .useBlockDescriptionPrefix()) {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> adder, TooltipFlag flag) {
                        adder.accept(Component.translatable("tooltip.agritechtwo.planter.till").withStyle(ChatFormatting.GRAY));
                        adder.accept(Component.translatable("tooltip.agritechtwo.planter.fertilize").withStyle(ChatFormatting.GRAY));
                    }
                };
            }
            return new BlockItem(block.get(),
                    new Item.Properties()
                            .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AgritechTwo.MODID, name)))
                            .useBlockDescriptionPrefix());
        });
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
