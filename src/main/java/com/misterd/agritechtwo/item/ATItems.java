package com.misterd.agritechtwo.item;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.item.custom.ClocheItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ATItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AgritechTwo.MODID);

    public static final RegistryObject<ClocheItem> CLOCHE = ITEMS.register("cloche_dome",
            () -> new ClocheItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}