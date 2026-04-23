package net.monkeyskl.inscriptions.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class VorpalEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<VorpalEnchantmentEffect> CODEC = MapCodec.unit(new VorpalEnchantmentEffect());

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 position) {
        if (!(entity instanceof LivingEntity living)) return;
        float damage = calculate(enchantmentLevel);

        if (enchantmentLevel==1){
            living.hurtServer(serverLevel, serverLevel.damageSources().generic(), damage);
            if (entity instanceof Player player){
                player.sendSystemMessage(Component.literal("VORPAL! 1"));
            }
        }

        if (enchantmentLevel==2){
            living.hurtServer(serverLevel, serverLevel.damageSources().generic(), damage);
            if (entity instanceof Player player){
                player.sendSystemMessage(Component.literal("VORPAL! 2"));
            }
        }

        if (enchantmentLevel==3){
            living.hurtServer(serverLevel, serverLevel.damageSources().generic(), damage);
            if (entity instanceof Player player){
                player.sendSystemMessage(Component.literal("VORPAL! 3"));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }

    public float calculate(int enchantmentLevel) {
        //Exponential
        return (float) (1f * Math.pow(1.5f, enchantmentLevel));
    }
}
