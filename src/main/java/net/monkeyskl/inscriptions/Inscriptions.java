package net.monkeyskl.inscriptions;

import net.fabricmc.api.ModInitializer;

import net.monkeyskl.inscriptions.block.ModBlocks;
import net.monkeyskl.inscriptions.block.entity.ModBlockEntities;
import net.monkeyskl.inscriptions.enchantment.ModEnchantmentEffects;
import net.monkeyskl.inscriptions.entity.ModEntities;
import net.monkeyskl.inscriptions.item.ModCreativeModeTab;
import net.monkeyskl.inscriptions.item.ModItems;
import net.monkeyskl.inscriptions.menu.ModMenuTypes;
import net.monkeyskl.inscriptions.particle.ModParticles;
import net.monkeyskl.inscriptions.recipe.ModRecipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Inscriptions is a mod that aims to rework Minecraft's enchantment system.
 * */
public class Inscriptions implements ModInitializer {

	// Brainstorming

//	Higher level enchanting like Sharpness 3-5 needs more than just bookshelves and experience points
//	Special bookshelves or different themed blocks to get certain enchantments Ex: Luck of the Sea might need prismarine/ prismarine type bookshelves
//	Enchanting less rng
//	Mending villagers are too OP
//	Fishing enchanted book is worth it

//	Overenchanting(TestCraftingBlock for now) needs max enchantment and an item (for now) Ex: Sharpness V -> Sharpness VI+ needs Amethyst Shard

//	Inscriptions are a different type of enchanted book. you won't beable to read it and have to decipher.
//	Inscriptions are powerful but rare

//	Magic will use something created with enchantments Ex: Fire Aspect -> Fireball

//	Curses two-sided blade. Use it and pay the price Ex: Increased all stats for 5 minutes but halved stats for 10



	public static final String MOD_ID = "inscriptions";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		LOGGER.info("Loading Inscriptions!");

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModCreativeModeTab.registerModCreativeModeTab();
		ModBlockEntities.registerBlockEntities();
		ModMenuTypes.registerMenuTypes();
		ModEnchantmentEffects.registerEnchantmentEffects();
		ModEntities.registerModEntities();
		ModParticles.registerModParticles();
		ModRecipes.registerModRecipes();
	}
}