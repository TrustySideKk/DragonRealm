/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import com.mojang.logging.LogUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.ai.pathing.PathNode;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonEntity;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPhaseType;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class HighlandDragonStrafePlayerPhase
extends HighlandDragonAbstractPhase {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int MINIMUM_TARGET_SPOT_AMOUNT = 5;
    private int seenTargetTimes;
    @Nullable
    private Path path;
    @Nullable
    private Vec3d pathTarget;
    @Nullable
    private LivingEntity target;
    private boolean shouldFindNewPath;

    public HighlandDragonStrafePlayerPhase(HighlandDragonEntity highlandDragonEntity) {
        super(highlandDragonEntity);
    }

    @Override
    public void serverTick() {
        double h;
        double e;
        double d;
        if (this.target == null) {
            LOGGER.warn("Skipping player strafe phase because no player was found");
            this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_HOLDING_PATTERN);
            return;
        }
        if (this.path != null && this.path.isFinished()) {
            d = this.target.getX();
            e = this.target.getZ();
            double f = d - this.dragon.getX();
            double g = e - this.dragon.getZ();
            h = Math.sqrt(f * f + g * g);
            double i = Math.min((double)0.4f + h / 80.0 - 1.0, 10.0);
            this.pathTarget = new Vec3d(d, this.target.getY() + i, e);
        }
        double d2 = d = this.pathTarget == null ? 0.0 : this.pathTarget.squaredDistanceTo(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
        if (d < 100.0 || d > 22500.0) {
            this.updatePath();
        }
        e = 64.0;
        if (this.target.squaredDistanceTo(this.dragon) < 4096.0) {
            if (this.dragon.canSee(this.target)) {
                ++this.seenTargetTimes;
                Vec3d vec3d = new Vec3d(this.target.getX() - this.dragon.getX(), 0.0, this.target.getZ() - this.dragon.getZ()).normalize();
                Vec3d vec3d2 = new Vec3d(MathHelper.sin(this.dragon.getYaw() * ((float)Math.PI / 180)), 0.0, -MathHelper.cos(this.dragon.getYaw() * ((float)Math.PI / 180))).normalize();
                float j = (float)vec3d2.dotProduct(vec3d);
                float k = (float)(Math.acos(j) * 57.2957763671875);
                k += 0.5f;
                if (this.seenTargetTimes >= 5 && k >= 0.0f && k < 10.0f) {
                    h = 1.0;
                    Vec3d vec3d3 = this.dragon.getRotationVec(1.0f);
                    double l = this.dragon.head.getX() - vec3d3.x * 1.0;
                    double m = this.dragon.head.getBodyY(0.5) + 0.5;
                    double n = this.dragon.head.getZ() - vec3d3.z * 1.0;
                    double o = this.target.getX() - l;
                    double p = this.target.getBodyY(0.5) - m;
                    double q = this.target.getZ() - n;
                    if (!this.dragon.isSilent()) {
                        this.dragon.getWorld().syncWorldEvent(null, WorldEvents.ENDER_DRAGON_SHOOTS, this.dragon.getBlockPos(), 0);
                    }
                    DragonFireballEntity dragonFireballEntity = new DragonFireballEntity(this.dragon.getWorld(), this.dragon, o, p, q);
                    dragonFireballEntity.refreshPositionAndAngles(l, m, n, 0.0f, 0.0f);
                    this.dragon.getWorld().spawnEntity(dragonFireballEntity);
                    this.seenTargetTimes = 0;
                    if (this.path != null) {
                        while (!this.path.isFinished()) {
                            this.path.next();
                        }
                    }
                    this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_HOLDING_PATTERN);
                }
            } else if (this.seenTargetTimes > 0) {
                --this.seenTargetTimes;
            }
        } else if (this.seenTargetTimes > 0) {
            --this.seenTargetTimes;
        }
    }

    private void updatePath() {
        if (this.path == null || this.path.isFinished()) {
            int i;
            int j = i = this.dragon.getNearestPathNodeIndex();
            if (this.dragon.getRandom().nextInt(8) == 0) {
                this.shouldFindNewPath = !this.shouldFindNewPath;
                j += 6;
            }
            j = this.shouldFindNewPath ? ++j : --j;
            //if (this.dragon.getFight() == null || this.dragon.getFight().getAliveEndCrystals() <= 0) {
                j -= 12;
                j &= 7;
                j += 12;
            //} else if ((j %= 12) < 0) {
            //    j += 12;
            //}
            this.path = this.dragon.findPath(i, j, null);
            if (this.path != null) {
                this.path.next();
            }
        }
        this.followPath();
    }

    private void followPath() {
        if (this.path != null && !this.path.isFinished()) {
            double f;
            BlockPos vec3i = this.path.getCurrentNodePos();
            this.path.next();
            double d = vec3i.getX();
            double e = vec3i.getZ();
            while ((f = (double)((float)vec3i.getY() + this.dragon.getRandom().nextFloat() * 20.0f)) < (double)vec3i.getY()) {
            }
            this.pathTarget = new Vec3d(d, f, e);
        }
    }

    @Override
    public void beginPhase() {
        this.seenTargetTimes = 0;
        this.pathTarget = null;
        this.path = null;
        this.target = null;
    }

    public void setTargetEntity(LivingEntity targetEntity) {
        this.target = targetEntity;
        int i = this.dragon.getNearestPathNodeIndex();
        int j = this.dragon.getNearestPathNodeIndex(this.target.getX(), this.target.getY(), this.target.getZ());
        int k = this.target.getBlockX();
        int l = this.target.getBlockZ();
        double d = (double)k - this.dragon.getX();
        double e = (double)l - this.dragon.getZ();
        double f = Math.sqrt(d * d + e * e);
        double g = Math.min((double)0.4f + f / 80.0 - 1.0, 10.0);
        int m = MathHelper.floor(this.target.getY() + g);
        PathNode pathNode = new PathNode(k, m, l);
        this.path = this.dragon.findPath(i, j, pathNode);
        if (this.path != null) {
            this.path.next();
            this.followPath();
        }
    }

    @Override
    @Nullable
    public Vec3d getPathTarget() {
        return this.pathTarget;
    }

    public HighlandDragonPhaseType<HighlandDragonStrafePlayerPhase> getType() {
        return HighlandDragonPhaseType.HIGHLAND_DRAGON_STRAFE_PLAYER;
    }
}

