package net.monkeyskl.inscriptions.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.monkeyskl.inscriptions.Inscriptions;

public class ModParticles {

    public static final SimpleParticleType NUMBER_PARTICLE =
            registerParticle("number_particle", FabricParticleTypes.simple(true));

    private static SimpleParticleType registerParticle(String name , SimpleParticleType particleType){
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, name), particleType);
    }

    public static void registerModParticles() {
        Inscriptions.LOGGER.info("Registering Inscriptions ModParticles:" + Inscriptions.MOD_ID);
    }
}
