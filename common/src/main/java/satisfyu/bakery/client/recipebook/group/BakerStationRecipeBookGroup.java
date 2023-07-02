package satisfyu.bakery.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import satisfyu.bakery.recipe.BakerStationRecipe;
import satisfyu.bakery.registry.ObjectRegistry;

import java.util.List;

@Environment(EnvType.CLIENT)
public enum BakerStationRecipeBookGroup implements IRecipeBookGroup {
    SEARCH(new ItemStack(Items.COMPASS)),
    CAKE(new ItemStack(Blocks.CAKE)),
    CUPCAKE(new ItemStack(ObjectRegistry.APPLE_CUPCAKE.get()));

    public static final List<IRecipeBookGroup> CAKE_GROUPS = ImmutableList.of(SEARCH, CAKE, CUPCAKE);

    private final List<ItemStack> icons;

    BakerStationRecipeBookGroup(ItemStack... entries) {
        this.icons = ImmutableList.copyOf(entries);
    }

    public boolean fitRecipe(Recipe<?> recipe) {
        if (recipe instanceof BakerStationRecipe bakerStationRecipe) {
            switch (this) {
                case SEARCH -> {
                    return true;
                }
                case CAKE -> {
                    if (bakerStationRecipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(ObjectRegistry.BLANK_CAKE.get().getDefaultInstance()))) {
                        return true;
                    }
                }
                case CUPCAKE -> {
                    if (bakerStationRecipe.getIngredients().stream().noneMatch((ingredient) -> ingredient.test(ObjectRegistry.SWEET_DOUGH.get().getDefaultInstance()))) {
                        return true;
                    }
                }
                default -> {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}
