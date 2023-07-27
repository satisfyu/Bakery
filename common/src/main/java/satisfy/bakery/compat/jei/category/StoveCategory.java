package satisfy.bakery.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import satisfy.bakery.Bakery;
import satisfy.bakery.client.gui.StoveGui;
import satisfy.bakery.compat.jei.BakeryJEIPlugin;
import satisfy.bakery.entity.StoveBlockEntity;
import satisfy.bakery.recipe.StoveRecipe;
import satisfy.bakery.registry.ObjectRegistry;

public class StoveCategory implements IRecipeCategory<StoveRecipe> {
    public static final RecipeType<StoveRecipe> STOVE = RecipeType.create(Bakery.MOD_ID, "stove", StoveRecipe.class);
    public static final int WIDTH = 124;
    public static final int HEIGHT = 60;
    public static final int WIDTH_OF = 26;
    public static final int HEIGHT_OF = 13;
    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawable burnIcon;
    private final IDrawableAnimated arrow;
    private final Component localizedName;

    public StoveCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(StoveGui.BG, WIDTH_OF, HEIGHT_OF, WIDTH, HEIGHT);
        this.arrow = helper.drawableBuilder(StoveGui.BG, 178, 20, 18, 25)
                .buildAnimated(StoveBlockEntity.TOTAL_COOKING_TIME, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ObjectRegistry.BRICK_STOVE.get().asItem().getDefaultInstance());
        this.burnIcon = helper.createDrawable(StoveGui.BG, 176, 0, 17, 15);
        this.localizedName = Component.translatable("rei.bakery.stove_category");
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, StoveRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();

        BakeryJEIPlugin.addSlot(builder,29 - WIDTH_OF, 18 - HEIGHT_OF, ingredients.get(0));
        BakeryJEIPlugin.addSlot(builder,47 - WIDTH_OF, 18 - HEIGHT_OF, ingredients.get(1));
        BakeryJEIPlugin.addSlot(builder,65 - WIDTH_OF, 18 - HEIGHT_OF, ingredients.get(2));

        // Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 126 - WIDTH_OF,  42 - HEIGHT_OF).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }

    @Override
    public void draw(StoveRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, StoveGui.ARROW_X - WIDTH_OF, StoveGui.ARROW_Y - HEIGHT_OF);
        burnIcon.draw(guiGraphics, 62 - WIDTH_OF, 49 - HEIGHT_OF);
    }

    @Override
    public RecipeType<StoveRecipe> getRecipeType() {
        return STOVE;
    }

    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }
}
