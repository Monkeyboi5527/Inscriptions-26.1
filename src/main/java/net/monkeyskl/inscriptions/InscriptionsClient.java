package net.monkeyskl.inscriptions;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.monkeyskl.inscriptions.entity.ModEntities;
import net.monkeyskl.inscriptions.entity.cilent.DummyModel;
import net.monkeyskl.inscriptions.entity.cilent.DummyRenderer;
import net.monkeyskl.inscriptions.entity.custom.DummyEntity;
import net.monkeyskl.inscriptions.menu.ModMenuTypes;
import net.monkeyskl.inscriptions.menu.custom.InscriptionTableScreen;

public class InscriptionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.INSCRIPTION_TABLE, InscriptionTableScreen::new);
        FabricDefaultAttributeRegistry.register(ModEntities.DUMMY, DummyEntity.createAttributes());
        EntityRendererRegistry.register(ModEntities.DUMMY, DummyRenderer::new);
        ModelLayerRegistry.registerModelLayer(DummyModel.LAYER_LOCATION, DummyModel::createBodyLayer);
    }
}
