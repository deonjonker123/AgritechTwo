package com.misterd.agritechtwo;

import com.misterd.agritechtwo.config.PlantablesConfig;
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

    // Compatibility
    public static ModConfigSpec.BooleanValue ENABLE_MYSTICAL_AGRICULTURE;
    public static ModConfigSpec.BooleanValue ENABLE_MYSTICAL_AGRADDITIONS;
    public static ModConfigSpec.BooleanValue ENABLE_FARMERS_DELIGHT;
    public static ModConfigSpec.BooleanValue ENABLE_ARS_NOUVEAU;
    public static ModConfigSpec.BooleanValue ENABLE_ARS_ELEMENTAL;
    public static ModConfigSpec.BooleanValue ENABLE_SILENT_GEAR;
    public static ModConfigSpec.BooleanValue ENABLE_JUST_DIRE_THINGS;
    public static ModConfigSpec.BooleanValue ENABLE_IMMERSIVE_ENGINEERING;
    public static ModConfigSpec.BooleanValue ENABLE_EVILCRAFT;
    public static ModConfigSpec.BooleanValue ENABLE_FORBIDDEN_ARCANUS;
    public static ModConfigSpec.BooleanValue ENABLE_INTEGRATED_DYNAMICS;
    public static ModConfigSpec.BooleanValue ENABLE_OCCULTISM;
    public static ModConfigSpec.BooleanValue ENABLE_PAMS_CROPS;
    public static ModConfigSpec.BooleanValue ENABLE_PAMS_TREES;
    public static ModConfigSpec.BooleanValue ENABLE_CROPTOPIA;
    public static ModConfigSpec.BooleanValue ENABLE_COBBLEMON;
    public static ModConfigSpec.BooleanValue ENABLE_ACTUALLY_ADDITIONS;

    // Fertilizers
    public static ModConfigSpec.DoubleValue FERTILIZER_BONE_MEAL_SPEED_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_BONE_MEAL_YIELD_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_FERTILIZED_ESSENCE_SPEED_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_FERTILIZED_ESSENCE_YIELD_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_MYSTICAL_FERTILIZER_SPEED_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_MYSTICAL_FERTILIZER_YIELD_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_IMMERSIVE_FERTILIZER_SPEED_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_IMMERSIVE_FERTILIZER_YIELD_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_ARCANE_BONE_MEAL_SPEED_MULTIPLIER;
    public static ModConfigSpec.DoubleValue FERTILIZER_ARCANE_BONE_MEAL_YIELD_MULTIPLIER;

    // Cloche
    public static ModConfigSpec.DoubleValue CLOCHE_SPEED_MULTIPLIER;
    public static ModConfigSpec.DoubleValue CLOCHE_YIELD_MULTIPLIER;

    public static ModConfigSpec.IntValue PLANTER_BASE_PROCESSING_TIME;

    // Booleans populated on load
    public static boolean enableMysticalAgriculture;
    public static boolean enableMysticalAgradditions;
    public static boolean enableFarmersDelight;
    public static boolean enableArsNouveau;
    public static boolean enableArsElemental;
    public static boolean enableSilentGear;
    public static boolean enableJustDireThings;
    public static boolean enableImmersiveEngineering;
    public static boolean enableEvilCraft;
    public static boolean enableForbiddenArcanus;
    public static boolean enableIntegratedDynamics;
    public static boolean enableOccultism;
    public static boolean enablePamsCrops;
    public static boolean enablePamsTrees;
    public static boolean enableCroptopia;
    public static boolean enableCobblemon;
    public static boolean enableActuallyAdditions;

    public static void register(ModContainer container) {
        compatibilityConfig();
        fertilizerConfig();
        machineConfig();
        COMMON_CONFIG = COMMON_BUILDER.build();
        SPEC = COMMON_CONFIG;
        container.registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }

    private static void compatibilityConfig() {
        COMMON_BUILDER.comment("Mod Compatibility Settings").push("compatibility");
        ENABLE_MYSTICAL_AGRICULTURE = COMMON_BUILDER.comment("Enable Mystical Agriculture compatibility").define("enable_mystical_agriculture", true);
        ENABLE_MYSTICAL_AGRADDITIONS = COMMON_BUILDER.comment("Enable Mystical Agradditions compatibility").define("enable_mystical_agradditions", true);
        ENABLE_FARMERS_DELIGHT = COMMON_BUILDER.comment("Enable Farmer's Delight compatibility").define("enable_farmers_delight", true);
        ENABLE_ARS_NOUVEAU = COMMON_BUILDER.comment("Enable Ars Nouveau compatibility").define("enable_ars_nouveau", true);
        ENABLE_ARS_ELEMENTAL = COMMON_BUILDER.comment("Enable Ars Elemental compatibility").define("enable_ars_elemental", true);
        ENABLE_SILENT_GEAR = COMMON_BUILDER.comment("Enable Silent Gear compatibility").define("enable_silent_gear", true);
        ENABLE_JUST_DIRE_THINGS = COMMON_BUILDER.comment("Enable Just Dire Things compatibility").define("enable_just_dire_things", true);
        ENABLE_IMMERSIVE_ENGINEERING = COMMON_BUILDER.comment("Enable Immersive Engineering compatibility").define("enable_immersive_engineering", true);
        ENABLE_EVILCRAFT = COMMON_BUILDER.comment("Enable EvilCraft compatibility").define("enable_evilcraft", true);
        ENABLE_FORBIDDEN_ARCANUS = COMMON_BUILDER.comment("Enable Forbidden and Arcanus compatibility").define("enable_forbidden_arcanus", true);
        ENABLE_INTEGRATED_DYNAMICS = COMMON_BUILDER.comment("Enable Integrated Dynamics compatibility").define("enable_integrated_dynamics", true);
        ENABLE_OCCULTISM = COMMON_BUILDER.comment("Enable Occultism compatibility").define("enable_occultism", true);
        ENABLE_PAMS_CROPS = COMMON_BUILDER.comment("Enable Pam's HarvestCraft - Crops compatibility").define("enable_pams_crops", true);
        ENABLE_PAMS_TREES = COMMON_BUILDER.comment("Enable Pam's HarvestCraft - Trees compatibility").define("enable_pams_trees", true);
        ENABLE_CROPTOPIA = COMMON_BUILDER.comment("Enable Croptopia compatibility").define("enable_croptopia", true);
        ENABLE_COBBLEMON = COMMON_BUILDER.comment("Enable Cobblemon compatibility").define("enable_cobblemon", true);
        ENABLE_ACTUALLY_ADDITIONS = COMMON_BUILDER.comment("Enable Actually Additions compatibility").define("enable_actually_additions", true);
        COMMON_BUILDER.pop();
    }

    private static void fertilizerConfig() {
        COMMON_BUILDER.comment("Fertilizer Configuration").push("fertilizers");
        FERTILIZER_BONE_MEAL_SPEED_MULTIPLIER = COMMON_BUILDER.comment("Speed multiplier for Bone Meal").defineInRange("bone_meal_speed_multiplier", 1.2D, 0.1D, 10.0D);
        FERTILIZER_BONE_MEAL_YIELD_MULTIPLIER = COMMON_BUILDER.comment("Yield multiplier for Bone Meal").defineInRange("bone_meal_yield_multiplier", 1.2D, 0.1D, 10.0D);
        FERTILIZER_FERTILIZED_ESSENCE_SPEED_MULTIPLIER = COMMON_BUILDER.comment("Speed multiplier for Fertilized Essence").defineInRange("fertilized_essence_speed_multiplier", 1.3D, 0.1D, 10.0D);
        FERTILIZER_FERTILIZED_ESSENCE_YIELD_MULTIPLIER = COMMON_BUILDER.comment("Yield multiplier for Fertilized Essence").defineInRange("fertilized_essence_yield_multiplier", 1.3D, 0.1D, 10.0D);
        FERTILIZER_MYSTICAL_FERTILIZER_SPEED_MULTIPLIER = COMMON_BUILDER.comment("Speed multiplier for Mystical Fertilizer").defineInRange("mystical_fertilizer_speed_multiplier", 1.6D, 0.1D, 10.0D);
        FERTILIZER_MYSTICAL_FERTILIZER_YIELD_MULTIPLIER = COMMON_BUILDER.comment("Yield multiplier for Mystical Fertilizer").defineInRange("mystical_fertilizer_yield_multiplier", 1.6D, 0.1D, 10.0D);
        FERTILIZER_IMMERSIVE_FERTILIZER_SPEED_MULTIPLIER = COMMON_BUILDER.comment("Speed multiplier for Immersive Engineering Fertilizer").defineInRange("immersive_fertilizer_speed_multiplier", 1.4D, 0.1D, 10.0D);
        FERTILIZER_IMMERSIVE_FERTILIZER_YIELD_MULTIPLIER = COMMON_BUILDER.comment("Yield multiplier for Immersive Engineering Fertilizer").defineInRange("immersive_fertilizer_yield_multiplier", 1.4D, 0.1D, 10.0D);
        FERTILIZER_ARCANE_BONE_MEAL_SPEED_MULTIPLIER = COMMON_BUILDER.comment("Speed multiplier for Arcane Bone Meal").defineInRange("arcane_bone_meal_speed_multiplier", 1.5D, 0.1D, 10.0D);
        FERTILIZER_ARCANE_BONE_MEAL_YIELD_MULTIPLIER = COMMON_BUILDER.comment("Yield multiplier for Arcane Bone Meal").defineInRange("arcane_bone_meal_yield_multiplier", 1.5D, 0.1D, 10.0D);
        COMMON_BUILDER.pop();
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

    // --- Fertilizer getters ---
    public static double getFertilizerBoneMealSpeedMultiplier()            { return FERTILIZER_BONE_MEAL_SPEED_MULTIPLIER.get(); }
    public static double getFertilizerBoneMealYieldMultiplier()            { return FERTILIZER_BONE_MEAL_YIELD_MULTIPLIER.get(); }
    public static double getFertilizerFertilizedEssenceSpeedMultiplier()   { return FERTILIZER_FERTILIZED_ESSENCE_SPEED_MULTIPLIER.get(); }
    public static double getFertilizerFertilizedEssenceYieldMultiplier()   { return FERTILIZER_FERTILIZED_ESSENCE_YIELD_MULTIPLIER.get(); }
    public static double getFertilizerMysticalFertilizerSpeedMultiplier()  { return FERTILIZER_MYSTICAL_FERTILIZER_SPEED_MULTIPLIER.get(); }
    public static double getFertilizerMysticalFertilizerYieldMultiplier()  { return FERTILIZER_MYSTICAL_FERTILIZER_YIELD_MULTIPLIER.get(); }
    public static double getFertilizerImmersiveFertilizerSpeedMultiplier() { return FERTILIZER_IMMERSIVE_FERTILIZER_SPEED_MULTIPLIER.get(); }
    public static double getFertilizerImmersiveFertilizerYieldMultiplier() { return FERTILIZER_IMMERSIVE_FERTILIZER_YIELD_MULTIPLIER.get(); }
    public static double getFertilizerArcaneBoneMealSpeedMultiplier()      { return FERTILIZER_ARCANE_BONE_MEAL_SPEED_MULTIPLIER.get(); }
    public static double getFertilizerArcaneBoneMealYieldMultiplier()      { return FERTILIZER_ARCANE_BONE_MEAL_YIELD_MULTIPLIER.get(); }

    // --- Cloche getters ---
    public static double getClocheSpeedMultiplier() { return CLOCHE_SPEED_MULTIPLIER.get(); }
    public static double getClocheYieldMultiplier() { return CLOCHE_YIELD_MULTIPLIER.get(); }

    public static int getPlanterBaseProcessingTime() { return PLANTER_BASE_PROCESSING_TIME.get(); }

    public static void loadConfig() {
        PlantablesConfig.loadConfig();
        LOGGER.info("AgriTech: Two configs reloaded");
    }

    @SubscribeEvent
    static void onLoad(ModConfigEvent event) {
        enableMysticalAgriculture = ENABLE_MYSTICAL_AGRICULTURE.get() && ModList.get().isLoaded("mysticalagriculture");
        enableMysticalAgradditions = ENABLE_MYSTICAL_AGRADDITIONS.get() && ModList.get().isLoaded("mysticalagradditions");
        enableFarmersDelight = ENABLE_FARMERS_DELIGHT.get() && ModList.get().isLoaded("farmersdelight");
        enableArsNouveau = ENABLE_ARS_NOUVEAU.get() && ModList.get().isLoaded("ars_nouveau");
        enableArsElemental = ENABLE_ARS_ELEMENTAL.get() && ModList.get().isLoaded("ars_elemental");
        enableSilentGear = ENABLE_SILENT_GEAR.get() && ModList.get().isLoaded("silentgear");
        enableJustDireThings = ENABLE_JUST_DIRE_THINGS.get() && ModList.get().isLoaded("justdirethings");
        enableImmersiveEngineering = ENABLE_IMMERSIVE_ENGINEERING.get() && ModList.get().isLoaded("immersiveengineering");
        enableEvilCraft = ENABLE_EVILCRAFT.get() && ModList.get().isLoaded("evilcraft");
        enableForbiddenArcanus = ENABLE_FORBIDDEN_ARCANUS.get() && ModList.get().isLoaded("forbidden_arcanus");
        enableIntegratedDynamics = ENABLE_INTEGRATED_DYNAMICS.get() && ModList.get().isLoaded("integrateddynamics");
        enableOccultism = ENABLE_OCCULTISM.get() && ModList.get().isLoaded("occultism");
        enablePamsCrops = ENABLE_PAMS_CROPS.get() && ModList.get().isLoaded("pamhc2crops");
        enablePamsTrees = ENABLE_PAMS_TREES.get() && ModList.get().isLoaded("pamhc2trees");
        enableCroptopia = ENABLE_CROPTOPIA.get() && ModList.get().isLoaded("croptopia");
        enableCobblemon = ENABLE_COBBLEMON.get() && ModList.get().isLoaded("cobblemon");
        enableActuallyAdditions = ENABLE_ACTUALLY_ADDITIONS.get() && ModList.get().isLoaded("actuallyadditions");
        LOGGER.info("AgriTech: Two configuration loaded");
        PlantablesConfig.loadConfig();
        logModCompatibility();
    }

    private static void logModCompatibility() {
        record Mod(boolean enabled, String modId, String label) {}
        LOGGER.info("Mod Compatibility Status:");
        for (Mod m : new Mod[]{
                new Mod(enableMysticalAgriculture,  "mysticalagriculture",  "Mystical Agriculture"),
                new Mod(enableMysticalAgradditions, "mysticalagradditions", "Mystical Agradditions"),
                new Mod(enableFarmersDelight,       "farmersdelight",       "Farmer's Delight"),
                new Mod(enableArsNouveau,           "ars_nouveau",          "Ars Nouveau"),
                new Mod(enableArsElemental,         "ars_elemental",        "Ars Elemental"),
                new Mod(enableSilentGear,           "silentgear",           "Silent Gear"),
                new Mod(enableJustDireThings,       "justdirethings",       "Just Dire Things"),
                new Mod(enableImmersiveEngineering, "immersiveengineering", "Immersive Engineering"),
                new Mod(enableEvilCraft,            "evilcraft",            "EvilCraft"),
                new Mod(enableForbiddenArcanus,     "forbidden_arcanus",    "Forbidden and Arcanus"),
                new Mod(enableIntegratedDynamics,   "integrateddynamics",   "Integrated Dynamics"),
                new Mod(enableOccultism,            "occultism",            "Occultism"),
                new Mod(enablePamsCrops,            "pamhc2crops",          "Pam's HarvestCraft - Crops"),
                new Mod(enablePamsTrees,            "pamhc2trees",          "Pam's HarvestCraft - Trees"),
                new Mod(enableCroptopia,            "croptopia",            "Croptopia"),
                new Mod(enableCobblemon,            "cobblemon",            "Cobblemon"),
                new Mod(enableActuallyAdditions,    "actuallyadditions",    "Actually Additions")
        }) {
            if (m.enabled() && ModList.get().isLoaded(m.modId())) {
                LOGGER.info("  - {}: ENABLED", m.label());
            }
        }
    }
}