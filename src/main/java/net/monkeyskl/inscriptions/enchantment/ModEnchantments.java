package net.monkeyskl.inscriptions.enchantment;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.enchantment.custom.TestEnchantmentEffect;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> TEST = key("test");
    public static final ResourceKey<Enchantment> VORPAL = key("vorpal");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        register(context, TEST, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.MELEE_WEAPON_ENCHANTABLE),
                1,
                3,
                Enchantment.dynamicCost(1, 11),
                Enchantment.dynamicCost(12, 11),
                1,
                EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM,
                        new TestEnchantmentEffect()));

        register(context, VORPAL, Enchantment.enchantment(Enchantment.definition(
                                items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                                items.getOrThrow(ItemTags.MELEE_WEAPON_ENCHANTABLE),
                                10,
                                6,
                                Enchantment.dynamicCost(1, 11),
                                Enchantment.dynamicCost(21, 11),
                                1,
                                EquipmentSlotGroup.MAINHAND))
                        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                        .withEffect(EnchantmentEffectComponents.DAMAGE,
                                new AddValue(LevelBasedValue.perLevel(1.0F, 0.5F))));
    }

    public static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.identifier()));
    }

    public static ResourceKey<Enchantment> key(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID,id));
    }

}