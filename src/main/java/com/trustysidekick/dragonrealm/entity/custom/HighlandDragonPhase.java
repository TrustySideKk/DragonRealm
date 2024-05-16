/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.trustysidekick.dragonrealm.entity.custom;

import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonPhaseType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public interface HighlandDragonPhase {
    public boolean isSittingOrHovering();

    public void clientTick();

    public void serverTick();

    public void crystalDestroyed(EndCrystalEntity var1, BlockPos var2, DamageSource var3, @Nullable PlayerEntity var4);

    public void beginPhase();

    public void endPhase();

    public float getMaxYAcceleration();

    public float getYawAcceleration();

    public HighlandDragonPhaseType<? extends HighlandDragonPhase> getType();

    @Nullable
    public Vec3d getPathTarget();

    public float modifyDamageTaken(DamageSource var1, float var2);
}

