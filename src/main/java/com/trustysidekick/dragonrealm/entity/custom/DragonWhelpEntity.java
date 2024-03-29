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
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.Random;

public class DragonWhelpEntity extends AnimalEntity {

    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(DragonWhelpEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public BlockPos targetForge;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    private static final float MAX_ROTATION_SPEED = 0.5f; // Adjust as needed
    private float yawSpeed = 0.1f; // Adjust as needed
    private float pitchSpeed = 0.1f; // Adjust as needed

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

        // Slow down the turn rate by limiting the rotation speed
        //float newYaw = MathHelper.clamp(this.getYaw() + yawSpeed, -MAX_ROTATION_SPEED, MAX_ROTATION_SPEED);
        //float newPitch = MathHelper.clamp(this.getPitch() + pitchSpeed, -MAX_ROTATION_SPEED, MAX_ROTATION_SPEED);

        // Update entity rotation
        //this.setRotation(newYaw, newPitch);

        if (targetForge != null) {
            faceBlock(targetForge);
            shootFireballAtBlock(targetForge);
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

        Vec3d spawnOffset = new Vec3d(0.0, getStandingEyeHeight() + 0.9, 0.0); // Adjust the Y offset as needed
        Vec3d spawnPosition = getPos().add(spawnOffset);

        SmallFireballEntity fireball = new SmallFireballEntity(this.getWorld(), spawnPosition.x, spawnPosition.y, spawnPosition.z, direction.x + ranSpread, direction.y + ranSpread, direction.z + ranSpread);

        //SmallFireballEntity fireball = new SmallFireballEntity(this.getWorld(), this, direction.x + ranSpread, direction.y + ranSpread, direction.z + ranSpread); // Change SmallFireballEntity to FireballEntity for a larger fireball
        //fireball.setPosition(this.getX(), this.getY(), this.getZ());
        this.getWorld().spawnEntity(fireball);
    }


    public void faceBlock(BlockPos targetBlock) {
        //TODO Make entity face block immediately.  Currently it faces it after the entity updates position (i.e. when touched).
        Vec3d entityPos = getPos(); // Entity's position
        Vec3d targetPos = new Vec3d(targetBlock.getX() + 0.5, targetBlock.getY() + 0.5, targetBlock.getZ() + 0.5); // Target block's center position

        // Calculate direction vector from entity to target block
        Vec3d direction = targetPos.subtract(entityPos).normalize();

        // Calculate yaw and pitch from direction vector
        double yaw = Math.toDegrees(Math.atan2(-direction.x, direction.z)); // Yaw is based on X and Z components
        double pitch = Math.toDegrees(Math.asin(-direction.y)); // Pitch is based on Y component

        // Set entity's rotation angles
        setYaw((float) yaw);
        setPitch((float) pitch);
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public Box getBoundingBox(EntityPose pose) {
        float width = 12.5f; // Increase width
        float height = 12.0f; // Increase height

        // Define the minimum and maximum coordinates of the bounding box
        double minX = -width / 2.0; // Adjust width as needed
        double minY = 0;
        double minZ = -width / 2.0;
        double maxX = width / 2.0;
        double maxY = height;
        double maxZ = width / 2.0;

        return new Box(minX, minY, minZ, maxX, maxY, maxZ);
    }


    @Override
    protected Box getHitbox() {
        float width = 12.5f; // Increase width
        float height = 12.0f; // Increase height

        // Define the minimum and maximum coordinates of the bounding box
        double minX = -width / 2.0; // Adjust width as needed
        double minY = 0;
        double minZ = -width / 2.0;
        double maxX = width / 2.0;
        double maxY = height;
        double maxZ = width / 2.0;

        return new Box(minX, minY, minZ, maxX, maxY, maxZ);

    }
}
