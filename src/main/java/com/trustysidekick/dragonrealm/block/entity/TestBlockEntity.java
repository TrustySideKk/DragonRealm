package com.trustysidekick.dragonrealm.block.entity;

import com.google.common.annotations.VisibleForTesting;
import com.trustysidekick.dragonrealm.block.custom.TestBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SingleStackInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class TestBlockEntity extends BlockEntity implements Clearable, SingleStackInventory {
    private ItemStack inputStack;
    private long burnStartTick;
    private long tickCount;
    private boolean isBurning;
    private int ticksThisSecond;


    public TestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TEST_BLOCK_ENTITY, pos, state);
        this.inputStack = ItemStack.EMPTY;
    }




    @Override
    public ItemStack decreaseStack(int count) {
        return null;
    }




    @Override
    public BlockEntity asBlockEntity() {
        return null;
    }


    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("InputItem", 10)) {
            this.inputStack = ItemStack.fromNbt(nbt.getCompound("InputItem"));
        }

        this.isBurning = nbt.getBoolean("IsBurning");
        this.burnStartTick = nbt.getLong("BurnStartTick");
        this.tickCount = nbt.getLong("TickCount");
    }

    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.getStack().isEmpty()) {
            nbt.put("InputItem", this.getStack().writeNbt(new NbtCompound()));
        }

        nbt.putBoolean("IsBurning", this.isBurning);
        nbt.putLong("burnStartTick", this.burnStartTick);
        nbt.putLong("TickCount", this.tickCount);
    }

    public boolean isBurningIngot() {
        return !this.getStack().isEmpty() && this.isBurning;
    }

    @Override
    public ItemStack getStack() {
        return this.inputStack;
    }

    @Override
    public void setStack(ItemStack stack) {
        if (stack.isIn(ItemTags.IRON_ORES) && this.world != null) {
            this.inputStack = stack;
            this.updateState((Entity)null, true);
            this.startPlaying();
        } else if (stack.isEmpty()) {
            this.decreaseStack(1);
        }

    }

    private void updateState(@Nullable Entity entity, boolean hasIngot) {
        if (this.world.getBlockState(this.getPos()) == this.getCachedState()) {
            //this.world.setBlockState(this.getPos(), (BlockState)this.getCachedState().with(TestBlock.CRAFTING, crafting), 2);
            this.world.emitGameEvent(GameEvent.BLOCK_CHANGE, this.getPos(), GameEvent.Emitter.of(entity, this.getCachedState()));
        }

    }


    @VisibleForTesting
    public void startPlaying() {
        this.burnStartTick = this.tickCount;
        this.isBurning = true;
        this.world.updateNeighborsAlways(this.getPos(), this.getCachedState().getBlock());
        this.world.syncWorldEvent((PlayerEntity)null, 1010, this.getPos(), Item.getRawId(this.getStack().getItem()));
        this.markDirty();
    }



    private void tick(World world, BlockPos pos, BlockState state) {
        //++this.ticksThisSecond;
        System.out.print("TICK!");

        ++this.tickCount;
    }


}
