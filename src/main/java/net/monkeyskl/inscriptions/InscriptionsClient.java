package net.monkeyskl.inscriptions;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.monkeyskl.inscriptions.entity.ModEntities;
import net.monkeyskl.inscriptions.entity.cilent.DummyArmorModel;
import net.monkeyskl.inscriptions.entity.cilent.DummyModel;
import net.monkeyskl.inscriptions.entity.cilent.DummyRenderer;
import net.monkeyskl.inscriptions.entity.custom.DummyEntity;
import net.monkeyskl.inscriptions.menu.ModMenuTypes;
import net.monkeyskl.inscriptions.menu.custom.InscriptionTableScreen;
import net.monkeyskl.inscriptions.particle.ModParticles;
import net.monkeyskl.inscriptions.particle.NumberParticle;

public class InscriptionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.INSCRIPTION_TABLE, InscriptionTableScreen::new);

        FabricDefaultAttributeRegistry.register(ModEntities.DUMMY, DummyEntity.createAttributes());
        EntityRendererRegistry.register(ModEntities.DUMMY, DummyRenderer::new);
        ModelLayerRegistry.registerModelLayer(DummyModel.LAYER_LOCATION, DummyModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(DummyArmorModel.LAYER_LOCATION, DummyArmorModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(DummyArmorModel.SMALL_LAYER_LOCATION, DummyArmorModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(DummyArmorModel.HEAD_LAYER, DummyArmorModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(DummyArmorModel.CHEST_LAYER, DummyArmorModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(DummyArmorModel.LEGS_LAYER, DummyArmorModel::createLegsLayer);
        ModelLayerRegistry.registerModelLayer(DummyArmorModel.FEET_LAYER, DummyArmorModel::createBodyLayer);

        ParticleProviderRegistry.getInstance().register(ModParticles.NUMBER_PARTICLE, NumberParticle.Provider::new);

    }
}
