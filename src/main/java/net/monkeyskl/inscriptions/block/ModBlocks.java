package net.monkeyskl.inscriptions.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.block.custom.InscriptionTableBlock;

import java.util.function.Function;

public class ModBlocks {

    public static final Block TEST_BLOCK = registerBlock(
            "test_block",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F)
                    .sound(SoundType.NETHERITE_BLOCK)
    );

    public static final Block INSCRIPTION_TABLE = registerBlock(
            "inscription_table",
            InscriptionTableBlock::new,
            BlockBehaviour.Properties.of()
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
    );

    private static Block registerBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, Block::new, properties);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, name));
        Block block = factory.apply(properties.setId(key));
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static void registerBlockItem(String name, Block block) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, name));
        Registry.register(BuiltInRegistries.ITEM, key,
                new BlockItem(block, new Item.Properties()
                        .setId(key)));
    }


    public static void registerModBlocks() {
        Inscriptions.LOGGER.info("Registering Inscriptions ModBlock:" + Inscriptions.MOD_ID);
    }
}
