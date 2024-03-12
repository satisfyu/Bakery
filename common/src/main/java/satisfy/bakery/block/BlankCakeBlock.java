package satisfy.bakery.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.item.BlockItem;
import org.jetbrains.annotations.NotNull;
import satisfy.bakery.registry.ObjectRegistry;

public class BlankCakeBlock extends Block {
    public static final BooleanProperty ONE = BooleanProperty.create("one");
    public static final BooleanProperty FOUR = BooleanProperty.create("four");

    public BlankCakeBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(ONE, true).setValue(FOUR, false));
    }

    private static final VoxelShape SHAPE = Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.5, 0.9375);

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(ONE, FOUR);
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            var itemStack = player.getItemInHand(hand);
            var item = itemStack.getItem();
            if (item instanceof BlockItem) {
                var block = ((BlockItem) item).getBlock();

                if (block == ObjectRegistry.STRAWBERRY_JAM.get()) {
                    world.setBlock(pos, ObjectRegistry.STRAWBERRY_CAKE.get().defaultBlockState(), 3);
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    return InteractionResult.sidedSuccess(world.isClientSide);
                } else if (block == ObjectRegistry.CHOCOLATE_JAM.get()) {
                    world.setBlock(pos, ObjectRegistry.CHOCOLATE_CAKE.get().defaultBlockState(), 3);
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    return InteractionResult.sidedSuccess(world.isClientSide);
                } else if (block == ObjectRegistry.SWEETBERRY_JAM.get()) {
                    world.setBlock(pos, ObjectRegistry.SWEETBERRY_CAKE.get().defaultBlockState(), 3);
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    return InteractionResult.sidedSuccess(world.isClientSide);
                } else if (item == ObjectRegistry.CHOCOLATE_TRUFFLE.get()) {
                    world.setBlock(pos, ObjectRegistry.CHERRY_GATEAU.get().defaultBlockState(), 3);
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    return InteractionResult.sidedSuccess(world.isClientSide);
                }
            }
        }
        return InteractionResult.PASS;
    }
}
