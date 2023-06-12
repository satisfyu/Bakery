package satisfyu.bakery.client.recipebook.custom;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.bakery.client.recipebook.IRecipeBookGroup;
import satisfyu.bakery.registry.ObjectRegistry;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum StoveRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    PASTRY(new ItemStack(ObjectRegistry.DOUGH.get())),
    CAKE(new ItemStack(ObjectRegistry.BUNDT_CAKE.get()));

    public static final List<IRecipeBookGroup> STOVE_GROUPS = ImmutableList.of(SEARCH, PASTRY, CAKE);

    private final List<ItemStack> icons;

    StoveRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<?> recipe) {
        return switch (this) {
            case SEARCH -> true;
            case PASTRY ->
                    recipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(Items.POTION.getDefaultInstance()));
            case CAKE ->
                    recipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(Items.POTION.getDefaultInstance()));
        };
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}
