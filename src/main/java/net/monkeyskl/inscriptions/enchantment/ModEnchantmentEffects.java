package net.monkeyskl.inscriptions.enchantment;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.effects.*;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.enchantment.custom.TestEnchantmentEffect;
import net.monkeyskl.inscriptions.enchantment.custom.VorpalEnchantmentEffect;

public class ModEnchantmentEffects {
    public static final ResourceKey<MapCodec<? extends EnchantmentEntityEffect>> TEST =
            registerEntityEffect("test", TestEnchantmentEffect.CODEC);

    public static final ResourceKey<MapCodec<? extends EnchantmentEntityEffect>> VORPAL =
            registerEntityEffect("vorpal", VorpalEnchantmentEffect.CODEC);

    private static ResourceKey<MapCodec<? extends EnchantmentEntityEffect>> registerEntityEffect(
            String name, MapCodec<? extends EnchantmentEntityEffect> codec) {

        ResourceKey<MapCodec<? extends EnchantmentEntityEffect>> key = ResourceKey.create(
                Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
                Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, name));

        Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
                Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, name), codec);

        return key;
    }

    public static void registerEnchantmentEffects() {
        Inscriptions.LOGGER.info("Registering Inscriptions ModEnchantmentEffects:" + Inscriptions.MOD_ID);
    }
}