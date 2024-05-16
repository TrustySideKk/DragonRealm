package com.trustysidekick.dragonrealm.block.custom;

import com.mojang.serialization.MapCodec;
import com.trustysidekick.dragonrealm.block.entity.ImplementedInventory;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.SmithingAnvilBlockEntity;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
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

import java.util.Random;

public class SmithingAnvilBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = SmithingAnvilBlock.createCuboidShape(0, 0, 0, 16, 12.5, 16);
    private int strike;

    public SmithingAnvilBlock(Settings settings) {
        super(settings);
        strike = 0;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SmithingAnvilBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        Random random = new Random();

        if (blockEntity instanceof SmithingAnvilBlockEntity) {
            ImplementedInventory inventory = (SmithingAnvilBlockEntity) blockEntity;

            if (!player.isSneaking() && hit.getSide() == Direction.UP && player.getStackInHand(hand).getItem() != ModItems.SMITHING_HAMMER) {
                if (inventory.getStack(9).isEmpty()) {
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

                    if (finalX != -1 && finalZ != -1) {
                        slot = finalX + finalZ * 3;

                        if (!player.getStackInHand(hand).isEmpty()) {
                            if (inventory.getStack(slot).isEmpty()) {
                                inventory.setStack(slot, player.getStackInHand(hand).copy());
                                player.getInventory().getMainHandStack().decrement(1);
                                blockEntity.markDirty();
                            }
                        } else {
                            player.getInventory().offerOrDrop(inventory.getStack(slot));
                            inventory.getStack(slot).decrement(1);
                            blockEntity.markDirty();
                        }
                    }
                } else {
                    double hitX = hit.getPos().getX() - pos.getX();
                    double hitZ = hit.getPos().getZ() - pos.getZ();
                    int slot;

                    if ((hitX >= 0.21 && hitX <= 0.79) && hitZ >= 0.21 && hitZ <= 0.79) {
                        slot = 9;
                        ItemStack extractedItem = inventory.getStack(slot);
                        if (!player.getInventory().insertStack(extractedItem)) {
                            player.getInventory().offerOrDrop(extractedItem);
                        }
                        inventory.getStack(slot).decrement(1);
                        blockEntity.markDirty();
                    }
                }
            }

            if (player.getStackInHand(hand).getItem() == ModItems.SMITHING_HAMMER && hit.getSide() == Direction.UP && inventory.getStack(9).isEmpty()) {
                hammerAnvil(world, pos, player, hand);

                for (int i = 0; i < 9; i++) {
                    if (inventory.getStack(i).getItem() == Items.RAW_IRON) {
                        int ranCount = random.nextInt(2 - 1 + 1) + 1;
                        inventory.getStack(i).decrement(1);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, new ItemStack(ModItems.ORE_CHUNK_IRON, ranCount)));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                        break;
                    }
                    if (inventory.getStack(i).getItem() == Items.RAW_GOLD) {
                        int ranCount = random.nextInt(2 - 1 + 1) + 1;
                        inventory.getStack(i).decrement(1);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, new ItemStack(ModItems.ORE_CHUNK_GOLD, ranCount)));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                        break;
                    }
                    if (inventory.getStack(i).getItem() == Items.RAW_COPPER) {
                        int ranCount = random.nextInt(2 - 1 + 1) + 1;
                        inventory.getStack(i).decrement(1);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, new ItemStack(ModItems.ORE_CHUNK_COPPER, ranCount)));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                        break;
                    }
                    if (inventory.getStack(i).getItem() == Items.STONE) {
                        inventory.getStack(i).decrement(1);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, new ItemStack(Items.COBBLESTONE, 1)));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                        break;
                    }
                    if (inventory.getStack(i).getItem() == Items.COBBLESTONE) {
                        inventory.getStack(i).decrement(1);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, new ItemStack(Items.GRAVEL, 1)));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                        break;
                    }
                    if (inventory.getStack(i).getItem() == Items.GRAVEL) {
                        inventory.getStack(i).decrement(1);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, new ItemStack(Items.SAND, 1)));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                        break;
                    }
                    if (inventory.getStack(i).getItem() == Items.DEEPSLATE) {
                        inventory.getStack(i).decrement(1);
                        world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5f, pos.getY() + 1.0f, pos.getZ() + 0.5f, new ItemStack(Items.COBBLED_DEEPSLATE, 1)));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                        break;
                    }
                }
                if (validRecipeDragonChestplate(inventory)) {
                    ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike++;
                    if (((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike++ >= 8) {
                        inventory.clear();
                        inventory.setStack(9, new ItemStack(ModItems.DRAGON_CHESTPLATE));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                    }
                }
                if (validRecipeDragonHelmet(inventory)) {
                    ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike++;
                    if (((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike++ >= 8) {
                        inventory.clear();
                        inventory.setStack(9, new ItemStack(ModItems.DRAGON_HELMET));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                    }
                }
                if (validRecipeDragonLeggings(inventory)) {
                    ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike++;
                    if (((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike++ >= 8) {
                        inventory.clear();
                        inventory.setStack(9, new ItemStack(ModItems.DRAGON_LEGGINGS));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
                    }
                }
                if (validRecipeDragonBoots(inventory)) {
                    ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike++;
                    if (((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike++ >= 8) {
                        inventory.clear();
                        inventory.setStack(9, new ItemStack(ModItems.DRAGON_BOOTS));
                        ((SmithingAnvilBlockEntity)world.getBlockEntity(pos)).strike = 0;
                        inventory.markDirty();
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
            if (blockEntity instanceof SmithingAnvilBlockEntity) {
                ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        //builder.add(STRIKE);
    }

    public void hammerAnvil(World world, BlockPos pos, PlayerEntity player, Hand hand) {
        Random random = new Random();
        float ranSparkSpeedX = random.nextFloat(1 - -1 + 1) + -1;
        float ranSparkSpeedY = random.nextFloat(1 - 0 + 1) + 0;
        float ranSparkSpeedZ = random.nextFloat(1 - -1 + 1) + -1;

        player.getStackInHand(hand).damage(20, player, (p) -> { p.sendToolBreakStatus(hand);});
        world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 1.0f, 1.0f);
        world.addParticle(ParticleTypes.ELECTRIC_SPARK, pos.getX() + 0.5f, pos.getY() + 0.75f, pos.getZ() + 0.5f, ranSparkSpeedX, ranSparkSpeedY, ranSparkSpeedZ);
    }

    private boolean validRecipeDragonChestplate(ImplementedInventory inventory){
        if (     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT) || (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT) || (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT) || (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)    ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validRecipeDragonHelmet(ImplementedInventory inventory){
        if (     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == Items.AIR && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == Items.AIR)     ||     (inventory.getStack(0).getItem() == Items.AIR && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == Items.AIR && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == Items.AIR && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == Items.AIR && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == Items.AIR && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == Items.AIR)     ||     (inventory.getStack(0).getItem() == Items.AIR && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == Items.AIR && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == Items.AIR && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == Items.AIR)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == Items.AIR && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == Items.AIR)     ||     (inventory.getStack(0).getItem() == Items.AIR && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == Items.AIR && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validRecipeDragonLeggings(ImplementedInventory inventory){
        if (     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validRecipeDragonBoots(ImplementedInventory inventory){
        if (     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == Items.AIR && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == Items.AIR)     ||     (inventory.getStack(0).getItem() == Items.AIR && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == Items.AIR && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == Items.AIR && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == Items.AIR)     ||     (inventory.getStack(0).getItem() == Items.AIR && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == Items.AIR && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == Items.AIR && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == Items.AIR && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == Items.AIR && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(6).getItem() == Items.AIR && inventory.getStack(7).getItem() == Items.AIR && inventory.getStack(8).getItem() == Items.AIR)     ||     (inventory.getStack(0).getItem() == Items.AIR && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == Items.AIR && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == ModItems.DRAGON_INGOT)     ||     (inventory.getStack(0).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(1).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(2).getItem() == Items.AIR && inventory.getStack(3).getItem() == Items.AIR && inventory.getStack(4).getItem() == Items.AIR && inventory.getStack(5).getItem() == Items.AIR && inventory.getStack(6).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(7).getItem() == ModItems.DRAGON_INGOT && inventory.getStack(8).getItem() == Items.AIR)     ) {
            return true;
        } else {
            return false;
        }
    }
}
