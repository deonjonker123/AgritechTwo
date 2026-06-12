package com.misterd.agritechtwo.compat.jei;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.misterd.agritechtwo.block.ATBlocks;
import com.misterd.agritechtwo.recipe.CropRecipe;
import com.misterd.agritechtwo.recipe.TreeRecipe;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.fml.ModList;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JeiPlugin
public class ATJeiPlugin implements IModPlugin {

    private static final Identifier PLUGIN_ID = Identifier.fromNamespaceAndPath("agritechtwo", "jei_plugin");
    private static IJeiRuntime jeiRuntime;
    private static boolean planterRegistered = false;
    private static boolean raisedBedRegistered = false;

    private static boolean evolvedLoaded() {
        return ModList.get().isLoaded("agritechevolved");
    }

    @Override
    public Identifier getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (!evolvedLoaded()) {
            registration.addRecipeCategories(new PlanterRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        }
        registration.addRecipeCategories(new RaisedBedRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (!evolvedLoaded()) {
            List<PlanterRecipe> planter = buildPlanterRecipes();
            planterRegistered = !planter.isEmpty();
            if (planterRegistered) {
                registration.addRecipes(PlanterRecipeCategory.PLANTER_RECIPE_TYPE, planter);
            }
        }
        List<RaisedBedRecipe> raisedBed = buildRaisedBedRecipes();
        raisedBedRegistered = !raisedBed.isEmpty();
        if (raisedBedRegistered) {
            registration.addRecipes(RaisedBedRecipeCategory.RAISED_BED_RECIPE_TYPE, raisedBed);
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        if (!evolvedLoaded()) {
            registration.addCraftingStation(PlanterRecipeCategory.PLANTER_RECIPE_TYPE, ATBlocks.OAK_PLANTER);
        }
        registration.addCraftingStation(RaisedBedRecipeCategory.RAISED_BED_RECIPE_TYPE, ATBlocks.OAK_RAISED_BED);
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        jeiRuntime = runtime;
        if (Minecraft.getInstance().getConnection() != null) {
            if (!evolvedLoaded() && !planterRegistered) {
                List<PlanterRecipe> planter = buildPlanterRecipes();
                if (!planter.isEmpty()) {
                    runtime.getRecipeManager().addRecipes(PlanterRecipeCategory.PLANTER_RECIPE_TYPE, planter);
                    LogUtils.getLogger().info("[AT2 JEI] Injected {} planter recipes from onRuntimeAvailable", planter.size());
                }
            }
            if (!raisedBedRegistered) {
                List<RaisedBedRecipe> raisedBed = buildRaisedBedRecipes();
                if (!raisedBed.isEmpty()) {
                    runtime.getRecipeManager().addRecipes(RaisedBedRecipeCategory.RAISED_BED_RECIPE_TYPE, raisedBed);
                    LogUtils.getLogger().info("[AT2 JEI] Injected {} raised bed recipes from onRuntimeAvailable", raisedBed.size());
                }
            }
        }
    }

    public static IJeiRuntime getJeiRuntime() {
        return jeiRuntime;
    }

    private static DynamicOps<JsonElement> getOps() {
        return RegistryOps.create(JsonOps.INSTANCE, Minecraft.getInstance().getConnection().registryAccess());
    }

    static List<PlanterRecipe> buildPlanterRecipes() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.getConnection() == null) {
            LogUtils.getLogger().warn("[AT2 JEI] No connection — deferred");
            return List.of();
        }
        List<PlanterRecipe> recipes = new ArrayList<>();
        DynamicOps<JsonElement> ops = getOps();

        Path modFilePath = ModList.get().getModFileById("agritechtwo").getFile().getFilePath();
        try {
            if (Files.isDirectory(modFilePath)) {
                Path resourcesDir = modFilePath.getParent().getParent().getParent().resolve("resources").resolve("main");
                Path recipePath = resourcesDir.resolve("data").resolve("agritechtwo").resolve("recipe");
                if (!Files.exists(recipePath)) {
                    recipePath = modFilePath.resolve("data").resolve("agritechtwo").resolve("recipe");
                }
                if (Files.exists(recipePath)) {
                    walkPlanterRecipes(recipePath, ops, recipes);
                } else {
                    LogUtils.getLogger().error("[AT2 JEI] Planter recipe path not found: {}", recipePath);
                }
            } else {
                try (FileSystem fs = FileSystems.newFileSystem(modFilePath, Map.of())) {
                    Path recipePath = fs.getPath("/data/agritechtwo/recipe");
                    if (Files.exists(recipePath)) {
                        walkPlanterRecipes(recipePath, ops, recipes);
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.getLogger().error("[AT2 JEI] Failed to walk planter recipe directory: {}", e.getMessage());
        }

        walkDatapackRecipes(mc, ops, "agritechtwo", recipes, true, false);

        LogUtils.getLogger().info("[AT2 JEI] Built {} planter recipes", recipes.size());
        return recipes;
    }

    static List<RaisedBedRecipe> buildRaisedBedRecipes() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.getConnection() == null) {
            LogUtils.getLogger().warn("[AT2 JEI] No connection — deferred");
            return List.of();
        }
        List<RaisedBedRecipe> recipes = new ArrayList<>();
        DynamicOps<JsonElement> ops = getOps();

        Path modFilePath = ModList.get().getModFileById("agritechtwo").getFile().getFilePath();
        try {
            if (Files.isDirectory(modFilePath)) {
                Path resourcesDir = modFilePath.getParent().getParent().getParent().resolve("resources").resolve("main");
                Path recipePath = resourcesDir.resolve("data").resolve("agritechtwo").resolve("recipe");
                if (!Files.exists(recipePath)) {
                    recipePath = modFilePath.resolve("data").resolve("agritechtwo").resolve("recipe");
                }
                if (Files.exists(recipePath)) {
                    walkRaisedBedRecipes(recipePath, ops, recipes);
                } else {
                    LogUtils.getLogger().error("[AT2 JEI] Raised bed recipe path not found: {}", recipePath);
                }
            } else {
                try (FileSystem fs = FileSystems.newFileSystem(modFilePath, Map.of())) {
                    Path recipePath = fs.getPath("/data/agritechtwo/recipe");
                    if (Files.exists(recipePath)) {
                        walkRaisedBedRecipes(recipePath, ops, recipes);
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.getLogger().error("[AT2 JEI] Failed to walk raised bed recipe directory: {}", e.getMessage());
        }

        walkDatapackRecipes(mc, ops, "agritechtwo", recipes, false, true);

        LogUtils.getLogger().info("[AT2 JEI] Built {} raised bed recipes", recipes.size());
        return recipes;
    }

    private static void walkDatapackRecipes(Minecraft mc, DynamicOps<JsonElement> ops, String namespace,
                                            List<?> recipes, boolean planter, boolean raisedBed) {
        var server = mc.getSingleplayerServer();
        if (server == null) return;
        try {
            Path datapackDir = server.getWorldPath(LevelResource.DATAPACK_DIR);
            if (!Files.isDirectory(datapackDir)) return;
            try (var dpStream = Files.list(datapackDir)) {
                dpStream.forEach(dp -> {
                    try {
                        if (Files.isDirectory(dp)) {
                            Path recipePath = dp.resolve("data").resolve(namespace).resolve("recipe");
                            if (Files.exists(recipePath)) {
                                if (planter) walkPlanterRecipes(recipePath, ops, castPlanterList(recipes));
                                if (raisedBed) walkRaisedBedRecipes(recipePath, ops, castRaisedBedList(recipes));
                            }
                        } else if (dp.toString().endsWith(".zip")) {
                            try (FileSystem fs = FileSystems.newFileSystem(dp, Map.of())) {
                                Path recipePath = fs.getPath("/data/" + namespace + "/recipe");
                                if (Files.exists(recipePath)) {
                                    if (planter) walkPlanterRecipes(recipePath, ops, castPlanterList(recipes));
                                    if (raisedBed) walkRaisedBedRecipes(recipePath, ops, castRaisedBedList(recipes));
                                }
                            }
                        }
                    } catch (Exception e) {
                        LogUtils.getLogger().error("[AT2 JEI] Failed to walk datapack {}: {}", dp.getFileName(), e.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            LogUtils.getLogger().error("[AT2 JEI] Failed to access datapack dir: {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<PlanterRecipe> castPlanterList(List<?> list) { return (List<PlanterRecipe>) list; }

    @SuppressWarnings("unchecked")
    private static List<RaisedBedRecipe> castRaisedBedList(List<?> list) { return (List<RaisedBedRecipe>) list; }

    private static void walkPlanterRecipes(Path recipePath, DynamicOps<JsonElement> ops, List<PlanterRecipe> recipes) throws Exception {
        try (var stream = Files.walk(recipePath)) {
            stream.filter(p -> p.toString().endsWith(".json")).forEach(p -> {
                try (var reader = Files.newBufferedReader(p)) {
                    JsonElement json = JsonParser.parseReader(reader);
                    if (!json.isJsonObject()) return;
                    var obj = json.getAsJsonObject();
                    var typeEl = obj.get("type");
                    if (typeEl == null) return;
                    String type = typeEl.getAsString();
                    if ("agritechtwo:crop".equals(type)) {
                        CropRecipe.CODEC.codec().parse(ops, obj)
                                .result().ifPresent(crop -> recipes.add(PlanterRecipe.fromCrop(crop)));
                    } else if ("agritechtwo:tree".equals(type)) {
                        TreeRecipe.CODEC.codec().parse(ops, obj)
                                .result().ifPresent(tree -> recipes.add(PlanterRecipe.fromTree(tree)));
                    }
                } catch (Exception e) {
                    LogUtils.getLogger().error("[AT2 JEI] Failed to parse {}: {}", p, e.getMessage());
                }
            });
        }
    }

    private static void walkRaisedBedRecipes(Path recipePath, DynamicOps<JsonElement> ops, List<RaisedBedRecipe> recipes) throws Exception {
        try (var stream = Files.walk(recipePath)) {
            stream.filter(p -> p.toString().endsWith(".json")).forEach(p -> {
                try (var reader = Files.newBufferedReader(p)) {
                    JsonElement json = JsonParser.parseReader(reader);
                    if (!json.isJsonObject()) return;
                    var obj = json.getAsJsonObject();
                    var typeEl = obj.get("type");
                    if (typeEl == null) return;
                    String type = typeEl.getAsString();
                    if ("agritechtwo:crop".equals(type)) {
                        CropRecipe.CODEC.codec().parse(ops, obj)
                                .result().ifPresent(crop -> recipes.add(RaisedBedRecipe.fromCrop(crop)));
                    } else if ("agritechtwo:tree".equals(type)) {
                        TreeRecipe.CODEC.codec().parse(ops, obj)
                                .result().ifPresent(tree -> recipes.add(RaisedBedRecipe.fromTree(tree)));
                    }
                } catch (Exception e) {
                    LogUtils.getLogger().error("[AT2 JEI] Failed to parse {}: {}", p, e.getMessage());
                }
            });
        }
    }
}