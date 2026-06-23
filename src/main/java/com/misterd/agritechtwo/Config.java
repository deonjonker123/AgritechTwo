package com.misterd.agritechtwo;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.slf4j.Logger;


public class Config {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec SPEC;

    public static ModConfigSpec.DoubleValue CLOCHE_SPEED_MULTIPLIER;
    public static ModConfigSpec.DoubleValue CLOCHE_YIELD_MULTIPLIER;

    public static ModConfigSpec.IntValue PLANTER_BASE_PROCESSING_TIME;

    public static void register(ModContainer container) {
        machineConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
        SPEC = COMMON_CONFIG;
        container.registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }

    private static void machineConfig() {
        COMMON_BUILDER.comment("Planter Settings").push("machines");
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
        LOGGER.info("AgriTech: Two configuration loaded");
    }
}