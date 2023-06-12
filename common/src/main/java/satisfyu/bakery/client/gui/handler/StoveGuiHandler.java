package satisfyu.bakery.client.gui.handler;


import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import satisfyu.bakery.client.gui.handler.slot.ExtendedSlot;
import satisfyu.bakery.client.gui.handler.slot.StoveOutputSlot;
import satisfyu.bakery.client.recipebook.AbstractPrivateRecipeScreenHandler;
import satisfyu.bakery.client.recipebook.IRecipeBookGroup;
import satisfyu.bakery.client.recipebook.custom.StoveRecipeBookGroup;
import satisfyu.bakery.recipe.StoveRecipe;
import satisfyu.bakery.registry.RecipeTypeRegistry;
import satisfyu.bakery.registry.ScreenHandlerTypeRegistry;

import java.util.List;

public class StoveGuiHandler extends AbstractPrivateRecipeScreenHandler {
    public static final int FUEL_SLOT = 3;
    public static final int OUTPUT_SLOT = 4;

    public StoveGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(5), new SimpleContainerData(4));
    }

    public StoveGuiHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData delegate) {
        super(ScreenHandlerTypeRegistry.STOVE_SCREEN_HANDLER.get(), syncId, 4, playerInventory, inventory, delegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(Inventory playerInventory, Container inventory) {
        this.addSlot(new ExtendedSlot(inventory, 0, 29, 18, this::isIngredient));
        this.addSlot(new ExtendedSlot(inventory, 1, 47, 18, this::isIngredient));
        this.addSlot(new ExtendedSlot(inventory, 2, 65, 18, this::isIngredient));
        this.addSlot(new ExtendedSlot(inventory, 3, 42, 48, StoveGuiHandler::isFuel));

        this.addSlot(new StoveOutputSlot(playerInventory.player, inventory, 4, 126, 42));
    }

    private void buildPlayerContainer(Inventory playerInventory) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private boolean isIngredient(ItemStack stack) {
        return this.world.getRecipeManager().getAllRecipesFor(RecipeTypeRegistry.STOVE_RECIPE_TYPE.get()).stream().anyMatch(recipe -> recipe.getIngredients().stream().anyMatch(x -> x.test(stack)));
    }

    private static boolean isFuel(ItemStack stack) {
        return AbstractFurnaceBlockEntity.isFuel(stack);
    }

    public int getScaledProgress(int arrowWidth) {
        final int progress = this.propertyDelegate.get(2);
        final int totalProgress = this.propertyDelegate.get(3);
        if (progress == 0) {
            return 0;
        }
        return progress * arrowWidth / totalProgress + 1;
    }

    public boolean isBeingBurned() {
        return propertyDelegate.get(1) != 0;
    }

    @Override
    public List<IRecipeBookGroup> getGroups() {
        return StoveRecipeBookGroup.STOVE_GROUPS;
    }

    @Override
    public boolean hasIngredient(Recipe<?> recipe) {
        if (recipe instanceof StoveRecipe StoveRecipe) {
            for (Ingredient ingredient : StoveRecipe.getIngredients()) {
                boolean found = false;
                for (Slot slot : this.slots) {
                    if (ingredient.test(slot.getItem())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int getCraftingSlotCount() {
        return 5;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        final int entityInputStart = 0;
        int entityOutputSlot = this.inputSlots;
        final int inventoryStart = entityOutputSlot + 1;
        final int hotbarStart = inventoryStart + 9 * 3;
        final int hotbarEnd = hotbarStart + 9;

        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            Item item = itemStack2.getItem();
            itemStack = itemStack2.copy();
            if (invSlot == entityOutputSlot) {
                item.onCraftedBy(itemStack2, player.level, player);
                if (!this.moveItemStackTo(itemStack2, inventoryStart, hotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemStack2, itemStack);
            } else if (invSlot >= entityInputStart && invSlot < entityOutputSlot ? !this.moveItemStackTo(itemStack2, inventoryStart, hotbarEnd, false) :
                    !this.moveItemStackTo(itemStack2, entityInputStart, entityOutputSlot, false) && (invSlot >= inventoryStart && invSlot < hotbarStart ? !this.moveItemStackTo(itemStack2, hotbarStart, hotbarEnd, false) :
                            invSlot >= hotbarStart && invSlot < hotbarEnd && !this.moveItemStackTo(itemStack2, inventoryStart, hotbarStart, false))) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }
            slot.setChanged();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemStack2);
            this.broadcastChanges();
        }
        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

}

