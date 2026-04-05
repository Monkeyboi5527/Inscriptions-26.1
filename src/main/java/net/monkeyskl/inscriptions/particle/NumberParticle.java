package net.monkeyskl.inscriptions.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteSet;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

@Environment(EnvType.CLIENT)
public class NumberParticle extends SimpleAnimatedParticle {

    private NumberParticle(ClientLevel level, double x, double y, double z,
                           double xa, double ya, double za, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0F);  // 0.0F = no gravity
        this.xd = xa;
        this.yd = ya;
        this.zd = za;
        this.lifetime = 20;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level,
                                       double x, double y, double z,
                                       double xAux, double yAux, double zAux,
                                       RandomSource random) {
            return new NumberParticle(level, x, y, z, xAux, yAux, zAux, sprites);
        }
    }
}