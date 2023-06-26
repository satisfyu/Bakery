package satisfyu.bakery.util;

import net.minecraft.resources.ResourceLocation;
import satisfyu.bakery.Bakery;

public class BakeryIdentifier extends ResourceLocation {

    public BakeryIdentifier(String path) {
        super(Bakery.MOD_ID, path);
    }

    public static String asString(String path) {
        return (Bakery.MOD_ID + ":" + path);
    }
}
