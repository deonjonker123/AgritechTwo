package com.misterd.agritechtwo.client.ber;

import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.util.RegistryHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.joml.Matrix4f;

public class PlanterBlockEntityRenderer implements BlockEntityRenderer<PlanterBlockEntity> {
    private static final ModelResourceLocation CLOCHE_DOME_MODEL = new ModelResourceLocation(
            ResourceLocation.fromNamespaceAndPath("agritechtwo", "block/cloche_dome"),
            "standalone"
    );
    private static final ResourceLocation WATER_STILL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still");

    public PlanterBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(PlanterBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemStackHandler inventory = blockEntity.inventory;
        float growthProgress       = blockEntity.getGrowthProgress();
        int growthStage            = blockEntity.getGrowthStage();

        if (blockEntity.getBlockState().getValue(PlanterBlock.CLOCHED)) {
            BakedModel domeModel = Minecraft.getInstance().getModelManager().getModel(CLOCHE_DOME_MODEL);
            poseStack.pushPose();
            poseStack.translate(0.0, 0 / 16.0, 0.0);
            Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.cutoutMipped()),
                    null,
                    domeModel,
                    1.0F, 1.0F, 1.0F,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    ModelData.EMPTY,
                    RenderType.cutoutMipped()
            );
            poseStack.popPose();
        }

        ItemStack soilStack = inventory.getStackInSlot(1);
        if (!soilStack.isEmpty()) {
            String soilId = RegistryHelper.getItemId(soilStack);
            if (soilId.equals("minecraft:water_bucket")) {
                renderWater(poseStack, bufferSource, packedLight);
            } else if (soilStack.getItem() instanceof BlockItem soilBlockItem) {
                BlockState soilState = soilBlockItem.getBlock().defaultBlockState();
                poseStack.pushPose();
                poseStack.translate(0.175, 0.4, 0.175);
                poseStack.scale(0.65F, 0.05F, 0.65F);
                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(soilState, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
            }
        }

        ItemStack plantStack = inventory.getStackInSlot(0);
        if (!plantStack.isEmpty() && !soilStack.isEmpty() && plantStack.getItem() instanceof BlockItem plantBlockItem) {
            String plantId = RegistryHelper.getItemId(plantStack);
            boolean isTree = PlantablesConfig.isValidSapling(plantId);
            boolean isCrop = PlantablesConfig.isValidSeed(plantId);

            if (isTree || isCrop) {
                BlockState plantState = plantBlockItem.getBlock().defaultBlockState();
                poseStack.pushPose();

                if (isTree) {
                    float scale = 0.3F + growthProgress * 0.4F;
                    poseStack.translate(0.5, 0.45, 0.5);
                    poseStack.scale(scale, scale, scale);
                    poseStack.translate(-0.5, 0.0, -0.5);
                } else {
                    plantState = getCropBlockState(plantStack, growthStage);
                    float growthScale = 0.2F + Math.min(1.0F, growthProgress) * 0.5F;
                    poseStack.translate(0.1725, 0.45, 0.1725);
                    poseStack.scale(0.65F, growthScale, 0.65F);
                }

                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(plantState, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
            }
        }
    }

    private void renderWater(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        TextureAtlasSprite sprite = Minecraft.getInstance()
                .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(WATER_STILL);

        float y    = 0.41F;
        float xMin = 0.175F;
        float xMax = 0.825F;
        float zMin = 0.175F;
        float zMax = 0.825F;

        float u0 = sprite.getU0();
        float u1 = sprite.getU1();
        float v0 = sprite.getV0();
        float v1 = sprite.getV1();

        poseStack.pushPose();
        Matrix4f matrix = poseStack.last().pose();
        VertexConsumer buffer = bufferSource.getBuffer(RenderType.translucent());

        buffer.addVertex(matrix, xMin, y, zMin).setColor(0x3F, 0x76, 0xE4, 0xA0).setUv(u0, v0).setLight(packedLight).setNormal(0, 1, 0);
        buffer.addVertex(matrix, xMin, y, zMax).setColor(0x3F, 0x76, 0xE4, 0xA0).setUv(u0, v1).setLight(packedLight).setNormal(0, 1, 0);
        buffer.addVertex(matrix, xMax, y, zMax).setColor(0x3F, 0x76, 0xE4, 0xA0).setUv(u1, v1).setLight(packedLight).setNormal(0, 1, 0);
        buffer.addVertex(matrix, xMax, y, zMin).setColor(0x3F, 0x76, 0xE4, 0xA0).setUv(u1, v0).setLight(packedLight).setNormal(0, 1, 0);

        poseStack.popPose();
    }

    private BlockState getCropBlockState(ItemStack stack, int age) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) return null;

        BlockState defaultState = blockItem.getBlock().defaultBlockState();

        for (Property<?> property : defaultState.getProperties()) {
            if (property instanceof IntegerProperty intProperty && property.getName().equals("age")) {
                int maxAge = intProperty.getPossibleValues().stream().mapToInt(Integer::intValue).max().orElse(7);
                return defaultState.setValue(intProperty, Math.min(age, maxAge));
            }
        }

        if (defaultState.hasProperty(BlockStateProperties.AGE_7))  return defaultState.setValue(BlockStateProperties.AGE_7,  Math.min(age, 7));
        if (defaultState.hasProperty(BlockStateProperties.AGE_3))  return defaultState.setValue(BlockStateProperties.AGE_3,  Math.min(age, 3));
        if (defaultState.hasProperty(BlockStateProperties.AGE_5))  return defaultState.setValue(BlockStateProperties.AGE_5,  Math.min(age, 5));
        if (defaultState.hasProperty(BlockStateProperties.AGE_15)) return defaultState.setValue(BlockStateProperties.AGE_15, Math.min(age, 15));
        if (defaultState.hasProperty(BlockStateProperties.AGE_25)) return defaultState.setValue(BlockStateProperties.AGE_25, Math.min(age, 25));

        return defaultState;
    }
}