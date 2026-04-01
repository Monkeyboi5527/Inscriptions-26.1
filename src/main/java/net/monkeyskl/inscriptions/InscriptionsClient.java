package net.monkeyskl.inscriptions;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.monkeyskl.inscriptions.menu.ModMenuTypes;
import net.monkeyskl.inscriptions.menu.custom.InscriptionTableScreen;

public class InscriptionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.INSCRIPTION_TABLE, InscriptionTableScreen::new);
    }
}
