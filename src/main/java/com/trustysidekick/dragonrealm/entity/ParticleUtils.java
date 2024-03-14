package com.trustysidekick.dragonrealm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class ParticleUtils {
    public static void emitParticlesTowardsBlock(Entity entity, World world, ParticleEffect particleEffect, BlockPos blockPos, double speed, int count) {
        Random random = new Random();
        double ranStep = 0.0 + (2.0 - 0.0) * random.nextDouble();
        double ranSpot = -0.5 + (0.5 - -0.5) * random.nextDouble();

        // Get the entity's position
        Vec3d entityPos = entity.getPos();

        // Get the block's position
        //Vec3d blockPosVec = new Vec3d(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
        Vec3d blockPosVec = new Vec3d(blockPos.getX() + ranSpot, blockPos.getY() + ranSpot, blockPos.getZ() + ranSpot);

        // Calculate the direction vector from the entity to the block
        Vec3d direction = blockPosVec.subtract(entityPos).normalize();

        // Calculate the number of steps to reach the block
        double distance = blockPosVec.distanceTo(entityPos);
        //int steps = (int) (distance / speed) + 1; // Adjust the speed for particle spacing
        int steps = (int) (distance / speed) + 1; // Adjust the speed for particle spacing



        // Calculate the step size for emitting particles along the trajectory
        double stepSizeX = direction.x * speed * ranStep;
        double stepSizeY = direction.y * speed * ranStep;
        double stepSizeZ = direction.z * speed * ranStep;

        // Emit particles at intervals along the trajectory until reaching the block
        double posX = entityPos.x;
        double posY = entityPos.y;
        double posZ = entityPos.z;

        for (int i = 0; i < steps; i++) {
            // Check if the world is a server world to prevent issues on the client
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;

                // Spawn particles at the current position
                serverWorld.spawnParticles(particleEffect, posX, posY, posZ, count, 0.0, 0.0, 0.0, 0.0);
            }

            // Move to the next position along the trajectory
            posX += stepSizeX;
            posY += stepSizeY;
            posZ += stepSizeZ;
        }
    }
}
