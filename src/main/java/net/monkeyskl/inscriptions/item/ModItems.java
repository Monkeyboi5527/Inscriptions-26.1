package net.monkeyskl.inscriptions.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.monkeyskl.inscriptions.Inscriptions;

import java.util.function.Function;

public class ModItems {

    public static final Item TEST_ITEM = registerItem("test_item", new Item.Properties());


    private static Item registerItem(String name, Item.Properties properties) {
        return registerItem(name, Item::new, properties);
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, name));
        Item item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void registerModItems() {
        Inscriptions.LOGGER.info("Registering Inscriptions ModItems:" + Inscriptions.MOD_ID);
    }
}
