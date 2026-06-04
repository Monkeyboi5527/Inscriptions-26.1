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

    private final SpriteSet sprites;
    
    private NumberParticle(ClientLevel level, double x, double y, double z,
                           SpriteSet sprites, int digit) {
        super(level, x, y, z, sprites.get(Math.abs(digit) % 10, 9));

        this.sprites = sprites;

        this.xd = 0;
        this.yd = 0.05;
        this.zd = 0;

        this.quadSize = 0.25f;
        this.lifetime = 40;
        this.hasPhysics = false;
        this.rCol = 1.0f;
        this.gCol = 1.0f;
        this.bCol = 1.0f;
        this.alpha = 1.0f;
    }
    
    

    @Override
    public void tick() {
        super.tick();
        
        if (this.age > this.lifetime - 10) {
            this.alpha = Math.max(0f, this.alpha - 0.1f);
        }
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
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
            
            if (zAux == -1.0D) {
                return new NumberParticle(level, x, y, z, sprites, (int) xAux);
            }

            
            int value = (int) xAux;
            String digits = String.valueOf(Math.abs(value));
            int numDigits = digits.length();
            double spacing = 0.2;
            double startX = x - (numDigits - 1) * spacing / 2.0;

            for (int i = 0; i < numDigits - 1; i++) {
                int digit = digits.charAt(i) - '0';
                level.addParticle(ModParticles.NUMBER_PARTICLE,
                        startX + i * spacing, y, z,
                        digit, 0.0D, -1.0D);
            }

            int lastDigit = digits.charAt(numDigits - 1) - '0';
            return new NumberParticle(level,
                    startX + (numDigits - 1) * spacing, y, z,
                    sprites, lastDigit);
        }
    }
}