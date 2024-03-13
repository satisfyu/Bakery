package satisfy.bakery.block;

import de.cristelknight.doapi.common.block.FacingBlock;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import satisfy.bakery.entity.CraftingBowlBlockEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class CraftingBowlBlock extends FacingBlock implements EntityBlock {
    private static final Supplier<VoxelShape> VOXEL_SHAPE_SUPPLIER = () -> Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.5, 0.8125);
    public static final Map<Direction, VoxelShape> SHAPES = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.values()) {
            map.put(direction, VOXEL_SHAPE_SUPPLIER.get());
        }
    });

    public CraftingBowlBlock(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CraftingBowlBlockEntity craftingBowlBlockEntity) {
            if (player.isShiftKeyDown()) {
                ItemStack removed = craftingBowlBlockEntity.removeItem();
                if (!removed.isEmpty()) {
                    if (!player.addItem(removed)) {
                        player.drop(removed, false);
                    }
                    world.sendBlockUpdated(pos, state, state, 3);
                    return InteractionResult.sidedSuccess(world.isClientSide());
                }
            } else {
                ItemStack stack = player.getItemInHand(hand);
                if (!stack.isEmpty() && canInsertStack(stack, craftingBowlBlockEntity)) {
                    ItemStack stackCopy = stack.copy();
                    stackCopy.setCount(1);
                    if (craftingBowlBlockEntity.addItem(stackCopy)) {
                        if (!player.getAbilities().instabuild) {
                            stack.shrink(1);
                        }
                        craftingBowlBlockEntity.setChanged();
                        world.sendBlockUpdated(pos, state, state, 3);
                        return InteractionResult.sidedSuccess(world.isClientSide());
                    }
                }
            }
            if (hand == InteractionHand.MAIN_HAND && !player.isShiftKeyDown() && !world.isClientSide()) {
                CraftingBowlBlockEntity entity = (CraftingBowlBlockEntity) blockEntity;
                long currentTime = world.getGameTime();
                if (entity.getCraftingProgress() == 0 || currentTime - entity.getLastInteractionTime() >= 5) {
                    entity.setLastInteractionTime(currentTime);
                    world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 0.0, 0.05, 0.0);
                    world.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 0.5F, 1.5F); // 75% schneller
                }
                return InteractionResult.sidedSuccess(world.isClientSide());
            }
        }
        return InteractionResult.PASS;
    }

    @SuppressWarnings("unused")
    public boolean canInsertStack(ItemStack stack, CraftingBowlBlockEntity craftingBowlBlockEntity) {
        if (!stack.isEdible() && !(stack.getItem() instanceof BlockItem)) {
            stack.getItem();
        }
        return true;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof CraftingBowlBlockEntity) {
                Containers.dropContents(world, pos, (CraftingBowlBlockEntity) be);
                world.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, world, pos, block, fromPos, isMoving);
        if (!world.isClientSide) {
            if (!canSurvive(state, world, pos)) {
                world.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return !world.isEmptyBlock(pos.below());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CraftingBowlBlockEntity(pos, state);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
