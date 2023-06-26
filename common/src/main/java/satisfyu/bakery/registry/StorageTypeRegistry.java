package satisfyu.bakery.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import satisfyu.bakery.util.BakeryIdentifier;

import java.util.Set;

public class StorageTypeRegistry {


    public static final ResourceLocation SHELF = new BakeryIdentifier("shelf");


    public static void registerBlocks(Set<Block> blocks) {
        blocks.add(ObjectRegistry.SHELF.get());

    }
}
