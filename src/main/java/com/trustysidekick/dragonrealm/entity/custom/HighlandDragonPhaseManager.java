/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import com.mojang.logging.LogUtils;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonEntity;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPhaseType;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPhase;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class HighlandDragonPhaseManager {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final HighlandDragonEntity dragon;
    private final HighlandDragonPhase[] phases = new HighlandDragonPhase[HighlandDragonPhaseType.count()];
    @Nullable
    private HighlandDragonPhase current;

    public HighlandDragonPhaseManager(HighlandDragonEntity dragon) {
        this.dragon = dragon;
        this.setPhase(HighlandDragonPhaseType.HIGHLAND_DRAGON_HOVER);
    }

    public void setPhase(HighlandDragonPhaseType<?> type) {
        if (this.current != null && type == this.current.getType()) {
            return;
        }
        if (this.current != null) {
            this.current.endPhase();
        }
        this.current = this.create(type);
        if (!this.dragon.getWorld().isClient) {
            this.dragon.getDataTracker().set(HighlandDragonEntity.HIGHLAND_DRAGON_PHASE_TYPE, type.getTypeId());
        }
        LOGGER.debug("Dragon is now in phase {} on the {}", (Object)type, (Object)(this.dragon.getWorld().isClient ? "client" : "server"));
        this.current.beginPhase();
    }

    public HighlandDragonPhase getCurrent() {
        return this.current;
    }

    public <T extends HighlandDragonPhase> T create(HighlandDragonPhaseType<T> type) {
        int i = type.getTypeId();
        if (this.phases[i] == null) {
            this.phases[i] = type.create(this.dragon);
        }
        return (T)this.phases[i];
    }
}

