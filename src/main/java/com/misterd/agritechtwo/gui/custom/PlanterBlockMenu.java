package com.misterd.agritechtwo.gui.custom;

import com.misterd.agritechtwo.block.custom.PlanterBlock;
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

        // TE slots
        addSlot(new PlanterSlot(blockEntity, 0, 8, 18));   // plant
        addSlot(new PlanterSlot(blockEntity, 1, 8, 54));   // soil
        addSlot(new FertilizerSlot(blockEntity, 2, 152, 18));

        int slot = 3;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                addSlot(new PlanterSlot(blockEntity, slot++, 62 + col * 18, 18 + row * 18));
            }
        }

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    // ------------------------------------------------------------------------
    // Shift-click
    // ------------------------------------------------------------------------

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot source = slots.get(index);
        if (source == null || !source.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = source.getItem();
        ItemStack copy = stack.copy();

        if (index < 15) {
            // From TE → player
            if (!moveItemStackTo(stack, 15, 51, true)) return ItemStack.EMPTY;
        } else {
            // From player → TE
            if (!moveToSpecialSlots(stack)) {
                if (!moveItemStackTo(stack, 0, 15, false)) return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            source.set(ItemStack.EMPTY);
        } else {
            source.setChanged();
        }

        source.onTake(player, stack);
        return copy;
    }

    private boolean moveToSpecialSlots(ItemStack stack) {
        String id = RegistryHelper.getItemId(stack);

        // Plant slot
        if ((PlantablesConfig.isValidSeed(id) || PlantablesConfig.isValidSapling(id))
                && blockEntity.getStack(0).isEmpty()) {

            insertSingle(stack, 0);
            return true;
        }

        // Soil slot
        if (PlantablesConfig.isValidSoil(id)
                && blockEntity.getStack(1).isEmpty()) {

            insertSingle(stack, 1);
            return true;
        }

        // Fertilizer slot
        if (PlantablesConfig.isValidFertilizer(id)
                && blockEntity.getStack(2).isEmpty()) {

            insertSingle(stack, 2);
            return true;
        }

        return false;
    }

    private void insertSingle(ItemStack stack, int slot) {
        try (Transaction tx = Transaction.openRoot()) {
            blockEntity.inventory.insert(slot, ItemResource.of(stack), 1, tx);
            tx.commit();
        }
        stack.shrink(1);
    }

    // ------------------------------------------------------------------------
    // Validity
    // ------------------------------------------------------------------------

    @Override
    public boolean stillValid(Player player) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player,
                blockEntity.getBlockState().getBlock()
        );
    }

    // ------------------------------------------------------------------------
    // Player inventory
    // ------------------------------------------------------------------------

    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 88 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int i = 0; i < 9; i++) {
            addSlot(new Slot(inv, i, 8 + i * 18, 147));
        }
    }

    // ------------------------------------------------------------------------
    // Custom Slots
    // ------------------------------------------------------------------------

    private static class PlanterSlot extends Slot {
        protected final PlanterBlockEntity be;
        protected final int index;

        public PlanterSlot(PlanterBlockEntity be, int index, int x, int y) {
            super(new SimpleContainer(be.inventory.size()), index, x, y);
            this.be = be;
            this.index = index;
        }

        @Override
        public ItemStack getItem() {
            return be.getStack(index);
        }

        @Override
        public void set(ItemStack stack) {
            try (Transaction tx = Transaction.openRoot()) {
                ItemStack existing = be.getStack(index);

                if (!existing.isEmpty()) {
                    be.inventory.extract(index, ItemResource.of(existing), existing.getCount(), tx);
                }

                if (!stack.isEmpty()) {
                    be.inventory.insert(index, ItemResource.of(stack), stack.getCount(), tx);
                }

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