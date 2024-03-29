package com.trustysidekick.dragonrealm.block;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.block.custom.QuenchTankBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block DRAGON_ALTAR_BLOCK = registerBlock("dragon_altar_block", new Block(FabricBlockSettings.create()));
    public static final Block DRAGON_FORGE_BLOCK = registerBlock("dragon_forge_block", new DragonForgeBlock(FabricBlockSettings.create().nonOpaque()));
    public static final Block QUENCH_TANK_BLOCK = registerBlock("quench_tank", new QuenchTankBlock(FabricBlockSettings.create()));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(DragonRealm.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(DragonRealm.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        DragonRealm.LOGGER.info("Registering Mod Blocks for " + DragonRealm.MOD_ID);
    }
}
