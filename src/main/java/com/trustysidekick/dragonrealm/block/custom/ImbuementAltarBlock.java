package com.trustysidekick.dragonrealm.block.custom;

import com.mojang.serialization.MapCodec;
import com.trustysidekick.dragonrealm.block.entity.ImplementedInventory;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.ImbuementAltarBlockEntity;
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
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ImbuementAltarBlockEntity) {
                ImplementedInventory inventory = (ImbuementAltarBlockEntity) blockEntity;



                if (!player.isSneaking() && hit.getSide() == Direction.UP) {
                    double hitX = hit.getPos().getX() - pos.getX();
                    double hitZ = hit.getPos().getZ() - pos.getZ();

                    int finalX = -1;
                    int finalZ = -1;
                    int slot;

                    if (hitX >= 0.21 && hitX <= 0.35) {
                        finalX = 0;
                    } else if (hitX >= 0.42 && hitX <= 0.57) {
                        finalX = 1;
                    } else if (hitX >= 0.65 && hitX <= 0.79) {
                        finalX = 2;
                    }

                    if (hitZ >= 0.21 && hitZ <= 0.35) {
                        finalZ = 0;
                    } else if (hitZ >= 0.42 && hitZ <= 0.57) {
                        finalZ = 1;
                    } else if (hitZ >= 0.65 && hitZ <= 0.79) {
                        finalZ = 2;
                    }

                    System.out.println("HitX: " + hitX);
                    System.out.println("HitZ: " + hitZ);

                    if (finalX != -1 && finalZ != -1) {
                        slot = finalX + finalZ * 3;

                        if (!player.getStackInHand(hand).isEmpty()) {
                            if (inventory.getStack(slot).isEmpty()) {
                                inventory.setStack(slot, new ItemStack(player.getStackInHand(hand).getItem(), 1));
                                player.getInventory().getMainHandStack().decrement(1);
                                inventory.markDirty();
                            }
                        } else {
                            ItemStack extractedItem = inventory.getStack(slot);
                            if (!player.getInventory().insertStack(extractedItem)) {
                                player.getInventory().offerOrDrop(extractedItem);
                            }
                            inventory.getStack(slot).decrement(1);
                            inventory.markDirty();
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.SMITHING_ANVIL_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
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
