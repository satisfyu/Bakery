package net.satisfy.bakery.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.satisfy.bakery.util.BakeryIdentifier;


public class BakeryPlacedFeature {
    public static final ResourceKey<PlacedFeature> STRAWBERRY_PATCH_CHANCE_KEY = registerKey("strawberries_chance");


    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new BakeryIdentifier(name));
    }
}


