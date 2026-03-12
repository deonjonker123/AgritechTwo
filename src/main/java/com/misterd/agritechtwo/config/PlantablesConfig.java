package com.misterd.agritechtwo.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.misterd.agritechtwo.Config;
import com.mojang.logging.LogUtils;
import net.neoforged.fml.loading.FMLPaths;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PlantablesConfig {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Map<String, CropInfo> crops = new HashMap<>();
    private static Map<String, TreeInfo> trees = new HashMap<>();
    private static Map<String, SoilInfo> soils = new HashMap<>();
    private static Map<String, FertilizerInfo> fertilizers = new HashMap<>();

    public static void loadConfig() {
        LOGGER.info("PlantablesConfig.loadConfig() invoked.");
        Path configPath = FMLPaths.CONFIGDIR.get().resolve("agritechtwo/plantables.json");
        if (!Files.exists(configPath)) {
            createDefaultConfig(configPath);
        }

        try {
            String jsonString = Files.readString(configPath);
            PlantablesConfigData configData = GSON.fromJson(jsonString, PlantablesConfigData.class);
            processConfig(configData);
        } catch (JsonSyntaxException | IOException e) {
            LOGGER.error("Failed to load plantables config file: {}", e.getMessage());
            LOGGER.info("Loading default plantables configuration instead");
            processConfig(getDefaultConfig());
        }

        PlantablesOverrideConfig.loadOverrides(crops, trees, soils, soils, fertilizers);
    }

    private static void createDefaultConfig(Path configPath) {
        try {
            Files.createDirectories(configPath.getParent());
            PlantablesConfigData defaultConfig = getDefaultConfig();
            Files.writeString(configPath, GSON.toJson(defaultConfig));
        } catch (IOException e) {
            LOGGER.error("Failed to create default config file: {}", e.getMessage());
        }
    }

    private static PlantablesConfigData getDefaultConfig() {
        LOGGER.info("Generating default plantables config.");
        PlantablesConfigData config = new PlantablesConfigData();

        List<CropEntry> defaultCrops = new ArrayList<>();
        addVanillaCrops(defaultCrops);
        if (Config.enableMysticalAgriculture) {
            LOGGER.info("Adding Mystical Agriculture crops to AgriTech Two config");
            addMysticalAgricultureCrops(defaultCrops);
        }
        if (Config.enableFarmersDelight) {
            LOGGER.info("Adding Farmer's Delight crops to AgriTech Two config");
            addFarmersDelightCrops(defaultCrops);
        }
        if (Config.enableArsNouveau) {
            LOGGER.info("Adding Ars Nouveau crops to AgriTech Two config");
            addArsNouveauCrops(defaultCrops);
        }
        if (Config.enableSilentGear) {
            LOGGER.info("Adding Silent Gear crops to AgriTech Two config");
            addSilentGearCrops(defaultCrops);
        }
        if (Config.enableImmersiveEngineering) {
            LOGGER.info("Adding Immersive Engineering Hemp Fiber to AgriTech Two config");
            addImmersiveEngineering(defaultCrops);
        }
        if (Config.enableOccultism) {
            LOGGER.info("Adding Occultism Crops to AgriTech Two config");
            addOccultimCrops(defaultCrops);
        }
        config.allowedCrops = defaultCrops;

        List<TreeEntry> defaultTrees = new ArrayList<>();
        addVanillaTrees(defaultTrees);
        if (Config.enableArsElemental) {
            LOGGER.info("Adding Ars Nouveau Archwood trees to AgriTech Two config");
            addArsElementalTrees(defaultTrees);
        }
        if (Config.enableArsNouveau) {
            LOGGER.info("Adding Ars Nouveau Archwood trees to AgriTech Two config");
            addArsNouveauTrees(defaultTrees);
        }
        if (Config.enableEvilCraft) {
            LOGGER.info("Adding Evilcraft trees to AgriTech Two config");
            addEvilCraftTrees(defaultTrees);
        }
        if (Config.enableForbiddenArcanus) {
            LOGGER.info("Adding Forbidden Arcanus trees to AgriTech Two config");
            addForbiddenArcanusTrees(defaultTrees);
        }
        if (Config.enableIntegratedDynamics) {
            LOGGER.info("Adding Menril trees to AgriTech Two config");
            addIntegratedDynamicsTrees(defaultTrees);
        }
        if (Config.enableOccultism) {
            LOGGER.info("Adding Occultism trees to AgriTech Two config");
            addOccultismTrees(defaultTrees);
        }
        config.allowedTrees = defaultTrees;

        List<SoilEntry> defaultSoils = new ArrayList<>();
        addVanillaSoils(defaultSoils);
        if (Config.enableMysticalAgriculture) {
            LOGGER.info("Adding Mystical Agriculture soils to AgriTech Two config");
            addMysticalAgricultureSoils(defaultSoils);
        }
        if (Config.enableFarmersDelight) {
            LOGGER.info("Adding Farmer's Delight soils to AgriTech Two config");
            addFarmersDelightSoils(defaultSoils);
        }
        if (Config.enableAgritechEvolved) {
            LOGGER.info("Adding Agritech Two soils to AgriTech Two config");
            addAgritechEvolvedSoils(defaultSoils);
        }
        if (Config.enableJustDireThings) {
            LOGGER.info("Adding Just Dire Things soils to AgriTech Two config");
            addJustDireThingsSoils(defaultSoils);
        }
        config.allowedSoils = defaultSoils;

        List<FertilizerEntry> defaultFertilizers = new ArrayList<>();
        addVanillaFertilizers(defaultFertilizers);
        if (Config.enableImmersiveEngineering) {
            LOGGER.info("Adding Immersive Engineering fertilizer to AgriTech Two config");
            addImmersiveEngineeringFertilizers(defaultFertilizers);
        }
        if (Config.enableForbiddenArcanus) {
            LOGGER.info("Adding Forbidden Arcanus fertilizer to AgriTech Two config");
            addForbiddenArcanusFertilizers(defaultFertilizers);
        }
        if (Config.enableMysticalAgriculture) {
            LOGGER.info("Adding Mystical Agriculture fertilizer to AgriTech Two config");
            addMysticalAgricultureFertilizers(defaultFertilizers);
        }
        if (Config.enableAgritechEvolved) {
            LOGGER.info("Adding Agritech Two biomass to AgriTech Two config");
            addAgritechEvolvedFertilizer(defaultFertilizers);
        }
        config.allowedFertilizers = defaultFertilizers;

        return config;
    }

    // Standard soils accepted by most farmland crops
    private static final List<String> STANDARD_FARMLAND_SOILS = List.of(
            "minecraft:farmland",
            "mysticalagriculture:inferium_farmland",
            "mysticalagriculture:prudentium_farmland",
            "mysticalagriculture:tertium_farmland",
            "mysticalagriculture:imperium_farmland",
            "mysticalagriculture:supremium_farmland",
            "mysticalagradditions:insanium_farmland",
            "justdirethings:goosoil_tier1",
            "justdirethings:goosoil_tier2",
            "justdirethings:goosoil_tier3",
            "justdirethings:goosoil_tier4",
            "farmersdelight:rich_soil_farmland"
    );

    // Standard soils accepted by most ground-planted crops (flowers, berries, etc.)
    private static final List<String> STANDARD_GROUND_SOILS = List.of(
            "minecraft:farmland",
            "minecraft:dirt",
            "minecraft:grass_block",
            "minecraft:rooted_dirt",
            "minecraft:coarse_dirt",
            "minecraft:podzol",
            "minecraft:mycelium",
            "minecraft:mud",
            "minecraft:moss_block",
            "minecraft:muddy_mangrove_roots",
            "mysticalagriculture:inferium_farmland",
            "mysticalagriculture:prudentium_farmland",
            "mysticalagriculture:tertium_farmland",
            "mysticalagriculture:imperium_farmland",
            "mysticalagriculture:supremium_farmland",
            "mysticalagradditions:insanium_farmland",
            "justdirethings:goosoil_tier1",
            "justdirethings:goosoil_tier2",
            "justdirethings:goosoil_tier3",
            "justdirethings:goosoil_tier4",
            "farmersdelight:rich_soil_farmland"
    );

    private static CropEntry makeCrop(String seed, List<String> validSoils, DropEntry... drops) {
        CropEntry crop = new CropEntry();
        crop.seed = seed;
        crop.validSoils = new ArrayList<>(validSoils);
        crop.drops = new ArrayList<>(List.of(drops));
        return crop;
    }

    private static DropEntry makeDrop(String item, int min, int max, float chance) {
        DropEntry drop = new DropEntry();
        drop.item = item;
        drop.count = new CountRange(min, max);
        drop.chance = chance;
        return drop;
    }

    private static DropEntry makeDrop(String item, int min, int max) {
        return makeDrop(item, min, max, 1.0F);
    }

    private static void addVanillaCrops(List<CropEntry> crops) {
        crops.add(makeCrop("minecraft:wheat_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("minecraft:wheat", 1, 1),
                makeDrop("minecraft:wheat_seeds", 1, 2, 0.5F)));

        crops.add(makeCrop("minecraft:beetroot_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("minecraft:beetroot", 1, 1),
                makeDrop("minecraft:beetroot_seeds", 1, 2, 0.5F)));

        crops.add(makeCrop("minecraft:carrot", STANDARD_FARMLAND_SOILS,
                makeDrop("minecraft:carrot", 2, 5)));

        crops.add(makeCrop("minecraft:potato", STANDARD_FARMLAND_SOILS,
                makeDrop("minecraft:potato", 2, 5),
                makeDrop("minecraft:poisonous_potato", 1, 1, 0.02F)));

        crops.add(makeCrop("minecraft:melon_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("minecraft:melon_slice", 3, 7)));

        crops.add(makeCrop("minecraft:pumpkin_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("minecraft:pumpkin", 1, 1)));

        crops.add(makeCrop("minecraft:sugar_cane",
                List.of("minecraft:dirt", "minecraft:grass_block", "minecraft:sand", "minecraft:red_sand"),
                makeDrop("minecraft:sugar_cane", 1, 3)));

        crops.add(makeCrop("minecraft:cactus",
                List.of("minecraft:sand", "minecraft:red_sand"),
                makeDrop("minecraft:cactus", 1, 3)));

        crops.add(makeCrop("minecraft:bamboo",
                List.of("minecraft:dirt", "minecraft:grass_block", "minecraft:rooted_dirt", "minecraft:coarse_dirt",
                        "minecraft:podzol", "minecraft:mycelium", "minecraft:mud", "minecraft:moss_block",
                        "minecraft:muddy_mangrove_roots", 
                        "farmersdelight:rich_soil", "farmersdelight:organic_compost"),
                makeDrop("minecraft:bamboo", 2, 4)));

        crops.add(makeCrop("minecraft:sweet_berries",
                List.of("minecraft:farmland", "minecraft:dirt", "minecraft:grass_block", "minecraft:rooted_dirt",
                        "minecraft:coarse_dirt", "minecraft:podzol", "minecraft:mycelium", "minecraft:mud",
                        "minecraft:moss_block", "minecraft:muddy_mangrove_roots",
                        "mysticalagriculture:inferium_farmland", "mysticalagriculture:prudentium_farmland",
                        "mysticalagriculture:tertium_farmland", "mysticalagriculture:imperium_farmland",
                        "mysticalagriculture:supremium_farmland", "mysticalagradditions:insanium_farmland",
                         
                        "justdirethings:goosoil_tier1", "justdirethings:goosoil_tier2",
                        "justdirethings:goosoil_tier3", "justdirethings:goosoil_tier4",
                        "farmersdelight:rich_soil_farmland", "farmersdelight:rich_soil", "farmersdelight:organic_compost"),
                makeDrop("minecraft:sweet_berries", 2, 4)));

        crops.add(makeCrop("minecraft:glow_berries",
                List.of("minecraft:moss_block"),
                makeDrop("minecraft:glow_berries", 2, 4)));

        crops.add(makeCrop("minecraft:nether_wart",
                List.of("minecraft:soul_sand"),
                makeDrop("minecraft:nether_wart", 1, 3)));

        crops.add(makeCrop("minecraft:chorus_flower",
                List.of("minecraft:end_stone"),
                makeDrop("minecraft:chorus_fruit", 1, 3),
                makeDrop("minecraft:chorus_flower", 1, 1, 0.02F)));

        crops.add(makeCrop("minecraft:kelp",
                List.of("minecraft:mud"),
                makeDrop("minecraft:kelp", 1, 2)));

        crops.add(makeCrop("minecraft:brown_mushroom",
                List.of("minecraft:mycelium", "minecraft:podzol", 
                        "farmersdelight:rich_soil", "farmersdelight:organic_compost"),
                makeDrop("minecraft:brown_mushroom", 1, 1)));

        crops.add(makeCrop("minecraft:red_mushroom",
                List.of("minecraft:mycelium", "minecraft:podzol", 
                        "farmersdelight:rich_soil", "farmersdelight:organic_compost"),
                makeDrop("minecraft:red_mushroom", 1, 1)));

        crops.add(makeCrop("minecraft:cocoa_beans",
                List.of("minecraft:jungle_log", "minecraft:jungle_wood",
                        "minecraft:stripped_jungle_log", "minecraft:stripped_jungle_wood"),
                makeDrop("minecraft:cocoa_beans", 1, 3)));

        crops.add(makeCrop("minecraft:pitcher_pod", STANDARD_FARMLAND_SOILS,
                makeDrop("minecraft:pitcher_plant", 1, 1)));

        crops.add(makeCrop("minecraft:torchflower_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("minecraft:torchflower", 1, 1)));

        // Flowers — all use STANDARD_GROUND_SOILS and drop 1 of themselves
        for (String flower : new String[]{
                "minecraft:allium", "minecraft:azure_bluet", "minecraft:blue_orchid",
                "minecraft:cornflower", "minecraft:dandelion", "minecraft:lily_of_the_valley",
                "minecraft:oxeye_daisy", "minecraft:poppy", "minecraft:red_tulip",
                "minecraft:orange_tulip", "minecraft:white_tulip", "minecraft:pink_tulip",
                "minecraft:wither_rose", "minecraft:lilac", "minecraft:peony",
                "minecraft:rose_bush", "minecraft:sunflower"
        }) {
            crops.add(makeCrop(flower, STANDARD_GROUND_SOILS, makeDrop(flower, 1, 1)));
        }
    }

    private static final List<String> STANDARD_TREE_SOILS = List.of(
            "minecraft:dirt", "minecraft:grass_block", "minecraft:podzol",
            "minecraft:coarse_dirt", "minecraft:mycelium"
    );

    private static TreeEntry makeTree(String sapling, List<String> validSoils, DropEntry... drops) {
        TreeEntry tree = new TreeEntry();
        tree.sapling = sapling;
        tree.validSoils = new ArrayList<>(validSoils);
        tree.drops = new ArrayList<>(List.of(drops));
        return tree;
    }

    private static void addMysticalAgricultureCrops(List<CropEntry> crops) {
        List<String> tier1Soils = List.of(
                "minecraft:farmland",
                "mysticalagriculture:inferium_farmland", "mysticalagriculture:prudentium_farmland",
                "mysticalagriculture:tertium_farmland", "mysticalagriculture:imperium_farmland",
                "mysticalagriculture:supremium_farmland", "mysticalagradditions:insanium_farmland",
                 
                "justdirethings:goosoil_tier1", "justdirethings:goosoil_tier2",
                "justdirethings:goosoil_tier3", "justdirethings:goosoil_tier4",
                "farmersdelight:rich_soil_farmland");

        for (String seed : new String[]{
                "mysticalagriculture:air_seeds", "mysticalagriculture:earth_seeds",
                "mysticalagriculture:water_seeds", "mysticalagriculture:fire_seeds",
                "mysticalagriculture:inferium_seeds", "mysticalagriculture:stone_seeds",
                "mysticalagriculture:dirt_seeds", "mysticalagriculture:wood_seeds",
                "mysticalagriculture:ice_seeds", "mysticalagriculture:deepslate_seeds"
        }) addMysticalAgricultureCrop(crops, seed, tier1Soils);

        List<String> tier2Soils = List.of(
                "mysticalagriculture:prudentium_farmland", "mysticalagriculture:tertium_farmland",
                "mysticalagriculture:imperium_farmland", "mysticalagriculture:supremium_farmland",
                "mysticalagradditions:insanium_farmland",  
                "justdirethings:goosoil_tier1", "justdirethings:goosoil_tier2",
                "justdirethings:goosoil_tier3", "justdirethings:goosoil_tier4");

        for (String seed : new String[]{
                "mysticalagriculture:nature_seeds", "mysticalagriculture:dye_seeds",
                "mysticalagriculture:nether_seeds", "mysticalagriculture:coal_seeds",
                "mysticalagriculture:coral_seeds", "mysticalagriculture:honey_seeds",
                "mysticalagriculture:amethyst_seeds", "mysticalagriculture:pig_seeds",
                "mysticalagriculture:chicken_seeds", "mysticalagriculture:cow_seeds",
                "mysticalagriculture:sheep_seeds", "mysticalagriculture:squid_seeds",
                "mysticalagriculture:fish_seeds", "mysticalagriculture:slime_seeds",
                "mysticalagriculture:turtle_seeds", "mysticalagriculture:armadillo_seeds",
                "mysticalagriculture:rubber_seeds", "mysticalagriculture:silicon_seeds",
                "mysticalagriculture:sulfur_seeds", "mysticalagriculture:aluminum_seeds",
                "mysticalagriculture:saltpeter_seeds", "mysticalagriculture:apatite_seeds",
                "mysticalagriculture:grains_of_infinity_seeds", "mysticalagriculture:mystical_flower_seeds",
                "mysticalagriculture:marble_seeds", "mysticalagriculture:limestone_seeds",
                "mysticalagriculture:basalt_seeds", "mysticalagriculture:menril_seeds"
        }) addMysticalAgricultureCrop(crops, seed, tier2Soils);

        List<String> tier3Soils = List.of(
                "mysticalagriculture:tertium_farmland", "mysticalagriculture:imperium_farmland",
                "mysticalagriculture:supremium_farmland", "mysticalagradditions:insanium_farmland",
                 
                "justdirethings:goosoil_tier2", "justdirethings:goosoil_tier3", "justdirethings:goosoil_tier4");

        for (String seed : new String[]{
                "mysticalagriculture:iron_seeds", "mysticalagriculture:copper_seeds",
                "mysticalagriculture:nether_quartz_seeds", "mysticalagriculture:glowstone_seeds",
                "mysticalagriculture:redstone_seeds", "mysticalagriculture:obsidian_seeds",
                "mysticalagriculture:prismarine_seeds", "mysticalagriculture:zombie_seeds",
                "mysticalagriculture:skeleton_seeds", "mysticalagriculture:creeper_seeds",
                "mysticalagriculture:spider_seeds", "mysticalagriculture:rabbit_seeds",
                "mysticalagriculture:tin_seeds", "mysticalagriculture:bronze_seeds",
                "mysticalagriculture:zinc_seeds", "mysticalagriculture:brass_seeds",
                "mysticalagriculture:silver_seeds", "mysticalagriculture:lead_seeds",
                "mysticalagriculture:graphite_seeds", "mysticalagriculture:blizz_seeds",
                "mysticalagriculture:blitz_seeds", "mysticalagriculture:basalz_seeds",
                "mysticalagriculture:amethyst_bronze_seeds", "mysticalagriculture:slimesteel_seeds",
                "mysticalagriculture:pig_iron_seeds", "mysticalagriculture:copper_alloy_seeds",
                "mysticalagriculture:redstone_alloy_seeds", "mysticalagriculture:conductive_alloy_seeds",
                "mysticalagriculture:steeleaf_seeds", "mysticalagriculture:ironwood_seeds",
                "mysticalagriculture:sky_stone_seeds", "mysticalagriculture:certus_quartz_seeds",
                "mysticalagriculture:quartz_enriched_iron_seeds", "mysticalagriculture:manasteel_seeds",
                "mysticalagriculture:aquamarine_seeds"
        }) addMysticalAgricultureCrop(crops, seed, tier3Soils);

        List<String> tier4Soils = List.of(
                "mysticalagriculture:imperium_farmland", "mysticalagriculture:supremium_farmland",
                "mysticalagradditions:insanium_farmland",  
                "justdirethings:goosoil_tier2", "justdirethings:goosoil_tier3", "justdirethings:goosoil_tier4");

        for (String seed : new String[]{
                "mysticalagriculture:gold_seeds", "mysticalagriculture:lapis_lazuli_seeds",
                "mysticalagriculture:end_seeds", "mysticalagriculture:experience_seeds",
                "mysticalagriculture:breeze_seeds", "mysticalagriculture:blaze_seeds",
                "mysticalagriculture:ghast_seeds", "mysticalagriculture:enderman_seeds",
                "mysticalagriculture:steel_seeds", "mysticalagriculture:nickel_seeds",
                "mysticalagriculture:constantan_seeds", "mysticalagriculture:electrum_seeds",
                "mysticalagriculture:invar_seeds", "mysticalagriculture:uranium_seeds",
                "mysticalagriculture:ruby_seeds", "mysticalagriculture:sapphire_seeds",
                "mysticalagriculture:peridot_seeds", "mysticalagriculture:soulium_seeds",
                "mysticalagriculture:signalum_seeds", "mysticalagriculture:lumium_seeds",
                "mysticalagriculture:flux_infused_ingot_seeds", "mysticalagriculture:hop_graphite_seeds",
                "mysticalagriculture:cobalt_seeds", "mysticalagriculture:rose_gold_seeds",
                "mysticalagriculture:soularium_seeds", "mysticalagriculture:dark_steel_seeds",
                "mysticalagriculture:pulsating_alloy_seeds", "mysticalagriculture:energetic_alloy_seeds",
                "mysticalagriculture:elementium_seeds", "mysticalagriculture:osmium_seeds",
                "mysticalagriculture:fluorite_seeds", "mysticalagriculture:refined_glowstone_seeds",
                "mysticalagriculture:refined_obsidian_seeds", "mysticalagriculture:knightmetal_seeds",
                "mysticalagriculture:fiery_ingot_seeds", "mysticalagriculture:compressed_iron_seeds",
                "mysticalagriculture:starmetal_seeds", "mysticalagriculture:fluix_seeds",
                "mysticalagriculture:energized_steel_seeds", "mysticalagriculture:blazing_crystal_seeds"
        }) addMysticalAgricultureCrop(crops, seed, tier4Soils);

        List<String> tier5Soils = List.of(
                "mysticalagriculture:supremium_farmland", "mysticalagradditions:insanium_farmland",
                 "justdirethings:goosoil_tier4");

        for (String seed : new String[]{
                "mysticalagriculture:diamond_seeds", "mysticalagriculture:emerald_seeds",
                "mysticalagriculture:netherite_seeds", "mysticalagriculture:wither_skeleton_seeds",
                "mysticalagriculture:platinum_seeds", "mysticalagriculture:iridium_seeds",
                "mysticalagriculture:enderium_seeds", "mysticalagriculture:flux_infused_gem_seeds",
                "mysticalagriculture:manyullyn_seeds", "mysticalagriculture:queens_slime_seeds",
                "mysticalagriculture:hepatizon_seeds", "mysticalagriculture:vibrant_alloy_seeds",
                "mysticalagriculture:end_steel_seeds", "mysticalagriculture:terrasteel_seeds",
                "mysticalagriculture:rock_crystal_seeds", "mysticalagriculture:draconium_seeds",
                "mysticalagriculture:yellorium_seeds", "mysticalagriculture:cyanite_seeds",
                "mysticalagriculture:niotic_crystal_seeds", "mysticalagriculture:spirited_crystal_seeds",
                "mysticalagriculture:uraninite_seeds"
        }) addMysticalAgricultureCrop(crops, seed, tier5Soils);

        if (Config.enableMysticalAgradditions) {
            addSpecialCruxCrop(crops, "mysticalagriculture:nether_star_seeds",       "mysticalagradditions:nether_star_crux");
            addSpecialCruxCrop(crops, "mysticalagriculture:dragon_egg_seeds",        "mysticalagradditions:dragon_egg_crux");
            addSpecialCruxCrop(crops, "mysticalagriculture:gaia_spirit_seeds",       "mysticalagradditions:gaia_spirit_crux");
            addSpecialCruxCrop(crops, "mysticalagriculture:awakened_draconium_seeds","mysticalagradditions:awakened_draconium_crux");
            addSpecialCruxCrop(crops, "mysticalagriculture:neutronium_seeds",        "mysticalagradditions:neutronium_crux");
            addSpecialCruxCrop(crops, "mysticalagriculture:nitro_crystal_seeds",     "mysticalagradditions:nitro_crystal_crux");
        }
    }

    private static void addSpecialCruxCrop(List<CropEntry> crops, String seedId, String cruxId) {
        String essence = seedId.replace("_seeds", "_essence");
        crops.add(makeCrop(seedId, List.of(cruxId), makeDrop(essence, 1, 1, 1.0F)));
    }

    private static void addMysticalAgricultureCrop(List<CropEntry> crops, String seedId, List<String> validSoils) {
        String essence = seedId.replace("_seeds", "_essence");
        crops.add(makeCrop(seedId, validSoils,
                makeDrop(essence, 1, 1, 1.0F),
                makeDrop(seedId, 1, 1, 0.2F)));
    }

    private static void addFarmersDelightCrops(List<CropEntry> crops) {
        crops.add(makeCrop("farmersdelight:cabbage_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("farmersdelight:cabbage", 1, 1, 1.0F),
                makeDrop("farmersdelight:cabbage_seeds", 1, 2, 1.0F)));

        crops.add(makeCrop("farmersdelight:tomato_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("farmersdelight:tomato", 1, 2, 1.0F)));

        crops.add(makeCrop("farmersdelight:onion", STANDARD_FARMLAND_SOILS,
                makeDrop("farmersdelight:onion", 1, 3, 1.0F)));
    }

    private static void addOccultimCrops(List<CropEntry> crops) {
        crops.add(makeCrop("occultism:datura_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("occultism:datura", 1, 1, 1.0F),
                makeDrop("occultism:datura_seeds", 1, 1, 0.5F)));
    }

    private static void addImmersiveEngineering(List<CropEntry> crops) {
        crops.add(makeCrop("immersiveengineering:seed", STANDARD_FARMLAND_SOILS,
                makeDrop("immersiveengineering:hemp_fiber", 4, 8, 1.0F),
                makeDrop("immersiveengineering:seed", 1, 1, 0.5F)));
    }

    private static void addArsNouveauCrops(List<CropEntry> crops) {
        crops.add(makeCrop("ars_nouveau:magebloom_crop", STANDARD_FARMLAND_SOILS,
                makeDrop("ars_nouveau:magebloom", 1, 1, 1.0F)));

        crops.add(makeCrop("ars_nouveau:sourceberry_bush",
                List.of("minecraft:farmland", "minecraft:dirt", "minecraft:grass_block",
                        "minecraft:rooted_dirt", "minecraft:coarse_dirt", "minecraft:podzol",
                        "minecraft:mycelium", "minecraft:mud", "minecraft:moss_block",
                        "minecraft:muddy_mangrove_roots",
                        "mysticalagriculture:inferium_farmland", "mysticalagriculture:prudentium_farmland",
                        "mysticalagriculture:tertium_farmland", "mysticalagriculture:imperium_farmland",
                        "mysticalagriculture:supremium_farmland", "mysticalagradditions:insanium_farmland",
                        "justdirethings:goosoil_tier1", "justdirethings:goosoil_tier2",
                        "justdirethings:goosoil_tier3", "justdirethings:goosoil_tier4",
                        "farmersdelight:rich_soil_farmland", "farmersdelight:organic_compost",
                        "farmersdelight:rich_soil"),
                makeDrop("ars_nouveau:sourceberry_bush", 2, 4, 1.0F)));
    }

    private static void addSilentGearCrops(List<CropEntry> crops) {
        crops.add(makeCrop("silentgear:fluffy_seeds", STANDARD_FARMLAND_SOILS,
                makeDrop("silentgear:fluffy_puff", 1, 4, 1.0F),
                makeDrop("silentgear:fluffy_seeds", 1, 1, 1.0F)));

        crops.add(makeCrop("silentgear:flax_seeds",
                List.of("minecraft:farmland",
                        "mysticalagriculture:inferium_farmland", "mysticalagriculture:prudentium_farmland",
                        "mysticalagriculture:tertium_farmland", "mysticalagriculture:imperium_farmland",
                        "mysticalagriculture:supremium_farmland", "mysticalagradditions:insanium_farmland",
                        "justdirethings:goosoil_tier1", "justdirethings:goosoil_tier2",
                        "justdirethings:goosoil_tier3", "justdirethings:goosoil_tier4",
                        "farmersdelight:rich_soil_farmland"),
                makeDrop("silentgear:flax_fiber", 1, 4, 1.0F),
                makeDrop("silentgear:flax_seeds", 1, 1, 0.2F),
                makeDrop("silentgear:flax_flowers", 1, 1, 0.2F)));
    }

    private static void addVanillaTrees(List<TreeEntry> trees) {
        trees.add(makeTree("minecraft:oak_sapling", STANDARD_TREE_SOILS,
                makeDrop("minecraft:oak_log", 2, 6),
                makeDrop("minecraft:oak_sapling", 1, 2, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F),
                makeDrop("minecraft:apple", 1, 1, 0.4F)));

        trees.add(makeTree("minecraft:birch_sapling", STANDARD_TREE_SOILS,
                makeDrop("minecraft:birch_log", 2, 6),
                makeDrop("minecraft:birch_sapling", 1, 2, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F)));

        trees.add(makeTree("minecraft:spruce_sapling", STANDARD_TREE_SOILS,
                makeDrop("minecraft:spruce_log", 4, 8),
                makeDrop("minecraft:spruce_sapling", 1, 2, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F)));

        trees.add(makeTree("minecraft:jungle_sapling", STANDARD_TREE_SOILS,
                makeDrop("minecraft:jungle_log", 2, 6),
                makeDrop("minecraft:jungle_sapling", 1, 2, 0.4F),
                makeDrop("minecraft:stick", 1, 2, 0.5F),
                makeDrop("minecraft:cocoa_beans", 1, 2, 0.2F)));

        trees.add(makeTree("minecraft:acacia_sapling", STANDARD_TREE_SOILS,
                makeDrop("minecraft:acacia_log", 2, 6),
                makeDrop("minecraft:acacia_sapling", 1, 2, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F)));

        trees.add(makeTree("minecraft:dark_oak_sapling", STANDARD_TREE_SOILS,
                makeDrop("minecraft:dark_oak_log", 4, 8),
                makeDrop("minecraft:dark_oak_sapling", 1, 2, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F),
                makeDrop("minecraft:apple", 1, 2, 0.3F)));

        trees.add(makeTree("minecraft:mangrove_propagule",
                List.of("minecraft:mud", "minecraft:muddy_mangrove_roots", "minecraft:dirt",
                        "minecraft:coarse_dirt", "minecraft:grass_block", "minecraft:podzol",
                        "minecraft:mycelium"),
                makeDrop("minecraft:mangrove_log", 2, 6),
                makeDrop("minecraft:mangrove_propagule", 1, 2, 0.5F),
                makeDrop("minecraft:mangrove_roots", 1, 3, 0.3F),
                makeDrop("minecraft:mangrove_roots", 1, 2, 0.5F)));

        trees.add(makeTree("minecraft:cherry_sapling", STANDARD_TREE_SOILS,
                makeDrop("minecraft:cherry_log", 2, 6),
                makeDrop("minecraft:cherry_sapling", 1, 2, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F)));

        List<String> azaleaSoils = List.of("minecraft:dirt", "minecraft:grass_block", "minecraft:podzol",
                "minecraft:coarse_dirt", "minecraft:rooted_dirt", "minecraft:moss_block",
                "minecraft:mycelium");

        trees.add(makeTree("minecraft:azalea", azaleaSoils,
                makeDrop("minecraft:oak_log", 2, 6),
                makeDrop("minecraft:azalea", 1, 1, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F),
                makeDrop("minecraft:moss_block", 1, 2, 0.2F)));

        trees.add(makeTree("minecraft:flowering_azalea", azaleaSoils,
                makeDrop("minecraft:oak_log", 2, 6),
                makeDrop("minecraft:flowering_azalea", 1, 1, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F),
                makeDrop("minecraft:moss_block", 1, 1, 0.2F)));

        List<String> fungalSoils = List.of("minecraft:crimson_nylium", "minecraft:warped_nylium");

        trees.add(makeTree("minecraft:crimson_fungus", fungalSoils,
                makeDrop("minecraft:crimson_stem", 2, 6),
                makeDrop("minecraft:nether_wart_block", 4, 8),
                makeDrop("minecraft:weeping_vines", 1, 2),
                makeDrop("minecraft:shroomlight", 2, 4)));

        trees.add(makeTree("minecraft:warped_fungus", fungalSoils,
                makeDrop("minecraft:warped_stem", 2, 6),
                makeDrop("minecraft:warped_wart_block", 4, 8),
                makeDrop("minecraft:twisting_vines", 1, 2),
                makeDrop("minecraft:shroomlight", 2, 4)));
    }

    private static void addArsElementalTrees(List<TreeEntry> trees) {
        trees.add(makeTree("ars_elemental:yellow_archwood_sapling", STANDARD_TREE_SOILS,
                makeDrop("ars_elemental:yellow_archwood_log", 4, 8),
                makeDrop("ars_elemental:yellow_archwood_sapling", 1, 1, 0.3F),
                makeDrop("ars_elemental:flashpine_pod", 1, 1, 0.2F),
                makeDrop("minecraft:stick", 1, 2, 0.5F)));
    }

    private static void addArsNouveauTrees(List<TreeEntry> trees) {
        record ArchwoodTree(String color, String pod) {}
        for (ArchwoodTree t : new ArchwoodTree[]{
                new ArchwoodTree("blue",   "frostaya"),
                new ArchwoodTree("red",    "bombegranate"),
                new ArchwoodTree("purple", "bastion"),
                new ArchwoodTree("green",  "mendosteen")
        }) {
            trees.add(makeTree("ars_nouveau:" + t.color() + "_archwood_sapling", STANDARD_TREE_SOILS,
                    makeDrop("ars_nouveau:" + t.color() + "_archwood_log", 4, 8),
                    makeDrop("ars_nouveau:" + t.color() + "_archwood_sapling", 1, 1, 0.3F),
                    makeDrop("ars_nouveau:" + t.pod() + "_pod", 1, 1, 0.2F),
                    makeDrop("minecraft:stick", 1, 2, 0.5F)));
        }
    }

    private static void addEvilCraftTrees(List<TreeEntry> trees) {
        trees.add(makeTree("evilcraft:undead_sapling", STANDARD_TREE_SOILS,
                makeDrop("evilcraft:undead_log", 2, 6),
                makeDrop("minecraft:dead_bush", 1, 2)));
    }

    private static void addForbiddenArcanusTrees(List<TreeEntry> trees) {
        trees.add(makeTree("forbidden_arcanus:aurum_sapling", STANDARD_TREE_SOILS,
                makeDrop("forbidden_arcanus:aurum_log", 2, 6),
                makeDrop("forbidden_arcanus:aurum_sapling", 1, 3),
                makeDrop("minecraft:stick", 1, 2, 0.5F),
                makeDrop("minecraft:gold_nugget", 1, 2, 0.1F)));

        trees.add(makeTree("forbidden_arcanus:growing_edelwood", STANDARD_TREE_SOILS,
                makeDrop("forbidden_arcanus:edelwood_log", 2, 6),
                makeDrop("forbidden_arcanus:carved_edelwood_log", 1, 1, 0.4F)));
    }

    private static void addIntegratedDynamicsTrees(List<TreeEntry> trees) {
        trees.add(makeTree("integrateddynamics:menril_sapling", STANDARD_TREE_SOILS,
                makeDrop("integrateddynamics:menril_log", 2, 6),
                makeDrop("integrateddynamics:menril_sapling", 1, 2),
                makeDrop("integrateddynamics:crystalized_menril_chunk", 1, 2, 0.5F),
                makeDrop("integrateddynamics:menril_berries", 2, 4, 0.5F),
                makeDrop("minecraft:stick", 1, 2, 0.5F)));
    }

    private static void addOccultismTrees(List<TreeEntry> trees) {
        trees.add(makeTree("occultism:otherworld_sapling", STANDARD_TREE_SOILS,
                makeDrop("occultism:otherworld_log", 2, 6),
                makeDrop("occultism:otherworld_sapling", 1, 3)));
    }

    private static void addVanillaSoils(List<SoilEntry> soils) {
        record S(String id, float mod) {}
        for (S s : new S[]{
                new S("minecraft:dirt",                   0.475F),
                new S("minecraft:coarse_dirt",            0.475F),
                new S("minecraft:podzol",                 0.475F),
                new S("minecraft:mycelium",               0.475F),
                new S("minecraft:mud",                    0.5F),
                new S("minecraft:muddy_mangrove_roots",   0.5F),
                new S("minecraft:rooted_dirt",            0.475F),
                new S("minecraft:moss_block",             0.475F),
                new S("minecraft:farmland",               0.5F),
                new S("minecraft:sand",                   0.5F),
                new S("minecraft:red_sand",               0.5F),
                new S("minecraft:grass_block",            0.475F),
                new S("minecraft:soul_sand",              0.5F),
                new S("minecraft:end_stone",              0.5F),
                new S("minecraft:jungle_log",             0.5F),
                new S("minecraft:jungle_wood",            0.5F),
                new S("minecraft:stripped_jungle_log",    0.5F),
                new S("minecraft:stripped_jungle_wood",   0.5F),
                new S("minecraft:crimson_nylium",         0.6F),
                new S("minecraft:warped_nylium",          0.6F)
        }) {
            SoilEntry e = new SoilEntry();
            e.soil = s.id();
            e.growthModifier = s.mod();
            soils.add(e);
        }
    }

    private static void addAgritechEvolvedSoils(List<SoilEntry> soils) {
        for (String[] s : new String[][]{
                { "2.0"},
                {            "1.5"}
        }) {
            SoilEntry e = new SoilEntry();
            e.soil = s[0];
            e.growthModifier = Float.parseFloat(s[1]);
            soils.add(e);
        }
    }

    private static void addFarmersDelightSoils(List<SoilEntry> soils) {
        for (String id : new String[]{
                "farmersdelight:rich_soil",
                "farmersdelight:rich_soil_farmland",
                "farmersdelight:organic_compost"
        }) {
            SoilEntry e = new SoilEntry();
            e.soil = id;
            e.growthModifier = 0.525F;
            soils.add(e);
        }
    }

    private static void addMysticalAgricultureSoils(List<SoilEntry> soils) {
        record S(String id, float mod) {}
        for (S s : new S[]{
                new S("mysticalagriculture:inferium_farmland",  0.55F),
                new S("mysticalagriculture:prudentium_farmland",0.625F),
                new S("mysticalagriculture:tertium_farmland",   0.75F),
                new S("mysticalagriculture:imperium_farmland",  0.875F),
                new S("mysticalagriculture:supremium_farmland", 1.0F)
        }) {
            SoilEntry e = new SoilEntry();
            e.soil = s.id();
            e.growthModifier = s.mod();
            soils.add(e);
        }

        if (Config.enableMysticalAgradditions) {
            for (String id : new String[]{
                    "mysticalagradditions:insanium_farmland",
                    "mysticalagradditions:nether_star_crux",
                    "mysticalagradditions:dragon_egg_crux",
                    "mysticalagradditions:awakened_draconium_crux",
                    "mysticalagradditions:neutronium_crux",
                    "mysticalagradditions:nitro_crystal_crux"
            }) {
                SoilEntry e = new SoilEntry();
                e.soil = id;
                e.growthModifier = 1.75F;
                soils.add(e);
            }
        }
    }

    private static void addJustDireThingsSoils(List<SoilEntry> soils) {
        record S(String id, float mod) {}
        for (S s : new S[]{
                new S("justdirethings:goosoil_tier1", 0.575F),
                new S("justdirethings:goosoil_tier2", 0.75F),
                new S("justdirethings:goosoil_tier3", 1.0F),
                new S("justdirethings:goosoil_tier4", 1.5F)
        }) {
            SoilEntry e = new SoilEntry();
            e.soil = s.id();
            e.growthModifier = s.mod();
            soils.add(e);
        }
    }

    private static void addVanillaFertilizers(List<FertilizerEntry> fertilizers) {
        FertilizerEntry boneMeal = new FertilizerEntry();
        boneMeal.item = "minecraft:bone_meal";
        boneMeal.speedMultiplier = (float) Config.getFertilizerBoneMealSpeedMultiplier();
        boneMeal.yieldMultiplier = (float) Config.getFertilizerBoneMealYieldMultiplier();
        fertilizers.add(boneMeal);
    }

    private static void addAgritechEvolvedFertilizer(List<FertilizerEntry> fertilizers) {
        FertilizerEntry biomass = new FertilizerEntry();
        biomass.item = "agritechtwo:biomass";
        biomass.speedMultiplier = (float) Config.getFertilizerBiomassSpeedMultiplier();
        biomass.yieldMultiplier = (float) Config.getFertilizerBiomassYieldMultiplier();
        fertilizers.add(biomass);

        FertilizerEntry compacted = new FertilizerEntry();
        compacted.item = "agritechtwo:compacted_biomass";
        compacted.speedMultiplier = (float) Config.getFertilizerCompactedBiomassSpeedMultiplier();
        compacted.yieldMultiplier = (float) Config.getFertilizerCompactedBiomassYieldMultiplier();
        fertilizers.add(compacted);
    }

    private static void addImmersiveEngineeringFertilizers(List<FertilizerEntry> fertilizers) {
        FertilizerEntry f = new FertilizerEntry();
        f.item = "immersiveengineering:fertilizer";
        f.speedMultiplier = (float) Config.getFertilizerFertilizedEssenceSpeedMultiplier();
        f.yieldMultiplier = (float) Config.getFertilizerFertilizedEssenceYieldMultiplier();
        fertilizers.add(f);
    }

    private static void addForbiddenArcanusFertilizers(List<FertilizerEntry> fertilizers) {
        FertilizerEntry f = new FertilizerEntry();
        f.item = "forbidden_arcanus:arcane_bone_meal";
        f.speedMultiplier = (float) Config.getFertilizerArcaneBoneMealSpeedMultiplier();
        f.yieldMultiplier = (float) Config.getFertilizerArcaneBoneMealYieldMultiplier();
        fertilizers.add(f);
    }

    private static void addMysticalAgricultureFertilizers(List<FertilizerEntry> fertilizers) {
        FertilizerEntry mystical = new FertilizerEntry();
        mystical.item = "mysticalagriculture:mystical_fertilizer";
        mystical.speedMultiplier = (float) Config.getFertilizerMysticalFertilizerSpeedMultiplier();
        mystical.yieldMultiplier = (float) Config.getFertilizerMysticalFertilizerYieldMultiplier();
        fertilizers.add(mystical);

        FertilizerEntry essence = new FertilizerEntry();
        essence.item = "mysticalagriculture:fertilized_essence";
        essence.speedMultiplier = (float) Config.getFertilizerFertilizedEssenceSpeedMultiplier();
        essence.yieldMultiplier = (float) Config.getFertilizerFertilizedEssenceYieldMultiplier();
        fertilizers.add(essence);
    }

    private static void processConfig(PlantablesConfigData configData) {
        crops.clear();
        trees.clear();
        soils.clear();
        fertilizers.clear();

        if (configData.allowedCrops != null) {
            for (CropEntry entry : configData.allowedCrops) {
                if (entry.seed != null && !entry.seed.isEmpty()) {
                    crops.put(entry.seed, createPlantInfo(entry.validSoils, entry.soil, entry.drops));
                }
            }
        }

        if (configData.allowedTrees != null) {
            for (TreeEntry entry : configData.allowedTrees) {
                if (entry.sapling != null && !entry.sapling.isEmpty()) {
                    trees.put(entry.sapling, createTreeInfo(entry.validSoils, entry.soil, entry.drops));
                }
            }
        }

        if (configData.allowedSoils != null) {
            for (SoilEntry entry : configData.allowedSoils) {
                if (entry.soil != null && !entry.soil.isEmpty()) {
                    soils.put(entry.soil, new SoilInfo(entry.growthModifier));
                }
            }
        }

        if (configData.allowedFertilizers != null) {
            for (FertilizerEntry entry : configData.allowedFertilizers) {
                if (entry.item != null && !entry.item.isEmpty()) {
                    fertilizers.put(entry.item, new FertilizerInfo(entry.speedMultiplier, entry.yieldMultiplier));
                }
            }
        }

        LOGGER.info("Loaded {} crops, {} trees, and {} soils from config", crops.size(), trees.size(), soils.size());
    }

    private static CropInfo createPlantInfo(List<String> validSoils, String soil, List<DropEntry> drops) {
        CropInfo info = new CropInfo();
        info.drops = new ArrayList<>();
        if (validSoils != null && !validSoils.isEmpty()) {
            info.validSoils.addAll(validSoils);
        } else if (soil != null && !soil.isEmpty()) {
            info.validSoils.add(soil);
        }
        if (drops != null) {
            for (DropEntry d : drops) {
                info.drops.add(new DropInfo(d.item,
                        d.count != null ? d.count.min : 1,
                        d.count != null ? d.count.max : 1,
                        d.chance));
            }
        }
        return info;
    }

    private static TreeInfo createTreeInfo(List<String> validSoils, String soil, List<DropEntry> drops) {
        TreeInfo info = new TreeInfo();
        info.drops = new ArrayList<>();
        if (validSoils != null && !validSoils.isEmpty()) {
            info.validSoils.addAll(validSoils);
        } else if (soil != null && !soil.isEmpty()) {
            info.validSoils.add(soil);
        }
        if (drops != null) {
            for (DropEntry d : drops) {
                info.drops.add(new DropInfo(d.item,
                        d.count != null ? d.count.min : 1,
                        d.count != null ? d.count.max : 1,
                        d.chance));
            }
        }
        return info;
    }

    public static boolean isValidSeed(String itemId) {
        return crops.containsKey(itemId);
    }

    public static boolean isValidSapling(String itemId) {
        return trees.containsKey(itemId);
    }

    public static boolean isValidSoil(String blockId) {
        return soils.containsKey(blockId);
    }

    public static boolean isValidFertilizer(String itemId) {
        return fertilizers.containsKey(itemId);
    }

    public static boolean isSoilValidForSeed(String soilId, String seedId) {
        CropInfo info = crops.get(seedId);
        return info != null && !info.validSoils.isEmpty() && info.validSoils.contains(soilId);
    }

    public static boolean isSoilValidForSapling(String soilId, String saplingId) {
        TreeInfo info = trees.get(saplingId);
        return info != null && !info.validSoils.isEmpty() && info.validSoils.contains(soilId);
    }

    public static List<DropInfo> getCropDrops(String seedId) {
        CropInfo info = crops.get(seedId);
        return info != null ? info.drops : Collections.emptyList();
    }

    public static List<DropInfo> getTreeDrops(String saplingId) {
        TreeInfo info = trees.get(saplingId);
        return info != null ? info.drops : Collections.emptyList();
    }

    public static float getSoilGrowthModifier(String blockId) {
        SoilInfo info = soils.get(blockId);
        return info != null ? info.growthModifier : 1.0F;
    }

    public static FertilizerInfo getFertilizerInfo(String itemId) {
        return fertilizers.get(itemId);
    }

    public static int getBaseSaplingGrowthTime(String saplingId) {
        return 2000;
    }

    public static Map<String, List<String>> getAllSeedToSoilMappings() {
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<String, CropInfo> entry : crops.entrySet()) {
            if (!entry.getValue().validSoils.isEmpty()) {
                result.put(entry.getKey(), new ArrayList<>(entry.getValue().validSoils));
            }
        }
        return result;
    }

    public static Map<String, List<String>> getAllSaplingToSoilMappings() {
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<String, TreeInfo> entry : trees.entrySet()) {
            if (!entry.getValue().validSoils.isEmpty()) {
                result.put(entry.getKey(), new ArrayList<>(entry.getValue().validSoils));
            }
        }
        return result;
    }

    public static class PlantablesConfigData {
        public List<CropEntry> allowedCrops;
        public List<TreeEntry> allowedTrees;
        public List<SoilEntry> allowedSoils;
        public List<FertilizerEntry> allowedFertilizers;
    }

    public static class CropEntry {
        public String seed;
        public String soil;
        public List<String> validSoils;
        public List<DropEntry> drops;
    }

    public static class TreeEntry {
        public String sapling;
        public String soil;
        public List<String> validSoils;
        public List<DropEntry> drops;
    }

    public static class SoilEntry {
        public String soil;
        public float growthModifier;
    }

    public static class FertilizerEntry {
        public String item;
        public float speedMultiplier = 1.2F;
        public float yieldMultiplier = 1.2F;
    }

    public static class DropEntry {
        public String item;
        public CountRange count;
        public float chance = 1.0F;
    }

    public static class CountRange {
        public int min;
        public int max;

        public CountRange() {
            this.min = 1;
            this.max = 1;
        }

        public CountRange(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static class CropInfo {
        public List<DropInfo> drops;
        public List<String> validSoils = new ArrayList<>();
    }

    public static class TreeInfo {
        public List<DropInfo> drops;
        public List<String> validSoils = new ArrayList<>();
    }

    public static class SoilInfo {
        public final float growthModifier;

        public SoilInfo(float growthModifier) {
            this.growthModifier = growthModifier;
        }
    }

    public static class FertilizerInfo {
        public final float speedMultiplier;
        public final float yieldMultiplier;

        public FertilizerInfo(float speedMultiplier, float yieldMultiplier) {
            this.speedMultiplier = speedMultiplier;
            this.yieldMultiplier = yieldMultiplier;
        }
    }

    public static class DropInfo {
        public final String item;
        public final int minCount;
        public final int maxCount;
        public final float chance;

        public DropInfo(String item, int minCount, int maxCount, float chance) {
            this.item = item;
            this.minCount = minCount;
            this.maxCount = maxCount;
            this.chance = chance;
        }
    }
}
