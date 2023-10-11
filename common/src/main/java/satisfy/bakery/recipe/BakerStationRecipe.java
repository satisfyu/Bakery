package satisfy.bakery.recipe;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import satisfy.bakery.registry.RecipeTypeRegistry;
import satisfy.bakery.util.GeneralUtil;

public class BakerStationRecipe implements Recipe<Container> {
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;

    public BakerStationRecipe(ItemStack result, NonNullList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.result = result;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        return GeneralUtil.matchesRecipe(inventory, ingredients, 1, 2);
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.BAKER_STATION_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.BAKER_STATION_RECIPE_TYPE.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<BakerStationRecipe> {
        private static final Codec<BakerStationRecipe> CODEC = RecordCodecBuilder.create(
                (instance) -> instance.group(CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter((shapelessRecipe) -> shapelessRecipe.result),
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((list) -> {
                            Ingredient[] ingredients = list.stream().filter((ingredient) -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                            if (ingredients.length == 0) {
                                return DataResult.error(() -> "No ingredients for bakery:baker recipe");
                            } else {
                                return ingredients.length > 3 ? DataResult.error(() -> "Too many ingredients for bakery:baker recipe") : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredients));
                            }
                        }, DataResult::success).forGetter((shapelessRecipe) -> shapelessRecipe.ingredients)
                ).apply(instance, BakerStationRecipe::new));
        @Override
        public Codec<BakerStationRecipe> codec() {
            return CODEC;
        }
        @Override
        public BakerStationRecipe fromNetwork(FriendlyByteBuf buf) {
            final var ingredients = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            return new BakerStationRecipe(buf.readItem(), ingredients);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, BakerStationRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            recipe.ingredients.forEach(entry -> entry.toNetwork(buf));
            buf.writeItem(recipe.result);
        }
    }

}