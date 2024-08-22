package net.satisfy.bakery.util;

import net.minecraft.resources.ResourceLocation;
import net.satisfy.bakery.Bakery;

public class BakeryIdentifier {

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(Bakery.MOD_ID, path);
    }

    public static ResourceLocation of(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}
