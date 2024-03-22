package com.trustysidekick.dragonrealm.block.custom;

import com.mojang.serialization.MapCodec;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.LeadItem;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.IMarkerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DragonForgeBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = DragonForgeBlock.createCuboidShape(0,0,0,16,12,16);
    public static final BooleanProperty BURNING = BooleanProperty.of("burning");
    public static final IntProperty DRAGON_COUNT = IntProperty.of("dragon_count",0, 3);
    public int attachedDragons = 0;



    public DragonForgeBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(BURNING, false));
        setDefaultState(getDefaultState().with(DRAGON_COUNT,0));
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
        //if (world.isClient) { return ActionResult.SUCCESS; }


        Inventory blockEntity = (Inventory) world.getBlockEntity(pos);

        if (!player.getStackInHand(hand).isEmpty() && (player.getStackInHand(hand).getItem() != Items.LEAD)) {
            if (blockEntity.getStack(0).isEmpty()) {
                blockEntity.setStack(0, player.getStackInHand(hand).copy());
                player.getStackInHand(hand).decrement(1);
            }
        } else {
            Item item = blockEntity.getStack(0).getItem();
            ItemStack stack = new ItemStack(item, 1);

            player.getInventory().offerOrDrop(stack);
            blockEntity.removeStack(0);
        }

        if (player.getStackInHand(hand).getItem() == Items.LEAD) {
            LeadItem.attachHeldMobsToBlock(player, world, pos);
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
        builder.add(DRAGON_COUNT);
    }




}