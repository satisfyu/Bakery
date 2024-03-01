package satisfy.bakery.block;


import de.cristelknight.doapi.common.block.StorageBlock;
import de.cristelknight.doapi.common.block.entity.StorageBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import satisfy.bakery.registry.StorageTypeRegistry;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class CakeStandBlock extends StorageBlock {

    public static VoxelShape SHAPE = makeShape();

    public static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.125, 0.5000625, 0.125, 0.125, 1.0000625, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.5000625, 0.125, 0.875, 1.0000625, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.5000625, 0.875, 0.875, 1.0000625, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.125, 1.0000625, 0.125, 0.875, 1.0000625, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.875, 0.5000625, 0.125, 0.875, 1.0000625, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.4375, 1, 0.4375, 0.5625, 1.0625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0.125, 0.4375, 0.5625, 0.4375, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.25, 0.375, 0.25, 0.75, 0.4375, 0.75));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.4375, 0.125, 0.875, 0.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.375, 0.0625, 0.375, 0.625, 0.1875, 0.625));
        shape = Shapes.or(shape, Shapes.box(0.875, 0.4375, 0.125, 0.9375, 0.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0.4375, 0.125, 0.125, 0.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.4375, 0.0625, 0.875, 0.5, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.4375, 0.875, 0.875, 0.5, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.0625, 0.6875));
        return shape;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public CakeStandBlock(Properties settings) {
        super(settings);
    }


    public int findFirstEmpty(NonNullList<ItemStack> inv) {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = inv.get(i);
            if (stack.isEmpty()) return i;
        }
        return Integer.MIN_VALUE;
    }

    public int findFirstFull(NonNullList<ItemStack> inv) {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = inv.get(i);
            if (!stack.isEmpty()) return i;
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof StorageBlockEntity shelfBlockEntity) {
            List<Item> items = new LinkedList<>();
            shelfBlockEntity.getInventory().forEach(stack -> {
                if (!stack.isEmpty()) items.add(stack.getItem());
            });
            if (player.isShiftKeyDown()) {
                boolean cCake = false;
                for (Item item : items) {
                    if (item instanceof BlockItem) {
                        cCake = true;
                        break;
                    }
                }
                if (cCake) {
                    remove(world, pos, player, shelfBlockEntity, 0);
                    return InteractionResult.sidedSuccess(world.isClientSide());
                } else {
                    int i = findFirstFull(shelfBlockEntity.getInventory());
                    if (i != Integer.MIN_VALUE) {
                        remove(world, pos, player, shelfBlockEntity, i);
                        return InteractionResult.sidedSuccess(world.isClientSide());
                    }
                }
            } else {
                ItemStack stack = player.getItemInHand(hand);
                if (!stack.isEmpty() && canInsertStack(stack)) {
                    if (stack.getItem() instanceof BlockItem) {
                        if (items.isEmpty()) {
                            add(world, pos, player, shelfBlockEntity, stack, 0);
                            return InteractionResult.sidedSuccess(world.isClientSide());
                        }
                    } else {
                        if (!(shelfBlockEntity.getInventory().get(0).getItem() instanceof BlockItem)) {
                            int i = findFirstEmpty(shelfBlockEntity.getInventory());
                            if (i != Integer.MIN_VALUE) {
                                add(world, pos, player, shelfBlockEntity, stack, i);
                                return InteractionResult.sidedSuccess(world.isClientSide());
                            }
                        }
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public ResourceLocation type() {
        return StorageTypeRegistry.CAKE_STAND;
    }

    @Override
    public Direction[] unAllowedDirections() {
        return new Direction[0];
    }

    @Override
    public boolean canInsertStack(ItemStack stack) {
        return stack.isEdible() || stack.getItem() instanceof BlockItem;
    }

    @Override
    public int getSection(Float x, Float y) {
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("block.bakery.canbeplaced.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        tooltip.add(Component.translatable("block.bakery.cakestand_1.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.WHITE));
        tooltip.add(Component.translatable("block.bakery.cakestand_2.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.WHITE));
    }
}
