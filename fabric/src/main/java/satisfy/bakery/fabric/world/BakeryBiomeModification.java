package satisfy.bakery.fabric.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import satisfy.bakery.util.BakeryIdentifier;
import satisfy.bakery.world.feature.BakeryPlacedFeature;

import java.util.function.Predicate;


public class BakeryBiomeModification {

    public static void init() {
        BiomeModification world = BiomeModifications.create(new BakeryIdentifier("world_features"));
        Predicate<BiomeSelectionContext> strawberryJungleBiomes = getBakerySelector("spawns_strawberry_jungle");
        Predicate<BiomeSelectionContext> strawberryTaigaBiomes = getBakerySelector("spawns_strawberry_taiga");

        world.add(ModificationPhase.ADDITIONS, strawberryJungleBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BakeryPlacedFeature.STRAWBERRY_JUNGLE_PATCH_CHANCE_KEY));
        world.add(ModificationPhase.ADDITIONS, strawberryTaigaBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BakeryPlacedFeature.STRAWBERRY_TAIGA_PATCH_CHANCE_KEY));
    }

    private static Predicate<BiomeSelectionContext> getBakerySelector(String path) {
        return BiomeSelectors.tag(TagKey.create(Registry.BIOME_REGISTRY, new BakeryIdentifier(path)));
    }


}
