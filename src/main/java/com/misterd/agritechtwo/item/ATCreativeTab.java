package com.misterd.agritechtwo.item;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ATCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AgritechTwo.MODID);

    public static final Supplier<CreativeModeTab> AGRITECH_TWO = CREATIVE_MODE_TAB.register("agritechtwo_creativetab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ATBlocks.OAK_PLANTER.get()))
                    .title(Component.translatable("creativetab.agritechtwo"))
                    .displayItems((itemDisplayParameters, output) -> {
                        if (!ModList.get().isLoaded("agritechevolved")) {
                            output.accept(ATBlocks.ACACIA_PLANTER);
                            output.accept(ATBlocks.BAMBOO_PLANTER);
                            output.accept(ATBlocks.BIRCH_PLANTER);
                            output.accept(ATBlocks.CHERRY_PLANTER);
                            output.accept(ATBlocks.CRIMSON_PLANTER);
                            output.accept(ATBlocks.DARK_OAK_PLANTER);
                            output.accept(ATBlocks.JUNGLE_PLANTER);
                            output.accept(ATBlocks.MANGROVE_PLANTER);
                            output.accept(ATBlocks.OAK_PLANTER);
                            output.accept(ATBlocks.PALE_OAK_PLANTER);
                            output.accept(ATBlocks.SPRUCE_PLANTER);
                            output.accept(ATBlocks.WARPED_PLANTER);
                        }

                        output.accept(ATBlocks.ACACIA_CRATE);
                        output.accept(ATBlocks.BAMBOO_CRATE);
                        output.accept(ATBlocks.BIRCH_CRATE);
                        output.accept(ATBlocks.CHERRY_CRATE);
                        output.accept(ATBlocks.CRIMSON_CRATE);
                        output.accept(ATBlocks.DARK_OAK_CRATE);
                        output.accept(ATBlocks.JUNGLE_CRATE);
                        output.accept(ATBlocks.MANGROVE_CRATE);
                        output.accept(ATBlocks.OAK_CRATE);
                        output.accept(ATBlocks.PALE_OAK_CRATE);
                        output.accept(ATBlocks.SPRUCE_CRATE);
                        output.accept(ATBlocks.WARPED_CRATE);

                        output.accept(ATBlocks.ACACIA_RAISED_BED);
                        output.accept(ATBlocks.BAMBOO_RAISED_BED);
                        output.accept(ATBlocks.BIRCH_RAISED_BED);
                        output.accept(ATBlocks.CHERRY_RAISED_BED);
                        output.accept(ATBlocks.CRIMSON_RAISED_BED);
                        output.accept(ATBlocks.DARK_OAK_RAISED_BED);
                        output.accept(ATBlocks.JUNGLE_RAISED_BED);
                        output.accept(ATBlocks.MANGROVE_RAISED_BED);
                        output.accept(ATBlocks.OAK_RAISED_BED);
                        output.accept(ATBlocks.PALE_OAK_RAISED_BED);
                        output.accept(ATBlocks.SPRUCE_RAISED_BED);
                        output.accept(ATBlocks.WARPED_RAISED_BED);

                        if (!ModList.get().isLoaded("agritechevolved")) {
                            output.accept(ATItems.CLOCHE.get());
                        }
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}