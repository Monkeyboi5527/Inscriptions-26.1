package net.monkeyskl.inscriptions.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public record TestEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<TestEnchantmentEffect> CODEC = MapCodec.unit(new TestEnchantmentEffect());

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse item, Entity user, Vec3 position) {
        if (enchantmentLevel == 1){
            serverLevel.explode(user, user.getX(), user.getY(), user.getZ(), 3f, Level.ExplosionInteraction.TNT);
        }
        if (enchantmentLevel == 2){
            EntityType.END_CRYSTAL.spawn(serverLevel, user.getOnPos(), EntitySpawnReason.SPAWN_ITEM_USE);
        }
        if (enchantmentLevel == 3){
            user.setDeltaMovement(user.getDeltaMovement().add(0.0, 0.5f, 0.0));
        }

    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }

}
