/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonEntity;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPhaseType;
import net.minecraft.sound.SoundEvents;

public class HighlandDragonSittingAttackingPhase
extends HighlandDragonAbstractSittingPhase {
    private static final int DURATION = 40;
    private int ticks;

    public HighlandDragonSittingAttackingPhase(HighlandDragonEntity highlandDragonEntity) {
        super(highlandDragonEntity);
    }

    @Override
    public void clientTick() {
        this.dragon.getWorld().playSound(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, this.dragon.getSoundCategory(), 2.5f, 0.8f + this.dragon.getRandom().nextFloat() * 0.3f, false);
    }

    @Override
    public void serverTick() {
        if (this.ticks++ >= 40) {
            this.dragon.getPhaseManager().setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_SITTING_FLAMING);
        }
    }

    @Override
    public void beginPhase() {
        this.ticks = 0;
    }

    public HighlandDragonPhaseType<HighlandDragonSittingAttackingPhase> getType() {
        return HighlandDragonPhaseType.HIGHLAND_DRAGON_SITTING_ATTACKING;
    }
}

