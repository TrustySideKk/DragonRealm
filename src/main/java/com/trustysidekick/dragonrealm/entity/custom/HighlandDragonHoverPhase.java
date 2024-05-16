/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class HighlandDragonHoverPhase
extends HighlandDragonAbstractPhase {
    @Nullable
    private Vec3d target;

    public HighlandDragonHoverPhase(HighlandDragonEntity highlandDragonEntity) {
        super(highlandDragonEntity);
    }

    @Override
    public void serverTick() {
        if (this.target == null) {
            this.target = this.dragon.getPos();
        }
    }

    @Override
    public boolean isSittingOrHovering() {
        return true;
    }

    @Override
    public void beginPhase() {
        this.target = null;
    }

    @Override
    public float getMaxYAcceleration() {
        return 1.0f;
    }

    @Override
    @Nullable
    public Vec3d getPathTarget() {
        return this.target;
    }

    public HighlandDragonPhaseType<HighlandDragonHoverPhase> getType() {
        return HighlandDragonPhaseType.HIGHLAND_DRAGON_HOVER;
    }
}

