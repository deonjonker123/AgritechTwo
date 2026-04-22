package com.misterd.agritechtwo;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.blockentity.ATBlockEntities;
import com.misterd.agritechtwo.client.ber.PlanterBlockEntityRenderer;
import com.misterd.agritechtwo.command.ATCommands;
import com.misterd.agritechtwo.gui.ATMenuTypes;
import com.misterd.agritechtwo.gui.custom.PlanterBlockScreen;
import com.misterd.agritechtwo.item.ATCreativeTab;
import com.misterd.agritechtwo.item.ATItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(AgritechTwo.MODID)
public class AgritechTwo {
    public static final String MODID = "agritechtwo";

    public AgritechTwo(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ATItems.register(modEventBus);
        ATBlocks.register(modEventBus);
        ATCreativeTab.register(modEventBus);
        ATBlockEntities.register(modEventBus);
        ATMenuTypes.register(modEventBus);
        Config.register(modContainer);
        modEventBus.register(Config.class);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        ATCommands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {

        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ATBlockEntities.PLANTER_BLOCK_BE.get(), PlanterBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ATMenuTypes.PLANTER_BLOCK_MENU.get(), PlanterBlockScreen::new);
        }

        @SubscribeEvent
        public static void onRegisterStandaloneModels(ModelEvent.RegisterStandalone event) {
            // Key lives in the renderer — one source of truth
            PlanterBlockEntityRenderer.onRegisterStandaloneModels(event);
        }
    }
}