/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;

public abstract class HighlandDragonAbstractSittingPhase
extends HighlandDragonAbstractPhase {
    public HighlandDragonAbstractSittingPhase(HighlandDragonEntity highlandDragonEntity) {
        super(highlandDragonEntity);
    }

    @Override
    public boolean isSittingOrHovering() {
        return true;
    }

    @Override
    public float modifyDamageTaken(DamageSource damageSource, float damage) {
        if (damageSource.getSource() instanceof PersistentProjectileEntity) {
            damageSource.getSource().setOnFireFor(1);
            return 0.0f;
        }
        return super.modifyDamageTaken(damageSource, damage);
    }
}

