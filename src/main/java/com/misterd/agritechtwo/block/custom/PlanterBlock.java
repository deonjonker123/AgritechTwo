package com.misterd.agritechtwo.block.custom;

import com.misterd.agritechtwo.blockentity.ATBlockEntities;
import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.gui.custom.PlanterBlockMenu;
import com.misterd.agritechtwo.item.ATItems;
import com.misterd.agritechtwo.item.custom.ClocheItem;
import com.misterd.agritechtwo.util.RegistryHelper;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;

import javax.annotation.Nullable;
import java.util.Map;

public class PlanterBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(1,  0,  1,  3, 11,  3),
            Block.box(13, 0,  1, 15, 11,  3),
            Block.box(1,  0, 13,  3, 11, 15),
            Block.box(13, 0, 13, 15, 11, 15),
            Block.box(2,  2,  2, 14, 10,  3),
            Block.box(2,  2, 13, 14, 10, 14),
            Block.box(2,  2,  3,  3, 10, 13),
            Block.box(13, 2,  3, 14, 10, 13),
            Block.box(3,  2,  3, 13,  3, 13)
    );

    public static final MapCodec<PlanterBlock> CODEC = simpleCodec(PlanterBlock::new);
    public static final BooleanProperty CLOCHED = BooleanProperty.create("cloched");

    private static final Map<String, String> ESSENCE_TO_FARMLAND = Map.of(
            "mysticalagriculture:inferium_essence",   "mysticalagriculture:inferium_farmland",
            "mysticalagriculture:prudentium_essence", "mysticalagriculture:prudentium_farmland",
            "mysticalagriculture:tertium_essence",    "mysticalagriculture:tertium_farmland",
            "mysticalagriculture:imperium_essence",   "mysticalagriculture:imperium_farmland",
            "mysticalagriculture:supremium_essence",  "mysticalagriculture:supremium_farmland",
            "mysticalagradditions:insanium_essence",  "mysticalagradditions:insanium_farmland"
    );

    public PlanterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CLOCHED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CLOCHED);
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
            if (state.getValue(CLOCHED)) {
                level.addFreshEntity(new ItemEntity(
                        level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        new ItemStack(ATItems.CLOCHE.get())
                ));
            }
            if (level.getBlockEntity(pos) instanceof PlanterBlockEntity planterBlockEntity) {
                planterBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    // -------------------------------------------------------------------------
    // useItemOn — dispatch to dedicated handlers
    // -------------------------------------------------------------------------

    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof PlanterBlockEntity planter)) {
            return ItemInteractionResult.FAIL;
        }

        ItemStack heldItem = player.getItemInHand(hand);
        String heldItemId = RegistryHelper.getItemId(heldItem);

        if (player.isCrouching() && heldItem.isEmpty() && state.getValue(CLOCHED)) {
            return handleClocheRemoval(state, level, pos, player);
        }
        if (player.isCrouching()) {
            return handleCrouchOpen(level, pos, player, planter);
        }
        if (heldItem.getItem() instanceof ClocheItem) {
            return handleClochePlace(state, level, pos, player, heldItem);
        }
        if (PlantablesConfig.isValidSeed(heldItemId) || PlantablesConfig.isValidSapling(heldItemId)) {
            return handlePlantInsert(state, level, pos, player, planter, heldItem, heldItemId);
        }
        if (PlantablesConfig.isValidSoil(heldItemId)) {
            return handleSoilInsert(state, level, pos, player, planter, heldItem, heldItemId);
        }
        if (PlantablesConfig.isValidFertilizer(heldItemId)) {
            return handleFertilizer(state, level, pos, player, planter, heldItem, heldItemId);
        }
        if (heldItem.getItem() instanceof HoeItem) {
            return handleHoeTill(level, pos, player, planter, heldItem, hand, hitResult);
        }
        if (ESSENCE_TO_FARMLAND.containsKey(heldItemId)) {
            return handleEssenceUpgrade(stack, level, pos, player, planter, heldItemId);
        }

        if (!level.isClientSide()) openGui(player, planter, pos);
        return ItemInteractionResult.SUCCESS;
    }

    // -------------------------------------------------------------------------
    // Handlers
    // -------------------------------------------------------------------------

    /** Sneak + empty hand on a cloched planter → pop the cloche off. */
    private ItemInteractionResult handleClocheRemoval(BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide()) {
            level.setBlock(pos, state.setValue(CLOCHED, false), 3);
            level.addFreshEntity(new ItemEntity(
                    level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                    new ItemStack(ATItems.CLOCHE.get())
            ));
            level.playSound(null, pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 0.5F, 1.2F);
        }
        return ItemInteractionResult.SUCCESS;
    }

    /** Sneak + any item → open GUI. */
    private ItemInteractionResult handleCrouchOpen(Level level, BlockPos pos, Player player, PlanterBlockEntity planter) {
        if (!level.isClientSide()) openGui(player, planter, pos);
        return ItemInteractionResult.SUCCESS;
    }

    /** Cloche item in hand → place cloche on planter. */
    private ItemInteractionResult handleClochePlace(BlockState state, Level level, BlockPos pos, Player player, ItemStack heldItem) {
        if (state.getValue(CLOCHED)) return ItemInteractionResult.FAIL;
        if (!level.isClientSide()) {
            level.setBlock(pos, state.setValue(CLOCHED, true), 3);
            if (!player.getAbilities().instabuild) heldItem.shrink(1);
            level.playSound(null, pos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
        return ItemInteractionResult.SUCCESS;
    }

    /** Seed / sapling in hand → insert into slot 0, validating against existing soil. */
    private ItemInteractionResult handlePlantInsert(BlockState state, Level level, BlockPos pos, Player player,
                                                    PlanterBlockEntity planter, ItemStack heldItem, String heldItemId) {
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
    }

    /** Soil in hand → insert into slot 1, validating against existing plant. */
    private ItemInteractionResult handleSoilInsert(BlockState state, Level level, BlockPos pos, Player player,
                                                   PlanterBlockEntity planter, ItemStack heldItem, String heldItemId) {
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
    }

    /** Fertilizer in hand → boost growth speed, or open GUI if preconditions not met. */
    private ItemInteractionResult handleFertilizer(BlockState state, Level level, BlockPos pos, Player player,
                                                   PlanterBlockEntity planter, ItemStack heldItem, String heldItemId) {
        if (planter.inventory.getStackInSlot(0).isEmpty()
                || planter.inventory.getStackInSlot(1).isEmpty()
                || planter.isReadyToHarvest()) {
            if (!level.isClientSide()) openGui(player, planter, pos);
            return ItemInteractionResult.SUCCESS;
        }
        if (!level.isClientSide()) {
            PlantablesConfig.FertilizerInfo info = PlantablesConfig.getFertilizerInfo(heldItemId);
            if (info != null) {
                planter.applyManualFertilizer(info.speedMultiplier);
                if (!player.getAbilities().instabuild) heldItem.shrink(1);
                level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                            pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5,
                            6, 0.3, 0.2, 0.3, 0.0);
                }
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }

    /** Hoe in hand → till the soil currently in slot 1. */
    private ItemInteractionResult handleHoeTill(Level level, BlockPos pos, Player player,
                                                PlanterBlockEntity planter, ItemStack heldItem,
                                                InteractionHand hand, BlockHitResult hitResult) {
        ItemStack soilStack = planter.inventory.getStackInSlot(1);
        if (!soilStack.isEmpty() && soilStack.getItem() instanceof BlockItem soilBlockItem) {
            BlockState soilState = soilBlockItem.getBlock().defaultBlockState();
            BlockState result = soilState.getToolModifiedState(
                    new UseOnContext(level, player, hand, heldItem, hitResult),
                    ItemAbilities.HOE_TILL, false);
            if (result != null) {
                planter.inventory.setStackInSlot(1, new ItemStack(result.getBlock()));
                level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.getAbilities().instabuild) {
                    heldItem.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide());
            }
        }
        return ItemInteractionResult.FAIL;
    }

    /** Mystical Agriculture essence in hand → upgrade the farmland in slot 1. */
    private ItemInteractionResult handleEssenceUpgrade(ItemStack stack, Level level, BlockPos pos, Player player,
                                                       PlanterBlockEntity planter, String heldItemId) {
        ItemStack soilStack = planter.inventory.getStackInSlot(1);
        if (!soilStack.isEmpty() && soilStack.getItem() instanceof BlockItem soilBlockItem) {
            String soilId = RegistryHelper.getBlockId(soilBlockItem.getBlock());
            boolean isValidFarmland = soilId.equals("minecraft:farmland")
                    || (soilId.startsWith("mysticalagriculture:") && soilId.endsWith("_farmland"))
                    || (soilId.startsWith("mysticalagradditions:") && soilId.endsWith("_farmland"));

            if (isValidFarmland) {
                String farmlandId = ESSENCE_TO_FARMLAND.get(heldItemId);
                Block resultBlock = RegistryHelper.getBlock(farmlandId);
                if (resultBlock != null) {
                    if (soilId.equals(farmlandId)) {
                        if (!level.isClientSide()) {
                            player.displayClientMessage(Component.translatable("message.agritech.same_farmland"), true);
                        }
                        return ItemInteractionResult.sidedSuccess(level.isClientSide());
                    }

                    planter.inventory.setStackInSlot(1, new ItemStack(resultBlock));
                    if (!player.getAbilities().instabuild) stack.shrink(1);
                    level.playSound(player, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return ItemInteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }
        return ItemInteractionResult.FAIL;
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

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