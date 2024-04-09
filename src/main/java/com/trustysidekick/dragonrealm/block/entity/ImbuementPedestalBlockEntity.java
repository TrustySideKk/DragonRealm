package com.trustysidekick.dragonrealm.block.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

//@Environment(EnvType.CLIENT)
public class ImbuementPedestalBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
    private int tick = 0;

    public ImbuementPedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IMBUEMENT_PEDESTAL_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) { return; }
/*
        if (tick >= 20) {


            System.out.println("Slot 1: " + inventory.get(0));
            System.out.println("Slot 2: " + inventory.get(1));
            System.out.println("Slot 3: " + inventory.get(2));
            System.out.println("Slot 4: " + inventory.get(3));
            System.out.println("Slot 5: " + inventory.get(4));
            System.out.println("Slot 6: " + inventory.get(5));
            System.out.println("Slot 7: " + inventory.get(6));
            System.out.println("Slot 8: " + inventory.get(7));
            System.out.println("Slot 9: " + inventory.get(8));

            tick = 0;
        } else {
            tick++;
        }
        */

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        return this.inventory.get(slot).isEmpty();
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return !this.inventory.get(slot).isEmpty();
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }


    public ItemStack getRenderStack(int slot) {
        return this.getStack(slot);
    }


}
