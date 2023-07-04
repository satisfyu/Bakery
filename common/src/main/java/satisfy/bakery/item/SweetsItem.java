package satisfy.bakery.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import satisfy.bakery.registry.EffectRegistry;

public class SweetsItem extends Item {
    public SweetsItem(Properties properties) {
        super(properties);
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
                entity.addEffect(new MobEffectInstance(EffectRegistry.SWEETS.get(), 15 * 20, Math.min(level, 10)));
                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
            }
        }
        return super.finishUsingItem(stack, world, entity);
    }

    private boolean isOvereaten(LivingEntity entity) {
        return entity.hasEffect(MobEffects.CONFUSION) || entity.hasEffect(MobEffects.HUNGER) || entity.hasEffect(MobEffects.POISON);
    }
}