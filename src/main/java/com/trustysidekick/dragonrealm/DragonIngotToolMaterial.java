package com.trustysidekick.dragonrealm;

import com.trustysidekick.dragonrealm.item.ModItems;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public class DragonIngotToolMaterial implements ToolMaterial {
    public static final DragonIngotToolMaterial INSTANCE = new DragonIngotToolMaterial();

    @Override
    public int getDurability() {
        return ToolMaterials.NETHERITE.getDurability();
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return ToolMaterials.NETHERITE.getMiningSpeedMultiplier();
    }

    @Override
    public float getAttackDamage() {
        return ToolMaterials.NETHERITE.getAttackDamage();
    }

    @Override
    public int getMiningLevel() {
        return MiningLevels.NETHERITE;
    }

    @Override
    public int getEnchantability() {
        return ToolMaterials.GOLD.getEnchantability();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.DRAGON_INGOT);
    }


}
