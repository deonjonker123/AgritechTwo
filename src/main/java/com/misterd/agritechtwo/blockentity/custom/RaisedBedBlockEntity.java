package com.misterd.agritechtwo.blockentity.custom;

import com.misterd.agritechtwo.Config;
import com.misterd.agritechtwo.block.custom.PlanterBlock;
import com.misterd.agritechtwo.blockentity.ATBlockEntities;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.gui.custom.RaisedBedBlockMenu;
import com.misterd.agritechtwo.item.ATItems;
import com.misterd.agritechtwo.util.RegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

import javax.annotation.Nullable;
import java.util.*;

public class RaisedBedBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(2) {
        @Override
        public long getCapacityAsLong(int index, ItemResource resource) {
            return 1;
        }

        @Override
        public boolean isValid(int index, ItemResource resource) {
            if (resource.isEmpty()) return false;
            String itemId = RegistryHelper.getItemId(resource.toStack());
            return switch (index) {
                case 0 -> PlantablesConfig.isValidSeed(itemId) || PlantablesConfig.isValidSapling(itemId);
                case 1 -> PlantablesConfig.isValidSoil(itemId);
                default -> false;
            };
        }

        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            RaisedBedBlockEntity.this.setChanged();
            Level lvl = RaisedBedBlockEntity.this.level;
            if (lvl != null && !lvl.isClientSide()) {
                lvl.sendBlockUpdated(
                        RaisedBedBlockEntity.this.getBlockPos(),
                        RaisedBedBlockEntity.this.getBlockState(),
                        RaisedBedBlockEntity.this.getBlockState(),
                        3
                );
            }
        }
    };

    private int growthProgress = 0;
    private int growthTicks = 0;
    private boolean readyToHarvest = false;
    private int lastGrowthStage = -1;

    public RaisedBedBlockEntity(BlockPos pos, BlockState blockState) {
        super(ATBlockEntities.RAISED_BED_BLOCK_BE.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RaisedBedBlockEntity be) {
        if (level.isClientSide()) return;

        ItemStack plantStack = be.getStack(0);
        ItemStack soilStack = be.getStack(1);

        if (plantStack.isEmpty() || soilStack.isEmpty()) {
            be.resetGrowth();
            return;
        }

        String plantId = RegistryHelper.getItemId(plantStack);
        String soilId = RegistryHelper.getItemId(soilStack);

        if (!be.isValidPlantSoilCombination(plantId, soilId)) {
            be.resetGrowth();
            return;
        }

        if (!be.readyToHarvest) {
            float soilMod = be.getSoilGrowthModifier(soilStack);
            float skyMod = getSkyDayModifier(level, pos);
            float totalMod = soilMod * skyMod;
            int baseTime = be.getBaseGrowthTime(plantStack);
            int adjustedTime = Math.max(1, Math.round((float) baseTime / totalMod));

            be.growthTicks++;

            if (be.growthTicks >= adjustedTime) {
                be.readyToHarvest = true;
                be.growthProgress = 100;
                be.lastGrowthStage = be.getGrowthStage();
                level.sendBlockUpdated(pos, state, state, 3);
                be.setChanged();
            } else {
                be.growthProgress = (int) ((float) be.growthTicks / adjustedTime * 100.0F);
                int stage = be.getGrowthStage();
                if (stage != be.lastGrowthStage) {
                    be.lastGrowthStage = stage;
                }
                if (be.growthTicks % 20 == 0) {
                    level.sendBlockUpdated(pos, state, state, 3);
                    be.setChanged();
                }
            }
        }

        if (be.readyToHarvest) {
            be.harvestPlant(level, pos);
        }
    }

    private static float getSkyDayModifier(Level level, BlockPos pos) {
        boolean canSeeSky = level.canSeeSky(pos.above());
        boolean isDay = level.isBrightOutside();
        if (canSeeSky && isDay) return (float) Config.getRaisedBedSkyDaySpeedMultiplier();
        return 1.0F;
    }

    public void applyManualFertilizer(float speedMultiplier) {
        if (readyToHarvest) return;
        ItemStack plantStack = getStack(0);
        ItemStack soilStack = getStack(1);
        if (plantStack.isEmpty() || soilStack.isEmpty()) return;

        float soilMod = getSoilGrowthModifier(soilStack);
        int adjustedTime = Math.max(1, Math.round(getBaseGrowthTime(plantStack) / soilMod));

        int boost = Math.max(1, Math.round(adjustedTime * 0.25F * speedMultiplier));
        growthTicks = Math.min(adjustedTime, growthTicks + boost);
        growthProgress = (int) ((float) growthTicks / adjustedTime * 100.0F);

        if (growthTicks >= adjustedTime) {
            readyToHarvest = true;
            growthProgress = 100;
        }

        lastGrowthStage = getGrowthStage();
        setChanged();
    }

    private boolean isValidPlantSoilCombination(String plantId, String soilId) {
        if (PlantablesConfig.isValidSeed(plantId)) return PlantablesConfig.isSoilValidForSeed(soilId, plantId);
        if (PlantablesConfig.isValidSapling(plantId)) return PlantablesConfig.isSoilValidForSapling(soilId, plantId);
        return false;
    }

    private boolean isTree() {
        ItemStack s = getStack(0);
        return !s.isEmpty() && PlantablesConfig.isValidSapling(RegistryHelper.getItemId(s));
    }

    private int getBaseGrowthTime(ItemStack plantStack) {
        String id = RegistryHelper.getItemId(plantStack);
        if (PlantablesConfig.isValidSeed(id)) return Config.getPlanterBaseProcessingTime();
        if (PlantablesConfig.isValidSapling(id)) return PlantablesConfig.getBaseSaplingGrowthTime(id);
        return Config.getPlanterBaseProcessingTime();
    }

    public float getSoilGrowthModifier(ItemStack soilStack) {
        if (soilStack.isEmpty()) return 1.0F;
        return PlantablesConfig.getSoilGrowthModifier(RegistryHelper.getItemId(soilStack));
    }

    private void resetGrowth() {
        growthProgress = 0;
        growthTicks = 0;
        readyToHarvest = false;
        lastGrowthStage = -1;
        setChanged();
    }

    public boolean isReadyToHarvest() {
        return readyToHarvest;
    }

    public void harvestPlant(Level level, BlockPos pos) {
        if (!readyToHarvest) return;

        List<ItemStack> drops = getHarvestDrops(getStack(0));
        for (ItemStack drop : drops) {
            double x = pos.getX() + 0.25 + level.getRandom().nextDouble() * 0.5;
            double y = pos.getY() + 0.5;
            double z = pos.getZ() + 0.25 + level.getRandom().nextDouble() * 0.5;
            ItemEntity entity = new ItemEntity(level, x, y, z, drop);
            entity.setDefaultPickUpDelay();
            level.addFreshEntity(entity);
        }

        resetGrowth();
    }

    private List<ItemStack> getHarvestDrops(ItemStack plantStack) {
        List<ItemStack> drops = new ArrayList<>();
        if (plantStack.isEmpty()) return drops;

        String plantId = RegistryHelper.getItemId(plantStack);
        List<PlantablesConfig.DropInfo> configDrops;

        if (PlantablesConfig.isValidSeed(plantId)) configDrops = PlantablesConfig.getCropDrops(plantId);
        else if (PlantablesConfig.isValidSapling(plantId)) configDrops = PlantablesConfig.getTreeDrops(plantId);
        else return drops;

        Random rng = new Random();
        for (PlantablesConfig.DropInfo info : configDrops) {
            if (rng.nextFloat() <= info.chance) {
                int count = info.maxCount > info.minCount
                        ? info.minCount + rng.nextInt(info.maxCount - info.minCount + 1)
                        : info.minCount;
                Item item = RegistryHelper.getItem(info.item);
                if (item != null) drops.add(new ItemStack(item, count));
            }
        }
        return drops;
    }

    public ItemStack getStack(int slot) {
        ItemResource res = inventory.getResource(slot);
        if (res.isEmpty()) return ItemStack.EMPTY;
        return res.toStack(inventory.getAmountAsInt(slot));
    }

    public float getGrowthProgress() {
        return growthProgress / 100.0F;
    }

    public int getGrowthStage() {
        if (isTree()) return growthProgress > 50 ? 1 : 0;
        return Math.min(8, (int) (growthProgress / 12.5F));
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        drops();
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            inv.setItem(i, getStack(i));
        }
        Containers.dropContents(level, worldPosition, inv);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        inventory.serialize(output);
        output.putInt("growthProgress", growthProgress);
        output.putInt("growthTicks", growthTicks);
        output.putBoolean("readyToHarvest", readyToHarvest);
        output.putInt("lastGrowthStage", lastGrowthStage);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        inventory.deserialize(input);
        growthProgress = input.getIntOr("growthProgress", 0);
        growthTicks = input.getIntOr("growthTicks", 0);
        readyToHarvest = input.getBooleanOr("readyToHarvest", false);
        lastGrowthStage = input.getIntOr("lastGrowthStage", -1);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.agritechtwo.raised_bed");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new RaisedBedBlockMenu(id, playerInv, this);
    }
}