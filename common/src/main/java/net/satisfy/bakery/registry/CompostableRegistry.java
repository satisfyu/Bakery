package net.satisfy.bakery.registry;

import net.minecraft.world.level.block.ComposterBlock;

public class CompostableRegistry {
    public static void registerCompostable() {
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.OAT.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.OAT_SEEDS.get().asItem(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY_SEEDS.get().asItem(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.YEAST.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DOUGH.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEET_DOUGH.get(), .3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CRUSTY_BREAD.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BREAD.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BAGUETTE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.TOAST.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BRAIDED_BREAD.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BUN.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.VEGETABLE_SANDWICH.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SANDWICH.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY_CAKE_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEETBERRY_CAKE_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_CAKE_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PUDDING_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.BUNDT_CAKE_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.LINZER_TART_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.APPLE_PIE_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GLOWBERRY_PIE_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_TART_SLICE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY_GLAZED_COOKIE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEETBERRY_GLAZED_COOKIE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_GLAZED_COOKIE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.STRAWBERRY_CUPCAKE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SWEETBERRY_CUPCAKE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.APPLE_CUPCAKE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.JAM_ROLL.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WAFFLE.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHOCOLATE_TRUFFLE.get(), 0.3f);
    }
}
