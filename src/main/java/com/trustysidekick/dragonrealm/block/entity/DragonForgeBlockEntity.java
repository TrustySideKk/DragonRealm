package com.trustysidekick.dragonrealm.block.entity;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.BlockState;
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
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1,ItemStack.EMPTY);
    public int progress = 0;
    private boolean foundDragon;


    public DragonForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRAGON_FORGE_BLOCK_ENTITY, pos, state);
        foundDragon = false;
    }


    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("dragon_forge_block.progress", this.progress);
    }


    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        this.progress = nbt.getInt("dragon_forge_block.progress");
    }


    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) { return; }

        //if (!world.isClient) {
            if (!inventory.get(0).isEmpty() && inventory.get(0).getItem() != ModItems.SEARING_IRON_INGOT) {

                Box box = new Box(pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5, pos.getX() - 5, pos.getY() - 5, pos.getZ() - 5);
                List<Entity> nearbyEntities = world.getOtherEntities(null, box);

                for (Entity entity : nearbyEntities) {
                    if (entity instanceof DragonWhelpEntity && ((DragonWhelpEntity) entity).targetForge == this.getPos()) {
                        foundDragon = true;
                        this.markDirty();
                        break;
                    }
                }

                if (!foundDragon) {
                    for (Entity entity : nearbyEntities) {
                        if (entity instanceof DragonWhelpEntity) {
                            if (((DragonWhelpEntity) entity).targetForge == null) {
                                foundDragon = true;
                                ((DragonWhelpEntity) entity).targetForge = this.getPos();
                                this.markDirty();
                                break;
                            }
                        }
                    }
                } else {
                    if (this.progress >= 160) {
                        if (inventory.get(0).getItem() == Items.IRON_INGOT) {
                            this.inventory.set(0, new ItemStack(ModItems.SEARING_IRON_INGOT, 1));
                            this.markDirty();
                        } else {
                            this.removeStack(0, 1);
                            this.markDirty();
                        }

                        this.getWorld().setBlockState(this.getPos(), state.with(DragonForgeBlock.BURNING, false));
                        this.progress = 0;
                        this.markDirty();
                    } else {
                        this.getWorld().setBlockState(this.getPos(), state.with(DragonForgeBlock.BURNING, true));
                        this.progress++;
                        this.markDirty();
                    }
                }
            } else {
                Box box = new Box(pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5, pos.getX() - 5, pos.getY() - 5, pos.getZ() - 5);
                List<Entity> nearbyEntities = world.getOtherEntities(null, box);

                for (Entity entity : nearbyEntities) {
                    if (entity instanceof DragonWhelpEntity) {
                        foundDragon = false;
                        ((DragonWhelpEntity) entity).targetForge = null;
                        this.getWorld().setBlockState(this.getPos(), state.with(DragonForgeBlock.BURNING, false));
                        this.markDirty();
                    }
                }
            }
        //}
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return this.inventory.get(0).isEmpty();
    }


    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return !this.inventory.get(0).isEmpty();
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

    public ItemStack getRenderStack() {
        if(this.getStack(0).isEmpty()) {
            return this.getStack(0);
        } else {
            return this.getStack(0);
        }
    }


}
