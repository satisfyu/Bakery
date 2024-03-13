package satisfy.bakery.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import satisfy.bakery.registry.BlockEntityTypeRegistry;
import satisfy.bakery.registry.ObjectRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CraftingBowlBlockEntity extends BlockEntity implements ImplementedInventory {
    @NotNull
    private Set<BlockPos> components = new HashSet<>(4);
    private int craftingProgress = 0;
    private static final int CRAFTING_TIME_TOTAL = 300;
    private float swingRotationDegrees = 0.0f;
    private NonNullList<ItemStack> ingredients;


    public CraftingBowlBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeRegistry.CRAFTING_BOWL_BLOCK_ENTITY.get(), pos, state);
        ingredients = NonNullList.withSize(3, ItemStack.EMPTY);

    }

    public List<ItemStack> getIngredient() {
        return this.ingredients;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ingredients = NonNullList.withSize(3, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, ingredients);
        craftingProgress = tag.getInt("CraftingProgress");
        swingRotationDegrees = tag.getFloat("SwingRotationDegrees");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, ingredients);
        tag.putInt("CraftingProgress", craftingProgress);
        tag.putFloat("SwingRotationDegrees", swingRotationDegrees);
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

    public int getCraftingProgress() {
        return craftingProgress;
    }

    public void setCraftingProgress(int craftingProgress) {
        this.craftingProgress = craftingProgress;
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
        }
        else if (containsIngredient(Items.WHEAT) && containsIngredient(Items.SUGAR) && containsIngredient(Items.EGG)) {
            result = ObjectRegistry.SWEET_DOUGH.get().getDefaultInstance();
        }
        else if (containsIngredient(Items.WHEAT) && containsIngredient(Items.SUGAR) && containsIngredient(Items.MILK_BUCKET)) {
            result = ObjectRegistry.CAKE_DOUGH.get().getDefaultInstance();
        }

        if (!result.isEmpty()) {
            ingredients.clear();
            addItem(result);
            setCraftingProgress(0);
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return ingredients;
    }

    @Override
    public boolean stillValid(Player player) {
        assert this.level != null;
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double) this.worldPosition.getX() + 0.5, (double) this.worldPosition.getY() + 0.5, (double) this.worldPosition.getZ() + 0.5) <= 64.0;
        }
    }
}