package satisfy.bakery.block.cakes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import satisfy.bakery.block.PieBlock;

import java.util.function.Supplier;

/**
 * I have no clue about Voxelshapes - so if you can get with a better solution please help! <3
 **/

public class LinzerTartBlock extends PieBlock {
    public LinzerTartBlock(Properties settings, Supplier<Item> slice) {
        super(settings, slice);
    }

    public static VoxelShape SHAPE_FULL = makeShape();

    public static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.1875, 0.9375));
        return shape;
    }

    public static VoxelShape SHAPE_3 = makeShape3();

    public static VoxelShape makeShape3() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0.5, 0.9375, 0.1875, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0.0625, 0.5, 0.1875, 0.5));
        return shape;
    }

    public static VoxelShape SHAPE_2 = makeShape2();

    public static VoxelShape makeShape2() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0.5, 0.9375, 0.1875, 0.9375));
        return shape;
    }

    public static VoxelShape SHAPE_1 = makeShape1();

    public static VoxelShape makeShape1() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.0625, 0, 0.5, 0.5, 0.1875, 0.9375));
        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        int cuts = state.getValue(CUTS);
        if (cuts == 0) {
            return SHAPE_FULL;
        } else if (cuts == 1) {
            return SHAPE_3;
        } else if (cuts == 2) {
            return SHAPE_2;
        } else {
            return SHAPE_1;
        }
    }
}
