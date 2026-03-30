package net.monkeyskl.inscriptions;

import net.fabricmc.api.ModInitializer;

import net.monkeyskl.inscriptions.block.ModBlocks;
import net.monkeyskl.inscriptions.enchantment.ModEnchantmentEffects;
import net.monkeyskl.inscriptions.item.ModCreativeModeTab;
import net.monkeyskl.inscriptions.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * IDEAS: <p>
 *  	-Over Enchanting <p>
 *  	 	- New Enchanting table(Inscription Table) & Bookshelves
 *  	-Enchanting = Minecraft's version of magic <p>
 *  	-Custom Enchants<p>
 *  	-Enchantment books like spells or something<p>
 *  	- <p>
 *  	- <p>
 *  	- <p>
 * INSCRIPTION SYSTEM: <p>
 *      -
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
		ModEnchantmentEffects.registerEnchantmentEffects();
	}
}