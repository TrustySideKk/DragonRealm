package com.trustysidekick.dragonrealm.entity.ai;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class DragonForgeLookGoal extends Goal {
    private final MobEntity mob;
    private BlockEntity block;

    public DragonForgeLookGoal(MobEntity mob) {
        this.mob = mob;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    public boolean canStart() {
        BlockPos entityPos = this.mob.getBlockPos();
        int radius = 5;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockPos = entityPos.add(x, y, z);
                    block = this.mob.getWorld().getBlockEntity(blockPos);
                    if (block instanceof DragonForgeBlockEntity && this.mob.getWorld().getBlockState(blockPos).get(DragonForgeBlock.BURNING)) {

                        if (this.mob.getWorld().isClient) {
                            System.out.println("TEST");
                            Random random = new Random();
                            double ranParticleVelocity = 1 + random.nextFloat() * (1.5 - 1);
                            double ranSpread = -0.05 + random.nextFloat() * (0.05 - -0.05);

                            Vec3d blockPosition = new Vec3d(block.getPos().getX() + 0.5, block.getPos().getY() + 0.5, block.getPos().getZ() + 0.5);
                            Vec3d entityPosition = this.mob.getPos();
                            Vec3d displacementVector = blockPosition.subtract(entityPosition);

                            this.mob.getWorld().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, displacementVector.getX(), displacementVector.getY() + 0.5, displacementVector.getZ() + 0.5, -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0);
                            this.mob.getWorld().addParticle(ParticleTypes.FLAME, displacementVector.getX(), displacementVector.getY() + 0.5, displacementVector.getZ() + 0.5, -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0);
                            return true;
                        }
                        double dx = block.getPos().getX() + 0.5 - this.mob.getPos().getX();
                        double dy = block.getPos().getY() + 0.5 - this.mob.getPos().getY(); // Use getEyeY() for better accuracy
                        double dz = block.getPos().getZ() + 0.5 - this.mob.getPos().getZ();
                        double horizontalAngle = Math.atan2(dz, dx);
                        double distanceXZ = Math.sqrt(dx * dx + dz * dz);
                        double verticalAngle = Math.atan2(dy, distanceXZ);
                        float yaw = (float) Math.toDegrees(horizontalAngle);
                        float pitch = (float) Math.toDegrees(verticalAngle);

                        //((PassiveEntity) entity).clearGoalsAndTasks();
                        this.mob.setYaw(yaw);
                        this.mob.setPitch(pitch);
                        this.mob.setVelocity(0, 0, 0);
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

        if (this.canStart()) {
            //System.out.println("canStart");
            //if (this.mob.getWorld().isClient) {

                Random random = new Random();
                //double ranParticleSpot = -1 + random.nextFloat() * (1 - -1);
                double ranParticleVelocity = 1 + random.nextFloat() * (1.5 - 1);
                double ranSpread = -0.05 + random.nextFloat() * (0.05 - -0.05);

                Vec3d blockPosition = new Vec3d(block.getPos().getX() + 0.5, block.getPos().getY() + 0.5, block.getPos().getZ() + 0.5);
                Vec3d entityPosition = this.mob.getPos();
                Vec3d displacementVector = blockPosition.subtract(entityPosition);

                this.mob.getWorld().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, displacementVector.getX(), displacementVector.getY() + 0.5, displacementVector.getZ() + 0.5, -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0);
                this.mob.getWorld().addParticle(ParticleTypes.FLAME, displacementVector.getX(), displacementVector.getY() + 0.5, displacementVector.getZ() + 0.5, -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0);
            //}

                /*
                if (world.getBlockState(pos).get(DragonForgeBlock.BURNING)) {
                    //System.out.println("Client: " + world.getBlockState(pos));

                    if (world.getBlockState(checkDragon1).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, checkDragon1.getX(), checkDragon1.getY() + 0.5, checkDragon1.getZ() + 0.5,  -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0); }
                    if (world.getBlockState(checkDragon1).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.FLAME, checkDragon1.getX(), checkDragon1.getY() + 0.5, checkDragon1.getZ() + 0.5, -ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0); }

                    if (world.getBlockState(checkDragon2).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, checkDragon2.getX() + 1, checkDragon2.getY() + 0.5, checkDragon2.getZ() + 0.5, ranParticleVelocity + 0.0, ranSpread + 0.0,  ranSpread + 0.0); }
                    if (world.getBlockState(checkDragon2).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.FLAME, checkDragon2.getX() + 1, checkDragon2.getY() + 0.5, checkDragon2.getZ() + 0.5, ranParticleVelocity + 0.0, ranSpread + 0.0, ranSpread + 0.0); }

                    if (world.getBlockState(checkDragon3).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, checkDragon3.getX() + 0.5, checkDragon3.getY() + 0.5, checkDragon3.getZ() + 1, ranSpread + 0.0, ranSpread + 0.0, ranParticleVelocity + 0.0); }
                    if (world.getBlockState(checkDragon3).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.FLAME, checkDragon3.getX() + 0.5, checkDragon3.getY() + 0.5, checkDragon3.getZ() + 1, ranSpread + 0.0, ranSpread + 0.0, ranParticleVelocity + 0.0); }

                    if (world.getBlockState(checkDragon4).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, checkDragon4.getX() + 0.5, checkDragon4.getY() + 0.5, checkDragon4.getZ(),  ranSpread + 0.0,  ranSpread + 0.0, -ranParticleVelocity + 0.0); }
                    if (world.getBlockState(checkDragon4).getBlock() == Blocks.COBBLESTONE) {world.addParticle(ParticleTypes.FLAME, checkDragon4.getX() + 0.5, checkDragon4.getY() + 0.5, checkDragon4.getZ(),  ranSpread + 0.0,  ranSpread + 0.0, -ranParticleVelocity + 0.0); }
                }

                 */



            //System.out.println("BURNING!");
        } else {
            //System.out.println("NO");
        }

    }


}
