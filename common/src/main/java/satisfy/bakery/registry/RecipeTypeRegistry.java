package satisfy.bakery.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import satisfy.bakery.Bakery;
import satisfy.bakery.recipe.BakingStationRecipe;
import satisfy.bakery.recipe.CookingPotRecipe;

import java.util.function.Supplier;

public class RecipeTypeRegistry {

    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Bakery.MOD_ID, Registries.RECIPE_SERIALIZER);
    public static final RegistrySupplier<RecipeSerializer<CookingPotRecipe>> COOKING_POT_RECIPE_SERIALIZER = create("pot_cooking", CookingPotRecipe.Serializer::new);
    public static final RegistrySupplier<RecipeSerializer<BakingStationRecipe>> BAKING_STATION_RECIPE_SERIALIZER = create("baking_station", BakingStationRecipe.Serializer::new);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Bakery.MOD_ID, Registries.RECIPE_TYPE);
    public static final RegistrySupplier<RecipeType<CookingPotRecipe>> COOKING_POT_RECIPE_TYPE = create("pot_cooking");
    public static final RegistrySupplier<RecipeType<BakingStationRecipe>> BAKING_STATION_RECIPE_TYPE = create("baking_station");

    private static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> create(String name, Supplier<RecipeSerializer<T>> serializer) {
        return RECIPE_SERIALIZERS.register(name, serializer);
    }

    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> create(String name) {
        Supplier<RecipeType<T>> type = () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        return RECIPE_TYPES.register(name, type);
    }

    public static void init() {
        RECIPE_SERIALIZERS.register();
        RECIPE_TYPES.register();
    }
}
