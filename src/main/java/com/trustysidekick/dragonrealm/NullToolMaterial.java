package com.trustysidekick.dragonrealm;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class NullToolMaterial implements ToolMaterial {
    public static final NullToolMaterial INSTANCE = new NullToolMaterial();

    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
