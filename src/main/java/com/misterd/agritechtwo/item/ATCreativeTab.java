package com.misterd.agritechtwo.item;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ATCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AgritechTwo.MODID);

    public static final RegistryObject<CreativeModeTab> AGRITECH_TWO = CREATIVE_MODE_TAB.register("agritechtwo_creativetab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ATBlocks.OAK_PLANTER.get()))
                    .title(Component.translatable("creativetab.agritechtwo"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ATBlocks.ACACIA_PLANTER.get());
                        output.accept(ATBlocks.BAMBOO_PLANTER.get());
                        output.accept(ATBlocks.BIRCH_PLANTER.get());
                        output.accept(ATBlocks.CHERRY_PLANTER.get());
                        output.accept(ATBlocks.CRIMSON_PLANTER.get());
                        output.accept(ATBlocks.DARK_OAK_PLANTER.get());
                        output.accept(ATBlocks.JUNGLE_PLANTER.get());
                        output.accept(ATBlocks.MANGROVE_PLANTER.get());
                        output.accept(ATBlocks.OAK_PLANTER.get());
                        output.accept(ATBlocks.SPRUCE_PLANTER.get());
                        output.accept(ATBlocks.WARPED_PLANTER.get());
                        output.accept(ATBlocks.TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.BLACK_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.BLUE_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.BROWN_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.CYAN_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.GRAY_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.GREEN_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.LIGHT_BLUE_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.LIGHT_GRAY_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.LIME_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.MAGENTA_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.ORANGE_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.PINK_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.PURPLE_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.RED_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.WHITE_TERRACOTTA_PLANTER.get());
                        output.accept(ATBlocks.YELLOW_TERRACOTTA_PLANTER.get());
                        output.accept(ATItems.CLOCHE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}