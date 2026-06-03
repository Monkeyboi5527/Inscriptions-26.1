package net.monkeyskl.inscriptions.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class NumberParticle extends SingleQuadParticle {

    private NumberParticle(ClientLevel level, double x, double y, double z,
                           SpriteSet sprites, int digit) {
        super(level, x, y, z, sprites.get(Math.abs(digit) % 10, 9));

        this.setSprite(sprites.get(Math.abs(digit) % 10, 9));

        this.quadSize = 0.25f;
        this.lifetime = 30;
        this.hasPhysics = false;
        this.yd = 0.05;

        this.rCol = 1.0f;
        this.gCol = 1.0f;
        this.bCol = 1.0f;
        this.alpha = 1.0f;
    }
    
    

    @Override
    public void tick() {
        super.tick();
        // Fade out over the last 10 ticks
        if (this.age > this.lifetime - 10) {
            this.alpha = Math.max(0f, this.alpha - 0.1f);
        }
    }

    @Override
    protected Layer getLayer() {
        return Layer.OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType options, ClientLevel level, 
                                                 double x, double y, double z, 
                                                 double xAux, double yAux, double zAux, 
                                                 RandomSource random) {
            return new NumberParticle(level, x, y, z, sprites, ((int) xAux));
        }
    }
}