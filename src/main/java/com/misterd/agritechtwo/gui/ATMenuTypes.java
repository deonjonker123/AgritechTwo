package com.misterd.agritechtwo.gui;

import com.misterd.agritechtwo.gui.custom.CrateBlockMenu;
import com.misterd.agritechtwo.gui.custom.PlanterBlockMenu;
import com.misterd.agritechtwo.gui.custom.RaisedBedBlockMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ATMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, "agritechtwo");

    public static final DeferredHolder<MenuType<?>, MenuType<PlanterBlockMenu>> PLANTER_BLOCK_MENU = registerMenuType("planter_block_menu", PlanterBlockMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<RaisedBedBlockMenu>> RAISED_BED_BLOCK_MENU = registerMenuType("raised_bed_block_menu", RaisedBedBlockMenu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<CrateBlockMenu>> CRATE_BLOCK_MENU = registerMenuType("crate_block_menu", CrateBlockMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}