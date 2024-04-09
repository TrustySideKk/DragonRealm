package com.trustysidekick.dragonrealm.block.entity;

import com.trustysidekick.dragonrealm.block.custom.SmithingAnvilBlock;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

//@Environment(EnvType.CLIENT)
public class SmithingAnvilBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);

    public SmithingAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SMITHING_ANVIL_BLOCK_ENTITY, pos, state);
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

        int strike = world.getBlockState(pos).get(SmithingAnvilBlock.STRIKE);
        if (strike == 5) {
            ImplementedInventory inventory = (SmithingAnvilBlockEntity) world.getBlockEntity(pos);

            if (canCraftDragonChestplate()) {
                inventory.clear();
                inventory.setStack(9, new ItemStack(ModItems.DRAGON_CHESTPLATE));
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
                inventory.markDirty();
            } else {
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
            }

            if (canCraftDragonHelmet()) {
                inventory.clear();
                inventory.setStack(9, new ItemStack(ModItems.DRAGON_HELMET));
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
                inventory.markDirty();
            } else {
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
            }

            if (canCraftDragonLeggings()) {
                inventory.clear();
                inventory.setStack(9, new ItemStack(ModItems.DRAGON_LEGGINGS));
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
                inventory.markDirty();
            } else {
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
            }

            if (canCraftDragonBoots()) {
                inventory.clear();
                inventory.setStack(9, new ItemStack(ModItems.DRAGON_BOOTS));
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
                inventory.markDirty();
            } else {
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
            }

            if (canCraftDragonBlock()) {
                inventory.clear();
                inventory.setStack(9, new ItemStack(Blocks.ANVIL));
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
                inventory.markDirty();
            } else {
                world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, 0));
            }
        }
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

    public boolean canCraftDragonChestplate (){
        if (     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT) || (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT) || (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == ModItems.DRAGON_INGOT) || (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)    ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canCraftDragonHelmet (){
        if (     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == Items.AIR && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == Items.AIR)     ||     (this.getStack(0).getItem() == Items.AIR && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == Items.AIR && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (this.getStack(0).getItem() == Items.AIR && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == Items.AIR && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == Items.AIR && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == Items.AIR)     ||     (this.getStack(0).getItem() == Items.AIR && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == Items.AIR && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == Items.AIR && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == Items.AIR)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == Items.AIR && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == Items.AIR)     ||     (this.getStack(0).getItem() == Items.AIR && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == Items.AIR && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canCraftDragonLeggings (){
        if (     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canCraftDragonBoots (){
        if (     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == Items.AIR && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == Items.AIR) || (this.getStack(0).getItem() == Items.AIR && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == Items.AIR && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (this.getStack(0).getItem() == Items.AIR && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == Items.AIR)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == Items.AIR && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == Items.AIR)     ||     (this.getStack(0).getItem() == Items.AIR && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == Items.AIR && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == Items.AIR && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == Items.AIR && this.getStack(7).getItem() == Items.AIR && this.getStack(8).getItem() == Items.AIR)     ||     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == Items.AIR && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == Items.AIR)     ||     (this.getStack(0).getItem() == Items.AIR && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == Items.AIR && this.getStack(4).getItem() == Items.AIR && this.getStack(5).getItem() == Items.AIR && this.getStack(6).getItem() == Items.AIR && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canCraftDragonBlock (){
        if (     (this.getStack(0).getItem() == ModItems.DRAGON_INGOT && this.getStack(1).getItem() == ModItems.DRAGON_INGOT && this.getStack(2).getItem() == ModItems.DRAGON_INGOT && this.getStack(3).getItem() == ModItems.DRAGON_INGOT && this.getStack(4).getItem() == ModItems.DRAGON_INGOT && this.getStack(5).getItem() == ModItems.DRAGON_INGOT && this.getStack(6).getItem() == ModItems.DRAGON_INGOT && this.getStack(7).getItem() == ModItems.DRAGON_INGOT && this.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ) {
            return true;
        } else {
            return false;
        }
    }


}
