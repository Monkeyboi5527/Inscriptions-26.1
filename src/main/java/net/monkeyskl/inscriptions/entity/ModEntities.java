package net.monkeyskl.inscriptions.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.entity.custom.DummyEntity;

public class ModEntities {

    private static final ResourceKey<EntityType<?>> DUMMY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "dummy"));

    public static final EntityType<DummyEntity> DUMMY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "dummy"),
            EntityType.Builder.of(DummyEntity::new, MobCategory.MISC)
                    .sized(0.5F, 1.975F)
                    .build(DUMMY_KEY));

    public static void registerModEntities() {
        Inscriptions.LOGGER.info("Registering ModEntities for: " + Inscriptions.MOD_ID);
    }
}
