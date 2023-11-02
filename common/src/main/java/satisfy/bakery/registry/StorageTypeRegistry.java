package satisfy.bakery.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import satisfy.bakery.util.BakeryIdentifier;

import java.util.Set;

public class StorageTypeRegistry {


    public static final ResourceLocation CAKE_STAND = new BakeryIdentifier("cake_stand");
    public static final ResourceLocation TRAY = new BakeryIdentifier("tray");
    public static final ResourceLocation BREADBOX = new BakeryIdentifier("breadbox");
    public static final ResourceLocation CAKE_DISPLAY = new BakeryIdentifier("cake_display");


    public static void registerBlocks(Set<Block> blocks) {
        blocks.add(ObjectRegistry.CAKE_STAND.get());
        blocks.add(ObjectRegistry.TRAY.get());
        blocks.add(ObjectRegistry.BREADBOX.get());
        blocks.add(ObjectRegistry.CAKE_DISPLAY.get());
    }
}
