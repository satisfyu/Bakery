package satisfy.bakery.compat.jei.transfer;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import satisfy.bakery.client.gui.handler.CookingPotGuiHandler;
import satisfy.bakery.compat.jei.category.CookingPotCategory;
import satisfy.bakery.recipe.CookingPotRecipe;
import satisfy.bakery.registry.ScreenHandlerTypeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookingTransferInfo implements IRecipeTransferInfo<CookingPotGuiHandler, CookingPotRecipe> {
    @Override
    public Class<? extends CookingPotGuiHandler> getContainerClass() {
        return CookingPotGuiHandler.class;
    }

    @Override
    public Optional<MenuType<CookingPotGuiHandler>> getMenuType() {
        return Optional.of(ScreenHandlerTypeRegistry.COOKING_POT_SCREEN_HANDLER.get());
    }

    @Override
    public RecipeType<CookingPotRecipe> getRecipeType() {
        return CookingPotCategory.COOKING_POT;
    }

    @Override
    public boolean canHandle(CookingPotGuiHandler container, CookingPotRecipe recipe) {
        return true;
    }

    @Override
    public List<Slot> getRecipeSlots(CookingPotGuiHandler container, CookingPotRecipe recipe) {
        List<Slot> slots = new ArrayList<>();
        slots.add(container.getSlot(7));
        for(int i = 1; i <= recipe.getIngredients().size() && i < 7; i++){
            slots.add(container.getSlot(i));
        }
        return slots;
    }

    @Override
    public List<Slot> getInventorySlots(CookingPotGuiHandler container, CookingPotRecipe recipe) {
        List<Slot> slots = new ArrayList<>();
        for (int i = 8; i < 8 + 36; i++) {
            Slot slot = container.getSlot(i);
            slots.add(slot);
        }
        return slots;
    }
}
