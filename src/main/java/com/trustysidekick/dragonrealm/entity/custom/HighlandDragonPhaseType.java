/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class HighlandDragonPhaseType<T extends HighlandDragonPhase> {
    private static HighlandDragonPhaseType<?>[] types = new HighlandDragonPhaseType[0];
    public static final HighlandDragonPhaseType<HighlandDragonHoldingPatternPhase> HIGHLAND_DRAGON_HOLDING_PATTERN = HighlandDragonPhaseType.register(HighlandDragonHoldingPatternPhase.class, "HighlandDragonHoldingPattern");
    public static final HighlandDragonPhaseType<HighlandDragonStrafePlayerPhase> HIGHLAND_DRAGON_STRAFE_PLAYER = HighlandDragonPhaseType.register(HighlandDragonStrafePlayerPhase.class, "HighlandDragonStrafePlayer");
    public static final HighlandDragonPhaseType<HighlandDragonLandingApproachPhase> HIGHLAND_DRAGON_LANDING_APPROACH = HighlandDragonPhaseType.register(HighlandDragonLandingApproachPhase.class, "HighlandDragonLandingApproach");
    public static final HighlandDragonPhaseType<HighlandDragonLandingPhase> HIGHLAND_DRAGON_LANDING = HighlandDragonPhaseType.register(HighlandDragonLandingPhase.class, "HighlandDragonLanding");
    public static final HighlandDragonPhaseType<HighlandDragonTakeoffPhase> HIGHLAND_DRAGON_TAKEOFF = HighlandDragonPhaseType.register(HighlandDragonTakeoffPhase.class, "HighlandDragonTakeoff");
    public static final HighlandDragonPhaseType<HighlandDragonSittingFlamingPhase> HIGHLAND_DRAGON_SITTING_FLAMING = HighlandDragonPhaseType.register(HighlandDragonSittingFlamingPhase.class, "HighlandDragonSittingFlaming");
    public static final HighlandDragonPhaseType<HighlandDragonSittingScanningPhase> HIGHLAND_DRAGON_SITTING_SCANNING = HighlandDragonPhaseType.register(HighlandDragonSittingScanningPhase.class, "HighlandDragonSittingScanning");
    public static final HighlandDragonPhaseType<HighlandDragonSittingAttackingPhase> HIGHLAND_DRAGON_SITTING_ATTACKING = HighlandDragonPhaseType.register(HighlandDragonSittingAttackingPhase.class, "HighlandDragonSittingAttacking");
    public static final HighlandDragonPhaseType<HighlandDragonChargingPlayerPhase> HIGHLAND_DRAGON_CHARGING_PLAYER = HighlandDragonPhaseType.register(HighlandDragonChargingPlayerPhase.class, "HighlandDragonChargingPlayer");
    public static final HighlandDragonPhaseType<HighlandDragonDyingPhase> HIGHLAND_DRAGON_DYING = HighlandDragonPhaseType.register(HighlandDragonDyingPhase.class, "HighlandDragonDying");
    public static final HighlandDragonPhaseType<HighlandDragonHoverPhase> HIGHLAND_DRAGON_HOVER = HighlandDragonPhaseType.register(HighlandDragonHoverPhase.class, "HighlandDragonHover");
    private final Class<? extends HighlandDragonPhase> phaseClass;
    private final int id;
    private final String name;

    private HighlandDragonPhaseType(int id, Class<? extends HighlandDragonPhase> phaseClass, String name) {
        this.id = id;
        this.phaseClass = phaseClass;
        this.name = name;
    }

    public HighlandDragonPhase create(HighlandDragonEntity dragon) {
        try {
            Constructor<HighlandDragonPhase> constructor = (Constructor<HighlandDragonPhase>) this.getConstructor();
            return constructor.newInstance(dragon);
        } catch (Exception exception) {
            throw new Error(exception);
        }
    }

    protected Constructor<? extends HighlandDragonPhase> getConstructor() throws NoSuchMethodException {
        return this.phaseClass.getConstructor(HighlandDragonEntity.class);
    }

    public int getTypeId() {
        return this.id;
    }

    public String toString() {
        return this.name + " (#" + this.id + ")";
    }

    public static HighlandDragonPhaseType<?> getFromId(int id) {
        if (id < 0 || id >= types.length) {
            return HIGHLAND_DRAGON_HOLDING_PATTERN;
        }
        return types[id];
    }

    public static int count() {
        return types.length;
    }

    private static <T extends HighlandDragonPhase> HighlandDragonPhaseType<T> register(Class<T> phaseClass, String name) {
        HighlandDragonPhaseType<T> phaseType = new HighlandDragonPhaseType<T>(types.length, phaseClass, name);
        types = Arrays.copyOf(types, types.length + 1);
        HighlandDragonPhaseType.types[phaseType.getTypeId()] = phaseType;
        return phaseType;
    }
}

