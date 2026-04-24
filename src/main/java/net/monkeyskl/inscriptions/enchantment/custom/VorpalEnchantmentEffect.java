package net.monkeyskl.inscriptions.enchantment.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public record VorpalEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<VorpalEnchantmentEffect> CODEC = MapCodec.unit(new VorpalEnchantmentEffect());


    @Override
    public void apply(ServerLevel serverLevel, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        if (target instanceof LivingEntity victim && context.owner() instanceof Player player) {

            float baseSharpnessV = 3.0f;

            float damage = (float) (baseSharpnessV * Math.pow(1.5f, level - 1));

            victim.hurt(
                    serverLevel.damageSources().playerAttack(player),
                    damage
            );
        }

    }
    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
