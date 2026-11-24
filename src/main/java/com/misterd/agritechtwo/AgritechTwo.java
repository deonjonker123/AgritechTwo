package com.misterd.agritechtwo;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.blockentity.ATBlockEntities;
import com.misterd.agritechtwo.client.ber.PlanterBlockEntityRenderer;
import com.misterd.agritechtwo.datamap.ATDataMaps;
import com.misterd.agritechtwo.gui.ATMenuTypes;
import com.misterd.agritechtwo.gui.custom.PlanterBlockScreen;
import com.misterd.agritechtwo.item.ATCreativeTab;
import com.misterd.agritechtwo.item.ATItems;
import com.misterd.agritechtwo.network.ATNetwork;
import com.misterd.agritechtwo.recipe.ATRecipe;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AgritechTwo.MODID)
public class AgritechTwo {
    public static final String MODID = "agritechtwo";
    public static int RECIPE_REVISION = 0;
    public static final Logger LOGGER = LogUtils.getLogger();

    public AgritechTwo(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ATBlocks.register(modEventBus);
        ATBlockEntities.register(modEventBus);
        ATItems.register(modEventBus);
        ATCreativeTab.register(modEventBus);
        ATMenuTypes.register(modEventBus);
        ATRecipe.register(modEventBus);
        ATDataMaps.register();

        Config.register(context);
        modEventBus.register(Config.class);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ATNetwork::register);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @SubscribeEvent
    public void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new SimplePreparableReloadListener<Void>() {
            @Override
            protected Void prepare(ResourceManager manager, ProfilerFiller profiler) {
                return null;
            }

            @Override
            protected void apply(Void object, ResourceManager manager, ProfilerFiller profiler) {
                RECIPE_REVISION++;
            }
        });
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> MenuScreens.register(ATMenuTypes.PLANTER_BLOCK_MENU.get(), PlanterBlockScreen::new));
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ATBlockEntities.PLANTER_BLOCK_BE.get(), PlanterBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
            event.register(PlanterBlockEntityRenderer.CLOCHE_DOME_MODEL);
        }
    }
}