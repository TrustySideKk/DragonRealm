package com.trustysidekick.dragonrealm.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SmithingBenchBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(16, ItemStack.EMPTY);

    public SmithingBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SMITHING_BENCH_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
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

    public void tick(World world, BlockPos pos, BlockState state) {
        System.out.println("Smithing Bench Tick");
    }



    // Determine the slot based on the hit position and face
    private int determineSlotFromHit(Direction face, Vec3d hitPos, BlockPos blockPos) {
        // Logic to determine slot based on hit position and face
        // You'll need to implement your own logic here based on your block's design
        // This example assumes a simple layout where slots are arranged linearly
        double yOffset = hitPos.y - blockPos.getY(); // Adjust based on block's height
        int row = (int) (yOffset * 4); // Assuming 4 rows of slots vertically
        int col = 0; // Assuming slots are arranged in a single row horizontally
        return col + row * 4; // Assuming 4 slots per row
    }


}
