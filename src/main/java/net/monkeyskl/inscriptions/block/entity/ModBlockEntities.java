package net.monkeyskl.inscriptions.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.block.ModBlocks;
import net.monkeyskl.inscriptions.block.entity.custom.InscriptionTableBlockEntity;
import net.monkeyskl.inscriptions.block.entity.custom.TestCraftingBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<InscriptionTableBlockEntity> INSCRIPTION_TABLE_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "inscription_table_be"),
                    FabricBlockEntityTypeBuilder.create(
                            InscriptionTableBlockEntity::new, ModBlocks.INSCRIPTION_TABLE
                    ).build());
    public static final BlockEntityType<TestCraftingBlockEntity> TEST_CRAFTING_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "test_crafting_be"),
                    FabricBlockEntityTypeBuilder.create(
                            TestCraftingBlockEntity::new, ModBlocks.TEST_CRAFTING
                    ).build());

    public static void registerBlockEntities() {
        Inscriptions.LOGGER.info("Registering BlockEntities for: " + Inscriptions.MOD_ID);
    }
}
