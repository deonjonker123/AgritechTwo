package com.misterd.agritechtwo.gui.custom;

import com.misterd.agritechtwo.blockentity.custom.CrateBlockEntity;
import com.misterd.agritechtwo.gui.ATMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class CrateBlockMenu extends AbstractContainerMenu {

    public final CrateBlockEntity blockEntity;
    private final ContainerData data;

    private static final int DATA_COLLECTING = 0;
    private static final int DATA_COUNT = 1;

    public CrateBlockMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, inv.player.level().getBlockEntity(buf.readBlockPos()));
    }

    public CrateBlockMenu(int id, Inventory inv, BlockEntity be) {
        super(ATMenuTypes.CRATE_BLOCK_MENU.get(), id);
        this.blockEntity = (CrateBlockEntity) be;

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return index == DATA_COLLECTING ? (blockEntity.isCollecting() ? 1 : 0) : 0;
            }

            @Override
            public void set(int index, int value) {
                if (index == DATA_COLLECTING) blockEntity.setCollecting(value == 1);
            }

            @Override
            public int getCount() { return DATA_COUNT; }
        };

        for (int row = 0; row < 6; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new CrateSlot(blockEntity, row * 9 + col, 8 + col * 18, 19 + row * 18));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addDataSlots(this.data);
    }

    public boolean isCollecting() { return data.get(DATA_COLLECTING) == 1; }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem().copy();
        ItemStack remaining = stack.copy();

        if (index < 54) {
            if (!moveItemStackTo(remaining, 54, slots.size(), true)) return ItemStack.EMPTY;
        } else {
            for (int slot = 0; slot < 54 && !remaining.isEmpty(); slot++) {
                ItemStack existing = blockEntity.getStack(slot);
                if (existing.isEmpty()) continue;
                if (!ItemStack.isSameItemSameComponents(existing, remaining)) continue;

                int space = existing.getMaxStackSize() - existing.getCount();
                if (space <= 0) continue;

                int toInsert = Math.min(space, remaining.getCount());
                try (Transaction tx = Transaction.openRoot()) {
                    int inserted = blockEntity.inventory.insert(slot, ItemResource.of(remaining), toInsert, tx);
                    if (inserted > 0) {
                        tx.commit();
                        remaining.shrink(inserted);
                    }
                }
            }
            for (int slot = 0; slot < 54 && !remaining.isEmpty(); slot++) {
                if (!blockEntity.getStack(slot).isEmpty()) continue;

                int toInsert = remaining.getCount();
                try (Transaction tx = Transaction.openRoot()) {
                    int inserted = blockEntity.inventory.insert(slot, ItemResource.of(remaining), toInsert, tx);
                    if (inserted > 0) {
                        tx.commit();
                        remaining.shrink(inserted);
                    }
                }
            }

            if (remaining.getCount() == stack.getCount()) return ItemStack.EMPTY; // nothing moved
        }

        if (remaining.isEmpty()) {
            source.set(ItemStack.EMPTY);
        } else {
            source.setChanged();
            slots.get(index).set(remaining);
        }

        blockEntity.setChanged();
        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 142 + row * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(inv, col, 8 + col * 18, 200));
    }

    private static class CrateSlot extends Slot {
        private final CrateBlockEntity be;
        private final int index;

        public CrateSlot(CrateBlockEntity be, int index, int x, int y) {
            super(new SimpleContainer(be.inventory.size()), index, x, y);
            this.be = be;
            this.index = index;
        }

        @Override
        public ItemStack getItem() { return be.getStack(index); }

        @Override
        public void set(ItemStack stack) {
            try (Transaction tx = Transaction.openRoot()) {
                ItemStack existing = be.getStack(index);
                if (!existing.isEmpty())
                    be.inventory.extract(index, ItemResource.of(existing), existing.getCount(), tx);
                if (!stack.isEmpty())
                    be.inventory.insert(index, ItemResource.of(stack), stack.getCount(), tx);
                tx.commit();
            }
            setChanged();
        }

        @Override
        public boolean mayPlace(ItemStack stack) { return true; }

        @Override
        public ItemStack remove(int amount) {
            ItemStack existing = getItem();
            if (existing.isEmpty()) return ItemStack.EMPTY;
            int toExtract = Math.min(amount, existing.getCount());
            try (Transaction tx = Transaction.openRoot()) {
                int extracted = be.inventory.extract(index, ItemResource.of(existing), toExtract, tx);
                tx.commit();
                return existing.copyWithCount(extracted);
            }
        }
    }
}