package satisfyu.bakery.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;
import satisfyu.bakery.block.CookingPotBlock;
import satisfyu.bakery.client.gui.handler.CookingPotGuiHandler;
import satisfyu.bakery.recipe.CookingPotRecipe;
import satisfyu.bakery.registry.BlockEntityRegistry;
import satisfyu.bakery.registry.RecipeTypeRegistry;
import satisfyu.bakery.registry.TagsRegistry;

import static net.minecraft.world.item.ItemStack.isSameItemSameTags;

public class CookingPotEntity extends BlockEntity implements BlockEntityTicker<CookingPotEntity>, Container, MenuProvider {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(MAX_CAPACITY, ItemStack.EMPTY);
    private static final int MAX_CAPACITY = 8;
    public static final int MAX_COOKING_TIME = 600; // Time in ticks (30s)
    private int cookingTime;
    public static final int BOTTLE_INPUT_SLOT = 6;
    public static final int OUTPUT_SLOT = 7;
    private static final int INGREDIENTS_AREA = 2 * 3;

    private boolean isBeingBurned;

    private final ContainerData delegate;

    public CookingPotEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COOKING_POT_BLOCK_ENTITY.get(), pos, state);
        this.delegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CookingPotEntity.this.cookingTime;
                    case 1 -> CookingPotEntity.this.isBeingBurned ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CookingPotEntity.this.cookingTime = value;
                    case 1 -> CookingPotEntity.this.isBeingBurned = value != 0;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        this.cookingTime = nbt.getInt("CookingTime");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory);
        nbt.putInt("CookingTime", this.cookingTime);
    }

    public boolean isBeingBurned() {
        if (getLevel() == null)
            throw new NullPointerException("Null world invoked");
        final BlockState belowState = this.getLevel().getBlockState(getBlockPos().below());
        if (belowState.is(TagsRegistry.ALLOWS_COOKING)) {
            try {
                return belowState.getValue(BlockStateProperties.LIT);
            } catch (IllegalArgumentException e) {
                return true;
            }
        }
        return false;
    }


    private boolean canCraft(Recipe<?> recipe) {
        if (recipe == null || recipe.getResultItem().isEmpty()) {
            return false;
        }
        if (recipe instanceof CookingPotRecipe cookingRecipe) {
            if (!this.getItem(BOTTLE_INPUT_SLOT).is(cookingRecipe.getContainer().getItem())) {
                return false;
            } else if (this.getItem(OUTPUT_SLOT).isEmpty()) {
                return true;
            } else {
                if (this.getItem(OUTPUT_SLOT).isEmpty()) {
                    return true;
                }
                final ItemStack recipeOutput = this.generateOutputItem(recipe);
                final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
                final int outputSlotCount = outputSlotStack.getCount();
                if (this.getItem(OUTPUT_SLOT).isEmpty()) {
                    return true;
                } else if (!isSameItemSameTags(outputSlotStack, recipeOutput)) {
                    return false;
                } else if (outputSlotCount < this.getMaxStackSize() && outputSlotCount < outputSlotStack.getMaxStackSize()) {
                    return true;
                } else {
                    return outputSlotCount < recipeOutput.getMaxStackSize();
                }
            }
        }
        return false;
    }

    private void craft(Recipe<?> recipe) {
        if (!canCraft(recipe)) {
            return;
        }
        final ItemStack recipeOutput = generateOutputItem(recipe);
        final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            setItem(OUTPUT_SLOT, recipeOutput.copy());
        } else if (outputSlotStack.is(recipeOutput.getItem())) {
            outputSlotStack.grow(recipeOutput.getCount());
        }
        final NonNullList<Ingredient> ingredients = recipe.getIngredients();
        boolean[] slotUsed = new boolean[INGREDIENTS_AREA];
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            final ItemStack bestSlot = this.getItem(i);
            if (ingredient.test(bestSlot) && !slotUsed[i]) {
                slotUsed[i] = true;
                bestSlot.shrink(1);
            } else {
                for (int j = 0; j < INGREDIENTS_AREA; j++) {
                    ItemStack stack = this.getItem(j);
                    if (ingredient.test(stack) && !slotUsed[j]) {
                        slotUsed[j] = true;
                        stack.shrink(1);
                    }
                }
            }
        }
        this.getItem(BOTTLE_INPUT_SLOT).shrink(1);
    }

    private ItemStack generateOutputItem(Recipe<?> recipe) {
        ItemStack outputStack = recipe.getResultItem();

        if (!(outputStack.getItem() instanceof EffectFood)) {
            return outputStack;
        }

        for (Ingredient ingredient : recipe.getIngredients()) {
            for (int j = 0; j < 6; j++) {
                ItemStack stack = this.getItem(j);
                if (ingredient.test(stack)) {
                    EffectFoodHelper.getEffects(stack).forEach(effect -> EffectFoodHelper.addEffect(outputStack, effect));
                    break;
                }
            }
        }
        return outputStack;
    }


    @Override
    public void tick(Level world, BlockPos pos, BlockState state, CookingPotEntity blockEntity) {
        if (world.isClientSide()) {
            return;
        }
        boolean isBeingBurned = isBeingBurned();
        if (!isBeingBurned) {
            if (state.getValue(CookingPotBlock.LIT)) {
                world.setBlock(pos, state.setValue(CookingPotBlock.LIT, false), Block.UPDATE_ALL);
            }
            return;
        }
        Recipe<?> recipe = world.getRecipeManager().getRecipeFor(RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE.get(), this, world).orElse(null);


        boolean canCraft = canCraft(recipe);
        if (canCraft) {
            this.cookingTime++;
            if (this.cookingTime >= MAX_COOKING_TIME) {
                this.cookingTime = 0;
                craft(recipe);
            }
        } else if (!canCraft(recipe)) {
            this.cookingTime = 0;
        }
        if (canCraft) {
            world.setBlock(pos, this.getBlockState().getBlock().defaultBlockState().setValue(CookingPotBlock.COOKING, true).setValue(CookingPotBlock.LIT, true), Block.UPDATE_ALL);
        } else if (state.getValue(CookingPotBlock.COOKING)) {
            world.setBlock(pos, this.getBlockState().getBlock().defaultBlockState().setValue(CookingPotBlock.COOKING, false).setValue(CookingPotBlock.LIT, true), Block.UPDATE_ALL);
        } else if (state.getValue(CookingPotBlock.LIT) != isBeingBurned) {
            world.setBlock(pos, state.setValue(CookingPotBlock.LIT, isBeingBurned), Block.UPDATE_ALL);
        }
    }


    @Override
    public int getContainerSize() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        this.setChanged();
    }


    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double) this.worldPosition.getX() + 0.5, (double) this.worldPosition.getY() + 0.5, (double) this.worldPosition.getZ() + 0.5) <= 64.0;
        }
    }

    @Override
    public void clearContent() {
        inventory.clear();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new CookingPotGuiHandler(syncId, inv, this, this.delegate);
    }
}


