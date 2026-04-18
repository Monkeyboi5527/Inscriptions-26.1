package net.monkeyskl.inscriptions.menu;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.menu.custom.InscriptionTableMenu;
import net.monkeyskl.inscriptions.menu.custom.TestCraftingMenu;

public class ModMenuTypes {
    public static final MenuType<InscriptionTableMenu> INSCRIPTION_TABLE =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "inscription_table"),
                    new MenuType<>(InscriptionTableMenu::new, FeatureFlags.VANILLA_SET));

    public static final MenuType<TestCraftingMenu> TEST_CRAFTING =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "test_crafting"),
                    new MenuType<>(TestCraftingMenu::new, FeatureFlags.VANILLA_SET));

    public static void registerMenuTypes() {
        Inscriptions.LOGGER.info("Registering ModMenuTypes for: " + Inscriptions.MOD_ID);
    }
}
