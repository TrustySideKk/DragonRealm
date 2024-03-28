package com.trustysidekick.dragonrealm.entity;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
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
                    //.dimensions(EntityDimensions.fixed(0.7f,0.7f)).build());
                    //.dimensions(EntityDimensions.fixed(10.7f,10.7f)).build());

    public static void registerModEntities() {
        DragonRealm.LOGGER.info("Registering Entities for " + DragonRealm.MOD_ID);
    }
}
