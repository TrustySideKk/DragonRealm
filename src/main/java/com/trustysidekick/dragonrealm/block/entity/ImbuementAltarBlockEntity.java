package com.trustysidekick.dragonrealm.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

//@Environment(EnvType.CLIENT)
public class ImbuementAltarBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int stopTick;
    public boolean isImbuing;


    public ImbuementAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IMBUEMENT_ALTAR_BLOCK_ENTITY, pos, state);
        this.stopTick = 300;
        this.isImbuing = false;
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
        //if (world.isClient) { return; }

        if (!inventory.get(0).isEmpty()) {
            //this.isImbuing = true;

            BlockPos pedestalBoxPos1 = this.getPos().add(-7, -7, -7);
            BlockPos pedestalBoxPos2 = this.getPos().add(7,7,7);

            Iterable<BlockPos> pedestalsInRange = BlockPos.iterate(pedestalBoxPos1,pedestalBoxPos2);

            for (BlockPos blockPos : pedestalsInRange) {
                BlockEntity blockEntity = this.getWorld().getBlockEntity(blockPos);

                if (blockEntity instanceof ImbuementPedestalBlockEntity) {
                    ImplementedInventory inventoryBlockEntity = (ImplementedInventory) blockEntity;
                    ((ImbuementPedestalBlockEntity)blockEntity).isImbuing = true;
                    ((ImbuementPedestalBlockEntity)blockEntity).targetAltar = this;
                    if (!inventoryBlockEntity.getStack(0).isEmpty()) {

                        //shootFireballAtBlock(this.getPos(), blockEntity.getPos());

                        if (stopTick == 300) {
                            this.getWorld().addParticle(ParticleTypes.CRIMSON_SPORE, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                            //this.getWorld().addParticle(ParticleTypes.FLAME, posX, posY, posZ, 0.0, 0.0, 0.0);

                        }
                        if (stopTick == 260) {
                            //this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                            spawnParticlesFromAltarToPedestal(this.getPos(), blockEntity.getPos());
                        }
                        if (stopTick == 220) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 200) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 180) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 160) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 140) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 120) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 100) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 90) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 80) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick == 70) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick > 0 && stopTick <= 69) {
                            this.getWorld().addParticle(ParticleTypes.FLAME, (blockEntity.getPos().getX() + 0.5), blockEntity.getPos().getY() + 0.75, (blockEntity.getPos().getZ() + 0.5), 0.0, 0.0, 0.0);
                        }
                        if (stopTick <= 0) {
                            inventoryBlockEntity.getStack(0).decrement(1);
                            stopTick = 300;
                            this.isImbuing = false;
                            inventoryBlockEntity.markDirty();
                        }
                    } else {
                        ((ImbuementPedestalBlockEntity)blockEntity).isImbuing = false;
                    }

                }
            }
            stopTick--;
        } else {
            this.isImbuing = false;

            BlockPos pedestalBoxPos1 = this.getPos().add(-7, -7, -7);
            BlockPos pedestalBoxPos2 = this.getPos().add(7,7,7);

            Iterable<BlockPos> blocksInRange = BlockPos.iterate(pedestalBoxPos1,pedestalBoxPos2);

            for (BlockPos blockPos : blocksInRange) {
                BlockEntity blockEntity = this.getWorld().getBlockEntity(blockPos);

                if (blockEntity instanceof ImbuementPedestalBlockEntity) {
                    ((ImbuementPedestalBlockEntity)blockEntity).isImbuing = false;
                }
            }
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
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


    private void spawnParticlesFromAltarToPedestal(BlockPos altarPos, BlockPos pedestalPos) {
        double startX = pedestalPos.getX() + 0.5;
        double startY = pedestalPos.getY() + 0.5;
        double startZ = pedestalPos.getZ() + 0.5;
        double endX = altarPos.getX() + 0.5;
        double endY = altarPos.getY() + 0.5;
        double endZ = altarPos.getZ() + 0.5;

        // Calculate the distance between the altar and the pedestal
        double distanceX = endX - startX;
        double distanceY = endY - startY;
        double distanceZ = endZ - startZ;

        // Calculate the time of flight
        double timeOfFlight = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ) / 0.5; // Adjust the speed as needed

        // Calculate the initial vertical velocity to achieve the desired arc
        double initialVerticalVelocity = (distanceY - 0.5 * (-9.81) * timeOfFlight * timeOfFlight) / timeOfFlight;

        // Spawn particles along the trajectory
        int numSteps = (int) (timeOfFlight * 20); // Adjust the density of particles
        for (int i = 0; i < numSteps; i++) {
            double t = (double) i / 20.0;
            double posX = startX + distanceX * t;
            double posY = startY + initialVerticalVelocity * t + 0.5 * (-9.81) * t * t;
            double posZ = startZ + distanceZ * t;
            this.getWorld().addParticle(ParticleTypes.FLAME, posX, posY, posZ, 0.0, 0.0, 0.0);
        }
    }

    public void shootFireballAtBlock(BlockPos altarPos, BlockPos pedestalPos) {
        // Calculate the direction vector from the altar to the pedestal
        Vec3d direction = new Vec3d(pedestalPos.getX() - altarPos.getX(), pedestalPos.getY() - altarPos.getY(), pedestalPos.getZ() - altarPos.getZ()).normalize();

        // Calculate the yaw and pitch angles based on the direction vector
        double yaw = Math.toDegrees(Math.atan2(direction.getZ(), direction.getX())); // Yaw is based on Z and X components
        double pitch = Math.toDegrees(Math.asin(direction.getY())); // Pitch is based on Y component

        // Spawn fire particles
        Random random = new Random();
        int numParticles = 10;
        for (int i = 0; i < numParticles; i++) {
            double offsetX = random.nextDouble() * 0.007; // Randomize particle position
            double offsetY = random.nextDouble() * 0.007;
            double offsetZ = random.nextDouble() * 0.007;
            SmallFireballEntity fireball = new SmallFireballEntity(this.getWorld(), altarPos.getX(), altarPos.getY(), altarPos.getZ(), direction.getX() + offsetX, direction.getY() + offsetY, direction.getZ() + offsetZ);
            this.getWorld().spawnEntity(fireball);
        }
    }


}
