package com.trustysidekick.dragonrealm.block.custom;

import com.mojang.serialization.MapCodec;
import com.trustysidekick.dragonrealm.block.entity.ImplementedInventory;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class DragonForgeBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = DragonForgeBlock.createCuboidShape(0, 0, 0, 16, 12, 16);
    public static final BooleanProperty BURNING = BooleanProperty.of("burning");


    public DragonForgeBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(BURNING, false));
    }


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DragonForgeBlockEntity(pos, state);
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DragonForgeBlockEntity) {
                ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ImplementedInventory) {
                ImplementedInventory inventoryBlockEntity = (ImplementedInventory) blockEntity;

                if (inventoryBlockEntity.isEmpty()) {
                    if (!player.getStackInHand(hand).isEmpty()) {
                        ItemStack heldItem = player.getStackInHand(hand);
                        inventoryBlockEntity.setStack(0, new ItemStack(heldItem.getItem(), 1));
                        player.getInventory().getMainHandStack().decrement(1);
                        inventoryBlockEntity.markDirty();
                    }
                } else {
                    if (player.getStackInHand(hand).isEmpty()) {
                        ItemStack extractedItem = inventoryBlockEntity.getStack(0);
                        ((DragonForgeBlockEntity) blockEntity).progress = 0;
                        if (!player.getInventory().insertStack(extractedItem)) {
                            //player.dropItem(extractedItem, false);
                            player.getInventory().offerOrDrop(extractedItem);
                        }
                        inventoryBlockEntity.getStack(0).decrement(1);
                        inventoryBlockEntity.markDirty();
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.DRAGON_FORGE_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BURNING);
    }
}