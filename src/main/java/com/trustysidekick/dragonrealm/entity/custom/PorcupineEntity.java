package com.trustysidekick.dragonrealm.entity.custom;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import com.trustysidekick.dragonrealm.entity.goals.DragonForgeLookGoal;
import com.trustysidekick.dragonrealm.entity.ModEntities;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityInteraction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PorcupineEntity extends AnimalEntity {
    public PorcupineEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new DragonForgeLookGoal(this));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.BEETROOT), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.15D));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createPorcupineAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.BEETROOT);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.PORCUPINE.create(world);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_FOX_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_DOLPHIN_DEATH;
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos entityPos = this.getBlockPos();
        int radius = 5;
        if (this.getWorld().isClient) {
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos blockPos = entityPos.add(x, y, z);
                        BlockEntity block = this.getWorld().getBlockEntity(blockPos);
                        if (block instanceof DragonForgeBlockEntity && this.getWorld().getBlockState(blockPos).get(DragonForgeBlock.BURNING)) {
                            System.out.println("YESBURN");

                            Random random = new Random();
                            double ranParticleVelocity = 1 + random.nextFloat() * (1.5 - 1);
                            double ranSpread = -0.05 + random.nextFloat() * (0.05 - -0.05);

                            Vec3d blockPosition = new Vec3d(block.getPos().getX() + 0.5, block.getPos().getY() + 0.5, block.getPos().getZ() + 0.5);
                            Vec3d entityPosition = this.getPos();
                            Vec3d displacementVector = blockPosition.subtract(entityPosition);

                            this.getWorld().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, displacementVector.getX(), displacementVector.getY() + 0.5, displacementVector.getZ() + 0.5, -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0);
                            this.getWorld().addParticle(ParticleTypes.FLAME, displacementVector.getX(), displacementVector.getY() + 0.5, displacementVector.getZ() + 0.5, -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0);
                        }
                    }
                }
            }
        }
    }

}
