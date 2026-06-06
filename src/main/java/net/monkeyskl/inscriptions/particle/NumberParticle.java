package net.monkeyskl.inscriptions.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class NumberParticle extends Particle {

    private final net.minecraft.client.gui.Font font;
    private final net.minecraft.network.chat.Component text;
    private float visualY = 0f;
    private float prevVisualY = 0f;

    public NumberParticle(ClientLevel level, double x, double y, double z, double damage) {
        super(level, x, y, z);
        this.font = Minecraft.getInstance().font;
        this.lifetime = 35;
        this.hasPhysics = false;

        
        String formatted = (damage % 1.0 == 0)
                ? String.valueOf((int) damage)
                : String.format("%.1f", damage);
        this.text = Component.literal(formatted);
    }




    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        this.prevVisualY = this.visualY;
        this.visualY += 0.08f; // float upward visually
    }

    @Override
    public ParticleRenderType getGroup() {
        return ParticleRenderType.NO_RENDER;
    }

   
    public void render(VertexConsumer buffer, Camera camera, float partialTick) {
        var pos = camera.position();
        float px = (float)(Mth.lerp(partialTick, this.xo, this.x) - pos.x);
        float py = (float)(Mth.lerp(partialTick, this.yo, this.y) - pos.y);
        float pz = (float)(Mth.lerp(partialTick, this.zo, this.z) - pos.z);
        float vy = Mth.lerp(partialTick, this.prevVisualY, this.visualY);

        // Fade out in last 6 ticks
        float fadeLength = 6f;
        float fadeout = this.age > this.lifetime - fadeLength
                ? (this.lifetime - this.age) / fadeLength
                : 1f;

        var poseStack = new com.mojang.blaze3d.vertex.PoseStack();
        poseStack.pushPose();
        poseStack.translate(px, py + vy * 0.1f, pz);
        
        poseStack.mulPose(camera.rotation());
        float scale = 0.025f;
        poseStack.scale(scale, -scale, scale);
        
        float x1 = -font.width(text) / 2f;

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

        int alpha = (int)(fadeout * 255);
        int color = (alpha << 24) | 0xFF4444;

        font.drawInBatch(text, x1, 0f, color, false,
                poseStack.last().pose(), bufferSource,
                Font.DisplayMode.SEE_THROUGH, 0, 0xF000F0);

        bufferSource.endBatch();
        poseStack.popPose();
    }



    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        public Provider(SpriteSet spriteSet) {
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level,
                                                 double x, double y, double z,
                                                 double xAux, double yAux, double zAux,
                                                 RandomSource random) {
            return new NumberParticle(level, x, y, z, xAux);
        }
    }

        
}