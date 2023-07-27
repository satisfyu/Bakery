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
import satisfy.bakery.client.gui.BakerStationGui;
import satisfy.bakery.compat.jei.BakeryJEIPlugin;
import satisfy.bakery.recipe.BakerStationRecipe;
import satisfy.bakery.registry.ObjectRegistry;

public class BakerStationCategory implements IRecipeCategory<BakerStationRecipe> {
    public static final RecipeType<BakerStationRecipe> BAKER_STATION = RecipeType.create(Bakery.MOD_ID, "baker", BakerStationRecipe.class);
    public static final int WIDTH = 124;
    public static final int HEIGHT = 60;
    public static final int WIDTH_OF = 26;
    public static final int HEIGHT_OF = 13;
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;
    private final Component localizedName;

    public BakerStationCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(BakerStationGui.BG, WIDTH_OF, HEIGHT_OF, WIDTH, HEIGHT);
        this.arrow = helper.drawableBuilder(BakerStationGui.BG, 177, 26, 22, 10)
                .buildAnimated(50, IDrawableAnimated.StartDirection.LEFT, false);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ObjectRegistry.BAKER_STATION.get().asItem().getDefaultInstance());
        this.localizedName = Component.translatable("rei.bakery.baking_category");
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BakerStationRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        // Inputs
        BakeryJEIPlugin.addSlot(builder,46 - WIDTH_OF, 27 - HEIGHT_OF, ingredients.get(0));
        BakeryJEIPlugin.addSlot(builder,59 - WIDTH_OF, 43 - HEIGHT_OF, ingredients.get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 128 - WIDTH_OF,  42 - HEIGHT_OF).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }

    @Override
    public void draw(BakerStationRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, BakerStationGui.ARROW_X - WIDTH_OF, BakerStationGui.ARROW_Y - HEIGHT_OF);
    }

    @Override
    public RecipeType<BakerStationRecipe> getRecipeType() {
        return BAKER_STATION;
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
