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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

import java.util.ArrayList;
import java.util.List;

public class ImbuementAltarBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = ImbuementAltarBlock.createCuboidShape(0, 0, 0, 16, 14, 16);
    private static final List<Item> ITEM_LIST_SLOT0 = new ArrayList<>();
    private static final List<Item> ITEM_LIST_SLOT1 = new ArrayList<>();

    static {
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_HELMET);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_CHESTPLATE);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_LEGGINGS);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_BOOTS);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_AXE);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_PICKAXE);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_SHOVEL);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_HOE);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_BATTLEAXE);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_CROSSBOW);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_SWORD);
        ITEM_LIST_SLOT0.add(ModItems.DRAGON_SHIELD);
    }

    static {
        ITEM_LIST_SLOT1.add(Items.COAL);
    }


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
        ImplementedInventory inventoryBlockEntity = (ImplementedInventory) world.getBlockEntity(pos);

        if (player.getStackInHand(hand).isEmpty()) {
            if (!(inventoryBlockEntity.getStack(0).isEmpty())) {
                //if (!((ImbuementAltarBlockEntity) inventoryBlockEntity).isImbuing) {
                    player.getInventory().offerOrDrop(inventoryBlockEntity.getStack(0));
                    inventoryBlockEntity.getStack(0).decrement(1);
                    inventoryBlockEntity.markDirty();
                //}
            }
            if (!(inventoryBlockEntity.getStack(1).isEmpty())) {
                //if (!((ImbuementAltarBlockEntity) inventoryBlockEntity).isImbuing) {
                    player.getInventory().offerOrDrop(inventoryBlockEntity.getStack(1));
                    inventoryBlockEntity.getStack(1).decrement(1);
                    inventoryBlockEntity.markDirty();
                //}
            }
        } else if (ITEM_LIST_SLOT0.contains(player.getStackInHand(hand).getItem())) {
            if (inventoryBlockEntity.getStack(0).getCount() < 1) {
                inventoryBlockEntity.setStack(0, new ItemStack(player.getStackInHand(hand).getItem()));
                player.getInventory().getMainHandStack().decrement(1);
                inventoryBlockEntity.markDirty();
            }
        } else if (ITEM_LIST_SLOT1.contains(player.getStackInHand(hand).getItem())) {
            if (inventoryBlockEntity.getStack(1).getCount() < 1) {
                inventoryBlockEntity.setStack(1, new ItemStack(player.getStackInHand(hand).getItem()));
                player.getInventory().getMainHandStack().decrement(1);
                inventoryBlockEntity.markDirty();
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
