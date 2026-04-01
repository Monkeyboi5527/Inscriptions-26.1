package net.monkeyskl.inscriptions.menu;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.menu.custom.InscriptionTableMenu;

public class ModMenuTypes {
    public static final MenuType<InscriptionTableMenu> INSCRIPTION_TABLE =
            Registry.register(BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "inscription_table"),
                    new MenuType<>((containerId, inventory) ->
                            new InscriptionTableMenu(containerId, inventory, ContainerLevelAccess.NULL),
                            FeatureFlags.VANILLA_SET));

    public static void registerMenuTypes() {
        Inscriptions.LOGGER.info("Registering ModMenuTypes for: " + Inscriptions.MOD_ID);
    }
}
