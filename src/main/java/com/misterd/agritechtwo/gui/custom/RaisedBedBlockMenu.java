package com.misterd.agritechtwo.gui.custom;

import com.misterd.agritechtwo.blockentity.custom.RaisedBedBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.gui.ATMenuTypes;
import com.misterd.agritechtwo.util.RegistryHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class RaisedBedBlockMenu extends AbstractContainerMenu {

    public final RaisedBedBlockEntity blockEntity;
    private final Level level;

    public RaisedBedBlockMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, inv.player.level().getBlockEntity(buf.readBlockPos()));
    }

    public RaisedBedBlockMenu(int id, Inventory inv, BlockEntity be) {
        super(ATMenuTypes.RAISED_BED_BLOCK_MENU.get(), id);
        this.blockEntity = (RaisedBedBlockEntity) be;
        this.level = inv.player.level();

        addSlot(new RaisedBedSlot(blockEntity, 0, 62, 19));  // seed/sapling
        addSlot(new RaisedBedSlot(blockEntity, 1, 98, 19));  // soil

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem();
        ItemStack copy = stack.copy();
        String itemId = RegistryHelper.getItemId(stack);

        if (index >= 2) {
            // Player inventory → try to place into seed or soil slot
            if (PlantablesConfig.isValidSeed(itemId) || PlantablesConfig.isValidSapling(itemId)) {
                if (blockEntity.getStack(0).isEmpty()) {
                    ItemStack existingSoil = blockEntity.getStack(1);
                    if (!existingSoil.isEmpty()) {
                        String soilId = RegistryHelper.getItemId(existingSoil);
                        boolean valid = PlantablesConfig.isValidSeed(itemId)
                                ? PlantablesConfig.isSoilValidForSeed(soilId, itemId)
                                : PlantablesConfig.isSoilValidForSapling(soilId, itemId);
                        if (!valid) return ItemStack.EMPTY;
                    }
                    slots.get(0).set(stack.copyWithCount(1));
                    stack.shrink(1);
                    return copy;
                }
            } else if (PlantablesConfig.isValidSoil(itemId)) {
                if (blockEntity.getStack(1).isEmpty()) {
                    ItemStack existingPlant = blockEntity.getStack(0);
                    if (!existingPlant.isEmpty()) {
                        String plantId = RegistryHelper.getItemId(existingPlant);
                        boolean valid = PlantablesConfig.isValidSeed(plantId)
                                ? PlantablesConfig.isSoilValidForSeed(itemId, plantId)
                                : PlantablesConfig.isSoilValidForSapling(itemId, plantId);
                        if (!valid) return ItemStack.EMPTY;
                    }
                    slots.get(1).set(stack.copyWithCount(1));
                    stack.shrink(1);
                    return copy;
                }
            }
        } else {
            // BE slot → move to player inventory
            if (!moveItemStackTo(stack, 2, slots.size(), true)) return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) source.set(ItemStack.EMPTY);
        else source.setChanged();

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 52 + row * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(inv, col, 8 + col * 18, 111));
    }

    private static class RaisedBedSlot extends Slot {
        private final RaisedBedBlockEntity be;
        private final int index;

        public RaisedBedSlot(RaisedBedBlockEntity be, int index, int x, int y) {
            super(new net.minecraft.world.SimpleContainer(be.inventory.size()), index, x, y);
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
        public boolean mayPlace(ItemStack stack) {
            return be.inventory.isValid(index, ItemResource.of(stack));
        }

        @Override
        public ItemStack remove(int amount) {
            ItemStack existing = getItem();
            if (existing.isEmpty()) return ItemStack.EMPTY;
            int toExtract = Math.min(amount, existing.getCount());
            try (Transaction tx = Transaction.openRoot()) {
                int extracted = be.inventory.extract(index, ItemResource.of(existing), toExtract, tx);
                tx.commit();
                return new ItemStack(existing.getItem(), extracted);
            }
        }
    }
}