/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import com.mojang.logging.LogUtils;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class HighlandDragonChargingPlayerPhase
extends HighlandDragonAbstractPhase {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int DURATION = 10;
    @Nullable
    private Vec3d pathTarget;
    private int chargingTicks;

    public HighlandDragonChargingPlayerPhase(HighlandDragonEntity highlandDragonEntity) {
        super(highlandDragonEntity);
    }

    @Override
    public void serverTick() {
        if (this.pathTarget == null) {
            LOGGER.warn("Aborting charge player as no target was set.");
            this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_HOLDING_PATTERN);
            return;
        }
        if (this.chargingTicks > 0 && this.chargingTicks++ >= 10) {
            this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_HOLDING_PATTERN);
            return;
        }
        double d = this.pathTarget.squaredDistanceTo(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
        if (d < 100.0 || d > 22500.0 || this.dragon.horizontalCollision || this.dragon.verticalCollision) {
            ++this.chargingTicks;
        }
    }

    @Override
    public void beginPhase() {
        this.pathTarget = null;
        this.chargingTicks = 0;
    }

    public void setPathTarget(Vec3d pathTarget) {
        this.pathTarget = pathTarget;
    }

    @Override
    public float getMaxYAcceleration() {
        return 3.0f;
    }

    @Override
    @Nullable
    public Vec3d getPathTarget() {
        return this.pathTarget;
    }

    public HighlandDragonPhaseType<HighlandDragonChargingPlayerPhase> getType() {
        return HighlandDragonPhaseType.HIGHLAND_DRAGON_CHARGING_PLAYER;
    }
}

