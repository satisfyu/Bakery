package net.satisfy.bakery;

import de.cristelknight.doapi.DoApiEP;
import net.satisfy.bakery.event.CommonEvents;
import net.satisfy.bakery.registry.*;
import net.satisfy.bakery.util.BakeryIdentifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bakery {
    public static final String MOD_ID = "bakery";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        ObjectRegistry.init();
        BlockEntityTypeRegistry.init();
        EffectRegistry.init();
        EntityRegistry.init();
        RecipeTypeRegistry.init();
        ScreenHandlerTypeRegistry.init();
        CommonEvents.init();
        TabRegistry.init();

        DoApiEP.registerBuiltInPack(Bakery.MOD_ID, new BakeryIdentifier("minecraft_bricks"), false);
    }

    public static void commonInit() {
    }
}

