package com.misterd.agritechtwo.client.ber;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.QuadInstance;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.model.standalone.SimpleUnbakedStandaloneModel;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;
import org.jspecify.annotations.Nullable;

import com.misterd.agritechtwo.AgritechTwo;
import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.util.RegistryHelper;

public class PlanterBlockEntityRenderer
        implements BlockEntityRenderer<PlanterBlockEntity, PlanterBlockEntityRenderer.RenderState> {

    // -------------------------------------------------------------------------
    // Standalone model key for the cloche dome
    // -------------------------------------------------------------------------
    public static final StandaloneModelKey<QuadCollection> CLOCHE_DOME_KEY = new StandaloneModelKey<>(
            new ModelDebugName() {
                @Override
                public String debugName() { return "agritechtwo: cloche_dome"; }
            }
    );

    private static final Identifier WATER_STILL =
            Identifier.fromNamespaceAndPath("minecraft", "block/water_still");

    public PlanterBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    // -------------------------------------------------------------------------
    // Render State
    // -------------------------------------------------------------------------
    public static class RenderState extends BlockEntityRenderState {
        public boolean    cloched       = false;
        public ItemStack  soilStack     = ItemStack.EMPTY;
        public ItemStack  plantStack    = ItemStack.EMPTY;
        public float      growthProgress = 0f;
        public int        growthStage   = 0;
        public boolean    soilIsWater   = false;
    }

    @Override
    public RenderState createRenderState() {
        return new RenderState();
    }

    @Override
    public void extractRenderState(PlanterBlockEntity be, RenderState state, float partialTick,
                                   Vec3 cameraPos,
                                   ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderState.extractBase(be, state, crumblingOverlay);

        state.cloched        = be.getBlockState().getValue(PlanterBlock.CLOCHED);
        state.soilStack  = be.getStack(1).copy();
        state.plantStack = be.getStack(0).copy();
        state.growthProgress = be.getGrowthProgress();
        state.growthStage    = be.getGrowthStage();
        state.soilIsWater    = !state.soilStack.isEmpty()
                && RegistryHelper.getItemId(state.soilStack).equals("minecraft:water_bucket");
    }

    // -------------------------------------------------------------------------
    // Submit
    // -------------------------------------------------------------------------
    @Override
    public void submit(RenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState cameraState) {

        final int light = state.lightCoords;

        // --- Cloche dome model ---
        if (state.cloched) {
            QuadCollection dome = Minecraft.getInstance()
                    .getModelManager().getStandaloneModel(CLOCHE_DOME_KEY);
            if (dome != null) {
                // Use entity cutout render type with the blocks atlas texture
                collector.submitCustomGeometry(poseStack,
                        RenderTypes.entityCutout(TextureAtlas.LOCATION_BLOCKS),
                        (pose, consumer) -> {
                            QuadInstance qi = new QuadInstance();
                            qi.setLightCoords(light);
                            for (BakedQuad quad : dome.getAll()) {
                                consumer.putBakedQuad(pose, quad, qi);
                            }
                        });
            }
        }

        // --- Soil ---
        if (!state.soilStack.isEmpty()) {
            if (state.soilIsWater) {
                submitWater(poseStack, collector, light);
            } else if (state.soilStack.getItem() instanceof BlockItem soilBlockItem) {
                BlockState soilState = soilBlockItem.getBlock().defaultBlockState();
                poseStack.pushPose();
                poseStack.translate(0.175, 0.4, 0.175);
                poseStack.scale(0.65f, 0.05f, 0.65f);
                submitBlockState(soilState, poseStack, collector, light, false);
                poseStack.popPose();
            }
        }

        // --- Plant ---
        if (!state.plantStack.isEmpty() && !state.soilStack.isEmpty()
                && state.plantStack.getItem() instanceof BlockItem plantBlockItem) {

            String plantId  = RegistryHelper.getItemId(state.plantStack);
            boolean isTree  = PlantablesConfig.isValidSapling(plantId);
            boolean isCrop  = PlantablesConfig.isValidSeed(plantId);

            if (isTree || isCrop) {
                BlockState plantState = plantBlockItem.getBlock().defaultBlockState();
                poseStack.pushPose();

                if (isTree) {
                    float scale = 0.3f + state.growthProgress * 0.4f;
                    poseStack.translate(0.5, 0.45, 0.5);
                    poseStack.scale(scale, scale, scale);
                    poseStack.translate(-0.5, 0.0, -0.5);
                } else {
                    plantState = getCropBlockState(state.plantStack, state.growthStage);
                    float gs = 0.2f + Math.min(1f, state.growthProgress) * 0.5f;
                    poseStack.translate(0.1725, 0.45, 0.1725);
                    poseStack.scale(0.65f, gs, 0.65f);
                }

                if (plantState != null) {
                    submitBlockState(plantState, poseStack, collector, light, false);
                }
                poseStack.popPose();
            }
        }
    }

    // -------------------------------------------------------------------------
    // Model registration — call from ClientModEvents
    // -------------------------------------------------------------------------
    public static void onRegisterStandaloneModels(ModelEvent.RegisterStandalone event) {
        event.register(
                CLOCHE_DOME_KEY,
                SimpleUnbakedStandaloneModel.quadCollection(
                        Identifier.fromNamespaceAndPath(AgritechTwo.MODID, "block/cloche_dome")
                )
        );
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /**
     * Render a BlockState by fetching its BlockStateModel from the ModelManager
     * and submitting its quads via submitCustomGeometry.
     */
    private static void submitBlockState(BlockState blockState, PoseStack poseStack,
                                         SubmitNodeCollector collector, int light, boolean cutout) {
        var modelSet = Minecraft.getInstance().getModelManager().getBlockStateModelSet();
        var model    = modelSet.get(blockState);
        if (model == null) return;

        var parts = new java.util.ArrayList<BlockStateModelPart>();
        model.collectParts(net.minecraft.util.RandomSource.create(), parts);

        var blockColors = Minecraft.getInstance().getBlockColors();
        int[] tints = new int[4];
        for (int i = 0; i < 4; i++) {
            BlockTintSource source = blockColors.getTintSource(blockState, i);
            tints[i] = source != null ? source.color(blockState) : -1;
        }

        RenderType renderType = cutout
                ? RenderTypes.entityCutout(TextureAtlas.LOCATION_BLOCKS, true)
                : RenderTypes.entitySolid(TextureAtlas.LOCATION_BLOCKS);

        collector.submitBlockModel(poseStack,
                renderType,
                parts,
                tints,
                light,
                net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,
                -1);
    }

    private void submitWater(PoseStack poseStack, SubmitNodeCollector collector, int light) {
        TextureAtlasSprite sprite = Minecraft.getInstance()
                .getAtlasManager()
                .getAtlasOrThrow(AtlasIds.BLOCKS)
                .getSprite(WATER_STILL);

        float y    = 0.41f;
        float xMin = 0.175f, xMax = 0.825f;
        float zMin = 0.175f, zMax = 0.825f;
        float u0 = sprite.getU0(), u1 = sprite.getU1();
        float v0 = sprite.getV0(), v1 = sprite.getV1();

        collector.submitCustomGeometry(poseStack,
                RenderTypes.entityTranslucent(TextureAtlas.LOCATION_BLOCKS),
                (pose, consumer) -> {
                    addWaterVertex(consumer, pose, xMin, y, zMin, u0, v0, light);
                    addWaterVertex(consumer, pose, xMin, y, zMax, u0, v1, light);
                    addWaterVertex(consumer, pose, xMax, y, zMax, u1, v1, light);
                    addWaterVertex(consumer, pose, xMax, y, zMin, u1, v0, light);
                });
    }

    private static void addWaterVertex(VertexConsumer c, PoseStack.Pose pose,
                                       float x, float y, float z,
                                       float u, float v, int light) {
        c.addVertex(pose, x, y, z)
                .setColor(0x3F, 0x76, 0xE4, 0xA0)
                .setUv(u, v)
                .setLight(light)
                .setNormal(pose, 0f, 1f, 0f);
    }

    private static @Nullable BlockState getCropBlockState(ItemStack stack, int age) {
        if (!(stack.getItem() instanceof BlockItem bi)) return null;
        BlockState def = bi.getBlock().defaultBlockState();
        for (Property<?> prop : def.getProperties()) {
            if (prop instanceof IntegerProperty ip && prop.getName().equals("age")) {
                int max = ip.getPossibleValues().stream().mapToInt(Integer::intValue).max().orElse(7);
                return def.setValue(ip, Math.min(age, max));
            }
        }
        if (def.hasProperty(BlockStateProperties.AGE_7))  return def.setValue(BlockStateProperties.AGE_7,  Math.min(age, 7));
        if (def.hasProperty(BlockStateProperties.AGE_3))  return def.setValue(BlockStateProperties.AGE_3,  Math.min(age, 3));
        if (def.hasProperty(BlockStateProperties.AGE_5))  return def.setValue(BlockStateProperties.AGE_5,  Math.min(age, 5));
        if (def.hasProperty(BlockStateProperties.AGE_15)) return def.setValue(BlockStateProperties.AGE_15, Math.min(age, 15));
        if (def.hasProperty(BlockStateProperties.AGE_25)) return def.setValue(BlockStateProperties.AGE_25, Math.min(age, 25));
        return def;
    }
}