package satisfy.bakery.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import satisfy.bakery.block.StoveBlock;
import satisfy.bakery.client.gui.handler.StoveGuiHandler;
import satisfy.bakery.recipe.StoveRecipe;
import satisfy.bakery.registry.BlockEntityRegistry;
import satisfy.bakery.registry.RecipeTypeRegistry;

import static net.minecraft.world.item.ItemStack.isSameItemSameTags;

public class StoveBlockEntity extends BlockEntity implements BlockEntityTicker<StoveBlockEntity>, ImplementedInventory, MenuProvider {

    private NonNullList<ItemStack> inventory;

    protected int burnTime;
    protected int burnTimeTotal;
    protected int cookTime;
    protected int cookTimeTotal;

    protected float experience;

    protected static final int FUEL_SLOT = StoveGuiHandler.FUEL_SLOT;
    protected static final int[] INGREDIENT_SLOTS = {1, 2, 3};
    protected static final int OUTPUT_SLOT = StoveGuiHandler.OUTPUT_SLOT;

    public static final int TOTAL_COOKING_TIME = 240;

    private final ContainerData propertyDelegate = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> StoveBlockEntity.this.burnTime;
                case 1 -> StoveBlockEntity.this.burnTimeTotal;
                case 2 -> StoveBlockEntity.this.cookTime;
                case 3 -> StoveBlockEntity.this.cookTimeTotal;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> StoveBlockEntity.this.burnTime = value;
                case 1 -> StoveBlockEntity.this.burnTimeTotal = value;
                case 2 -> StoveBlockEntity.this.cookTime = value;
                case 3 -> StoveBlockEntity.this.cookTimeTotal = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public StoveBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.STOVE_BLOCK_ENTITY.get(), pos, state);
        this.inventory = NonNullList.withSize(5, ItemStack.EMPTY);
    }

    public void dropExperience(ServerLevel world, Vec3 pos) {
        ExperienceOrb.award(world, pos, (int) experience);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if(side.equals(Direction.UP)){
            return INGREDIENT_SLOTS;
        } else if (side.equals(Direction.DOWN)){
            return new int[]{OUTPUT_SLOT};
        } else return new int[]{FUEL_SLOT};
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(5, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        this.burnTime = nbt.getShort("BurnTime");
        this.cookTime = nbt.getShort("CookTime");
        this.cookTimeTotal = nbt.getShort("CookTimeTotal");
        this.burnTimeTotal = this.getTotalBurnTime(this.getItem(FUEL_SLOT));
        this.experience = nbt.getFloat("Experience");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putShort("BurnTime", (short) this.burnTime);
        nbt.putShort("CookTime", (short) this.cookTime);
        nbt.putShort("CookTimeTotal", (short) this.cookTimeTotal);
        nbt.putFloat("Experience", this.experience);
        ContainerHelper.saveAllItems(nbt, this.inventory);
    }

    protected boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void tick(Level world, BlockPos pos, BlockState state, StoveBlockEntity blockEntity) {
        if (world.isClientSide) {
            return;
        }
        boolean initialBurningState = blockEntity.isBurning();
        boolean dirty = false;
        if (initialBurningState) {
            --this.burnTime;
        }

        final var recipe = world.getRecipeManager().getRecipeFor(RecipeTypeRegistry.STOVE_RECIPE_TYPE.get(), blockEntity, world).orElse(null);
        RegistryAccess access = level.registryAccess();
        if (recipe == null) return;
        if (!initialBurningState && canCraft(recipe.value(), access)) {
            this.burnTime = this.burnTimeTotal = this.getTotalBurnTime(this.getItem(FUEL_SLOT));
            if (burnTime > 0) {
                dirty = true;
                final ItemStack fuelStack = this.getItem(FUEL_SLOT);
                if (fuelStack.getItem().hasCraftingRemainingItem()) {
                    setItem(FUEL_SLOT, new ItemStack(fuelStack.getItem().getCraftingRemainingItem()));
                } else if (fuelStack.getCount() > 1) {
                    removeItem(FUEL_SLOT, 1);
                } else if (fuelStack.getCount() == 1) {
                    setItem(FUEL_SLOT, ItemStack.EMPTY);
                }
            }
        }
        if (isBurning() && canCraft(recipe.value(), access)) {
            ++this.cookTime;
            if (this.cookTime == cookTimeTotal) {
                this.cookTime = 0;
                craft(recipe.value(), access);
                dirty = true;
            }
        } else if (!canCraft(recipe.value(), access)) {
            this.cookTime = 0;
        }
        if (initialBurningState != isBurning()) {
            if (state.getValue(StoveBlock.LIT) != (burnTime > 0)) {
                world.setBlock(pos, state.setValue(StoveBlock.LIT, burnTime > 0), Block.UPDATE_ALL);
                dirty = true;
            }
        }
        if (dirty) {
            setChanged();
        }

    }

    protected boolean canCraft(StoveRecipe recipe, RegistryAccess access) {
        if (recipe == null || recipe.getResultItem(access).isEmpty()) {
            return false;
        } else if (this.getItem(FUEL_SLOT).isEmpty()) {
            return false;
        } else if (this.getItem(OUTPUT_SLOT).isEmpty()) {
            return true;
        } else {
            if (this.getItem(OUTPUT_SLOT).isEmpty()) {
                return true;
            }
            final ItemStack recipeOutput = recipe.getResultItem(access);
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

    protected void craft(StoveRecipe recipe, RegistryAccess access) {
        if (recipe == null || !canCraft(recipe, access)) {
            return;
        }
        final ItemStack recipeOutput = recipe.getResultItem(access);
        final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            setItem(OUTPUT_SLOT, recipeOutput);
        } else if (outputSlotStack.is(recipeOutput.getItem())) {
            outputSlotStack.grow(recipeOutput.getCount());
        }


        final NonNullList<Ingredient> ingredients = recipe.getIngredients();
        boolean[] slotUsed = new boolean[3];
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            ItemStack bestSlot = getItem(i);
            if (ingredient.test(bestSlot) && !slotUsed[i]) {
                slotUsed[i] = true;
                ItemStack remainderStack = getRemainderItem(bestSlot);
                bestSlot.shrink(1);
                if (!remainderStack.isEmpty()) {
                    setItem(i, remainderStack);
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    ItemStack stack = getItem(j);
                    if (ingredient.test(stack) && !slotUsed[j]) {
                        slotUsed[j] = true;
                        ItemStack remainderStack = getRemainderItem(stack);
                        stack.shrink(1);
                        if (!remainderStack.isEmpty()) {
                            setItem(j, remainderStack);
                        }
                    }
                }
            }
        }
    }


    protected int getTotalBurnTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            final Item item = fuel.getItem();
            return AbstractFurnaceBlockEntity.getFuel().getOrDefault(item, 0);
        }
    }

    private ItemStack getRemainderItem(ItemStack stack) {
        if (stack.getItem().hasCraftingRemainingItem()) {
            return new ItemStack(stack.getItem().getCraftingRemainingItem());
        }
        return ItemStack.EMPTY;
    }


    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public void setItem(int slot, ItemStack stack) {
        final ItemStack stackInSlot = this.inventory.get(slot);
        boolean dirty = !stack.isEmpty() && ItemStack.isSameItem(stack, stackInSlot) && ItemStack.matches(stack, stackInSlot);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        boolean hasIngredientChange = false;
        for (int ingredientSlot : INGREDIENT_SLOTS) {
            if (!ItemStack.isSameItemSameTags(this.getItem(ingredientSlot), stackInSlot)) {
                hasIngredientChange = true;
                break;
            }
        }
        if (hasIngredientChange && !dirty) {
            this.cookTimeTotal = TOTAL_COOKING_TIME;
            this.cookTime = 0;
            this.setChanged();
        }
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
    public Component getDisplayName() {
        return Component.translatable(this.getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new StoveGuiHandler(syncId, inv, this, this.propertyDelegate);
    }
}