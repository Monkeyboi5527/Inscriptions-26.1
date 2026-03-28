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
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.enchantment.custom.TestEnchantmentEffect;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> TEST = key("test");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        register(context, TEST, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.MELEE_WEAPON_ENCHANTABLE),
                10,
                1,
                Enchantment.dynamicCost(1, 11),
                Enchantment.dynamicCost(12, 11),
                1,
                EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM,
                        new TestEnchantmentEffect()));

    }

    public static void register(final BootstrapContext<Enchantment> context, final ResourceKey<Enchantment> key, final Enchantment.Builder builder) {
        context.register(key, builder.build(key.identifier()));
    }

    public static ResourceKey<Enchantment> key(final String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID,id));
    }

}