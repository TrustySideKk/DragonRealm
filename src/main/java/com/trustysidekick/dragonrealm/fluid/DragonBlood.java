package com.trustysidekick.dragonrealm.fluid;

import com.trustysidekick.dragonrealm.DragonRealm;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;


public class DragonBlood extends FlowableFluid implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(Registries.FLUID, new Identifier(DragonRealm.MOD_ID, "dragon_blood"), this);
    }

    @Override
    public Fluid getFlowing() {
        return Registry.register(Registries.FLUID, new Identifier(DragonRealm.MOD_ID, "dragon_blood_flow"), this);
    }

    @Override
    public Fluid getStill() {
        return Registry.register(Registries.FLUID, new Identifier(DragonRealm.MOD_ID, "dragon_blood_still"), this);
    }

    @Override
    protected boolean isInfinite(World world) {
        return false;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {

    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public Item getBucketItem() {
        return Items.GLASS_BOTTLE;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 10;
    }

    @Override
    protected float getBlastResistance() {
        return 0;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return Blocks.WATER.getDefaultState();
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }
}
