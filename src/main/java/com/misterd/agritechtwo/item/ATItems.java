package com.misterd.agritechtwo.item;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.item.custom.ClocheItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ATItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AgritechTwo.MODID);

    public static final DeferredHolder<Item, ClocheItem> CLOCHE = ITEMS.register("cloche_dome",
            () -> new ClocheItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "cloche_dome")))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
