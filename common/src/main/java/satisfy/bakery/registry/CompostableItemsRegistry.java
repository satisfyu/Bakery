package satisfy.bakery.registry;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostableItemsRegistry {

    public static void init() {
        registerCompostableItem(ObjectRegistry.OAT.get(), 0.4F);
        registerCompostableItem(ObjectRegistry.OAT_SEEDS.get(), 0.4F);
        registerCompostableItem(ObjectRegistry.STRAWBERRY.get(), 0.4F);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_SEEDS.get(), 0.4F);
    }

    public static void registerCompostableItem(ItemLike item, float chance) {
        if (item.asItem() != Items.AIR) {
            ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
        }
    }
}
