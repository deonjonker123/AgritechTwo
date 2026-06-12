package com.misterd.agritechtwo;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.slf4j.Logger;


public class Config {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec COMMON_CONFIG;
    public static ModConfigSpec SPEC;

    // Cloche
    public static ModConfigSpec.DoubleValue CLOCHE_SPEED_MULTIPLIER;
    public static ModConfigSpec.DoubleValue CLOCHE_YIELD_MULTIPLIER;

    // Planter
    public static ModConfigSpec.IntValue PLANTER_BASE_PROCESSING_TIME;

    // Raised Bed
    public static ModConfigSpec.DoubleValue RAISED_BED_SKY_DAY_SPEED_MULTIPLIER;
    public static ModConfigSpec.IntValue BASKET_PICKUP_INTERVAL_TICKS;

    public static void register(ModContainer container) {
        machineConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
        SPEC = COMMON_CONFIG;
        container.registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }

    private static void machineConfig() {
        COMMON_BUILDER.comment("Machine Settings").push("machines");
        planterConfig();
        raisedBedConfig();
        crateConfig();
        COMMON_BUILDER.pop();
    }

    private static void planterConfig() {
        COMMON_BUILDER.comment("Planter Configuration").push("planter");
        PLANTER_BASE_PROCESSING_TIME = COMMON_BUILDER.comment("Base processing time (ticks)").defineInRange("base_processing_time", 1200, 1, 72000);
        CLOCHE_SPEED_MULTIPLIER = COMMON_BUILDER.comment("Speed multiplier applied when a cloche is attached to a planter").defineInRange("cloche_speed_multiplier", 1.15D, 0.1D, 10.0D);
        CLOCHE_YIELD_MULTIPLIER = COMMON_BUILDER.comment("Yield multiplier applied when a cloche is attached to a planter").defineInRange("cloche_yield_multiplier", 1.10D, 0.1D, 10.0D);
        COMMON_BUILDER.pop();
    }

    private static void raisedBedConfig() {
        COMMON_BUILDER.comment("Raised Bed Configuration").push("raised_bed");
        RAISED_BED_SKY_DAY_SPEED_MULTIPLIER = COMMON_BUILDER.comment("Speed multiplier applied when the raised bed has sky access and it is daytime").defineInRange("sky_day_speed_multiplier", 1.25D, 0.1D, 10.0D);
        COMMON_BUILDER.pop();
    }

    private static void crateConfig() {
        COMMON_BUILDER.comment("Crate Configuration").push("crate");
        BASKET_PICKUP_INTERVAL_TICKS = COMMON_BUILDER.comment("How often (in ticks) the crate scans for nearby item entities").defineInRange("pickup_interval_ticks", 20, 1, 1200);
        COMMON_BUILDER.pop();
    }

    // --- Cloche getters ---
    public static double getClocheSpeedMultiplier() { return CLOCHE_SPEED_MULTIPLIER.get(); }
    public static double getClocheYieldMultiplier() { return CLOCHE_YIELD_MULTIPLIER.get(); }

    // --- Planter getters ---
    public static int getPlanterBaseProcessingTime() { return PLANTER_BASE_PROCESSING_TIME.get(); }

    // --- Raised Bed getters ---
    public static double getRaisedBedSkyDaySpeedMultiplier() { return RAISED_BED_SKY_DAY_SPEED_MULTIPLIER.get(); }

    public static int getBasketPickupIntervalTicks() { return BASKET_PICKUP_INTERVAL_TICKS.get(); }

    public static void loadConfig() {
        LOGGER.info("AgriTech configs reloaded");
    }
}