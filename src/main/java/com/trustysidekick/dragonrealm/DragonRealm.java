package com.trustysidekick.dragonrealm;

import com.trustysidekick.dragonrealm.block.ModBlocks;
import com.trustysidekick.dragonrealm.block.custom.DragonForgeBlock;
import com.trustysidekick.dragonrealm.block.custom.SmithingAnvilBlock;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.SmithingAnvilBlockEntity;
import com.trustysidekick.dragonrealm.entity.ModEntities;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import com.trustysidekick.dragonrealm.entity.custom.PorcupineEntity;
import com.trustysidekick.dragonrealm.item.ModItemGroups;
import com.trustysidekick.dragonrealm.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DragonRealm implements ModInitializer {

	public static final String MOD_ID = "dragonrealm";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModEntities.registerModEntities();
		CustomEntitySpawns.RegisterEntitySpawns();


		FabricDefaultAttributeRegistry.register(ModEntities.PORCUPINE, PorcupineEntity.createPorcupineAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DRAGONWHELP, DragonWhelpEntity.createDragonWhelpAttributes());






		//// EVENTS ////
		// Left-click on SmithingAnvilBlock
		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
			BlockEntity entity = world.getBlockEntity(pos);

			if (!world.isClient) {
				if (player != null && entity instanceof SmithingAnvilBlockEntity) {
					if (player.getStackInHand(hand).getItem() == Items.STICK && direction == Direction.UP) {
						int strike = world.getBlockState(pos).get(SmithingAnvilBlock.STRIKE);
						if (strike < 5) {
							strike++;
							world.setBlockState(pos, world.getBlockState(pos).with(SmithingAnvilBlock.STRIKE, strike));
						}
						world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 10.0f, 1.0f);
						return ActionResult.SUCCESS;
					}

				}
			}
			return ActionResult.PASS;
		});

		// Right-click dragon using a bottle
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof DragonWhelpEntity) {
				if (player != null && player.getStackInHand(hand).getItem() == Items.GLASS_BOTTLE) {
					if (((DragonWhelpEntity) entity).getHealth() < ((DragonWhelpEntity) entity).getMaxHealth()) {
						int bloodDraw = ((DragonWhelpEntity)entity).getBloodDraw();
						if (bloodDraw > 0) {
							((DragonWhelpEntity) entity).setBloodDraw(bloodDraw - 1);
							player.getInventory().getMainHandStack().decrement(1);
							player.getInventory().offerOrDrop(new ItemStack(ModItems.DRAGON_BLOOD));
						}
					}
					return ActionResult.SUCCESS;
				}
			}
			return ActionResult.PASS;
		});


	}

}
