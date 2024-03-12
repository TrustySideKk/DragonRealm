package com.trustysidekick.dragonrealm.block.entity;

import com.mojang.datafixers.types.templates.Tag;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.state.property.Property;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;


public class DragonForgeBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1,ItemStack.EMPTY);
    private BlockPos checkDragon1;
    private BlockPos checkDragon2;
    private BlockPos checkDragon3;
    private BlockPos checkDragon4;
    private int cooking;
    public int progress = 0;
    public int maxProgress = 160;

    public DragonForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRAGON_FORGE_BLOCK_ENTITY, pos, state);

    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("dragon_forge_block.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("dragon_forge_block.progress");
    }


    public void tick(World world, BlockPos pos, BlockState state) {
        checkDragon1 = new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ());
        checkDragon2 = new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ());
        checkDragon3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 4);
        checkDragon4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 4);
        Inventory inv = (Inventory) world.getBlockEntity(pos);
        Random random = new Random();
        double ranParticleSpot = -1 + random.nextFloat() * (1 - -1);
        double ranParticleVelocity = 1 + random.nextFloat() * (1.5 - 1);
        double ranSpread = -0.05 + random.nextFloat() * (0.05 - -0.05);


        if (world.isClient) {
            if (inv.getStack(0).getItem() == Items.IRON_INGOT) {
                System.out.println(ranParticleSpot);
                if (world.getBlockState(checkDragon1).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, checkDragon1.getX(), checkDragon1.getY() + 0.5, checkDragon1.getZ() + 0.5,  -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0); }
                if (world.getBlockState(checkDragon1).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.FLAME, checkDragon1.getX(), checkDragon1.getY() + 0.5, checkDragon1.getZ() + 0.5, -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0); }

                if (world.getBlockState(checkDragon2).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, checkDragon2.getX() + 1, checkDragon2.getY() + 0.5, checkDragon2.getZ() + 0.5, ranParticleVelocity + 0.0, ranSpread + 0.0,  ranSpread + 0.0); }
                if (world.getBlockState(checkDragon2).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.FLAME, checkDragon2.getX() + 1, checkDragon2.getY() + 0.5, checkDragon2.getZ() + 0.5, ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0); }

                if (world.getBlockState(checkDragon3).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, checkDragon3.getX() + 0.5, checkDragon3.getY() + 0.5, checkDragon3.getZ() + 1, ranSpread + 0.0, ranSpread + 0.0, ranParticleVelocity + 0.0); }
                if (world.getBlockState(checkDragon3).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.FLAME, checkDragon3.getX() + 0.5, checkDragon3.getY() + 0.5, checkDragon3.getZ() + 1, ranSpread + 0.0, ranSpread + 0.0, ranParticleVelocity + 0.0); }

                if (world.getBlockState(checkDragon4).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, checkDragon4.getX() + 0.5, checkDragon4.getY() + 0.5, checkDragon4.getZ(),  ranSpread + 0.0,  ranSpread + 0.0, -ranParticleVelocity + 0.0); }
                if (world.getBlockState(checkDragon4).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.FLAME, checkDragon4.getX() + 0.5, checkDragon4.getY() + 0.5, checkDragon4.getZ(),  ranSpread + 0.0,  ranSpread + 0.0, -ranParticleVelocity + 0.0); }
            }
        }

        if (!inventory.get(0).isEmpty()) {
            if (world.getBlockState(checkDragon1).getBlock() == Blocks.COBBLESTONE || world.getBlockState(checkDragon2).getBlock() == Blocks.COBBLESTONE || world.getBlockState(checkDragon3).getBlock() == Blocks.COBBLESTONE || world.getBlockState(checkDragon4).getBlock() == Blocks.COBBLESTONE) {
                if (this.progress >= 160 && this.inventory.get(0).getItem() != ModItems.SEARING_IRON_INGOT) {
                        this.inventory.set(0, new ItemStack(ModItems.SEARING_IRON_INGOT, 1));
                        this.progress = 0;
                    }
                    this.progress++;
                } else {
                    this.progress = 0;
                }
            } else {
                this.progress = 0;
            }


    }

    @Override
    public DefaultedList<ItemStack> getItems() { return inventory; }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        if (inventory.get(0).getCount() == 0) {
            setStack(slot, stack);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        if (inventory.get(0).getItem() == ModItems.SEARING_IRON_INGOT) {
            return true;
        } else {
            return false;
        }
    }

    public ItemStack getRenderStack() {
        if (this.getStack(0).isEmpty()) {
            return this.getStack(0);
        } else {
            return this.getStack(0);
        }
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
}
