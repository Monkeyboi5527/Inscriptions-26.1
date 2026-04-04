package net.monkeyskl.inscriptions.entity.cilent;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.monkeyskl.inscriptions.entity.custom.DummyEntity;

public class DummyRenderer extends LivingEntityRenderer<DummyEntity, DummyRenderState, DummyModel> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath("inscriptions", "textures/entity/dummy/dummy.png");

    public DummyRenderer(EntityRendererProvider.Context context) {
        super(context, new DummyModel(context.bakeLayer(DummyModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTextureLocation(DummyRenderState state) {
        return TEXTURE;
    }

    @Override
    public DummyRenderState createRenderState() {
        return new DummyRenderState();
    }
}
