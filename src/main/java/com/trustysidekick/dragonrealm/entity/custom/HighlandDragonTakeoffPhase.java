/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.jetbrains.annotations.Nullable;

public class HighlandDragonTakeoffPhase
extends HighlandDragonAbstractPhase {
    private boolean shouldFindNewPath;
    @Nullable
    private Path path;
    @Nullable
    private Vec3d pathTarget;

    public HighlandDragonTakeoffPhase(HighlandDragonEntity highlandDragonEntity) {
        super(highlandDragonEntity);
    }

    @Override
    public void serverTick() {
        if (this.shouldFindNewPath || this.path == null) {
            this.shouldFindNewPath = false;
            this.updatePath();
        } else {
            BlockPos blockPos = this.dragon.getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.offsetOrigin(this.dragon.getFightOrigin()));
            if (!blockPos.isWithinDistance(this.dragon.getPos(), 10.0)) {
                this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_HOLDING_PATTERN);
            }
        }
    }

    @Override
    public void beginPhase() {
        this.shouldFindNewPath = true;
        this.path = null;
        this.pathTarget = null;
    }

    private void updatePath() {
        int i = this.dragon.getNearestPathNodeIndex();
        Vec3d vec3d = this.dragon.getRotationVectorFromPhase(1.0f);
        int j = this.dragon.getNearestPathNodeIndex(-vec3d.x * 40.0, 105.0, -vec3d.z * 40.0);
        //if (this.dragon.getFight() == null || this.dragon.getFight().getAliveEndCrystals() <= 0) {
            j -= 12;
            j &= 7;
            j += 12;
        //} else if ((j %= 12) < 0) {
        //    j += 12;
        //}
        this.path = this.dragon.findPath(i, j, null);
        this.followPath();
    }

    private void followPath() {
        if (this.path != null) {
            this.path.next();
            if (!this.path.isFinished()) {
                double d;
                BlockPos vec3i = this.path.getCurrentNodePos();
                this.path.next();
                while ((d = (double)((float)vec3i.getY() + this.dragon.getRandom().nextFloat() * 20.0f)) < (double)vec3i.getY()) {
                }
                this.pathTarget = new Vec3d(vec3i.getX(), d, vec3i.getZ());
            }
        }
    }

    @Override
    @Nullable
    public Vec3d getPathTarget() {
        return this.pathTarget;
    }

    public HighlandDragonPhaseType<HighlandDragonTakeoffPhase> getType() {
        return HighlandDragonPhaseType.HIGHLAND_DRAGON_TAKEOFF;
    }
}

