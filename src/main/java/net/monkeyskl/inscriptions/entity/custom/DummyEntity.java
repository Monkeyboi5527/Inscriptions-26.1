package net.monkeyskl.inscriptions.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.Level;

public class DummyEntity extends ArmorStand {

    public DummyEntity(EntityType<? extends ArmorStand> type, Level level) {
        super(type, level);
    }

}
