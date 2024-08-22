package net.satisfy.bakery.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.satisfy.bakery.util.BakeryIdentifier;

import java.util.Set;

public class StorageTypeRegistry {


    public static final ResourceLocation CAKE_STAND = BakeryIdentifier.of("cake_stand");
    public static final ResourceLocation TRAY = BakeryIdentifier.of("tray");
    public static final ResourceLocation BREADBOX = BakeryIdentifier.of("breadbox");
    public static final ResourceLocation CAKE_DISPLAY = BakeryIdentifier.of("cake_display");
    public static final ResourceLocation CUPCAKE_DISPLAY = BakeryIdentifier.of("cupcake_display");
    public static final ResourceLocation WALL_DISPLAY = BakeryIdentifier.of("wall_display");


    public static void registerBlocks(Set<Block> blocks) {
        blocks.add(ObjectRegistry.CAKE_STAND.get());
        blocks.add(ObjectRegistry.TRAY.get());
        blocks.add(ObjectRegistry.BREADBOX.get());
        blocks.add(ObjectRegistry.CAKE_DISPLAY.get());
        blocks.add(ObjectRegistry.CUPCAKE_DISPLAY.get());
        blocks.add(ObjectRegistry.WALL_DISPLAY.get());
    }
}
