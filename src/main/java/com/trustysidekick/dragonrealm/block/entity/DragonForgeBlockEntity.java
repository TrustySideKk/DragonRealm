package com.trustysidekick.dragonrealm.block.entity;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;


public class DragonForgeBlockEntity extends BlockEntity implements ImplementedInventory {
    public final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2,ItemStack.EMPTY);
    private boolean foundDragon;
    private Entity targetDragon;


    public DragonForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRAGON_FORGE_BLOCK_ENTITY, pos, state);
        foundDragon = false;
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
        foundDragon = false;

        Box box = new Box(pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5, pos.getX() - 5, pos.getY() - 5, pos.getZ() - 5);
        List<Entity> nearbyEntities = world.getOtherEntities(null, box);

        for (Entity entity : nearbyEntities) {
            if (entity instanceof DragonWhelpEntity) {
                foundDragon = true;
            }
        }

        if (!foundDragon) {
            targetDragon = null;
        }
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return false;
    }


    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return false;
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


    public boolean isReady() {
        if (this.inventory.get(0).getItem() != Items.AIR && this.inventory.get(0).getItem() != ModItems.SEARING_IRON_INGOT && (this.inventory.get(1).getItem() == Items.COAL && this.inventory.get(1).getCount() == 5)) {
            return true;
        } else {
            return false;
        }
    }

    public void setTargetDragon(Entity entity) {
        targetDragon = entity;
    }

    public Entity getTargetDragon() {
        return targetDragon;
    }



}
