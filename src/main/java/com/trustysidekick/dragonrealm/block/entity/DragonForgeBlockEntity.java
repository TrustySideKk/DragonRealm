package com.trustysidekick.dragonrealm.block.entity;

import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class DragonForgeBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1,ItemStack.EMPTY);
    //private static final int INPUT_SLOT = 0;

    protected final PropertyDelegate propertyDelegate;
    public int progress = 0;
    public int maxProgress = 160;

    public DragonForgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DRAGON_FORGE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DragonForgeBlockEntity.this.progress;
                    case 1 -> DragonForgeBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DragonForgeBlockEntity.this.progress = value;
                    case 1 -> DragonForgeBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            //public int size() { return 2; }
            public int size() { return 1; }
        };
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
        if (!world.isClient()) {
            //System.out.println("TICK");
        }

        if (!inventory.get(0).isEmpty()) {
            BlockPos checkDragon1 = new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ());
            BlockPos checkDragon2 = new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ());
            BlockPos checkDragon3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 2);
            BlockPos checkDragon4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 2);

            if (world.getBlockState(checkDragon1).getBlock() == Blocks.COBBLESTONE || world.getBlockState(checkDragon2).getBlock() == Blocks.COBBLESTONE || world.getBlockState(checkDragon3).getBlock() == Blocks.COBBLESTONE || world.getBlockState(checkDragon4).getBlock() == Blocks.COBBLESTONE) {
                System.out.println("Cobblestone!");

            }
            if (this.progress >= 160 && this.inventory.get(0).getItem() != Items.GOLD_INGOT) {
                this.inventory.set(0, new ItemStack(ModItems.SEARING_IRON_INGOT, 1));
            }
            this.progress++;
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
}
