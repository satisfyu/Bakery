package net.satisfy.bakery.event;

import de.cristelknight.doapi.common.registry.DoApiSoundEventRegistry;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.satisfy.bakery.registry.ObjectRegistry;
import net.satisfy.bakery.util.BakeryIdentifier;
import org.jetbrains.annotations.Nullable;

public class CommonEvents {
    private static final ResourceLocation ATTACK_SPEED_MODIFIER_ID = BakeryIdentifier.of("attack_speed_modifier");

    public static void init() {
        PlayerEvent.ATTACK_ENTITY.register(CommonEvents::attack);
    }

    public static EventResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(ObjectRegistry.SMALL_COOKING_POT_ITEM.get())) {
            level.playSound(null, target.getX(), target.getY(), target.getZ(), DoApiSoundEventRegistry.COOKING_POT_HIT.get(), SoundSource.PLAYERS, 0.5F, 0.75F);
            target.hurt(level.damageSources().generic(), 1.2F);
            itemStack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(itemStack));
            addSpeedAttribute(itemStack);

            if (target instanceof Mob mob) {
                mob.setTarget(player);
            }

            return EventResult.interruptTrue();
        } else if (itemStack.is(ObjectRegistry.ROLLING_PIN.get())) {
            if (!level.isClientSide) {
                level.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 1.0F, 0.5F);
                if (target instanceof LivingEntity livingTarget) {
                    livingTarget.hurt(level.damageSources().generic(), 2.0F);
                    livingTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1));

                    if (livingTarget instanceof Mob mob) {
                        mob.setTarget(player);
                    }
                }
                itemStack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(itemStack));
                addSpeedAttribute(itemStack);
                return EventResult.interruptTrue();
            }
        }
        return EventResult.pass();
    }


    private static void addSpeedAttribute(ItemStack stack) {
        var modifiers = ItemAttributeModifiers.builder();
        ItemAttributeModifiers itemAttributeModifiers = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);

        if(itemAttributeModifiers != null) {
            for(ItemAttributeModifiers.Entry entry : itemAttributeModifiers.modifiers()){
                modifiers.add(entry.attribute(), entry.modifier(), entry.slot());
            }
        }

        modifiers.add(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER_ID, -2.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers.build());
    }
}
