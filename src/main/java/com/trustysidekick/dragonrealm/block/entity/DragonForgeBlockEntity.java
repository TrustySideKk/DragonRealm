package com.trustysidekick.dragonrealm.block.entity;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.entity.custom.PorcupineEntity;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;


public class DragonForgeBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1,ItemStack.EMPTY);
    public int progress = 0;
    private int dragonCount = 0;
    public static Entity[] attachedDragons = new Entity[4];

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
        System.out.println(attachedDragons.length);

        if (!world.isClient) {
            for (Entity dragon : attachedDragons) {
                if (dragon != null) {
                    //System.out.println(dragon.getUuidAsString());
                }
            }
        }


        Box box = new Box(pos.getX() + 5, pos.getY() + 5, pos.getZ() + 5, pos.getX() - 5, pos.getY() - 5, pos.getZ() - 5);
        List<Entity> nearbyEntities = world.getOtherEntities(null, box);

        for (Entity entity : nearbyEntities) {
            if (entity instanceof PorcupineEntity) {
                if (this.dragonCount < 3) {
                    this.dragonCount = this.dragonCount + 1;
                }
            }
        }



        Inventory blockInventory = (Inventory) world.getBlockEntity(pos);


        if (inventory.get(0).getItem() == Items.IRON_INGOT) {
            if (dragonCount > 0) {
                world.setBlockState(pos, state.with(DragonForgeBlock.BURNING, true));
                if (world.getBlockState(pos).get(DragonForgeBlock.BURNING)) {
                    if (this.progress >= (160 / this.dragonCount)) {
                        this.inventory.set(0, new ItemStack(ModItems.SEARING_IRON_INGOT, 1));
                        this.progress = 0;
                        world.setBlockState(pos, state.with(DragonForgeBlock.BURNING, false));
                    } else {
                        this.progress++;
                    }
                }
                }
        } else {
            this.dragonCount = 0;
            this.progress = 0;
            world.setBlockState(pos, state.with(DragonForgeBlock.BURNING, false));
            this.markDirty();
        }
    }






    @Override
    public DefaultedList<ItemStack> getItems() { return inventory; }



    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        if (!inventory.get(0).isEmpty()) {
            return false;
        } else {
            setStack(slot, stack);
            return true;
        }
    }


    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return false;
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
