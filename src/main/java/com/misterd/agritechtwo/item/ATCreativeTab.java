package com.misterd.agritechtwo.item;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.ATBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ATCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AgritechTwo.MODID);

    public static final Supplier<CreativeModeTab> AGRITECH_TWO = CREATIVE_MODE_TAB.register("agritechtwo_creativetab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ATBlocks.OAK_PLANTER.get()))
                    .title(Component.translatable("creativetab.agritechtwo"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ATBlocks.ACACIA_PLANTER);
                        output.accept(ATBlocks.BAMBOO_PLANTER);
                        output.accept(ATBlocks.BIRCH_PLANTER);
                        output.accept(ATBlocks.CHERRY_PLANTER);
                        output.accept(ATBlocks.CRIMSON_PLANTER);
                        output.accept(ATBlocks.DARK_OAK_PLANTER);
                        output.accept(ATBlocks.JUNGLE_PLANTER);
                        output.accept(ATBlocks.MANGROVE_PLANTER);
                        output.accept(ATBlocks.OAK_PLANTER);
                        output.accept(ATBlocks.SPRUCE_PLANTER);
                        output.accept(ATBlocks.WARPED_PLANTER);
                        output.accept(ATItems.CLOCHE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
