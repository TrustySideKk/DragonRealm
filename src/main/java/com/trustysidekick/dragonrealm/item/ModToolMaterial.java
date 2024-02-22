package com.trustysidekick.dragonrealm.item;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    DRAGON_INGOT(MiningLevels.NETHERITE, ToolMaterials.NETHERITE.getDurability(), ToolMaterials.NETHERITE.getMiningSpeedMultiplier(), ToolMaterials.NETHERITE.getAttackDamage(), ToolMaterials.NETHERITE.getEnchantability(), () -> Ingredient.ofItems(ModItems.DRAGON_INGOT)),
    NULL_TOOL_MATERIAL(MiningLevels.DIAMOND,ToolMaterials.DIAMOND.getDurability(), ToolMaterials.DIAMOND.getMiningSpeedMultiplier(), ToolMaterials.DIAMOND.getAttackDamage(), ToolMaterials.DIAMOND.getEnchantability(), () -> Ingredient.ofItems(ModItems.NULL_TOOL_MATERIAL));





    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;








    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
