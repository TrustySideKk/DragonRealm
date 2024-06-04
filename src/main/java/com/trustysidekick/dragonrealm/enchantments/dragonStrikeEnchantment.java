package com.trustysidekick.dragonrealm.enchantments;

import com.trustysidekick.dragonrealm.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class dragonStrikeEnchantment extends Enchantment {
    protected dragonStrikeEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(rarity, target, slotTypes);
    }


    @Override
    public int getMinPower(int level) {
        return 10;
    }

    @Override
    public int getMaxPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return true;
    }

//    @Override
//    public Identifier getIdentifier() {
//        return new Identifier("mymod", "custom_enchantment");
//    }

//    @Override
//    public float getAttackDamage(int level, EntityAttribute attribute) {
//        return (float) (level * 1.5); // Adjust the damage boost based on the enchantment level
//    }

//    @Override
//    public void onTargetDamaged(int level, ItemStack item, net.minecraft.entity.LivingEntity target, net.minecraft.entity.LivingEntity attacker) {
//        // Add custom behavior when the enchantment triggers, such as applying effects or modifying attributes
//    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        //if(target instanceof LivingEntity) {
            ItemStack stack = user.getMainHandStack();
            if (stack.getItem() == ModItems.DRAGON_SWORD) {
                NbtCompound tag = stack.getOrCreateNbt();
                float cowKill = tag.getInt("kills") * 0.5f;
                //((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, (int) cowKill));
                target.damage(target.getDamageSources().generic(), user.getMainHandStack().getDamage() + cowKill);
            }

        //}

        super.onTargetDamaged(user, target, level);
    }
}
