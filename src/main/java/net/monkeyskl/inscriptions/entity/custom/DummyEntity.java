package net.monkeyskl.inscriptions.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DummyEntity extends ArmorStand {

    public DummyEntity(EntityType<? extends ArmorStand> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return ArmorStand.createAttributes();
    }

    @Override
    public boolean showArms() {
        return true;
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack itemStack) {
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
            return;
        }
        super.setItemSlot(slot, itemStack);
    }
}
