package net.monkeyskl.inscriptions.item;

import net.monkeyskl.inscriptions.Inscriptions;

public class ModItems {

//    public static final Item TEST_ITEM = registerItem("test_item", new Item(new Item.Properties()));

//    private static Item registerItem(String name, Item item) {
//        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
//                Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, name));
//        return Registry.register(Registries.ITEM, key,
//                new Item());
//    }

    public static void registerModItems() {
        Inscriptions.LOGGER.info("Registering Inscriptions ModItems:" + Inscriptions.MOD_ID);
    }
}
