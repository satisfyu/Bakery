package satisfy.bakery.block;

import de.cristelknight.doapi.common.block.StorageBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;
import satisfy.bakery.registry.StorageTypeRegistry;
import satisfy.bakery.util.BakeryProperties;
import satisfy.bakery.util.LineConnectingType;


public class CakeDisplayBlock extends StorageBlock {

    public static final DirectionProperty FACING;
    public static final EnumProperty<LineConnectingType> TYPE;

    public CakeDisplayBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(((this.stateDefinition.any().setValue(FACING, Direction.NORTH)).setValue(TYPE, LineConnectingType.NONE)));
    }

    @Override
    public int size(){
        return 6;
    }

    @Override
    public boolean canInsertStack(ItemStack stack) {
        return stack.isEdible() || stack.getItem() instanceof BlockItem;
    }

    @Override
    public ResourceLocation type() {
        return StorageTypeRegistry.CAKE_DISPLAY;
    }

    @Override
    public Direction[] unAllowedDirections() {
        return new Direction[]{Direction.DOWN, Direction.UP};
    }

    @Override
    public int getSection(Float x, Float y) {
        float l = (float) 1/3;

        int nSection;
        if (x < 0.375F) {
            nSection = 0;
        } else {
            nSection = x < 0.6875F ? 1 : 2;
        }

        int i = y >= l ? 1 : 2;
        return nSection + i * 3;
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        BlockState blockState = this.defaultBlockState().setValue(FACING, facing);

        Level world = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();

        return switch (facing) {
            case EAST ->
                    blockState.setValue(TYPE, getType(blockState, world.getBlockState(clickedPos.south()), world.getBlockState(clickedPos.north())));
            case SOUTH ->
                    blockState.setValue(TYPE, getType(blockState, world.getBlockState(clickedPos.west()), world.getBlockState(clickedPos.east())));
            case WEST ->
                    blockState.setValue(TYPE, getType(blockState, world.getBlockState(clickedPos.north()), world.getBlockState(clickedPos.south())));
            default ->
                    blockState.setValue(TYPE, getType(blockState, world.getBlockState(clickedPos.east()), world.getBlockState(clickedPos.west())));
        };
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClientSide) return;

        Direction facing = state.getValue(FACING);

        LineConnectingType type;
        switch (facing) {
            case EAST ->
                    type = getType(state, world.getBlockState(pos.south()), world.getBlockState(pos.north()));
            case SOUTH ->
                    type =  getType(state, world.getBlockState(pos.west()), world.getBlockState(pos.east()));
            case WEST ->
                    type =  getType(state, world.getBlockState(pos.north()), world.getBlockState(pos.south()));
            default ->
                    type =  getType(state, world.getBlockState(pos.east()), world.getBlockState(pos.west()));
        }
        if (state.getValue(TYPE) != type) {
            state = state.setValue(TYPE, type);
        }
        world.setBlock(pos, state, 3);
    }

    public LineConnectingType getType(BlockState state, BlockState left, BlockState right) {
        boolean shape_left_same = left.getBlock() == state.getBlock() && left.getValue(FACING) == state.getValue(FACING);
        boolean shape_right_same = right.getBlock() == state.getBlock() && right.getValue(FACING) == state.getValue(FACING);

        if (shape_left_same && shape_right_same) {
            return LineConnectingType.MIDDLE;
        } else if (shape_left_same) {
            return LineConnectingType.LEFT;
        } else if (shape_right_same) {
            return LineConnectingType.RIGHT;
        }
        return LineConnectingType.NONE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }


    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        TYPE = BakeryProperties.LINE_CONNECTING_TYPE;
    }
}
