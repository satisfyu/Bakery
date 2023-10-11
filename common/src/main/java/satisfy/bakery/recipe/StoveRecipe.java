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


public class StoveRecipe implements Recipe<Container> {
    protected final NonNullList<Ingredient> ingredients;
    protected final ItemStack result;
    protected final float experience;

    public StoveRecipe(ItemStack result, NonNullList<Ingredient> ingredients, float experience) {
        this.ingredients = ingredients;
        this.result = result;
        this.experience = experience;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        return GeneralUtil.matchesRecipe(inventory, ingredients, 1, 3);
    }
    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return ItemStack.EMPTY;
    }


    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public float getExperience() {
        return experience;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeTypeRegistry.STOVE_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeRegistry.STOVE_RECIPE_TYPE.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<StoveRecipe> {
        private static final Codec<StoveRecipe> CODEC = RecordCodecBuilder.create(
                (instance) -> instance.group(CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter((shapelessRecipe) -> shapelessRecipe.result),
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((list) -> {
                            Ingredient[] ingredients = list.stream().filter((ingredient) -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                            if (ingredients.length == 0) {
                                return DataResult.error(() -> "No ingredients for bakery:stove recipe");
                            } else {
                                return ingredients.length > 6 ? DataResult.error(() -> "Too many ingredients for bakery:stove recipe") : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredients));
                            }
                        }, DataResult::success).forGetter((shapelessRecipe) -> shapelessRecipe.ingredients),
                        Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter((abstractCookingRecipe) -> abstractCookingRecipe.experience)
                ).apply(instance, StoveRecipe::new));
        @Override
        public Codec<StoveRecipe> codec() {
            return CODEC;
        }

        @Override
        public StoveRecipe fromNetwork(FriendlyByteBuf buf) {
            final var ingredients = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            final ItemStack output = buf.readItem();
            final float xp = buf.readFloat();
            return new StoveRecipe(output, ingredients, xp);
        }

        @Override
        public void toNetwork(FriendlyByteBuf packet, StoveRecipe recipe) {
            packet.writeVarInt(recipe.ingredients.size());
            recipe.ingredients.forEach(entry -> entry.toNetwork(packet));
            packet.writeItem(recipe.result);
            packet.writeFloat(recipe.experience);
        }
    }
}