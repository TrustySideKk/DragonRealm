package com.trustysidekick.dragonrealm.block.custom;

import com.mojang.serialization.MapCodec;
import com.trustysidekick.dragonrealm.block.entity.ImplementedInventory;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.ImbuementAltarBlockEntity;
import com.trustysidekick.dragonrealm.block.entity.QuenchTankBlockEntity;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ImbuementAltarBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = ImbuementAltarBlock.createCuboidShape(0, 0, 0, 16, 14, 16);

    public ImbuementAltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ImbuementAltarBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        ImplementedInventory inventoryBlockEntity = (ImplementedInventory) blockEntity;
        ItemStack heldItem = player.getStackInHand(hand);
        BlockState blockState = world.getBlockState(pos);

        if (player.getStackInHand(hand).isEmpty()) {
            if (!(inventoryBlockEntity.getStack(0).isEmpty())) {
                if (!((ImbuementAltarBlockEntity) blockEntity).isImbuing) {
                    player.getInventory().offerOrDrop(inventoryBlockEntity.getStack(0));
                    inventoryBlockEntity.getStack(0).decrement(1);
                    blockEntity.markDirty();
                }
            }
        } else {
            if (inventoryBlockEntity.getStack(0).isEmpty()) {
                inventoryBlockEntity.setStack(0, new ItemStack(heldItem.getItem(), 1));
                heldItem.decrement(1);
                blockEntity.markDirty();
            }
        }



        return ActionResult.SUCCESS;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.IMBUEMENT_ALTAR_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ImbuementAltarBlockEntity) {
                ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

}
