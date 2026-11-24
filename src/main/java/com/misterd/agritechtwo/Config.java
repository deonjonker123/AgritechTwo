package com.misterd.agritechtwo;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

public class Config {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.DoubleValue CLOCHE_SPEED_MULTIPLIER;
    public static ForgeConfigSpec.DoubleValue CLOCHE_YIELD_MULTIPLIER;

    public static ForgeConfigSpec.IntValue PLANTER_BASE_PROCESSING_TIME;

    public static void register(FMLJavaModLoadingContext context) {
        machineConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
        SPEC = COMMON_CONFIG;
        context.registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }

    private static void machineConfig() {
        COMMON_BUILDER.comment("Agritech Settings").push("agritech");
        planterConfig();
        COMMON_BUILDER.pop();
    }

    private static void planterConfig() {
        COMMON_BUILDER.comment("Planter Configuration").push("planter");
        PLANTER_BASE_PROCESSING_TIME = COMMON_BUILDER.comment("Base processing time (ticks)").defineInRange("base_processing_time", 1200, 1, 72000);
        CLOCHE_SPEED_MULTIPLIER = COMMON_BUILDER.comment("Speed multiplier applied when a cloche is attached to a planter").defineInRange("cloche_speed_multiplier", 1.15D, 0.1D, 10.0D);
        CLOCHE_YIELD_MULTIPLIER = COMMON_BUILDER.comment("Yield multiplier applied when a cloche is attached to a planter").defineInRange("cloche_yield_multiplier", 1.10D, 0.1D, 10.0D);
        COMMON_BUILDER.pop();
    }

    public static double getClocheSpeedMultiplier() { return CLOCHE_SPEED_MULTIPLIER.get(); }
    public static double getClocheYieldMultiplier() { return CLOCHE_YIELD_MULTIPLIER.get(); }

    public static int getPlanterBaseProcessingTime() { return PLANTER_BASE_PROCESSING_TIME.get(); }

    @SubscribeEvent
    static void onLoad(ModConfigEvent event) {
        LOGGER.info("AgriTech configuration loaded");
    }
}