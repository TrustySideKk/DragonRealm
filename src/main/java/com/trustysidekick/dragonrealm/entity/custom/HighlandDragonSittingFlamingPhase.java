/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class HighlandDragonSittingFlamingPhase
extends HighlandDragonAbstractSittingPhase {
    private static final int DURATION = 200;
    private static final int MAX_TIMES_RUN = 4;
    private static final int DRAGON_BREATH_MAX_TICK = 10;
    private int ticks;
    private int timesRun;
    @Nullable
    private AreaEffectCloudEntity dragonBreathEntity;

    public HighlandDragonSittingFlamingPhase(HighlandDragonEntity highlandDragonEntity) {
        super(highlandDragonEntity);
    }

    @Override
    public void clientTick() {
        ++this.ticks;
        if (this.ticks % 2 == 0 && this.ticks < 10) {
            Vec3d vec3d = this.dragon.getRotationVectorFromPhase(1.0f).normalize();
            vec3d.rotateY(-0.7853982f);
            double d = this.dragon.head.getX();
            double e = this.dragon.head.getBodyY(0.5);
            double f = this.dragon.head.getZ();
            for (int i = 0; i < 8; ++i) {
                double g = d + this.dragon.getRandom().nextGaussian() / 2.0;
                double h = e + this.dragon.getRandom().nextGaussian() / 2.0;
                double j = f + this.dragon.getRandom().nextGaussian() / 2.0;
                for (int k = 0; k < 6; ++k) {
                    this.dragon.getWorld().addParticle(ParticleTypes.DRAGON_BREATH, g, h, j, -vec3d.x * (double)0.08f * (double)k, -vec3d.y * (double)0.6f, -vec3d.z * (double)0.08f * (double)k);
                }
                vec3d.rotateY(0.19634955f);
            }
        }
    }

    @Override
    public void serverTick() {
        ++this.ticks;
        if (this.ticks >= 200) {
            if (this.timesRun >= 4) {
                this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_TAKEOFF);
            } else {
                this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_SITTING_SCANNING);
            }
        } else if (this.ticks == 10) {
            double g;
            Vec3d vec3d = new Vec3d(this.dragon.head.getX() - this.dragon.getX(), 0.0, this.dragon.head.getZ() - this.dragon.getZ()).normalize();
            float f = 5.0f;
            double d = this.dragon.head.getX() + vec3d.x * 5.0 / 2.0;
            double e = this.dragon.head.getZ() + vec3d.z * 5.0 / 2.0;
            double h = g = this.dragon.head.getBodyY(0.5);
            BlockPos.Mutable mutable = new BlockPos.Mutable(d, h, e);
            while (this.dragon.getWorld().isAir(mutable)) {
                if ((h -= 1.0) < 0.0) {
                    h = g;
                    break;
                }
                mutable.set(d, h, e);
            }
            h = MathHelper.floor(h) + 1;
            this.dragonBreathEntity = new AreaEffectCloudEntity(this.dragon.getWorld(), d, h, e);
            this.dragonBreathEntity.setOwner(this.dragon);
            this.dragonBreathEntity.setRadius(5.0f);
            this.dragonBreathEntity.setDuration(200);
            this.dragonBreathEntity.setParticleType(ParticleTypes.DRAGON_BREATH);
            this.dragonBreathEntity.addEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE));
            this.dragon.getWorld().spawnEntity(this.dragonBreathEntity);
        }
    }

    @Override
    public void beginPhase() {
        this.ticks = 0;
        ++this.timesRun;
    }

    @Override
    public void endPhase() {
        if (this.dragonBreathEntity != null) {
            this.dragonBreathEntity.discard();
            this.dragonBreathEntity = null;
        }
    }

    public HighlandDragonPhaseType<HighlandDragonSittingFlamingPhase> getType() {
        return HighlandDragonPhaseType.HIGHLAND_DRAGON_SITTING_FLAMING;
    }

    public void reset() {
        this.timesRun = 0;
    }
}

