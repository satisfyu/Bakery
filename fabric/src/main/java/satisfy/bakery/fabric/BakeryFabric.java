package satisfy.bakery.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import satisfy.bakery.Bakery;
import satisfy.bakery.fabric.world.BakeryBiomeModification;
import satisfy.bakery.registry.CompostableRegistry;
import satisfy.bakery.util.BakeryIdentifier;

public class BakeryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Bakery.init();
        BakeryBiomeModification.init();
        CompostableRegistry.registerCompostable();

        FabricLoader.getInstance().getModContainer(Bakery.MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new BakeryIdentifier("minecraft_bricks"), container, ResourcePackActivationType.NORMAL);
        });
    }
}
