package satisfy.bakery.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
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

public class CookingPotRecipe implements Recipe<Container> {
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack container;
    private final ItemStack result;

    public CookingPotRecipe(ItemStack result, NonNullList<Ingredient> ingredients, ItemStack container) {
        this.ingredients = ingredients;
        this.container = container;
        this.result = result;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        return GeneralUtil.matchesRecipe(inventory, ingredients, 1, 7);
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
        return RecipeTypeRegistry.COOKING_POT_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public ItemStack getContainer() {
        return container;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<CookingPotRecipe> {
        private static final Codec<CookingPotRecipe> CODEC = RecordCodecBuilder.create(
                (instance) -> instance.group(CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter((shapelessRecipe) -> shapelessRecipe.result)
                        , Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((list) -> {
                            Ingredient[] ingredients = list.stream().filter((ingredient) -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                            if (ingredients.length == 0) {
                                return DataResult.error(() -> "No ingredients for bakery:pot_cooking recipe");
                            } else {
                                return ingredients.length > 6 ? DataResult.error(() -> "Too many ingredients for bakery:pot_cooking recipe") : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredients));
                            }
                        }, DataResult::success).forGetter((shapelessRecipe) -> shapelessRecipe.ingredients),
                        CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("container").forGetter((shapelessRecipe) -> shapelessRecipe.container)
                ).apply(instance, CookingPotRecipe::new));

        @Override
        public Codec<CookingPotRecipe> codec() {
            return CODEC;
        }

        @Override
        public CookingPotRecipe fromNetwork(FriendlyByteBuf buf) {
            final var ingredients = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            return new CookingPotRecipe(buf.readItem(), ingredients, buf.readItem());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CookingPotRecipe recipe) {
            buf.writeItem(recipe.result);
            buf.writeVarInt(recipe.ingredients.size());
            recipe.ingredients.forEach(entry -> entry.toNetwork(buf));
            buf.writeItem(recipe.getContainer());
        }
    }

}