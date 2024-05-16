/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.jetbrains.annotations.Nullable;

public class HighlandDragonLandingPhase
extends HighlandDragonAbstractPhase {
    @Nullable
    private Vec3d target;

    public HighlandDragonLandingPhase(HighlandDragonEntity highlandDragonEntity) {
        super(highlandDragonEntity);
    }

    @Override
    public void clientTick() {
        Vec3d vec3d = this.dragon.getRotationVectorFromPhase(1.0f).normalize();
        vec3d.rotateY(-0.7853982f);
        double d = this.dragon.head.getX();
        double e = this.dragon.head.getBodyY(0.5);
        double f = this.dragon.head.getZ();
        for (int i = 0; i < 8; ++i) {
            Random random = this.dragon.getRandom();
            double g = d + random.nextGaussian() / 2.0;
            double h = e + random.nextGaussian() / 2.0;
            double j = f + random.nextGaussian() / 2.0;
            Vec3d vec3d2 = this.dragon.getVelocity();
            this.dragon.getWorld().addParticle(ParticleTypes.DRAGON_BREATH, g, h, j, -vec3d.x * (double)0.08f + vec3d2.x, -vec3d.y * (double)0.3f + vec3d2.y, -vec3d.z * (double)0.08f + vec3d2.z);
            vec3d.rotateY(0.19634955f);
        }
    }

    @Override
    public void serverTick() {
        if (this.target == null) {
            this.target = Vec3d.ofBottomCenter(this.dragon.getWorld().getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.offsetOrigin(this.dragon.getFightOrigin())));
        }
        if (this.target.squaredDistanceTo(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ()) < 1.0) {
            this.dragon.getPhaseManager().create(HighlandDragonPhaseType.HIGHLAND_DRAGON_SITTING_FLAMING).reset();
            this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_SITTING_SCANNING);
        }
    }

    @Override
    public float getMaxYAcceleration() {
        return 1.5f;
    }

    @Override
    public float getYawAcceleration() {
        float f = (float)this.dragon.getVelocity().horizontalLength() + 1.0f;
        float g = Math.min(f, 40.0f);
        return g / f;
    }

    @Override
    public void beginPhase() {
        this.target = null;
    }

    @Override
    @Nullable
    public Vec3d getPathTarget() {
        return this.target;
    }

    public HighlandDragonPhaseType<HighlandDragonLandingPhase> getType() {
        return HighlandDragonPhaseType.HIGHLAND_DRAGON_LANDING;
    }
}

