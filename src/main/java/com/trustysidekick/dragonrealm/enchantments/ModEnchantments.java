package com.trustysidekick.dragonrealm.enchantments;

import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.client.texture.TextureStitcher;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Enchantment DRAGON_STRIKE = new dragonStrikeEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});

    public static void registerEnchantments() {
        Registry.register(Registries.ENCHANTMENT, new Identifier("dragonrealm", "dragon_strike"), DRAGON_STRIKE);
    }
}
