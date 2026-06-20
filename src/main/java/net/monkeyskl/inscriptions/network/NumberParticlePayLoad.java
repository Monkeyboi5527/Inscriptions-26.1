package net.monkeyskl.inscriptions.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record NumberParticlePayLoad(int entityId, float damage) implements CustomPacketPayload {

    public static final Identifier ID = Identifier.fromNamespaceAndPath("inscriptions", "number_particle");
    public static final CustomPacketPayload.Type<NumberParticlePayLoad> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, NumberParticlePayLoad> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, NumberParticlePayLoad::entityId,
                    ByteBufCodecs.FLOAT, NumberParticlePayLoad::damage,
                    NumberParticlePayLoad::new
            );

    @Override
    public CustomPacketPayload.Type<NumberParticlePayLoad> type() {
        return TYPE;
    }
}