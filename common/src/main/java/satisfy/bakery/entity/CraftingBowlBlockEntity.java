package satisfy.bakery.entity;

import de.cristelknight.doapi.common.entity.ImplementedInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import satisfy.bakery.registry.BlockEntityTypeRegistry;
import satisfy.bakery.registry.ObjectRegistry;

public class CraftingBowlBlockEntity extends BlockEntity implements ImplementedInventory, BlockEntityTicker<CraftingBowlBlockEntity> {
    private NonNullList<ItemStack> ingredients = NonNullList.withSize(3, ItemStack.EMPTY);
    private int craftingProgress = 0;
    private long lastInteractionTime = 0;
    private static final int CRAFTING_TIME_TOTAL = 300;

    public CraftingBowlBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeRegistry.CRAFTING_BOWL_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ingredients = NonNullList.withSize(3, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, ingredients);
        craftingProgress = tag.getInt("CraftingProgress");
        lastInteractionTime = tag.getLong("LastInteractionTime");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, ingredients);
        tag.putInt("CraftingProgress", craftingProgress);
        tag.putLong("LastInteractionTime", lastInteractionTime);
    }

    public boolean addItem(ItemStack itemStack) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).isEmpty()) {
                ingredients.set(i, itemStack.copy());
                itemStack.shrink(1);
                this.setChanged();
                return true;
            }
        }
        return false;
    }

    public ItemStack removeItem() {
        for (int i = ingredients.size() - 1; i >= 0; i--) {
            if (!ingredients.get(i).isEmpty()) {
                ItemStack removed = ingredients.get(i);
                ingredients.set(i, ItemStack.EMPTY);
                this.setChanged();
                return removed;
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean containsIngredient(Item searchItem) {
        for (ItemStack stack : ingredients) {
            if (stack.getItem() == searchItem) {
                return true;
            }
        }
        return false;
    }

    public void transformContents() {
        ItemStack result = ItemStack.EMPTY;
        if (containsIngredient(Items.WHEAT) && containsIngredient(ObjectRegistry.YEAST.get())) {
            result = ObjectRegistry.DOUGH.get().getDefaultInstance();
        } else if (containsIngredient(Items.WHEAT) && containsIngredient(Items.SUGAR) && containsIngredient(Items.EGG)) {
            result = ObjectRegistry.SWEET_DOUGH.get().getDefaultInstance();
        } else if (containsIngredient(Items.WHEAT) && containsIngredient(Items.SUGAR) && containsIngredient(Items.MILK_BUCKET)) {
            result = ObjectRegistry.CAKE_DOUGH.get().getDefaultInstance();
        }

        if (!result.isEmpty()) {
            ingredients.clear();
            addItem(result);
            craftingProgress = 0;
        }
    }

    @SuppressWarnings("unused")
    public void setCraftingProgress(int craftingProgress) {
        this.craftingProgress = craftingProgress;
    }

    public int getCraftingProgress() {
        return craftingProgress;
    }

    public long getLastInteractionTime() {
        return lastInteractionTime;
    }

    public void setLastInteractionTime(long lastInteractionTime) {
        this.lastInteractionTime = lastInteractionTime;
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, CraftingBowlBlockEntity be) {
        if (!level.isClientSide) {
            long currentTime = level.getGameTime();
            if (currentTime - be.getLastInteractionTime() <= 20 * 15) {
                be.craftingProgress++;
                if (be.craftingProgress >= CRAFTING_TIME_TOTAL) {
                    be.transformContents();
                    be.craftingProgress = 0;
                }
            } else {
                be.craftingProgress = 0;
            }
            be.setChanged();
        }
    }


    public NonNullList<ItemStack> getItems() {
        return ingredients;
    }

    public boolean stillValid(Player player) {
        assert this.level != null;
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        }
        return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
    }
}
