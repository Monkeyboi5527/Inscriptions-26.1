package net.monkeyskl.inscriptions.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.block.ModBlocks;

public class ModCreativeModeTab {

    private static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB,
                    Identifier.fromNamespaceAndPath("inscriptions", "itemgroup.inscriptions.inscriptions"));


    public static void registerModCreativeModeTab() {
        Inscriptions.LOGGER.info("Registering Inscriptions ModCreativeModeTab: " + Inscriptions.MOD_ID);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_MODE_TAB, FabricCreativeModeTab.builder()
                .title(Component.translatable("itemgroup.inscriptions.inscriptions"))
                .icon(() -> new ItemStack(ModBlocks.TEST_BLOCK.asItem()))
                .displayItems((context, output) -> {
                    output.accept(ModBlocks.TEST_BLOCK.asItem());
                    output.accept(ModItems.TEST_ITEM);
                    output.accept(ModBlocks.INSCRIPTION_TABLE.asItem());
                })
                .build()
        );
    }


}
