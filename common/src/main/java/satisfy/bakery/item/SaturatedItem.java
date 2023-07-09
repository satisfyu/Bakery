package satisfy.bakery.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SaturatedItem extends Item {
    public SaturatedItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag context) {
        tooltip.add(Component.translatable("item.bakery.stuffed.duration." + this.getDescriptionId()).withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("item.bakery.stuffed_1").withStyle(ChatFormatting.WHITE));
        tooltip.add(Component.translatable("item.bakery.stuffed_2").withStyle(ChatFormatting.WHITE));

    }
}
