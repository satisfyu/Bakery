package satisfy.bakery.fabric;

import net.fabricmc.api.ModInitializer;
import satisfy.bakery.Bakery;
import satisfy.bakery.registry.CompostableRegistry;

public class BakeryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Bakery.init();
        CompostableRegistry.registerCompostable();
    }
}
