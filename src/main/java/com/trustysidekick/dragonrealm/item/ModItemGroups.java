package com.trustysidekick.dragonrealm.item;

import com.trustysidekick.dragonrealm.DragonRealm;

import com.trustysidekick.dragonrealm.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup DRAGONREALM_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(DragonRealm.MOD_ID, "dragon_pickaxe"),
                    FabricItemGroup.builder().displayName(Text.translatable("itemgroup.pickaxe"))
                    .icon(() -> new ItemStack(ModItems.DRAGON_PICKAXE)).entries((displayContext, entries) -> {
                        //Items
                        entries.add(ModItems.DRAGON_PICKAXE);
                        entries.add(ModItems.DRAGON_AXE);
                        entries.add(ModItems.DRAGON_SHOVEL);
                        //Blocks
                        entries.add(ModBlocks.RUBY_BLOCK);
                    }).build());
    public static void registerItemGroups() {
        DragonRealm.LOGGER.info("Registering Item Groups for " + DragonRealm.MOD_ID);
    }
}
