package com.night.tek.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.night.tek.item.ModItems;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class FormerTile extends TileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public FormerTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public FormerTile() {
        this(ModTileEntities.FORMER_TILE.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(5) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:
                        return stack.getItem() == ModItems.CRYSTAL.get();
                    case 1:
                        return stack.getItem() == ModItems.CRYSTAL.get();
                    case 2:
                        return stack.getItem() == ModItems.CRYSTAL.get();
                    case 3:
                        return stack.getItem() == ModItems.CRYSTAL.get();
                    case 4:
                        return stack.getItem() == ModItems.LENS.get();

                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void generate() {
        boolean ready0 = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == ModItems.CRYSTAL.get();
        boolean ready1 = this.itemHandler.getStackInSlot(1).getCount() > 0
                && this.itemHandler.getStackInSlot(1).getItem() == ModItems.CRYSTAL.get();
        boolean ready2 = this.itemHandler.getStackInSlot(2).getCount() > 0
                && this.itemHandler.getStackInSlot(2).getItem() == ModItems.CRYSTAL.get();
        boolean ready3 = this.itemHandler.getStackInSlot(3).getCount() > 0
                && this.itemHandler.getStackInSlot(3).getItem() == ModItems.CRYSTAL.get();

        if (ready0 && ready1 && ready2 && ready3) {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);
            this.itemHandler.getStackInSlot(2).shrink(1);
            this.itemHandler.getStackInSlot(3).shrink(1);

            this.itemHandler.insertItem(4, new ItemStack(ModItems.LENS.get()), false);
        }

    }
}