package com.misterd.agritechtwo.client.ber;

import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.util.RegistryHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.items.ItemStackHandler;

public class PlanterBlockEntityRenderer implements BlockEntityRenderer<PlanterBlockEntity> {
    public PlanterBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(PlanterBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemStackHandler inventory     = blockEntity.inventory;
        float growthProgress           = blockEntity.getGrowthProgress();
        int growthStage                = blockEntity.getGrowthStage();

        // Render soil
        ItemStack soilStack = inventory.getStackInSlot(1);
        if (!soilStack.isEmpty() && soilStack.getItem() instanceof BlockItem soilBlockItem) {
            BlockState soilState = soilBlockItem.getBlock().defaultBlockState();
            poseStack.pushPose();
            poseStack.translate(0.175, 0.4, 0.175);
            poseStack.scale(0.65F, 0.05F, 0.65F);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(soilState, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }

        // Render plant
        ItemStack plantStack = inventory.getStackInSlot(0);
        if (plantStack.isEmpty() || soilStack.isEmpty()) return;

        if (!(plantStack.getItem() instanceof BlockItem plantBlockItem)) return;

        String plantId = RegistryHelper.getItemId(plantStack);
        boolean isTree = PlantablesConfig.isValidSapling(plantId);
        boolean isCrop = PlantablesConfig.isValidSeed(plantId);
        if (!isTree && !isCrop) return;

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