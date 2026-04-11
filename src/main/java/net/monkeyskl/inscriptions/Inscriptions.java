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
 * IDEAS: <p>
 *  	-Enchanting = Minecraft's version of magic <p>
 *  	-Custom Enchants<p>
 *  	-Enchantment books like spells or something<p>
 *  	-Different playstyles <p>
 *  	- <p>
 *  	- <p>
 * INSCRIPTION SYSTEM: <p>
 *      -Upgraded Enchanting Table <p>
 *      -Different type of bookshelves <p>
 *      -Over Enchanting {
 *			Needs a material to upgrade Ex: Sharpness V -> Sharpness VI needs an Amethyst Shard
 *      }
 * */
public class Inscriptions implements ModInitializer {
	public static final String MOD_ID = "inscriptions";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

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