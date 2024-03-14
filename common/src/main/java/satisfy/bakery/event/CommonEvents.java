package satisfy.bakery.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.LootEvent;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import satisfy.bakery.registry.ObjectRegistry;
import satisfy.bakery.registry.SoundEventRegistry;
import satisfy.bakery.util.LoottableInjector;

public class CommonEvents {

    public static void init() {
        LootEvent.MODIFY_LOOT_TABLE.register(CommonEvents::onModifyLootTable);
        PlayerEvent.ATTACK_ENTITY.register(CommonEvents::attack);
    }

    public static EventResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(ObjectRegistry.SMALL_COOKING_POT_ITEM.get())) {
            level.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEventRegistry.COOKING_POT_HIT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            target.hurt(level.damageSources().generic(), 1.2F);
            itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            return EventResult.interruptTrue();
        } else if (itemStack.is(ObjectRegistry.ROLLING_PIN.get())) {
            if (!level.isClientSide) {
                level.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
                if (target instanceof LivingEntity) {
                    target.hurt(level.damageSources().generic(), 1.0F);
                    ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1));
                }
                itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
                return EventResult.interruptTrue();
            }
        }
        return EventResult.pass();
    }


    private static void onModifyLootTable(@Nullable LootDataManager lootDataManager, ResourceLocation id, LootEvent.LootTableModificationContext ctx, boolean b) {
        LoottableInjector.InjectLoot(id, ctx);
    }
}
