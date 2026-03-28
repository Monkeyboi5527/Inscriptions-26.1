package net.monkeyskl.inscriptions.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public record TestEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<TestEnchantmentEffect> CODEC = MapCodec.unit(new TestEnchantmentEffect());

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 position) {
        Level.ExplosionInteraction blockInteraction = Level.ExplosionInteraction.TNT;
        if (!serverLevel.isClientSide()){
            serverLevel.explode(entity, entity.getX(), entity.getY(), entity.getZ(), 3f, blockInteraction);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }

}
