package com.trustysidekick.dragonrealm.entity;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonEntity;
import com.trustysidekick.dragonrealm.entity.custom.PorcupineEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<PorcupineEntity> PORCUPINE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "porcupine"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PorcupineEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    public static final EntityType<DragonWhelpEntity> DRAGONWHELP = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "dragonwhelp"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    //Highland Dragon can ...
    public static final EntityType<HighlandDragonEntity> HIGHLAND_DRAGON = Registry.register(Registries.ENTITY_TYPE,
        //new Identifier(DragonRealm.MOD_ID, "highlanddragon"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HighlandDragonEntity::new).dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());
        new Identifier(DragonRealm.MOD_ID, "highlanddragon"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HighlandDragonEntity::new).build());

    public static final EntityType<DragonWhelpEntity> SCORCHWING = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "scorchwing"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    public static final EntityType<DragonWhelpEntity> MAGMACRAWLER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "magmacrawler"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    public static final EntityType<DragonWhelpEntity> SANDSTALKER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "sandstalker"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    public static final EntityType<DragonWhelpEntity> SHOCKSTORM = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "shockstorm"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    public static final EntityType<DragonWhelpEntity> VENOMFANG = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "venomfang"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    public static final EntityType<DragonWhelpEntity> DEATHWHISPERER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "deathwhisperer"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    //dragonlord is the kind of all dragons and it 
    public static final EntityType<DragonWhelpEntity> DRAGONLORD = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "dragonlord"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    //echoshroud can create duplicate "holograms" of itself during a battle to trick you on which is attacking
    public static final EntityType<DragonWhelpEntity> ECHOSHROUD = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(DragonRealm.MOD_ID, "echoshroud"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DragonWhelpEntity::new)
                    .dimensions(EntityDimensions.fixed(3.0f,3.0f)).build());

    public static void registerModEntities() {
        DragonRealm.LOGGER.info("Registering Entities for " + DragonRealm.MOD_ID);
    }
}
