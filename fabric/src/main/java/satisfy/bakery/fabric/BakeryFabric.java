package satisfy.bakery.fabric;

import net.fabricmc.api.ModInitializer;
import satisfy.bakery.Bakery;
import satisfy.bakery.fabric.world.BakeryBiomeModification;

public class BakeryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Bakery.init();
        BakeryBiomeModification.init();
    }
}
