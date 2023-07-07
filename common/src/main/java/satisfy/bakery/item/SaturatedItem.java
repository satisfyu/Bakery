package satisfy.bakery.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import satisfy.bakery.registry.EffectRegistry;

import java.util.List;

public class SaturatedItem extends Item {

    private final int duration;

    public SaturatedItem(Properties properties, int duration) {
        super(properties);
        this.duration = duration;

    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, net.minecraft.world.level.Level world, net.minecraft.world.entity.LivingEntity entity) {
        if (!world.isClientSide) {
            entity.addEffect(new MobEffectInstance(EffectRegistry.SATURATED.get(), duration));
            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
        }
        return super.finishUsingItem(stack, world, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag context) {
        tooltip.add(Component.translatable("item.bakery.stuffed.duration." + this.getDescriptionId()).withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("item.bakery.stuffed").withStyle(ChatFormatting.WHITE));
    }
}
