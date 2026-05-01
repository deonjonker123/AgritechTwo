package com.misterd.agritechtwo.blockentity.custom;

import com.misterd.agritechtwo.Config;
import com.misterd.agritechtwo.blockentity.ATBlockEntities;
import com.misterd.agritechtwo.gui.custom.CrateBlockMenu;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;

import javax.annotation.Nullable;
import java.util.List;

public class CrateBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(54) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            CrateBlockEntity.this.setChanged();
        }
    };

    private int tickCounter = 0;
    private boolean collecting = true;

    public CrateBlockEntity(BlockPos pos, BlockState blockState) {
        super(ATBlockEntities.CRATE_BLOCK_BE.get(), pos, blockState);
    }

    public boolean isCollecting() { return collecting; }

    public void setCollecting(boolean collecting) {
        this.collecting = collecting;
        setChanged();
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CrateBlockEntity be) {
        if (level.isClientSide()) return;
        if (!be.collecting) return;

        be.tickCounter++;
        if (be.tickCounter < Config.getBasketPickupIntervalTicks()) return;
        be.tickCounter = 0;

        int radius = 2;
        AABB area = new AABB(
                pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius,
                pos.getX() + 1 + radius, pos.getY() + 1 + radius, pos.getZ() + 1 + radius
        );

        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, area);
        for (ItemEntity itemEntity : items) {
            if (itemEntity.isRemoved()) continue;
            ItemStack stack = itemEntity.getItem();
            if (stack.isEmpty()) continue;

            ItemResource res = ItemResource.of(stack);
            int toInsert = stack.getCount();
            int inserted = 0;

            for (int slot = 0; slot < be.inventory.size() && toInsert > 0; slot++) {
                try (Transaction tx = Transaction.openRoot()) {
                    int amount = be.inventory.insert(slot, res, toInsert, tx);
                    if (amount > 0) {
                        tx.commit();
                        inserted += amount;
                        toInsert -= amount;
                    }
                }
            }

            if (inserted > 0) {
                if (inserted >= stack.getCount()) {
                    itemEntity.discard();
                } else {
                    stack.shrink(inserted);
                    itemEntity.setItem(stack);
                }
                be.setChanged();
            }
        }
    }

    public ItemStack getStack(int slot) {
        ItemResource res = inventory.getResource(slot);
        if (res.isEmpty()) return ItemStack.EMPTY;
        return res.toStack(inventory.getAmountAsInt(slot));
    }

    public ResourceHandler<ItemResource> getItemHandler() {
        return inventory;
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
        output.putBoolean("collecting", collecting);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        inventory.deserialize(input);
        collecting = input.getBooleanOr("collecting", true);
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
        return Component.translatable("container.agritechtwo.crate");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new CrateBlockMenu(id, playerInv, this);
    }
}