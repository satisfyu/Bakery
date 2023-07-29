package satisfy.bakery.registry;

import net.minecraft.world.level.block.ComposterBlock;

public class CompostableRegistry {

    public static void init() {
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY_SEEDS.get(), .2f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.OAT_SEEDS.get(), .2f);

        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.OAT.get(), .3f);

        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DOUGH.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEET_DOUGH.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BUTTER.get(), .3f);

        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY_CAKE_SLICE.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_CAKE_SLICE.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BUNDT_CAKE_SLICE.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEETBERRY_CAKE_SLICE.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_TART_SLICE.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.LINZER_TART_SLICE.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GLOWBERRY_PIE_SLICE.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PUDDING_SLICE.get(), .3f);


        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY_CUPCAKE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.APPLE_CUPCAKE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEETBERRY_CUPCAKE.get(), 1f);

        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CROISSANT.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WAFFLE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_TRUFFLE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BRAIDED_BREAD.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BAGUETTE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BUN.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BREAD.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CRUSTY_BREAD.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.TOAST.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.VEGETABLE_SANDWICH.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SANDWICH.get(), 1f);


        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY_GLAZED_COOKIE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEETBERRY_GLAZED_COOKIE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_GLAZED_COOKIE.get(), 1f);

        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_CAKE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BUNDT_CAKE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BLANK_CAKE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEETBERRY_CAKE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.APPLE_CUPCAKE.get(), 1f);

        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_TART.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.LINZER_TART.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEETBERRY_CAKE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_TART.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GLOWBERRY_TART.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PUDDING.get(), 1f);

    }
}
