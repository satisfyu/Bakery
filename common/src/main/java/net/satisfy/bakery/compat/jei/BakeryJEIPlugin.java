package net.satisfy.bakery.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfy.bakery.client.gui.handler.StoveGuiHandler;
import net.satisfy.bakery.compat.jei.category.BakerStationCategory;
import net.satisfy.bakery.compat.jei.category.CookingPotCategory;
import net.satisfy.bakery.compat.jei.category.CraftingBowlCategory;
import net.satisfy.bakery.compat.jei.category.StoveCategory;
import net.satisfy.bakery.compat.jei.transfer.CookingTransferInfo;
import net.satisfy.bakery.recipe.BakingStationRecipe;
import net.satisfy.bakery.recipe.CookingPotRecipe;
import net.satisfy.bakery.recipe.CraftingBowlRecipe;
import net.satisfy.bakery.recipe.StoveRecipe;
import net.satisfy.bakery.registry.ObjectRegistry;
import net.satisfy.bakery.registry.RecipeTypeRegistry;
import net.satisfy.bakery.registry.ScreenHandlerTypeRegistry;
import net.satisfy.bakery.util.BakeryIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;


@JeiPlugin
public class BakeryJEIPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CookingPotCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new StoveCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CraftingBowlCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new BakerStationCategory(registration.getJeiHelpers().getGuiHelper()));
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<CookingPotRecipe> fridgeRecipes = rm.getAllRecipesFor(RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE.get());
        registration.addRecipes(CookingPotCategory.COOKING_POT, fridgeRecipes);

        List<StoveRecipe> stoveRecipes = rm.getAllRecipesFor(RecipeTypeRegistry.STOVE_RECIPE_TYPE.get());
        registration.addRecipes(StoveCategory.STOVE, stoveRecipes);

        List<BakingStationRecipe> cakingRecipes = rm.getAllRecipesFor(RecipeTypeRegistry.BAKING_STATION_RECIPE_TYPE.get());
        registration.addRecipes(BakerStationCategory.CAKING, cakingRecipes);

        List<CraftingBowlRecipe> doughingRecipes = rm.getAllRecipesFor(RecipeTypeRegistry.CRAFTING_BOWL_RECIPE_TYPE.get());
        registration.addRecipes(CraftingBowlCategory.DOUGHING, doughingRecipes);
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new BakeryIdentifier("jei_plugin");
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(new CookingTransferInfo());
        registration.addRecipeTransferHandler(StoveGuiHandler.class, ScreenHandlerTypeRegistry.STOVE_SCREEN_HANDLER.get(), StoveCategory.STOVE, 1, 3, 5, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ObjectRegistry.SMALL_COOKING_POT.get().asItem().getDefaultInstance(), CookingPotCategory.COOKING_POT);
        registration.addRecipeCatalyst(ObjectRegistry.BAKER_STATION.get().asItem().getDefaultInstance(), BakerStationCategory.CAKING);
        registration.addRecipeCatalyst(ObjectRegistry.CRAFTING_BOWL.get().asItem().getDefaultInstance(), CraftingBowlCategory.DOUGHING);
        registration.addRecipeCatalyst(ObjectRegistry.BRICK_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.STONE_BRICKS_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.COBBLESTONE_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.DEEPSLATE_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.GRANITE_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.MUD_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.SANDSTONE_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.END_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.RED_NETHER_BRICKS_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
        registration.addRecipeCatalyst(ObjectRegistry.QUARTZ_STOVE.get().asItem().getDefaultInstance(), StoveCategory.STOVE);
    }

    public static void addSlot(IRecipeLayoutBuilder builder, int x, int y, Ingredient ingredient){
        builder.addSlot(RecipeIngredientRole.INPUT, x, y).addIngredients(ingredient);
    }
}
