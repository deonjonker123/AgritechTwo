package com.misterd.agritechtwo.compat.jade;

import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class ATJadePlugin implements IWailaPlugin {
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(PlanterProvider.INSTANCE, PlanterBlockEntity.class);
    }

    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(PlanterProvider.INSTANCE, PlanterBlock.class);
    }
}
