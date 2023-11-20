package satisfy.bakery.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CompostableRegistry {
    public static void registerCompostable() {
        registerCompostableItem(ObjectRegistry.OAT, .3f);
        registerCompostableItem(ObjectRegistry.OAT_SEEDS, .3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY, .3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_SEEDS, .3f);
        registerCompostableItem(ObjectRegistry.YEAST, .3f);
        registerCompostableItem(ObjectRegistry.DOUGH, .3f);
        registerCompostableItem(ObjectRegistry.SWEET_DOUGH, .3f);
        registerCompostableItem(ObjectRegistry.CRUSTY_BREAD, 0.3f);
        registerCompostableItem(ObjectRegistry.BREAD, 0.3f);
        registerCompostableItem(ObjectRegistry.BAGUETTE, 0.3f);
        registerCompostableItem(ObjectRegistry.TOAST, 0.3f);
        registerCompostableItem(ObjectRegistry.BRAIDED_BREAD, 0.3f);
        registerCompostableItem(ObjectRegistry.BUN, 0.3f);
        registerCompostableItem(ObjectRegistry.VEGETABLE_SANDWICH, 0.3f);
        registerCompostableItem(ObjectRegistry.SANDWICH, 0.3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_CAKE_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.SWEETBERRY_CAKE_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.CHOCOLATE_CAKE_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.PUDDING_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.BUNDT_CAKE_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.LINZER_TART_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.APPLE_PIE_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.GLOWBERRY_PIE_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.CHOCOLATE_TART_SLICE, 0.3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_GLAZED_COOKIE, 0.3f);
        registerCompostableItem(ObjectRegistry.SWEETBERRY_GLAZED_COOKIE, 0.3f);
        registerCompostableItem(ObjectRegistry.CHOCOLATE_GLAZED_COOKIE, 0.3f);
        registerCompostableItem(ObjectRegistry.STRAWBERRY_CUPCAKE, 0.3f);
        registerCompostableItem(ObjectRegistry.SWEETBERRY_CUPCAKE, 0.3f);
        registerCompostableItem(ObjectRegistry.APPLE_CUPCAKE, 0.3f);
        registerCompostableItem(ObjectRegistry.JAM_ROLL, 0.3f);
        registerCompostableItem(ObjectRegistry.WAFFLE, 0.3f);
        registerCompostableItem(ObjectRegistry.CHOCOLATE_TRUFFLE, 0.3f);
    }

    public static <T extends ItemLike> void registerCompostableItem(RegistrySupplier<T> item, float chance) {
        if (item.get().asItem() != Items.AIR) {
            ComposterBlock.COMPOSTABLES.put(item.get(), chance);
        }
    }
}
