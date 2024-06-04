/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import com.trustysidekick.dragonrealm.entity.ModEntities;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPart;
import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.ai.pathing.PathMinHeap;
import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
//import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonFight;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPhase;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPhaseType;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPhaseManager;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class HighlandDragonEntity
extends MobEntity
implements Monster {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final TrackedData<Integer> HIGHLAND_DRAGON_PHASE_TYPE = DataTracker.registerData(HighlandDragonEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TargetPredicate CLOSE_PLAYER_PREDICATE = TargetPredicate.createAttackable().setBaseMaxDistance(64.0);
    private static final int MAX_HEALTH = 200;
    private static final int field_30429 = 400;
    /**
     * The damage the dragon can take before it takes off, represented as a ratio to the full health.
     */
    private static final float TAKEOFF_THRESHOLD = 0.25f;
    private static final String DRAGON_DEATH_TIME_KEY = "DragonDeathTime";
    private static final String DRAGON_PHASE_KEY = "DragonPhase";
    /**
     * (yaw, y, ?)
     */
    public final double[][] segmentCircularBuffer = new double[64][3];
    public int latestSegment = -1;
    private final HighlandDragonPart[] parts;
    private final HighlandDragonPart body;
    public final HighlandDragonPart head;
    private final HighlandDragonPart leg_left;
    private final HighlandDragonPart foot2;
    private final HighlandDragonPart leg_right;
    private final HighlandDragonPart foot;
    private final HighlandDragonPart neck;
    private final HighlandDragonPart wing_right;
    private final HighlandDragonPart arm_inner;
    private final HighlandDragonPart arm_outer;
    private final HighlandDragonPart wing_splines;
    private final HighlandDragonPart wing_left;
    private final HighlandDragonPart arm_inner2;
    private final HighlandDragonPart arm_outer2;
    private final HighlandDragonPart wing_splines2;
    private final HighlandDragonPart tail;
    public float prevWingPosition;
    public float wingPosition;
    public boolean slowedDownByBlock;
    public int ticksSinceDeath;
    public float yawAcceleration;
    @Nullable
    public EndCrystalEntity connectedCrystal;
    @Nullable
    //private HighlandDragonFight fight;
    private BlockPos fightOrigin = BlockPos.ORIGIN;
    private final HighlandDragonPhaseManager phaseManager;
    private int ticksUntilNextGrowl = 100;
    private float damageDuringSitting;
    /**
     * The first 12 path nodes are used for end crystals; the others are not tied to them.
     */
    private final PathNode[] pathNodes = new PathNode[24];
    /**
     * An array of 24 bitflags, where node #i leads to #j if and only if
     * {@code (pathNodeConnections[i] & (1 << j)) != 0}.
     */
    private final int[] pathNodeConnections = new int[24];
    private final PathMinHeap pathHeap = new PathMinHeap();

    public HighlandDragonEntity(EntityType<? extends HighlandDragonEntity> entityType, World world) {
        super(ModEntities.HIGHLAND_DRAGON, world);
        this.head = new HighlandDragonPart(this, "head", 1.0f, 1.0f);
        this.neck = new HighlandDragonPart(this, "neck", 3.0f, 3.0f);
        this.body = new HighlandDragonPart(this, "body", 5.0f, 3.0f);
        this.leg_left = new HighlandDragonPart(this, "leg_left", 1.0f, 2.0f);
        this.foot2 = new HighlandDragonPart(this, "foot2", 1.0f, 1.0f);
        this.leg_right = new HighlandDragonPart(this, "leg_right", 1.0f, 1.0f);
        this.foot = new HighlandDragonPart(this, "foot", 1.0f, 1.0f);
        this.wing_right = new HighlandDragonPart(this, "wing_right", 4.0f, 3.0f);
        this.arm_inner = new HighlandDragonPart(this, "arm_inner", 2.0f, 1.0f);
        this.arm_outer = new HighlandDragonPart(this, "arm_outer", 2.0f, 1.0f);
        this.wing_splines = new HighlandDragonPart(this, "wing_splines", 3.0f, 1.0f);
        this.wing_left = new HighlandDragonPart(this, "wing_left", 4.0f, 3.0f);
        this.arm_inner2 = new HighlandDragonPart(this, "arm_inner2", 2.0f, 1.0f);
        this.arm_outer2 = new HighlandDragonPart(this, "arm_outer2", 2.0f, 1.0f);
        this.wing_splines2 = new HighlandDragonPart(this, "wing_splines2", 3.0f, 1.0f);
        this.tail = new HighlandDragonPart(this, "tail", 2.0f, 2.0f);
        this.parts = new HighlandDragonPart[]{
            this.head,
            this.neck,
            this.body,
            this.leg_left,
            this.foot2,
            this.leg_right,
            this.foot,
            this.wing_right,
            this.arm_inner,
            this.arm_outer,
            this.wing_splines,
            this.wing_left,
            this.arm_inner2,
            this.arm_outer2,
            this.wing_splines2,
            this.tail
        };
        this.setHealth(this.getMaxHealth());
        this.noClip = true;
        this.ignoreCameraFrustum = true;
        this.phaseManager = new HighlandDragonPhaseManager(this);
    }

    //public void setFight(HighlandDragonFight fight) {
    //    this.fight = fight;
    //}

    public void setFightOrigin(BlockPos fightOrigin) {
        this.fightOrigin = fightOrigin;
    }

    public BlockPos getFightOrigin() {
        return this.fightOrigin;
    }

    public static DefaultAttributeContainer.Builder createHighlandDragonAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0);
    }

    @Override
    public boolean isFlappingWings() {
        float f = MathHelper.cos(this.wingPosition * ((float)Math.PI * 2));
        float g = MathHelper.cos(this.prevWingPosition * ((float)Math.PI * 2));
        return g <= -0.3f && f >= -0.3f;
    }

    @Override
    public void addFlapEffects() {
        if (this.getWorld().isClient && !this.isSilent()) {
            this.getWorld().playSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_FLAP, this.getSoundCategory(), 5.0f, 0.8f + this.random.nextFloat() * 0.3f, false);
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(HIGHLAND_DRAGON_PHASE_TYPE, HighlandDragonPhaseType.HIGHLAND_DRAGON_HOVER.getTypeId());
    }

    public double[] getSegmentProperties(int segmentNumber, float tickDelta) {
        if (this.isDead()) {
            tickDelta = 0.0f;
        }
        tickDelta = 1.0f - tickDelta;
        int i = this.latestSegment - segmentNumber & 0x3F;
        int j = this.latestSegment - segmentNumber - 1 & 0x3F;
        double[] ds = new double[3];
        double d = this.segmentCircularBuffer[i][0];
        double e = MathHelper.wrapDegrees(this.segmentCircularBuffer[j][0] - d);
        ds[0] = d + e * (double)tickDelta;
        d = this.segmentCircularBuffer[i][1];
        e = this.segmentCircularBuffer[j][1] - d;
        ds[1] = d + e * (double)tickDelta;
        ds[2] = MathHelper.lerp((double)tickDelta, this.segmentCircularBuffer[i][2], this.segmentCircularBuffer[j][2]);
        return ds;
    }

    @Override
    public void tickMovement() {
        int ab;
        float p;
        float o;
        float n;
        ServerWorld serverWorld;
        //HighlandDragonFight HighlandDragonFight;
        World world;
        this.addAirTravelEffects();
        if (this.getWorld().isClient) {
            this.setHealth(this.getHealth());
            if (!this.isSilent() && !this.phaseManager.getCurrent().isSittingOrHovering() && --this.ticksUntilNextGrowl < 0) {
                this.getWorld().playSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, this.getSoundCategory(), 2.5f, 0.8f + this.random.nextFloat() * 0.3f, false);
                this.ticksUntilNextGrowl = 200 + this.random.nextInt(200);
            }
        }
        //if (this.fight == null && (world = this.getWorld()) instanceof ServerWorld && (HighlandDragonFight = (serverWorld = (ServerWorld)world).getHighlandDragonFight()) != null && this.getUuid().equals(HighlandDragonFight.getDragonUuid())) {
        //    this.fight = HighlandDragonFight;
        //}
        this.prevWingPosition = this.wingPosition;
        if (this.isDead()) {
            float f = (this.random.nextFloat() - 0.5f) * 8.0f;
            float g = (this.random.nextFloat() - 0.5f) * 4.0f;
            float h = (this.random.nextFloat() - 0.5f) * 8.0f;
            this.getWorld().addParticle(ParticleTypes.EXPLOSION, this.getX() + (double)f, this.getY() + 2.0 + (double)g, this.getZ() + (double)h, 0.0, 0.0, 0.0);
            return;
        }
        this.tickWithEndCrystals();
        Vec3d vec3d = this.getVelocity();
        float g = 0.2f / ((float)vec3d.horizontalLength() * 10.0f + 1.0f);
        this.wingPosition = this.phaseManager.getCurrent().isSittingOrHovering() ? (this.wingPosition += 0.1f) : (this.slowedDownByBlock ? (this.wingPosition += g * 0.5f) : (this.wingPosition += (g *= (float)Math.pow(2.0, vec3d.y))));
        this.setYaw(MathHelper.wrapDegrees(this.getYaw()));
        if (this.isAiDisabled()) {
            this.wingPosition = 0.5f;
            return;
        }
        if (this.latestSegment < 0) {
            for (int i = 0; i < this.segmentCircularBuffer.length; ++i) {
                this.segmentCircularBuffer[i][0] = this.getYaw();
                this.segmentCircularBuffer[i][1] = this.getY();
            }
        }
        if (++this.latestSegment == this.segmentCircularBuffer.length) {
            this.latestSegment = 0;
        }
        this.segmentCircularBuffer[this.latestSegment][0] = this.getYaw();
        this.segmentCircularBuffer[this.latestSegment][1] = this.getY();
        if (this.getWorld().isClient) {
            if (this.bodyTrackingIncrements > 0) {
                this.lerpPosAndRotation(this.bodyTrackingIncrements, this.serverX, this.serverY, this.serverZ, this.serverYaw, this.serverPitch);
                --this.bodyTrackingIncrements;
            }
            this.phaseManager.getCurrent().clientTick();
        } else {
            Vec3d vec3d2;
            HighlandDragonPhase phase = this.phaseManager.getCurrent();
            phase.serverTick();
            if (this.phaseManager.getCurrent() != phase) {
                phase = this.phaseManager.getCurrent();
                phase.serverTick();
            }
            if ((vec3d2 = phase.getPathTarget()) != null) {
                double d = vec3d2.x - this.getX();
                double e = vec3d2.y - this.getY();
                double j = vec3d2.z - this.getZ();
                double k = d * d + e * e + j * j;
                float l = phase.getMaxYAcceleration();
                double m = Math.sqrt(d * d + j * j);
                if (m > 0.0) {
                    e = MathHelper.clamp(e / m, (double)(-l), (double)l);
                }
                this.setVelocity(this.getVelocity().add(0.0, e * 0.01, 0.0));
                this.setYaw(MathHelper.wrapDegrees(this.getYaw()));
                Vec3d vec3d3 = vec3d2.subtract(this.getX(), this.getY(), this.getZ()).normalize();
                Vec3d vec3d4 = new Vec3d(MathHelper.sin(this.getYaw() * ((float)Math.PI / 180)), this.getVelocity().y, -MathHelper.cos(this.getYaw() * ((float)Math.PI / 180))).normalize();
                n = Math.max(((float)vec3d4.dotProduct(vec3d3) + 0.5f) / 1.5f, 0.0f);
                if (Math.abs(d) > (double)1.0E-5f || Math.abs(j) > (double)1.0E-5f) {
                    o = MathHelper.clamp(MathHelper.wrapDegrees(180.0f - (float)MathHelper.atan2(d, j) * 57.295776f - this.getYaw()), -50.0f, 50.0f);
                    this.yawAcceleration *= 0.8f;
                    this.yawAcceleration += o * phase.getYawAcceleration();
                    this.setYaw(this.getYaw() + this.yawAcceleration * 0.1f);
                }
                o = (float)(2.0 / (k + 1.0));
                p = 0.06f;
                this.updateVelocity(0.06f * (n * o + (1.0f - o)), new Vec3d(0.0, 0.0, -1.0));
                if (this.slowedDownByBlock) {
                    this.move(MovementType.SELF, this.getVelocity().multiply(0.8f));
                } else {
                    this.move(MovementType.SELF, this.getVelocity());
                }
                Vec3d vec3d5 = this.getVelocity().normalize();
                double q = 0.8 + 0.15 * (vec3d5.dotProduct(vec3d4) + 1.0) / 2.0;
                this.setVelocity(this.getVelocity().multiply(q, 0.91f, q));
            }
        }
        this.bodyYaw = this.getYaw();
        Vec3d[] vec3ds = new Vec3d[this.parts.length];
        for (int r = 0; r < this.parts.length; ++r) {
            vec3ds[r] = new Vec3d(this.parts[r].getX(), this.parts[r].getY(), this.parts[r].getZ());
        }
        float s = (float)(this.getSegmentProperties(5, 1.0f)[1] - this.getSegmentProperties(10, 1.0f)[1]) * 10.0f * ((float)Math.PI / 180);
        float t = MathHelper.cos(s);
        float u = MathHelper.sin(s);
        float v = this.getYaw() * ((float)Math.PI / 180);
        float w = MathHelper.sin(v);
        float x = MathHelper.cos(v);
        this.movePart(this.body, w * 0.5f, 0.0, -x * 0.5f);
        this.movePart(this.wing_right, x * 4.5f, 2.0, w * 4.5f);
        this.movePart(this.wing_left, x * -4.5f, 2.0, w * -4.5f);
        if (!this.getWorld().isClient && this.hurtTime == 0) {
            this.launchLivingEntities(this.getWorld().getOtherEntities(this, this.wing_right.getBoundingBox().expand(4.0, 2.0, 4.0).offset(0.0, -2.0, 0.0), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR));
            this.launchLivingEntities(this.getWorld().getOtherEntities(this, this.wing_left.getBoundingBox().expand(4.0, 2.0, 4.0).offset(0.0, -2.0, 0.0), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR));
            this.damageLivingEntities(this.getWorld().getOtherEntities(this, this.head.getBoundingBox().expand(1.0), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR));
            this.damageLivingEntities(this.getWorld().getOtherEntities(this, this.neck.getBoundingBox().expand(1.0), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR));
        }
        float y = MathHelper.sin(this.getYaw() * ((float)Math.PI / 180) - this.yawAcceleration * 0.01f);
        float z = MathHelper.cos(this.getYaw() * ((float)Math.PI / 180) - this.yawAcceleration * 0.01f);
        float aa = this.getHeadVerticalMovement();
        this.movePart(this.head, y * 6.5f * t, aa + u * 6.5f, -z * 6.5f * t);
        this.movePart(this.neck, y * 5.5f * t, aa + u * 5.5f, -z * 5.5f * t);
        double[] ds = this.getSegmentProperties(5, 1.0f);
        for (ab = 0; ab < 3; ++ab) {
            HighlandDragonPart HighlandDragonPart = null;
//            if (ab == 0) {
                HighlandDragonPart = this.tail;
//            }
//            if (ab == 1) {
//                HighlandDragonPart = this.tail2;
//            }
//            if (ab == 2) {
//                HighlandDragonPart = this.tail3;
//            }
            double[] es = this.getSegmentProperties(12 + ab * 2, 1.0f);
            float ac = this.getYaw() * ((float)Math.PI / 180) + this.wrapYawChange(es[0] - ds[0]) * ((float)Math.PI / 180);
            n = MathHelper.sin(ac);
            o = MathHelper.cos(ac);
            p = 1.5f;
            float ad = (float)(ab + 1) * 2.0f;
            this.movePart(HighlandDragonPart, -(w * 1.5f + n * ad) * t, es[1] - ds[1] - (double)((ad + 1.5f) * u) + 1.5, (x * 1.5f + o * ad) * t);
        }
        if (!this.getWorld().isClient) {
            this.slowedDownByBlock = this.destroyBlocks(this.head.getBoundingBox()) | this.destroyBlocks(this.neck.getBoundingBox()) | this.destroyBlocks(this.body.getBoundingBox());
            //if (this.fight != null) {
            //    this.fight.updateFight(this);
            //}
        }
        for (ab = 0; ab < this.parts.length; ++ab) {
            this.parts[ab].prevX = vec3ds[ab].x;
            this.parts[ab].prevY = vec3ds[ab].y;
            this.parts[ab].prevZ = vec3ds[ab].z;
            this.parts[ab].lastRenderX = vec3ds[ab].x;
            this.parts[ab].lastRenderY = vec3ds[ab].y;
            this.parts[ab].lastRenderZ = vec3ds[ab].z;
        }
    }

    private void movePart(HighlandDragonPart HighlandDragonPart, double dx, double dy, double dz) {
        HighlandDragonPart.setPosition(this.getX() + dx, this.getY() + dy, this.getZ() + dz);
    }

    private float getHeadVerticalMovement() {
        if (this.phaseManager.getCurrent().isSittingOrHovering()) {
            return -1.0f;
        }
        double[] ds = this.getSegmentProperties(5, 1.0f);
        double[] es = this.getSegmentProperties(0, 1.0f);
        return (float)(ds[1] - es[1]);
    }

    /**
     * Things to do every tick related to end crystals. The Highland Dragon:
     * 
     * * Disconnects from its crystal if it is removed
     * * If it is connected to a crystal, then heals every 10 ticks
     * * With a 1 in 10 chance each tick, searches for the nearest crystal and connects to it if present
     */
    private void tickWithEndCrystals() {
        if (this.connectedCrystal != null) {
            if (this.connectedCrystal.isRemoved()) {
                this.connectedCrystal = null;
            } else if (this.age % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
                this.setHealth(this.getHealth() + 1.0f);
            }
        }
        if (this.random.nextInt(10) == 0) {
            List<EndCrystalEntity> list = this.getWorld().getNonSpectatingEntities(EndCrystalEntity.class, this.getBoundingBox().expand(32.0));
            EndCrystalEntity endCrystalEntity = null;
            double d = Double.MAX_VALUE;
            for (EndCrystalEntity endCrystalEntity2 : list) {
                double e = endCrystalEntity2.squaredDistanceTo(this);
                if (!(e < d)) continue;
                d = e;
                endCrystalEntity = endCrystalEntity2;
            }
            this.connectedCrystal = endCrystalEntity;
        }
    }

    private void launchLivingEntities(List<Entity> entities) {
        double d = (this.body.getBoundingBox().minX + this.body.getBoundingBox().maxX) / 2.0;
        double e = (this.body.getBoundingBox().minZ + this.body.getBoundingBox().maxZ) / 2.0;
        for (Entity entity : entities) {
            if (!(entity instanceof LivingEntity)) continue;
            double f = entity.getX() - d;
            double g = entity.getZ() - e;
            double h = Math.max(f * f + g * g, 0.1);
            entity.addVelocity(f / h * 4.0, 0.2f, g / h * 4.0);
            if (this.phaseManager.getCurrent().isSittingOrHovering() || ((LivingEntity)entity).getLastAttackedTime() >= entity.age - 2) continue;
            entity.damage(this.getDamageSources().mobAttack(this), 5.0f);
            this.applyDamageEffects(this, entity);
        }
    }

    private void damageLivingEntities(List<Entity> entities) {
        for (Entity entity : entities) {
            if (!(entity instanceof LivingEntity)) continue;
            entity.damage(this.getDamageSources().mobAttack(this), 10.0f);
            this.applyDamageEffects(this, entity);
        }
    }

    private float wrapYawChange(double yawDegrees) {
        return (float)MathHelper.wrapDegrees(yawDegrees);
    }

    private boolean destroyBlocks(Box box) {
        int i = MathHelper.floor(box.minX);
        int j = MathHelper.floor(box.minY);
        int k = MathHelper.floor(box.minZ);
        int l = MathHelper.floor(box.maxX);
        int m = MathHelper.floor(box.maxY);
        int n = MathHelper.floor(box.maxZ);
        boolean bl = false;
        boolean bl2 = false;
        for (int o = i; o <= l; ++o) {
            for (int p = j; p <= m; ++p) {
                for (int q = k; q <= n; ++q) {
                    BlockPos blockPos = new BlockPos(o, p, q);
                    BlockState blockState = this.getWorld().getBlockState(blockPos);
                    if (blockState.isAir() || blockState.isIn(BlockTags.DRAGON_TRANSPARENT)) continue;
                    if (!this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) || blockState.isIn(BlockTags.DRAGON_IMMUNE)) {
                        bl = true;
                        continue;
                    }
                    bl2 = this.getWorld().removeBlock(blockPos, false) || bl2;
                }
            }
        }
        if (bl2) {
            BlockPos blockPos2 = new BlockPos(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(m - j + 1), k + this.random.nextInt(n - k + 1));
            this.getWorld().syncWorldEvent(WorldEvents.ENDER_DRAGON_BREAKS_BLOCK, blockPos2, 0);
        }
        return bl;
    }

    public boolean damagePart(HighlandDragonPart part, DamageSource source, float amount) {
        if (this.phaseManager.getCurrent().getType() == HighlandDragonPhaseType.HIGHLAND_DRAGON_DYING) {
            return false;
        }
        amount = this.phaseManager.getCurrent().modifyDamageTaken(source, amount);
        if (part != this.head) {
            amount = amount / 4.0f + Math.min(amount, 1.0f);
        }
        if (amount < 0.01f) {
            return false;
        }
        if (source.getAttacker() instanceof PlayerEntity || source.isIn(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS)) {
            float f = this.getHealth();
            this.parentDamage(source, amount);
            if (this.isDead() && !this.phaseManager.getCurrent().isSittingOrHovering()) {
                this.setHealth(1.0f);
                this.phaseManager.setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_DYING);
            }
            if (this.phaseManager.getCurrent().isSittingOrHovering()) {
                this.damageDuringSitting = this.damageDuringSitting + f - this.getHealth();
                if (this.damageDuringSitting > 0.25f * this.getMaxHealth()) {
                    this.damageDuringSitting = 0.0f;
                    this.phaseManager.setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_TAKEOFF);
                }
            }
        }
        return true;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (!this.getWorld().isClient) {
            return this.damagePart(this.body, source, amount);
        }
        return false;
    }

    protected boolean parentDamage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }

    @Override
    public void kill() {
        this.remove(RemovalReason.KILLED);
        this.emitGameEvent(GameEvent.ENTITY_DIE);
        //if (this.fight != null) {
        //    this.fight.updateFight(this);
        //    this.fight.dragonKilled(this);
        //}
    }

    @Override
    protected void updatePostDeath() {
        //if (this.fight != null) {
        //    this.fight.updateFight(this);
        //}
        ++this.ticksSinceDeath;
        if (this.ticksSinceDeath >= 180 && this.ticksSinceDeath <= 200) {
            float f = (this.random.nextFloat() - 0.5f) * 8.0f;
            float g = (this.random.nextFloat() - 0.5f) * 4.0f;
            float h = (this.random.nextFloat() - 0.5f) * 8.0f;
            this.getWorld().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)f, this.getY() + 2.0 + (double)g, this.getZ() + (double)h, 0.0, 0.0, 0.0);
        }
        boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
        int i = 500;
        //if (this.fight != null && !this.fight.hasPreviouslyKilled()) {
        //    i = 12000;
        //}
        if (this.getWorld() instanceof ServerWorld) {
            if (this.ticksSinceDeath > 150 && this.ticksSinceDeath % 5 == 0 && bl) {
                ExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)i * 0.08f));
            }
            if (this.ticksSinceDeath == 1 && !this.isSilent()) {
                this.getWorld().syncGlobalEvent(WorldEvents.ENDER_DRAGON_DIES, this.getBlockPos(), 0);
            }
        }
        this.move(MovementType.SELF, new Vec3d(0.0, 0.1f, 0.0));
        if (this.ticksSinceDeath == 200 && this.getWorld() instanceof ServerWorld) {
            if (bl) {
                ExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)i * 0.2f));
            }
            //if (this.fight != null) {
            //    this.fight.dragonKilled(this);
            //}
            this.remove(RemovalReason.KILLED);
            this.emitGameEvent(GameEvent.ENTITY_DIE);
        }
    }

    public int getNearestPathNodeIndex() {
        if (this.pathNodes[0] == null) {
            for (int i = 0; i < 24; ++i) {
                int m;
                int l;
                int j = 5;
                int k = i;
                if (i < 12) {
                    l = MathHelper.floor(60.0f * MathHelper.cos(2.0f * ((float)(-Math.PI) + 0.2617994f * (float)k)));
                    m = MathHelper.floor(60.0f * MathHelper.sin(2.0f * ((float)(-Math.PI) + 0.2617994f * (float)k)));
                } else if (i < 20) {
                    l = MathHelper.floor(40.0f * MathHelper.cos(2.0f * ((float)(-Math.PI) + 0.3926991f * (float)(k -= 12))));
                    m = MathHelper.floor(40.0f * MathHelper.sin(2.0f * ((float)(-Math.PI) + 0.3926991f * (float)k)));
                    j += 10;
                } else {
                    l = MathHelper.floor(20.0f * MathHelper.cos(2.0f * ((float)(-Math.PI) + 0.7853982f * (float)(k -= 20))));
                    m = MathHelper.floor(20.0f * MathHelper.sin(2.0f * ((float)(-Math.PI) + 0.7853982f * (float)k)));
                }
                int n = Math.max(this.getWorld().getSeaLevel() + 10, this.getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(l, 0, m)).getY() + j);
                this.pathNodes[i] = new PathNode(l, n, m);
            }
            this.pathNodeConnections[0] = 6146;
            this.pathNodeConnections[1] = 8197;
            this.pathNodeConnections[2] = 8202;
            this.pathNodeConnections[3] = 16404;
            this.pathNodeConnections[4] = 32808;
            this.pathNodeConnections[5] = 32848;
            this.pathNodeConnections[6] = 65696;
            this.pathNodeConnections[7] = 131392;
            this.pathNodeConnections[8] = 131712;
            this.pathNodeConnections[9] = 263424;
            this.pathNodeConnections[10] = 526848;
            this.pathNodeConnections[11] = 525313;
            this.pathNodeConnections[12] = 1581057;
            this.pathNodeConnections[13] = 3166214;
            this.pathNodeConnections[14] = 2138120;
            this.pathNodeConnections[15] = 6373424;
            this.pathNodeConnections[16] = 4358208;
            this.pathNodeConnections[17] = 12910976;
            this.pathNodeConnections[18] = 9044480;
            this.pathNodeConnections[19] = 9706496;
            this.pathNodeConnections[20] = 15216640;
            this.pathNodeConnections[21] = 0xD0E000;
            this.pathNodeConnections[22] = 11763712;
            this.pathNodeConnections[23] = 0x7E0000;
        }
        return this.getNearestPathNodeIndex(this.getX(), this.getY(), this.getZ());
    }

    public int getNearestPathNodeIndex(double x, double y, double z) {
        float f = 10000.0f;
        int i = 0;
        PathNode pathNode = new PathNode(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
        int j = 0;
        //if (this.fight == null || this.fight.getAliveEndCrystals() == 0) {
        //    j = 12;
        //}
        for (int k = j; k < 24; ++k) {
            float g;
            if (this.pathNodes[k] == null || !((g = this.pathNodes[k].getSquaredDistance(pathNode)) < f)) continue;
            f = g;
            i = k;
        }
        return i;
    }

    @Nullable
    public Path findPath(int from, int to, @Nullable PathNode pathNode) {
        PathNode pathNode2;
        for (int i = 0; i < 24; ++i) {
            pathNode2 = this.pathNodes[i];
            pathNode2.visited = false;
            pathNode2.heapWeight = 0.0f;
            pathNode2.penalizedPathLength = 0.0f;
            pathNode2.distanceToNearestTarget = 0.0f;
            pathNode2.previous = null;
            pathNode2.heapIndex = -1;
        }
        PathNode pathNode3 = this.pathNodes[from];
        pathNode2 = this.pathNodes[to];
        pathNode3.penalizedPathLength = 0.0f;
        pathNode3.heapWeight = pathNode3.distanceToNearestTarget = pathNode3.getDistance(pathNode2);
        this.pathHeap.clear();
        this.pathHeap.push(pathNode3);
        PathNode pathNode4 = pathNode3;
        int j = 0;
        //if (this.fight == null || this.fight.getAliveEndCrystals() == 0) {
        //    j = 12;
        //}
        while (!this.pathHeap.isEmpty()) {
            int l;
            PathNode pathNode5 = this.pathHeap.pop();
            if (pathNode5.equals(pathNode2)) {
                if (pathNode != null) {
                    pathNode.previous = pathNode2;
                    pathNode2 = pathNode;
                }
                return this.getPathOfAllPredecessors(pathNode3, pathNode2);
            }
            if (pathNode5.getDistance(pathNode2) < pathNode4.getDistance(pathNode2)) {
                pathNode4 = pathNode5;
            }
            pathNode5.visited = true;
            int k = 0;
            for (l = 0; l < 24; ++l) {
                if (this.pathNodes[l] != pathNode5) continue;
                k = l;
                break;
            }
            for (l = j; l < 24; ++l) {
                if ((this.pathNodeConnections[k] & 1 << l) <= 0) continue;
                PathNode pathNode6 = this.pathNodes[l];
                if (pathNode6.visited) continue;
                float f = pathNode5.penalizedPathLength + pathNode5.getDistance(pathNode6);
                if (pathNode6.isInHeap() && !(f < pathNode6.penalizedPathLength)) continue;
                pathNode6.previous = pathNode5;
                pathNode6.penalizedPathLength = f;
                pathNode6.distanceToNearestTarget = pathNode6.getDistance(pathNode2);
                if (pathNode6.isInHeap()) {
                    this.pathHeap.setNodeWeight(pathNode6, pathNode6.penalizedPathLength + pathNode6.distanceToNearestTarget);
                    continue;
                }
                pathNode6.heapWeight = pathNode6.penalizedPathLength + pathNode6.distanceToNearestTarget;
                this.pathHeap.push(pathNode6);
            }
        }
        if (pathNode4 == pathNode3) {
            return null;
        }
        LOGGER.debug("Failed to find path from {} to {}", (Object)from, (Object)to);
        if (pathNode != null) {
            pathNode.previous = pathNode4;
            pathNode4 = pathNode;
        }
        return this.getPathOfAllPredecessors(pathNode3, pathNode4);
    }

    private Path getPathOfAllPredecessors(PathNode unused, PathNode node) {
        ArrayList<PathNode> list = Lists.newArrayList();
        PathNode pathNode = node;
        list.add(0, pathNode);
        while (pathNode.previous != null) {
            pathNode = pathNode.previous;
            list.add(0, pathNode);
        }
        return new Path(list, new BlockPos(node.x, node.y, node.z), true);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(DRAGON_PHASE_KEY, this.phaseManager.getCurrent().getType().getTypeId());
        nbt.putInt(DRAGON_DEATH_TIME_KEY, this.ticksSinceDeath);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains(DRAGON_PHASE_KEY)) {
            this.phaseManager.setPhase(HighlandDragonPhaseType.getFromId(nbt.getInt(DRAGON_PHASE_KEY)));
        }
        if (nbt.contains(DRAGON_DEATH_TIME_KEY)) {
            this.ticksSinceDeath = nbt.getInt(DRAGON_DEATH_TIME_KEY);
        }
    }

    @Override
    public void checkDespawn() {
    }

    public HighlandDragonPart[] getBodyParts() {
        return this.parts;
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ENDER_DRAGON_HURT;
    }

    @Override
    protected float getSoundVolume() {
        return 5.0f;
    }

    public float getChangeInNeckPitch(int segmentOffset, double[] segment1, double[] segment2) {
        double e;
        HighlandDragonPhase phase = this.phaseManager.getCurrent();
        HighlandDragonPhaseType<? extends HighlandDragonPhase> phaseType = phase.getType();
        if (phaseType == HighlandDragonPhaseType.HIGHLAND_DRAGON_LANDING || phaseType == HighlandDragonPhaseType.HIGHLAND_DRAGON_TAKEOFF) {
            BlockPos blockPos = this.getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.offsetOrigin(this.fightOrigin));
            double d = Math.max(Math.sqrt(blockPos.getSquaredDistance(this.getPos())) / 4.0, 1.0);
            e = (double)segmentOffset / d;
        } else {
            e = phase.isSittingOrHovering() ? (double)segmentOffset : (segmentOffset == 6 ? 0.0 : segment2[1] - segment1[1]);
        }
        return (float)e;
    }

    public Vec3d getRotationVectorFromPhase(float tickDelta) {
        Vec3d vec3d;
        HighlandDragonPhase phase = this.phaseManager.getCurrent();
        HighlandDragonPhaseType<? extends HighlandDragonPhase> phaseType = phase.getType();
        if (phaseType == HighlandDragonPhaseType.HIGHLAND_DRAGON_LANDING || phaseType == HighlandDragonPhaseType.HIGHLAND_DRAGON_TAKEOFF) {
            BlockPos blockPos = this.getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.offsetOrigin(this.fightOrigin));
            float f = Math.max((float)Math.sqrt(blockPos.getSquaredDistance(this.getPos())) / 4.0f, 1.0f);
            float g = 6.0f / f;
            float h = this.getPitch();
            float i = 1.5f;
            this.setPitch(-g * 1.5f * 5.0f);
            vec3d = this.getRotationVec(tickDelta);
            this.setPitch(h);
        } else if (phase.isSittingOrHovering()) {
            float j = this.getPitch();
            float f = 1.5f;
            this.setPitch(-45.0f);
            vec3d = this.getRotationVec(tickDelta);
            this.setPitch(j);
        } else {
            vec3d = this.getRotationVec(tickDelta);
        }
        return vec3d;
    }

    public void crystalDestroyed(EndCrystalEntity endCrystal, BlockPos pos, DamageSource source) {
        PlayerEntity playerEntity = source.getAttacker() instanceof PlayerEntity ? (PlayerEntity)source.getAttacker() : this.getWorld().getClosestPlayer(CLOSE_PLAYER_PREDICATE, pos.getX(), pos.getY(), pos.getZ());
        if (endCrystal == this.connectedCrystal) {
            this.damagePart(this.head, this.getDamageSources().explosion(endCrystal, playerEntity), 10.0f);
        }
        this.phaseManager.getCurrent().crystalDestroyed(endCrystal, pos, source, playerEntity);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (HIGHLAND_DRAGON_PHASE_TYPE.equals(data) && this.getWorld().isClient) {
            this.phaseManager.setPhase(HighlandDragonPhaseType.getFromId(this.getDataTracker().get(HIGHLAND_DRAGON_PHASE_TYPE)));
        }
        super.onTrackedDataSet(data);
    }

    public HighlandDragonPhaseManager getPhaseManager() {
        return this.phaseManager;
    }

    //@Nullable
    //public HighlandDragonFight getFight() {
    //    return this.fight;
    //}

    @Override
    public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
        return false;
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }

    @Override
    public boolean canUsePortals() {
        return false;
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        HighlandDragonPart[] HighlandDragonParts = this.getBodyParts();
        for (int i = 0; i < HighlandDragonParts.length; ++i) {
            HighlandDragonParts[i].setId(i + packet.getId());
        }
    }

    @Override
    public boolean canTarget(LivingEntity target) {
        return target.canTakeDamage();
    }

    @Override
    protected Vector3f getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
        return new Vector3f(0.0f, this.body.getHeight(), 0.0f);
    }
}

