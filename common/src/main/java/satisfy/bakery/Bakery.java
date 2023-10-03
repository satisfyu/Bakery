package satisfy.bakery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfy.bakery.event.CommonEvents;
import satisfy.bakery.registry.*;

public class Bakery {
    public static final String MOD_ID = "bakery";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static void init() {
        ObjectRegistry.init();
        BlockEntityRegistry.init();
        EffectRegistry.init();
        EntityRegistry.init();
        RecipeTypeRegistry.init();
        SoundEventRegistry.init();
        ScreenHandlerTypeRegistry.init();
        CommonEvents.init();
        CompostableRegistry.init();
        BakeryTabRegistry.init();
    }

    public static void commonInit() {
    }
}

