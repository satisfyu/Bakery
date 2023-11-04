package satisfy.bakery.client;

import de.cristelknight.doapi.client.render.block.storage.StorageBlockEntityRenderer;
import de.cristelknight.doapi.client.render.block.storage.StorageTypeRenderer;
import net.minecraft.resources.ResourceLocation;
import satisfy.bakery.Bakery;
import satisfy.bakery.client.render.block.*;
import satisfy.bakery.registry.StorageTypeRegistry;

public class ClientStorageTypes {
    public static void registerStorageType(ResourceLocation location, StorageTypeRenderer renderer) {
        StorageBlockEntityRenderer.registerStorageType(location, renderer);
    }

    public static void init() {
        Bakery.LOGGER.debug("Registering Storage Block Renderers!");
        registerStorageType(StorageTypeRegistry.CAKE_STAND, new CakeStandRenderer());
        registerStorageType(StorageTypeRegistry.TRAY, new TrayRenderer());
        registerStorageType(StorageTypeRegistry.BREADBOX, new BreadBoxRenderer());
        registerStorageType(StorageTypeRegistry.CAKE_DISPLAY, new CakeDisplayRenderer());
        registerStorageType(StorageTypeRegistry.CUPCAKE_DISPLAY, new CupcakeDisplayRenderer());
        registerStorageType(StorageTypeRegistry.WALL_DISPLAY, new WallDisplayRenderer());

    }
}
