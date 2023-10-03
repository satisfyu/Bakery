package satisfy.bakery.registry;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostableRegistry {

    public static void init() {
        registerCompostableItem(ObjectRegistry.OAT.get(), .3f);
        registerCompostableItem(ObjectRegistry.OAT_SEEDS.get(), .3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY.get(), .3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_SEEDS.get(), .3f);
        registerCompostableItem(ObjectRegistry.YEAST.get(), .3f);
        registerCompostableItem(ObjectRegistry.DOUGH.get(), .3f);
        registerCompostableItem(ObjectRegistry.SWEET_DOUGH.get(), .3f);
        registerCompostableItem(ObjectRegistry.CRUSTY_BREAD.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.BREAD.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.BAGUETTE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.TOAST.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.BRAIDED_BREAD.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.BUN.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.VEGETABLE_SANDWICH.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.SANDWICH.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_CAKE_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.SWEETBERRY_CAKE_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.CHOCOLATE_CAKE_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.PUDDING_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.BUNDT_CAKE_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.LINZER_TART_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.APPLE_PIE_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.GLOWBERRY_PIE_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.CHOCOLATE_TART_SLICE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_GLAZED_COOKIE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.SWEETBERRY_GLAZED_COOKIE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.CHOCOLATE_GLAZED_COOKIE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_CUPCAKE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.SWEETBERRY_CUPCAKE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.APPLE_CUPCAKE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.JAM_ROLL.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.WAFFLE.get(), 0.3f);
        registerCompostableItem(ObjectRegistry.CHOCOLATE_TRUFFLE.get(), 0.3f);
    }

    public static void registerCompostableItem(ItemLike item, float chance) {
        if (item.asItem() != Items.AIR) {
            ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
        }
    }
}
