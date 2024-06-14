package net.satisfy.bakery.fabric;

import net.fabricmc.api.ModInitializer;
import net.satisfy.bakery.Bakery;
import net.satisfy.bakery.registry.CompostableRegistry;

public class BakeryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Bakery.init();
        CompostableRegistry.registerCompostable();
    }
}
