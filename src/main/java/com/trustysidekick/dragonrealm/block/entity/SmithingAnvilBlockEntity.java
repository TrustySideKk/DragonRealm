package com.trustysidekick.dragonrealm.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SmithingAnvilBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public SmithingAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SMITHING_ANVIL_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        return this.getStack(slot).isEmpty();
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return !this.getStack(slot).isEmpty();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        // Save inventory state or perform other actions when inventory changes
    }


    public void insertItem(int x, int z, ItemStack stack, PlayerEntity player) {
        int slot = x + z * 3;

        if (inventory.get(slot).isEmpty()) {
            inventory.set(slot, new ItemStack(stack.getItem(), 1));
            //inventory.set(slot, stack.copy());
            player.getInventory().getMainHandStack().decrement(1);
            markDirty(); // Mark the block entity as dirty to save the changes
        }

    }

    public void tick(World world, BlockPos pos, BlockState state) {
        /*
        System.out.println("Slot 1: " + inventory.get(0));
        System.out.println("Slot 2: " + inventory.get(1));
        System.out.println("Slot 3: " + inventory.get(2));
        System.out.println("Slot 4: " + inventory.get(3));
        System.out.println("Slot 5: " + inventory.get(4));
        System.out.println("Slot 6: " + inventory.get(5));
        System.out.println("Slot 7: " + inventory.get(6));
        System.out.println("Slot 8: " + inventory.get(7));
        System.out.println("Slot 9: " + inventory.get(8));

         */
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        //nbt.putInt("dragon_forge_block.progress", this.progress);
    }


    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        //this.progress = nbt.getInt("dragon_forge_block.progress");
    }


    public ItemStack getRenderStack() {
        if (this.inventory.get(0).isEmpty()) {
            return new ItemStack(Items.AIR, 1);
        } else {
            return this.inventory.get(0);
        }
    }



    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }


}
