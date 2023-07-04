package satisfy.bakery.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import satisfy.bakery.registry.RecipeTypeRegistry;
import satisfy.bakery.util.GeneralUtil;


public class StoveRecipe implements Recipe<Container> {

    protected final ResourceLocation id;
    protected final NonNullList<Ingredient> inputs;
    protected final ItemStack output;
    protected final float experience;

    public StoveRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output, float experience) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
        this.experience = experience;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        return GeneralUtil.matchesRecipe(inventory, inputs, 0, 2);
    }

    @Override
    public ItemStack assemble(Container container) {
        return output.copy();
    }


    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output.copy();
    }


    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }


    @Override
    public ResourceLocation getId() {
        return this.id;
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

        @Override
        public StoveRecipe fromJson(ResourceLocation id, JsonObject json) {
            final var ingredients = GeneralUtil.deserializeIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for StoveCooking Recipe");
            } else if (ingredients.size() > 3) {
                throw new JsonParseException("Too many ingredients for StoveCooking Recipe");
            } else {
                final ItemStack outputStack = ShapedRecipe.itemStackFromJson(json);
                float xp = GsonHelper.getAsFloat(json, "experience", 0.0F);
                return new StoveRecipe(id, ingredients, outputStack, xp);

            }

        }


        @Override
        public StoveRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            final var ingredients = NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);
            ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            final ItemStack output = buf.readItem();
            final float xp = buf.readFloat();
            return new StoveRecipe(id, ingredients, output, xp);
        }

        @Override
        public void toNetwork(FriendlyByteBuf packet, StoveRecipe recipe) {
            packet.writeVarInt(recipe.inputs.size());
            recipe.inputs.forEach(entry -> entry.toNetwork(packet));
            packet.writeItem(recipe.output);
            packet.writeFloat(recipe.experience);
        }
    }
}