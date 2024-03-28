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

                        //ITEMS
                        entries.add(ModItems.DRAGON_HORN_TORCH);
                        entries.add(ModItems.DRAGON_BACKPACK);
                        entries.add(ModItems.DRAGON_INGOT);
                        entries.add(ModItems.SEARING_IRON_INGOT);
                        entries.add(ModItems.DRAGON_HEAD);
                        entries.add(ModItems.DRAGON_TALON);
                        entries.add(ModItems.DRAGON_MEAT);
                        entries.add(ModItems.DRAGON_HIDE);
                        entries.add(ModItems.DRAGON_SCALE);
                        entries.add(ModItems.DRAGON_BONE);
                        entries.add(ModItems.DRAGON_HORN);
                        entries.add(ModItems.DRAGON_TOOTH);
                        entries.add(ModItems.DRAGON_EYE);
                        entries.add(ModItems.DRAGON_INGOT_BLADE);
                        entries.add(ModItems.DRAGON_INGOT_SPEARHEAD);
                        entries.add(ModItems.DRAGON_BONE_SHAFT);
                        entries.add(ModItems.DRAGON_BONE_HILT);
                        entries.add(ModItems.DRAGON_STEAK);
                        entries.add(ModItems.PORCUPINE_SPAWN_EGG);
                        //entries.add(ModItems.NULL_MATERIAL); // To not allow repair dragon horn/bone/scale tools.
                        entries.add(ModItems.DRAGON_BLOOD);

                        // TOOLS
                        entries.add(ModItems.DRAGON_PICKAXE);
                        entries.add(ModItems.DRAGON_HORN_PICKAXE);
                        entries.add(ModItems.DRAGON_JAWBONE_AXE);
                        entries.add(ModItems.DRAGON_SCALE_SHOVEL);
                        entries.add(ModItems.DRAGON_AXE);
                        entries.add(ModItems.DRAGON_SHOVEL);
                        entries.add(ModItems.DRAGON_HOE);

                        //WEAPONS
                        entries.add(ModItems.DRAGON_CLUB);
                        entries.add(ModItems.DRAGON_SPEAR);
                        entries.add(ModItems.DRAGON_BOW);
                        entries.add(ModItems.DRAGON_CROSSBOW);
                        entries.add(ModItems.DRAGON_SWORD);
                        entries.add(ModItems.DRAGON_SHIELD);
                        entries.add(ModItems.DRAGON_BATTLEAXE);

                        //ARMOR
                        entries.add(ModItems.DRAGON_HELMET);
                        entries.add(ModItems.DRAGON_CHESTPLATE);
                        entries.add(ModItems.DRAGON_LEGGINGS);
                        entries.add(ModItems.DRAGON_BOOTS);
                        entries.add(ModItems.DRAGON_HIDE_TUNIC);
                        entries.add(ModItems.DRAGON_HIDE_CAP);
                        entries.add(ModItems.DRAGON_HIDE_PANTS);
                        entries.add(ModItems.DRAGON_HIDE_BOOTS);
                        entries.add(ModItems.DRAGON_HIDE_CAPE);

                        //BLOCKS
                        entries.add(ModBlocks.DRAGON_ALTAR_BLOCK);
                        entries.add(ModBlocks.DRAGON_FORGE_BLOCK);
                        entries.add(ModBlocks.QUENCH_TANK_BLOCK);

                    }).build());
    public static void registerItemGroups() {
        DragonRealm.LOGGER.info("Registering Item Groups for " + DragonRealm.MOD_ID);
    }
}
