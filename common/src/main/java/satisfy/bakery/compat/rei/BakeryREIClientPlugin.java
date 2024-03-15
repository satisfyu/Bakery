package satisfy.bakery.compat.rei;

import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import satisfy.bakery.compat.rei.caking.BakerStationCategory;
import satisfy.bakery.compat.rei.caking.BakerStationDisplay;
import satisfy.bakery.compat.rei.doughing.CraftingBowlCategory;
import satisfy.bakery.compat.rei.doughing.CraftingBowlDisplay;
import satisfy.bakery.compat.rei.cooking.CookingPotCategory;
import satisfy.bakery.compat.rei.cooking.CookingPotDisplay;
import satisfy.bakery.compat.rei.stove.StoveCategory;
import satisfy.bakery.compat.rei.stove.StoveDisplay;
import satisfy.bakery.recipe.BakingStationRecipe;
import satisfy.bakery.recipe.CraftingBowlRecipe;
import satisfy.bakery.recipe.CookingPotRecipe;
import satisfy.bakery.recipe.StoveRecipe;
import satisfy.bakery.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;


public class BakeryREIClientPlugin {
    public static void registerCategories(CategoryRegistry registry) {
        registry.add(new CookingPotCategory());
        registry.add(new StoveCategory());
        registry.add(new CraftingBowlCategory());
        registry.add(new BakerStationCategory());
        registry.addWorkstations(BakerStationCategory.BAKER_STATION_DISPLAY, EntryStacks.of(ObjectRegistry.BAKER_STATION.get()));
        registry.addWorkstations(CraftingBowlCategory.CRAFTING_BOWL_DISPLAY, EntryStacks.of(ObjectRegistry.CRAFTING_BOWL.get()));
        registry.addWorkstations(CookingPotDisplay.COOKING_POT_DISPLAY, EntryStacks.of(ObjectRegistry.SMALL_COOKING_POT.get()));
        registry.addWorkstations(StoveDisplay.STOVE_DISPLAY, EntryStacks.of(ObjectRegistry.BRICK_STOVE.get()), EntryStacks.of(ObjectRegistry.STONE_BRICKS_STOVE.get()), EntryStacks.of(ObjectRegistry.COBBLESTONE_STOVE.get()), EntryStacks.of(ObjectRegistry.DEEPSLATE_STOVE.get()), EntryStacks.of(ObjectRegistry.GRANITE_STOVE.get()), EntryStacks.of(ObjectRegistry.MUD_STOVE.get()), EntryStacks.of(ObjectRegistry.SANDSTONE_STOVE.get()), EntryStacks.of(ObjectRegistry.END_STOVE.get()), EntryStacks.of(ObjectRegistry.RED_NETHER_BRICKS_STOVE.get()), EntryStacks.of(ObjectRegistry.QUARTZ_STOVE.get())
        );
    }

    public static void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(CookingPotRecipe.class, CookingPotDisplay::new);
        registry.registerFiller(StoveRecipe.class, StoveDisplay::new);
        registry.registerFiller(CraftingBowlRecipe.class, CraftingBowlDisplay::new);
        registry.registerFiller(BakingStationRecipe.class, BakerStationDisplay::new);

    }

    public static List<Ingredient> ingredients(Recipe<Container> recipe, ItemStack stack) {
        List<Ingredient> l = new ArrayList<>(recipe.getIngredients());
        l.add(0, Ingredient.of(stack.getItem()));
        return l;
    }
}
