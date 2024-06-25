package net.satisfy.bakery.event;

import de.cristelknight.doapi.common.registry.DoApiSoundEventRegistry;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.LootEvent;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.phys.EntityHitResult;
import net.satisfy.bakery.registry.ObjectRegistry;
import net.satisfy.bakery.util.LoottableInjector;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CommonEvents {
    private static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

    public static void init() {
        LootEvent.MODIFY_LOOT_TABLE.register(CommonEvents::onModifyLootTable);
        PlayerEvent.ATTACK_ENTITY.register(CommonEvents::attack);
    }

    public static EventResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(ObjectRegistry.SMALL_COOKING_POT_ITEM.get())) {
            level.playSound(null, target.getX(), target.getY(), target.getZ(), DoApiSoundEventRegistry.COOKING_POT_HIT.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
            target.hurt(level.damageSources().generic(), 1.2F);
            itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            itemStack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -2.0, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);

            if (target instanceof Mob mob) {
                mob.setTarget(player);
            }

            return EventResult.interruptTrue();
        } else if (itemStack.is(ObjectRegistry.ROLLING_PIN.get())) {
            if (!level.isClientSide) {
                level.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
                if (target instanceof LivingEntity livingTarget) {
                    livingTarget.hurt(level.damageSources().generic(), 2.0F);
                    livingTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1));

                    if (livingTarget instanceof Mob mob) {
                        mob.setTarget(player);
                    }
                }
                itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                itemStack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -2.0, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                return EventResult.interruptTrue();
            }
        }
        return EventResult.pass();
    }

    private static void onModifyLootTable(@Nullable LootDataManager lootDataManager, ResourceLocation id, LootEvent.LootTableModificationContext ctx, boolean b) {
        LoottableInjector.InjectLoot(id, ctx);
    }
}
