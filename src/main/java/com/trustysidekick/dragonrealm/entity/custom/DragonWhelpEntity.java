package com.trustysidekick.dragonrealm.entity.custom;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import com.trustysidekick.dragonrealm.entity.ModEntities;
import com.trustysidekick.dragonrealm.entity.ai.DragonForgeLookGoal;
import com.trustysidekick.dragonrealm.entity.ai.DragonWhelpAttackGoal;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.Random;

public class DragonWhelpEntity extends AnimalEntity {

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(DragonWhelpEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int particleCoolDown = 0;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public DragonWhelpEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 40;
            attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos entityPos = this.getBlockPos();
        int radius = 5;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockPos = entityPos.add(x, y, z);
                    BlockEntity block = this.getWorld().getBlockEntity(blockPos);

                    if (block instanceof DragonForgeBlockEntity && this.getWorld().getBlockState(block.getPos()).get(DragonForgeBlock.BURNING)) {
                        if (this.getWorld() instanceof ServerWorld serverWorld) {
                            //if (particleCoolDown < 5) {
                                faceBlock(block.getPos());
                                shootFireballAtBlock(block.getPos());
                           // } else {
                            //    particleCoolDown = particleCoolDown - 1;
                            //}
                        }
                    }
                }
            }
        }

        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new DragonForgeLookGoal(this));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new DragonWhelpAttackGoal(this, 1D, true));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(4, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.BEETROOT), false));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.15D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
    }

    public static DefaultAttributeContainer.Builder createDragonWhelpAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.BEETROOT);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.DRAGONWHELP.create(world);
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




    public void shootFireballAtBlock(BlockPos targetBlock) {
        Random random = new Random();
        double ranSpread = -0.05 + random.nextFloat() * (0.05 - -0.05);

        Vec3d targetPosition = new Vec3d(targetBlock.getX() + 0.5, targetBlock.getY() + 0.5, targetBlock.getZ() + 0.5);
        Vec3d direction = targetPosition.subtract(getPos()).normalize();

        SmallFireballEntity fireball = new SmallFireballEntity(this.getWorld(), this, direction.x + ranSpread, direction.y + ranSpread, direction.z + ranSpread); // Change SmallFireballEntity to FireballEntity for a larger fireball
        fireball.setPosition(this.getX(), this.getY(), this.getZ());
        this.getWorld().spawnEntity(fireball);
    }

    public void faceBlock(BlockPos targetBlock) {
        /*
        Vec3d entityPos = new Vec3d(getX(), getEyeY(), getZ()); // Entity's position (assuming its eye level)
        Vec3d targetPos = new Vec3d(targetBlock.getX() + 0.5, targetBlock.getY() + 0.5, targetBlock.getZ() + 0.5); // Target block's position
        Vec3d direction = targetPos.subtract(entityPos).normalize(); // Calculate direction vector from entity to target block

        // Calculate yaw and pitch from direction vector
        double yaw = Math.atan2(direction.z, direction.x) * (180 / Math.PI);
        double pitch = Math.asin(direction.y) * (180 / Math.PI);

        // Set entity's rotation angles
        setYaw((float) yaw);
        setPitch((float) pitch);
*/


        float rotationSpeed = 0.15f; // Adjust the rotation speed as needed
        float yawRotation = 0.0f;

        // Update the yaw rotation
        yawRotation += rotationSpeed;

        // Ensure the yaw rotation stays within the valid range (-180 to 180 degrees)
        if (yawRotation > 180.0f) {
            yawRotation -= 360.0f;
        } else if (yawRotation < -180.0f) {
            yawRotation += 360.0f;
        }

        // Set the entity's yaw rotation
        setYaw(yawRotation);
    }
}
