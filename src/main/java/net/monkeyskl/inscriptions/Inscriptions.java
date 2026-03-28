package net.monkeyskl.inscriptions;

import net.fabricmc.api.ModInitializer;

import net.monkeyskl.inscriptions.enchantment.ModEnchantmentEffects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Inscriptions implements ModInitializer {
	public static final String MOD_ID = "inscriptions";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModEnchantmentEffects.registerEnchantmentEffects();
	}
}