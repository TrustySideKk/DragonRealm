package com.trustysidekick.dragonrealm.block.entity;

import com.ibm.icu.text.MessagePattern;
import com.trustysidekick.dragonrealm.block.custom.QuenchTankBlock;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Random;

import static com.trustysidekick.dragonrealm.block.custom.QuenchTankBlock.TANK;

public class QuenchTankBlockEntity extends BlockEntity implements ImplementedInventory{
    public final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2,ItemStack.EMPTY);
    private long stopTick;
    public boolean isQuenching;

    public QuenchTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUENCH_TANK_BLOCK_ENTITY, pos, state);
        this.stopTick = 160;
        this.isQuenching = false;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        //if (world.isClient) { return; }
        BlockState blockState = world.getBlockState(pos);
        boolean randomBoolean = new Random().nextBoolean();
        double particlePosX = (Math.random() * (0.3 + 0.3)) - 0.3;
        double particlePosZ = (Math.random() * (0.3 + 0.3)) - 0.3;

        if (inventory.get(0).getItem() == ModItems.SEARING_IRON_INGOT && inventory.get(1).getCount() == 3) {
            isQuenching = true;
            if (stopTick <= 0) {
                inventory.clear();
                inventory.set(0, new ItemStack(ModItems.DRAGON_INGOT, 1));
                world.setBlockState(pos, blockState.with(TANK, 0));
                this.markDirty();
                stopTick = 160;
                isQuenching = false;
            }
            if (stopTick >= 107 && stopTick <= 160) {
                if (randomBoolean) { world.addParticle(ParticleTypes.DUST_PLUME, (pos.getX() + 0.5) + particlePosX, pos.getY() + 0.75, (pos.getZ() + 0.5) + particlePosZ, 0.0, 0.0, 0.0); }

            }
            if (stopTick >= 54 && stopTick <= 106) {
                world.setBlockState(pos, blockState.with(TANK, 2));
                if (randomBoolean) { world.addParticle(ParticleTypes.DUST_PLUME, (pos.getX() + 0.5) + particlePosX, pos.getY() + 0.5, (pos.getZ() + 0.5) + particlePosZ, 0.0, 0.0, 0.0); }
            }
            if (stopTick >= 1 && stopTick <= 53) {
                world.setBlockState(pos, blockState.with(TANK, 1));
                if (randomBoolean) { world.addParticle(ParticleTypes.DUST_PLUME, (pos.getX() + 0.5) + particlePosX, pos.getY() + 0.35, (pos.getZ() + 0.5) + particlePosZ, 0.0, 0.0, 0.0); }
            }
            world.playSound(null, pos, SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS, 10.0f, 1.0f);
            stopTick--;
        }

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
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


    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return false;
    }


    public ItemStack getRenderStack(int slot) {
        return this.getStack(slot);
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

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }
}
