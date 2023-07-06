package satisfy.bakery.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SweetlySatiatedItem extends SweetsItem {


    public SweetlySatiatedItem(Properties properties, int duration) {
        super(properties, duration);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag context) {
        tooltip.add(Component.translatable("item.bakery.sweetly.duration." + this.getDescriptionId()).withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("item.bakery.sweetly").withStyle(ChatFormatting.WHITE));
    }
}