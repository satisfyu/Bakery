package satisfy.bakery.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import satisfy.bakery.Bakery;
import satisfy.bakery.recipe.BakingStationRecipe;
import satisfy.bakery.recipe.CraftingBowlRecipe;
import satisfy.bakery.registry.ObjectRegistry;

public class BakerStationCategory implements IRecipeCategory<BakingStationRecipe> {
    public static final RecipeType<BakingStationRecipe> CAKING = RecipeType.create(Bakery.MOD_ID, "caking", BakingStationRecipe.class);
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Bakery.MOD_ID, "textures/gui/crafting_bowl.png");

    private final IDrawable background;
    private final IDrawable icon;

    public BakerStationCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ObjectRegistry.BAKER_STATION.get()));
    }

    @Override
    public @NotNull RecipeType<BakingStationRecipe> getRecipeType() {
        return CAKING;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("rei.bakery.caking_category");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BakingStationRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 35).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 35).addItemStack(recipe.getResultItem(null));
    }
}