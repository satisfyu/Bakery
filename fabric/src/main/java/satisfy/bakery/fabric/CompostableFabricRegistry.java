package satisfy.bakery.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import satisfy.bakery.registry.ObjectRegistry;

public class CompostableFabricRegistry {

    public static void init() {
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.STRAWBERRY_SEEDS.get(), .2f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.OAT_SEEDS.get(), .2f);

        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.STRAWBERRY.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.OAT.get(), .3f);

        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.DOUGH.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.SWEET_DOUGH.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.YEAST.get(), .3f);

        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.STRAWBERRY_CAKE_SLICE.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CHOCOLATE_CAKE_SLICE.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.BUNDT_CAKE_SLICE.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.SWEETBERRY_CAKE_SLICE.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CHOCOLATE_TART_SLICE.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.LINZER_TART_SLICE.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.GLOWBERRY_PIE_SLICE.get(), .3f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.PUDDING_SLICE.get(), .3f);


        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.STRAWBERRY_CUPCAKE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.APPLE_CUPCAKE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.SWEETBERRY_CUPCAKE.get(), 1f);

        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CROISSANT.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.WAFFLE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CHOCOLATE_TRUFFLE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.BRAIDED_BREAD.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.BAGUETTE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.BUN.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.BREAD.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CRUSTY_BREAD.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.TOAST.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.VEGETABLE_SANDWICH.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.SANDWICH.get(), 1f);


        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.STRAWBERRY_GLAZED_COOKIE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.SWEETBERRY_GLAZED_COOKIE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CHOCOLATE_GLAZED_COOKIE.get(), 1f);

        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CHOCOLATE_CAKE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.BUNDT_CAKE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.BLANK_CAKE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.SWEETBERRY_CAKE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.APPLE_CUPCAKE.get(), 1f);

        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CHOCOLATE_TART.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.LINZER_TART.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.SWEETBERRY_CAKE.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.CHOCOLATE_TART.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.GLOWBERRY_TART.get(), 1f);
        CompostingChanceRegistry.INSTANCE.add(ObjectRegistry.PUDDING.get(), 1f);

    }
}
