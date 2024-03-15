package satisfy.bakery.util;

import net.minecraft.resources.ResourceLocation;
import satisfy.bakery.Bakery;

public class BakeryIdentifier extends ResourceLocation {

    public BakeryIdentifier(String path) {
        super(Bakery.MOD_ID, path);
    }

    @SuppressWarnings("unused")
    public static String asString(String path) {
        return (Bakery.MOD_ID + ":" + path);
    }
}
