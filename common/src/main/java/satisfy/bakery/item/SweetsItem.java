package satisfy.bakery.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import satisfy.bakery.registry.EffectRegistry;

import java.util.List;

public class SweetsItem extends Item {
    private final int duration;

    public SweetsItem(Properties properties, int duration) {
        super(properties);
        this.duration = duration;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (!world.isClientSide) {
            int level = 0;
            MobEffectInstance effect = entity.getEffect(EffectRegistry.SWEETS.get());
            if (effect != null) {
                level = effect.getAmplifier() + 1;
            }

            if (isOvereaten(entity) || level >= 5 && RandomSource.create().nextFloat() < 0.2f) {
                entity.removeEffect(EffectRegistry.SWEETS.get());
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 10 * 20));
                entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 10 * 20));
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 10 * 20));
            } else {
                entity.addEffect(new MobEffectInstance(EffectRegistry.SWEETS.get(), duration, Math.min(level, 10)));
                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        return super.finishUsingItem(stack, world, entity);
    }

    private boolean isOvereaten(LivingEntity entity) {
        return entity.hasEffect(MobEffects.CONFUSION) || entity.hasEffect(MobEffects.HUNGER) || entity.hasEffect(MobEffects.POISON);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag context) {
        tooltip.add(Component.translatable("item.bakery.sweeteffect.duration." + this.getDescriptionId()).withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("item.bakery.sweeteffect_1").withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.translatable("item.bakery.sweeteffect_2").withStyle(ChatFormatting.WHITE));

    }
}