package com.trustysidekick.dragonrealm.block.entity;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.SingleStackInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;

public class DragonForgeBlockEntity extends BlockEntity implements Clearable, SingleStackInventory {
    private ItemStack stack;

    public DragonForgeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.stack = ItemStack.EMPTY;
    }

    @Override
    public ItemStack getStack() {
        return null;
    }

    @Override
    public ItemStack decreaseStack(int count) {
        return null;
    }

    @Override
    public void setStack(ItemStack stack) {

    }

    @Override
    public BlockEntity asBlockEntity() {
        return null;
    }
}
