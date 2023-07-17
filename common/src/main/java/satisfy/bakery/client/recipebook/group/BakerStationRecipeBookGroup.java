package satisfy.bakery.client.recipebook.group;

import com.google.common.collect.ImmutableList;
import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.client.player.ClientPickBlockCallback;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import satisfy.bakery.recipe.BakerStationRecipe;
import satisfy.bakery.registry.ObjectRegistry;

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

    public boolean fitRecipe(Recipe<? extends Container> recipe, RegistryAccess registryAccess) {
        return switch (this) {
            case SEARCH -> true;
            case CAKE ->
                    recipe.getIngredients().stream().anyMatch((ingredient) -> ingredient.test(ObjectRegistry.BLANK_CAKE.get().getDefaultInstance()));
            case CUPCAKE ->
                    recipe.getIngredients().stream().noneMatch((ingredient) -> ingredient.test(ObjectRegistry.SWEET_DOUGH.get().getDefaultInstance()));
        };
    }

    @Override
    public List<ItemStack> getIcons() {
        return this.icons;
    }

}
