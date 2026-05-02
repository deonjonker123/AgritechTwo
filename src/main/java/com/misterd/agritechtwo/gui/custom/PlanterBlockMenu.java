package com.misterd.agritechtwo.gui.custom;

import com.misterd.agritechtwo.blockentity.custom.PlanterBlockEntity;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.gui.ATMenuTypes;
import com.misterd.agritechtwo.util.RegistryHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

public class PlanterBlockMenu extends AbstractContainerMenu {

    public final PlanterBlockEntity blockEntity;
    private final Level level;

    public PlanterBlockMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, inv.player.level().getBlockEntity(buf.readBlockPos()));
    }

    public PlanterBlockMenu(int id, Inventory inv, BlockEntity be) {
        super(ATMenuTypes.PLANTER_BLOCK_MENU.get(), id);
        this.blockEntity = (PlanterBlockEntity) be;
        this.level = inv.player.level();

        addSlot(new PlanterSlot(blockEntity, 0, 8, 18));
        addSlot(new PlanterSlot(blockEntity, 1, 8, 54));
        addSlot(new FertilizerSlot(blockEntity, 2, 152, 18));

        int slot = 3;
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 4; col++)
                addSlot(new PlanterSlot(blockEntity, slot++, 62 + col * 18, 18 + row * 18));

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem();
        ItemStack copy = stack.copy();

        if (index < 15) {
            if (!moveItemStackTo(stack, 15, 51, true)) return ItemStack.EMPTY;
        } else {
            if (!moveToSpecialSlots(stack)) return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) source.set(ItemStack.EMPTY);
        else source.setChanged();

        source.onTake(player, stack);
        return copy;
    }

    private boolean moveToSpecialSlots(ItemStack stack) {
        String id = RegistryHelper.getItemId(stack);

        if ((PlantablesConfig.isValidSeed(id) || PlantablesConfig.isValidSapling(id))
                && blockEntity.getStack(0).isEmpty()) {
            ItemStack existingSoil = blockEntity.getStack(1);
            if (!existingSoil.isEmpty()) {
                String soilId = RegistryHelper.getItemId(existingSoil);
                boolean valid = PlantablesConfig.isValidSeed(id)
                        ? PlantablesConfig.isSoilValidForSeed(soilId, id)
                        : PlantablesConfig.isSoilValidForSapling(soilId, id);
                if (!valid) return false;
            }
            insertSingle(stack, 0);
            return true;
        }

        if (PlantablesConfig.isValidSoil(id) && blockEntity.getStack(1).isEmpty()) {
            ItemStack existingPlant = blockEntity.getStack(0);
            if (!existingPlant.isEmpty()) {
                String plantId = RegistryHelper.getItemId(existingPlant);
                boolean valid = PlantablesConfig.isValidSeed(plantId)
                        ? PlantablesConfig.isSoilValidForSeed(id, plantId)
                        : PlantablesConfig.isSoilValidForSapling(id, plantId);
                if (!valid) return false;
            }
            insertSingle(stack, 1);
            return true;
        }

        if (PlantablesConfig.isValidFertilizer(id)) {
            return insertIntoBlockEntity(stack, 2, 3);
        }

        return false;
    }

    private boolean insertIntoBlockEntity(ItemStack stack, int startSlot, int endSlot) {
        if (stack.isEmpty()) return false;
        int inserted = 0;

        for (int i = startSlot; i < endSlot && !stack.isEmpty(); i++) {
            ItemStack existing = blockEntity.getStack(i);
            if (existing.isEmpty() || !ItemStack.isSameItemSameComponents(existing, stack)) continue;
            int space = stack.getMaxStackSize() - existing.getCount();
            if (space <= 0) continue;
            int toInsert = Math.min(space, stack.getCount());
            try (Transaction tx = Transaction.openRoot()) {
                int actual = blockEntity.inventory.insert(i, ItemResource.of(stack), toInsert, tx);
                tx.commit();
                stack.shrink(actual);
                inserted += actual;
            }
        }

        for (int i = startSlot; i < endSlot && !stack.isEmpty(); i++) {
            if (!blockEntity.getStack(i).isEmpty()) continue;
            if (!blockEntity.inventory.isValid(i, ItemResource.of(stack))) continue;
            int toInsert = Math.min(stack.getMaxStackSize(), stack.getCount());
            try (Transaction tx = Transaction.openRoot()) {
                int actual = blockEntity.inventory.insert(i, ItemResource.of(stack), toInsert, tx);
                tx.commit();
                stack.shrink(actual);
                inserted += actual;
            }
        }

        return inserted > 0;
    }

    private void insertSingle(ItemStack stack, int slot) {
        try (Transaction tx = Transaction.openRoot()) {
            blockEntity.inventory.insert(slot, ItemResource.of(stack), 1, tx);
            tx.commit();
        }
        stack.shrink(1);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player,
                blockEntity.getBlockState().getBlock()
        );
    }

    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 88 + row * 18));
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; i++)
            addSlot(new Slot(inv, i, 8 + i * 18, 147));
    }

    private static class PlanterSlot extends Slot {
        protected final PlanterBlockEntity be;
        protected final int index;

        public PlanterSlot(PlanterBlockEntity be, int index, int x, int y) {
            super(new SimpleContainer(be.inventory.size()), index, x, y);
            this.be = be;
            this.index = index;
        }

        @Override public ItemStack getItem() { return be.getStack(index); }

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

    private static class FertilizerSlot extends PlanterSlot {
        public FertilizerSlot(PlanterBlockEntity be, int index, int x, int y) {
            super(be, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return PlantablesConfig.isValidFertilizer(RegistryHelper.getItemId(stack));
        }
    }
}