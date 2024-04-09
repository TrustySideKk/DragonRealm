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
        BlockEntity blockEntity = world.getBlockEntity(pos);
        ImplementedInventory inventoryBlockEntity = (ImplementedInventory) blockEntity;
        ItemStack heldItem = player.getStackInHand(hand);
        BlockState blockState = world.getBlockState(pos);

        if (player.getStackInHand(hand).isEmpty()) {
            if (!(inventoryBlockEntity.getStack(0).isEmpty())) {
                if (!((QuenchTankBlockEntity)blockEntity).isQuenching) {
                    player.getInventory().offerOrDrop(inventoryBlockEntity.getStack(0));
                    inventoryBlockEntity.getStack(0).decrement(1);
                    blockEntity.markDirty();
                }
            }
        } else {
            if (heldItem.getItem() == ModItems.SEARING_IRON_INGOT) {
                if (inventoryBlockEntity.getStack(0).isEmpty()) {
                    inventoryBlockEntity.setStack(0, new ItemStack(heldItem.getItem(), 1));
                    heldItem.decrement(1);
                    blockEntity.markDirty();
                }
            }

            if (player.getStackInHand(hand).getItem() == ModItems.DRAGON_BLOOD) {
                if (inventoryBlockEntity.getStack(1).getCount() <= 2) {
                    int count = inventoryBlockEntity.getStack(1).getCount();
                    inventoryBlockEntity.setStack(1, new ItemStack(heldItem.getItem(), count + 1));
                    world.setBlockState(pos, blockState.with(TANK, count + 1));
                    player.getInventory().getMainHandStack().decrement(1);
                    player.getInventory().offerOrDrop(new ItemStack(Items.GLASS_BOTTLE, 1));
                    blockEntity.markDirty();
                }
            }

            if (heldItem.getItem() == Items.GLASS_BOTTLE) {
                if (!inventoryBlockEntity.getStack(1).isEmpty()) {
                    int count = inventoryBlockEntity.getStack(1).getCount();
                    inventoryBlockEntity.getStack(1).decrement(1);
                    player.getInventory().offerOrDrop(new ItemStack(ModItems.DRAGON_BLOOD, 1));
                    world.setBlockState(pos, blockState.with(TANK, count - 1));
                    heldItem.decrement(1);
                    blockEntity.markDirty();
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
