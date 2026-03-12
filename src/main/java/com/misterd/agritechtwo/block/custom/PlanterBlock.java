package com.misterd.agritechtwo.block.custom;

import com.misterd.agritechtwo.blockentity.ATBlockEntities;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.gui.custom.PlanterBlockMenu;
import com.misterd.agritechtwo.util.RegistryHelper;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.fml.ModList;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class PlanterBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.1D, 15.0D, 11.0D, 15.0D);
    public static final MapCodec<PlanterBlock> CODEC = simpleCodec(PlanterBlock::new);

    public PlanterBlock(Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PlanterBlockEntity(pos, state);
    }

    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof PlanterBlockEntity planterBlockEntity) {
                planterBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof PlanterBlockEntity planter)) {
            return ItemInteractionResult.FAIL;
        }

        if (player.isCrouching()) {
            if (!level.isClientSide()) {
                openGui(player, planter, pos);
            }
            return ItemInteractionResult.SUCCESS;
        }

        ItemStack heldItem = player.getItemInHand(hand);
        String heldItemId = RegistryHelper.getItemId(heldItem);

        if (PlantablesConfig.isValidSeed(heldItemId) || PlantablesConfig.isValidSapling(heldItemId)) {
            if (!planter.inventory.getStackInSlot(0).isEmpty()) {
                if (!level.isClientSide()) openGui(player, planter, pos);
                return ItemInteractionResult.SUCCESS;
            }

            if (level.isClientSide()) return ItemInteractionResult.SUCCESS;

            ItemStack existingSoil = planter.inventory.getStackInSlot(1);
            if (!existingSoil.isEmpty()) {
                String soilId = RegistryHelper.getItemId(existingSoil);
                boolean valid = PlantablesConfig.isValidSeed(heldItemId)
                        ? PlantablesConfig.isSoilValidForSeed(soilId, heldItemId)
                        : PlantablesConfig.isSoilValidForSapling(soilId, heldItemId);
                if (!valid) {
                    player.displayClientMessage(Component.translatable("message.agritechtwo.invalid_seed_soil_combination"), true);
                    return ItemInteractionResult.SUCCESS;
                }
            }

            planter.inventory.setStackInSlot(0, heldItem.copyWithCount(1));
            heldItem.shrink(1);
            level.playSound(null, pos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.sendBlockUpdated(pos, state, state, 2);
            planter.setChanged();
            return ItemInteractionResult.SUCCESS;

        } else if (PlantablesConfig.isValidSoil(heldItemId)) {
            if (!planter.inventory.getStackInSlot(1).isEmpty()) {
                if (!level.isClientSide()) openGui(player, planter, pos);
                return ItemInteractionResult.SUCCESS;
            }

            if (level.isClientSide()) return ItemInteractionResult.SUCCESS;

            ItemStack existingPlant = planter.inventory.getStackInSlot(0);
            if (!existingPlant.isEmpty()) {
                String plantId = RegistryHelper.getItemId(existingPlant);
                boolean valid = PlantablesConfig.isValidSeed(plantId)
                        ? PlantablesConfig.isSoilValidForSeed(heldItemId, plantId)
                        : PlantablesConfig.isSoilValidForSapling(heldItemId, plantId);
                if (!valid) {
                    player.displayClientMessage(Component.translatable("message.agritechtwo.invalid_seed_soil_combination"), true);
                    return ItemInteractionResult.SUCCESS;
                }
            }

            planter.inventory.setStackInSlot(1, heldItem.copyWithCount(1));
            heldItem.shrink(1);
            level.playSound(null, pos, SoundEvents.GRAVEL_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
            level.sendBlockUpdated(pos, state, state, 2);
            planter.setChanged();
            return ItemInteractionResult.SUCCESS;

        } else if (heldItem.getItem() instanceof HoeItem) {
            ItemStack soilStack = planter.inventory.getStackInSlot(1);
            if (!soilStack.isEmpty() && soilStack.getItem() instanceof BlockItem soilBlockItem) {
                String soilId = RegistryHelper.getBlockId(soilBlockItem.getBlock());

                Map<String, String> tillableBlocks = new HashMap<>();
                tillableBlocks.put("minecraft:dirt", "minecraft:farmland");
                tillableBlocks.put("minecraft:grass_block", "minecraft:farmland");
                tillableBlocks.put("minecraft:mycelium", "minecraft:farmland");
                tillableBlocks.put("minecraft:podzol", "minecraft:farmland");
                tillableBlocks.put("minecraft:coarse_dirt", "minecraft:farmland");
                tillableBlocks.put("minecraft:rooted_dirt", "minecraft:farmland");
                if (ModList.get().isLoaded("farmersdelight")) {
                    tillableBlocks.put("farmersdelight:rich_soil", "farmersdelight:rich_soil_farmland");
                }

                if (tillableBlocks.containsKey(soilId)) {
                    Block resultBlock = RegistryHelper.getBlock(tillableBlocks.get(soilId));
                    if (resultBlock != null) {
                        planter.inventory.setStackInSlot(1, new ItemStack(resultBlock));
                        level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                        if (!player.getAbilities().instabuild) {
                            heldItem.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                        }
                        return ItemInteractionResult.sidedSuccess(level.isClientSide());
                    }
                }
            }

        } else {
            Map<String, String> essenceToFarmland = new HashMap<>();
            essenceToFarmland.put("mysticalagriculture:inferium_essence", "mysticalagriculture:inferium_farmland");
            essenceToFarmland.put("mysticalagriculture:prudentium_essence", "mysticalagriculture:prudentium_farmland");
            essenceToFarmland.put("mysticalagriculture:tertium_essence", "mysticalagriculture:tertium_farmland");
            essenceToFarmland.put("mysticalagriculture:imperium_essence", "mysticalagriculture:imperium_farmland");
            essenceToFarmland.put("mysticalagriculture:supremium_essence", "mysticalagriculture:supremium_farmland");
            essenceToFarmland.put("mysticalagradditions:insanium_essence", "mysticalagradditions:insanium_farmland");

            if (essenceToFarmland.containsKey(heldItemId)) {
                ItemStack soilStack = planter.inventory.getStackInSlot(1);
                if (!soilStack.isEmpty() && soilStack.getItem() instanceof BlockItem soilBlockItem) {
                    String soilId = RegistryHelper.getBlockId(soilBlockItem.getBlock());

                    boolean isValidFarmland = soilId.equals("minecraft:farmland")
                            || (soilId.startsWith("mysticalagriculture:") && soilId.endsWith("_farmland"))
                            || (soilId.startsWith("mysticalagradditions:") && soilId.endsWith("_farmland"));

                    if (isValidFarmland) {
                        String farmlandId = essenceToFarmland.get(heldItemId);
                        Block resultBlock = RegistryHelper.getBlock(farmlandId);
                        if (resultBlock != null) {
                            if (soilId.equals(farmlandId)) {
                                if (!level.isClientSide()) {
                                    player.displayClientMessage(Component.translatable("message.agritech.same_farmland"), true);
                                }
                                return ItemInteractionResult.sidedSuccess(level.isClientSide());
                            }

                            planter.inventory.setStackInSlot(1, new ItemStack(resultBlock));
                            if (!player.getAbilities().instabuild) {
                                stack.shrink(1);
                            }
                            level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 1.0F);
                            return ItemInteractionResult.sidedSuccess(level.isClientSide());
                        }
                    }
                }
            }
        }

        if (!level.isClientSide()) {
            openGui(player, planter, pos);
        }
        return ItemInteractionResult.SUCCESS;
    }

    private void openGui(Player player, PlanterBlockEntity planter, BlockPos pos) {
        MenuProvider menuProvider = new SimpleMenuProvider(
                (containerId, playerInventory, playerEntity) -> new PlanterBlockMenu(containerId, playerInventory, planter),
                Component.translatable("container.agritechtwo.planter")
        );
        player.openMenu(menuProvider, pos);
    }

    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == ATBlockEntities.PLANTER_BLOCK_BE.get()
                ? (lvl, pos, blockState, be) -> PlanterBlockEntity.tick(lvl, pos, blockState, (PlanterBlockEntity) be)
                : null;
    }
}