package com.misterd.agritechtwo.item;

import com.misterd.agritechtwo.AgritechTwo;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ATItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AgritechTwo.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
