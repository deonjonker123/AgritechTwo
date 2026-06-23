package com.misterd.agritechtwo;

import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.blockentity.ATBlockEntities;
import com.misterd.agritechtwo.client.ber.PlanterBlockEntityRenderer;
import com.misterd.agritechtwo.datamap.ATDataMaps;
import com.misterd.agritechtwo.gui.ATMenuTypes;
import com.misterd.agritechtwo.gui.custom.PlanterBlockScreen;
import com.misterd.agritechtwo.item.ATCreativeTab;
import com.misterd.agritechtwo.item.ATItems;
import com.misterd.agritechtwo.recipe.ATRecipe;
import com.mojang.logging.LogUtils;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(AgritechTwo.MODID)
public class AgritechTwo {
    public static final String MODID = "agritechtwo";
    public static int RECIPE_REVISION = 0;
    public static final Logger LOGGER = LogUtils.getLogger();

    public AgritechTwo(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ATItems.register(modEventBus);
        ATBlocks.register(modEventBus);
        ATCreativeTab.register(modEventBus);
        ATBlockEntities.register(modEventBus);
        ATMenuTypes.register(modEventBus);
        ATDataMaps.register(modEventBus);
        ATRecipe.register(modEventBus);
        Config.register(modContainer);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

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
        public static void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
            event.register(new ModelResourceLocation(
                    ResourceLocation.fromNamespaceAndPath(MODID, "block/cloche_dome"),
                    "standalone"
            ));
        }
    }
}
