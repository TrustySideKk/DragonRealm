package com.trustysidekick.dragonrealm.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuenchTankBlockEntity extends BlockEntity {

    public QuenchTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.QUENCH_TANK_BLOCK_ENTITY, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {

    }
}
