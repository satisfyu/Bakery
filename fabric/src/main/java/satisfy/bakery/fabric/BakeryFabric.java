package satisfy.bakery.fabric;

import net.fabricmc.api.ModInitializer;
import satisfy.bakery.fabric.world.BakeryBiomeModification;
import satisfyu.bakery.Bakery;

public class BakeryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Bakery.init();
        BakeryBiomeModification.init();
    }
}
