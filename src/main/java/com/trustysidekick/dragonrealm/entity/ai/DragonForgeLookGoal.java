package com.trustysidekick.dragonrealm.entity.ai;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import com.trustysidekick.dragonrealm.entity.client.DragonWhelpModel;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;


public class DragonForgeLookGoal extends Goal {
    private final MobEntity mob;
    private BlockEntity block;

    public DragonForgeLookGoal(MobEntity mob) {
        this.mob = mob;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    public boolean canStart() {
        BlockPos entityPos = this.mob.getBlockPos();
        int radius = 4;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockPos = entityPos.add(x, y, z);
                    block = this.mob.getWorld().getBlockEntity(blockPos);
                    if (block instanceof DragonForgeBlockEntity && this.mob.getWorld().getBlockState(blockPos).get(DragonForgeBlock.BURNING)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    public void tick() {

     }


}
