package net.monkeyskl.inscriptions.block.entity;

import net.monkeyskl.inscriptions.Inscriptions;

public class ModBlockEntities {
//    public static final BlockEntityType<InscriptionTableBlockEntity> INSCRIPTION_TABLE_BE =
//            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
//                    Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "inscription_table_be"),
//                    FabricBlockEntityTypeBuilder.create(
//                            InscriptionTableBlockEntity::new, ModBlocks.INSCRIPTION_TABLE
//                    ).build());

    public static void registerBlockEntities() {
        Inscriptions.LOGGER.info("Registering BlockEntities for: " + Inscriptions.MOD_ID);
    }
}
