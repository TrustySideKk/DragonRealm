package com.trustysidekick.dragonrealm.block.custom;


import com.mojang.serialization.MapCodec;
import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import com.trustysidekick.dragonrealm.block.entity.ImplementedInventory;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.QuenchTankBlockEntity;
import com.trustysidekick.dragonrealm.item.ModItemGroups;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class QuenchTankBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final IntProperty TANK = IntProperty.of("tank", 0, 3);
    private static final VoxelShape SHAPE = QuenchTankBlock.createCuboidShape(0, 0, 0, 16, 12, 16);

    public QuenchTankBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(TANK, 0));
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            BlockState blockState = world.getBlockState(pos);

            if (player.getStackInHand(hand).isEmpty()) {
                switch (blockState.get(TANK)) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }

            if (player.getStackInHand(hand).getItem() == ModItems.DRAGON_BLOOD) {
                switch (blockState.get(TANK)) {
                    case 0:
                        world.setBlockState(pos, blockState.with(TANK, 1));
                        if (!player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE, 1))) {
                            player.dropItem(new ItemStack(Items.GLASS_BOTTLE, 1), false);
                        }
                        player.getStackInHand(hand).decrement(1);
                        break;
                    case 1:
                        world.setBlockState(pos, blockState.with(TANK, 2));
                        if (!player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE, 1))) {
                            player.dropItem(new ItemStack(Items.GLASS_BOTTLE, 1), false);
                        }
                        player.getStackInHand(hand).decrement(1);
                        break;
                    case 2:
                        world.setBlockState(pos, blockState.with(TANK, 3));
                        if (!player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE, 1))) {
                            player.dropItem(new ItemStack(Items.GLASS_BOTTLE, 1), false);
                        }
                        player.getStackInHand(hand).decrement(1);
                        break;
                    case 3:
                        break;
                }

            }

            if (player.getStackInHand(hand).getItem() == Items.GLASS_BOTTLE) {
                switch (blockState.get(TANK)) {
                    case 0:
                        break;
                    case 1:
                        world.setBlockState(pos, blockState.with(TANK, 0));
                        if (!player.getInventory().insertStack(new ItemStack(ModItems.DRAGON_BLOOD, 1))) {
                            player.dropItem(new ItemStack(ModItems.DRAGON_BLOOD, 1), false);
                        }
                        player.getStackInHand(hand).decrement(1);
                        break;
                    case 2:
                        world.setBlockState(pos, blockState.with(TANK, 1));
                        if (!player.getInventory().insertStack(new ItemStack(ModItems.DRAGON_BLOOD, 1))) {
                            player.dropItem(new ItemStack(ModItems.DRAGON_BLOOD, 1), false);
                        }
                        player.getStackInHand(hand).decrement(1);
                        break;
                    case 3:
                        world.setBlockState(pos, blockState.with(TANK, 2));
                        if (!player.getInventory().insertStack(new ItemStack(ModItems.DRAGON_BLOOD, 1))) {
                            player.dropItem(new ItemStack(ModItems.DRAGON_BLOOD, 1), false);
                        }
                        player.getStackInHand(hand).decrement(1);
                        break;
                }

            }

            if (player.getStackInHand(hand).getItem() == ModItems.SEARING_IRON_INGOT) {
                if (blockState.get(TANK) == 3) {
                    world.setBlockState(pos, blockState.with(TANK, 0));
                    if (!player.getInventory().insertStack(new ItemStack(ModItems.DRAGON_INGOT, 1))) {
                        player.dropItem(new ItemStack(ModItems.DRAGON_INGOT, 1), false);
                    }
                    player.getStackInHand(hand).decrement(1);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new QuenchTankBlockEntity(pos, state);
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TANK);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.QUENCH_TANK_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
