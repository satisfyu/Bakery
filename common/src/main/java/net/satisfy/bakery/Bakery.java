package net.satisfy.bakery;

import net.satisfy.bakery.event.CommonEvents;
import net.satisfy.bakery.registry.EntityTypeRegistry;
import net.satisfy.bakery.registry.ObjectRegistry;
import net.satisfy.bakery.registry.RecipeTypeRegistry;
import net.satisfy.bakery.registry.TabRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bakery {
    public static final String MOD_ID = "bakery";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        ObjectRegistry.init();
        EntityTypeRegistry.init();
        RecipeTypeRegistry.init();
        CommonEvents.init();
        TabRegistry.init();
    }
}

