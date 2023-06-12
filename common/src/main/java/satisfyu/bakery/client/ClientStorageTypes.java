package satisfyu.bakery.client;

import de.cristelknight.doapi.client.render.block.storage.StorageBlockEntityRenderer;
import de.cristelknight.doapi.client.render.block.storage.StorageTypeRenderer;
import net.minecraft.resources.ResourceLocation;
import satisfyu.bakery.Bakery;
import satisfyu.bakery.client.render.block.CakeStandRenderer;
import satisfyu.bakery.client.render.block.TrayRenderer;
import satisfyu.bakery.registry.DoAPIRegistry;

public class ClientStorageTypes {
    public static void registerStorageType(ResourceLocation location, StorageTypeRenderer renderer){
        StorageBlockEntityRenderer.registerStorageType(location, renderer);
    }

    public static void init(){
        Bakery.LOGGER.debug("Registering Storage Block Renderers!");
        registerStorageType(DoAPIRegistry.CAKE_STAND, new CakeStandRenderer());
        registerStorageType(DoAPIRegistry.TRAY, new TrayRenderer());
    }
}
