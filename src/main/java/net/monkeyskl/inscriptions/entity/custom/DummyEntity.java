package net.monkeyskl.inscriptions.entity.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.monkeyskl.inscriptions.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class DummyEntity extends ArmorStand {

    private final List<Display.TextDisplay> DAMAGE_DISPLAYS = new ArrayList<>();
    private final java.util.Map<Display.TextDisplay, Integer> DISPLAY_FADE_TICKS = new java.util.HashMap<>();

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
    public ItemStack getPickResult() {
        return new ItemStack(ModItems.DUMMY);
    }

    private void playBrokenSound() {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_BREAK, this.getSoundSource(), 1.0F, 1.0F);
    }

    private void brokenByPlayer(ServerLevel level, DamageSource source) {
        ItemStack result = new ItemStack(ModItems.DUMMY);
        result.set(DataComponents.CUSTOM_NAME, this.getCustomName());
        Block.popResource(this.level(), this.blockPosition(), result);
        this.brokenByAnything(level, source);
    }

    private void brokenByAnything(ServerLevel level, DamageSource source) {
        this.playBrokenSound();
        this.dropAllDeathLoot(level, source);

        for (EquipmentSlot slot : EquipmentSlot.VALUES) {
            ItemStack itemStack = this.equipment.set(slot, ItemStack.EMPTY);
            if (!itemStack.isEmpty() && !EnchantmentHelper.has(itemStack, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)) {
                Block.popResource(this.level(), this.blockPosition().above(), itemStack);
            }
        }
    }

    private void causeDamage(ServerLevel level, DamageSource source, float dmg) {
        float health = this.getHealth();
        health -= dmg;
        if (health <= 0.5F) {
            this.brokenByAnything(level, source);
            this.kill(level);
        } else {
            this.setHealth(health);
            this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
        }
    }

    private void showBreakingParticles() {
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level())
                    .sendParticles(
                            new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.defaultBlockState()),
                            this.getX(),
                            this.getY(0.6666666666666666),
                            this.getZ(),
                            10,
                            this.getBbWidth() / 4.0F,
                            this.getBbHeight() / 4.0F,
                            this.getBbWidth() / 4.0F,
                            0.05
                    );
        }
    }

    @Override
    public void knockback(double power, double xd, double zd) {
        // NO KNOCKBACK
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {

        // Temporary replacement for NumberParticle
        if (level instanceof ServerLevel) {

            DAMAGE_DISPLAYS.removeIf(e -> e == null || e.isRemoved());

            if (DAMAGE_DISPLAYS.size() >= 5) {
                Display.TextDisplay oldest = DAMAGE_DISPLAYS.get(0);
                if (oldest != null && !oldest.isRemoved()) {
                    oldest.discard();
                    DISPLAY_FADE_TICKS.remove(oldest);
                }
                DAMAGE_DISPLAYS.remove(0);
            }

            Display.TextDisplay display = EntityType.TEXT_DISPLAY.create(level, EntitySpawnReason.COMMAND);

            if (display != null) {
                display.setText(Component.literal(String.valueOf(Math.round(damage * 10) / 10.0)));

                int slot = DAMAGE_DISPLAYS.size();
                double xOffset = (slot - 2) * 0.5;

                display.setPos(this.getX() + xOffset, this.getY() + 3, this.getZ());
                display.setBillboardConstraints(Display.BillboardConstraints.CENTER);
                display.setDeltaMovement(0, 0.04, 0);
                display.setTextOpacity((byte) 255);

                level.addFreshEntity(display);
                DAMAGE_DISPLAYS.add(display);
                DISPLAY_FADE_TICKS.put(display, 30);
            }
        }
        if (this.isRemoved()) {
            return false;
        } else if (!level.getGameRules().get(GameRules.MOB_GRIEFING) && source.getEntity() instanceof Mob) {
            return false;
        } else if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            this.kill(level);
            return false;
        } else if (this.isInvulnerableTo(level, source) || this.isMarker()) {
            return false;
        } else if (source.is(DamageTypeTags.IS_EXPLOSION)) {
            this.brokenByAnything(level, source);
            this.kill(level);
            return false;
        } else if (source.is(DamageTypeTags.IGNITES_ARMOR_STANDS)) {
            if (this.isOnFire()) {
                this.causeDamage(level, source, 0.15F);
            } else {
                this.igniteForSeconds(5.0F);
            }

            return false;
        } else if (source.is(DamageTypeTags.BURNS_ARMOR_STANDS) && this.getHealth() > 0.5F) {
            this.causeDamage(level, source, 4.0F);
            return false;
        } else {
            boolean allowIncrementalBreaking = source.is(DamageTypeTags.CAN_BREAK_ARMOR_STAND);
            boolean shouldKill = source.is(DamageTypeTags.ALWAYS_KILLS_ARMOR_STANDS);
            if (!allowIncrementalBreaking && !shouldKill) {
                return false;
            } else if (source.getEntity() instanceof Player player && !player.getAbilities().mayBuild) {
                return false;
            } else if (source.isCreativePlayer()) {
                this.playBrokenSound();
                this.showBreakingParticles();
                this.kill(level);
                return true;
            } else {

                // Temporary replacement for NumberParticle
                if (source.getEntity() instanceof Player player) {
                    player.sendSystemMessage(Component.literal(player.getName().getString() + " dealt " + Math.round(damage * 10) / 10.0 + " hearts of damage"));
                }
                
                long time = level.getGameTime();
                long timeSinceHit = time - this.lastHit;
                boolean isCrouching = source.getEntity() instanceof Player player && player.isCrouching();
                long requiredCooldown = isCrouching ? 5L : 1L;

                if (!shouldKill && timeSinceHit > requiredCooldown) {
                    level.broadcastEntityEvent(this, (byte) 32);
                    this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
                    this.lastHit = time;
                } else {
                    this.brokenByPlayer(level, source);
                    this.showBreakingParticles();
                    this.kill(level);
                }

                return true;
            }
        }

    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack itemStack) {
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
            return;
        }
        super.setItemSlot(slot, itemStack);
    }

    @Override
    public int getArmorValue() {
        return super.getArmorValue();
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            DISPLAY_FADE_TICKS.entrySet().removeIf(entry -> {
                Display.TextDisplay d = entry.getKey();
                int ticksLeft = entry.getValue();

                if (d.isRemoved() || ticksLeft <= 0) {
                    if (!d.isRemoved()) d.discard();
                    DAMAGE_DISPLAYS.remove(d);
                    return true;
                }

                byte opacity = (byte)(int)((ticksLeft / 30.0) * 255);
                d.setTextOpacity(opacity);
                entry.setValue(ticksLeft - 1);
                return false;
            });
        }
    }
}
