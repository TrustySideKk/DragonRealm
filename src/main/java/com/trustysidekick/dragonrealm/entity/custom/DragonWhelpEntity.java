package com.trustysidekick.dragonrealm.entity.custom;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import com.trustysidekick.dragonrealm.entity.ModEntities;
import com.trustysidekick.dragonrealm.entity.ai.DragonForgeLookGoal;
import com.trustysidekick.dragonrealm.entity.ai.DragonWhelpAttackGoal;
import com.trustysidekick.dragonrealm.entity.client.DragonWhelpModel;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.*;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.List;
import java.util.Random;

public class DragonWhelpEntity extends AnimalEntity {

    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(DragonWhelpEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public BlockPos targetForge;
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    private Vec3d frozenMotion = Vec3d.ZERO;


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
            double diffX = Math.abs(this.getPos().getX()) - Math.abs(targetForge.getX());
            double diffY = Math.abs(this.getPos().getY()) - Math.abs(targetForge.getY());
            double diffZ = Math.abs(this.getPos().getZ()) - Math.abs(targetForge.getZ());

            if (diffX > Math.abs(5) || diffY > Math.abs(5) || diffZ > Math.abs(5)) {
                targetForge = null;
                frozenMotion = Vec3d.ZERO;
            } else {
                //faceBlock(targetForge);
                shootFireballAtBlock(targetForge);
                frozenMotion = getVelocity();
            }
        } else {
            frozenMotion = Vec3d.ZERO;
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
        // Assuming you have access to the world instance and the specified block position
        //BlockPos targetBlockPos = targetBlock;
        //BlockPos topSurfaceBlockPos = this.getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING, targetBlock);

        //TODO ***potential fire-breathing head position, need to test this***
        Vec3d mouthPos = new Vec3d(DragonWhelpModel.head.getChild("jaw_lower").getTransform().pivotX,
                DragonWhelpModel.head.getChild("jaw_lower").getTransform().pivotY,
                DragonWhelpModel.head.getChild("jaw_lower").getTransform().pivotZ);

        // Calculate the direction vector from the entity's head position to the target block's top surface
        //Vec3d direction = new Vec3d(topSurfaceBlockPos.getX() - this.getX(), topSurfaceBlockPos.getY() - this.getEyeY(), topSurfaceBlockPos.getZ() - this.getZ()).normalize();
        Vec3d direction = new Vec3d(targetBlock.getX() - this.getPos().getX(), targetBlock.getY() - this.getEyePos().getY(), targetBlock.getZ() - this.getPos().getZ()).normalize();
    //    Vec3d direction = new Vec3d(targetBlock.getX() - mouthPos.x, targetBlock.getY() - mouthPos.y, targetBlock.getZ() - mouthPos.z).normalize();

        // Calculate the yaw and pitch angles based on the direction vector
        double yaw = Math.toDegrees(Math.atan2(-direction.x, direction.z)); // Yaw is based on X and Z components
        double pitch = Math.toDegrees(Math.asin(-direction.y)); // Pitch is based on Y component


        // Set the entity's rotation
        this.setYaw((float) yaw);
        this.setPitch((float) pitch);

        // Assuming you have access to the world instance and the entity object
        //Vec3d headPos = entity.getEyePosition(1.0F); // Get the entity's head position
        // WORKS // Vec3d headPos = this.getEyePos(); // Get the entity's head position

        double yawRadians = Math.toRadians(this.bodyYaw); // Convert yaw to radians
        double offsetX1 = Math.sin(yawRadians); // Calculate X offset based on yaw
        double offsetZ1 = Math.cos(yawRadians); // Calculate Z offset based on yaw

        Vec3d headPos = new Vec3d(this.getX() + offsetX1, 0.0, this.getZ() + offsetZ1);


        // Now you can use the head position to spawn fire particles towards the target block's top surface
        // For example:
        Vec3d velocity = direction.multiply(0.1); // Adjust the speed as needed

        // Spawn fire particles

        int numParticles = 10;
        for (int i = 0; i < numParticles; i++) {
            double offsetX = random.nextGaussian() * 0.007; // Randomize particle position
            double offsetY = random.nextGaussian() * 0.007;
            double offsetZ = random.nextGaussian() * 0.007;
            //this.getWorld().addParticle(ParticleTypes.FLAME, headPos.x, headPos.y, headPos.z, velocity.x + offsetX, velocity.y + offsetY, velocity.z + offsetZ);
            SmallFireballEntity fireball = new SmallFireballEntity(this.getWorld(), headPos.x, headPos.y - 58.5, headPos.z, velocity.x + offsetX, velocity.y + offsetY, velocity.z + offsetZ);
    //        SmallFireballEntity fireball = new SmallFireballEntity(this.getWorld(), mouthPos.getX(), mouthPos.getY(), mouthPos.getZ(), velocity.x + offsetX, velocity.y + offsetY, velocity.z + offsetZ);
            this.getWorld().spawnEntity(fireball);
            //System.out.println("Fireball spawned: " + mouthPos.getX() + ", " + mouthPos.getY() + ", " + mouthPos.getZ());
        }




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
