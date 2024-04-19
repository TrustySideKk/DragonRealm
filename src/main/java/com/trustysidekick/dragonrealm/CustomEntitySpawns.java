package com.trustysidekick.dragonrealm;

import com.trustysidekick.dragonrealm.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class CustomEntitySpawns {
    public static void RegisterEntitySpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FOREST), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA_PLATEAU), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_SAVANNA), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SUNFLOWER_PLAINS), SpawnGroup.CREATURE, ModEntities.DRAGONWHELP, 5, 2, 3);

        SpawnRestriction.register(ModEntities.DRAGONWHELP, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);

    }
}
