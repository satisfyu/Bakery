package satisfy.bakery;

import de.cristelknight.doapi.DoApiExpectPlatform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfy.bakery.event.CommonEvents;
import satisfy.bakery.registry.*;
import satisfy.bakery.util.BakeryIdentifier;

public class Bakery {
    public static final String MOD_ID = "bakery";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static void init() {
        ObjectRegistry.init();
        BlockEntityTypeRegistry.init();
        EffectRegistry.init();
        EntityRegistry.init();
        RecipeTypeRegistry.init();
        SoundEventRegistry.init();
        ScreenHandlerTypeRegistry.init();
        CommonEvents.init();
        TabRegistry.init();

        DoApiExpectPlatform.registerBuiltInPack(Bakery.MOD_ID, new BakeryIdentifier("minecraft_bricks"), false);
    }

    public static void commonInit() {
    }
}

