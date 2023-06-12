package satisfyu.bakery.block;


import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import satisfyu.bakery.registry.DoAPIRegistry;
import satisfyu.bakery.util.GeneralUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TrayBlock extends CakeStandBlock {
    public TrayBlock(Properties settings) {
        super(settings);
    }

    private static final Supplier<VoxelShape> voxelShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.25, 0, 0.15625, 0.75, 0.0625, 0.84375), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.71875, 0.0625, 0.125, 0.78125, 0.1875, 0.875), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.21875, 0.0625, 0.125, 0.28125, 0.1875, 0.875), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.28125, 0.0625, 0.8125, 0.71875, 0.1875, 0.875), BooleanOp.OR);
        shape = Shapes.joinUnoptimized(shape, Shapes.box(0.28125, 0.0625, 0.125, 0.71875, 0.1875, 0.1875), BooleanOp.OR);
        return shape;
    };

    public static final Map<Direction, VoxelShape> SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL.stream().toList()) {
            map.put(direction, GeneralUtil.rotateShape(Direction.NORTH, direction, voxelShapeSupplier.get()));
        }
    });

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(FACING));
    }


    @Override
    public int size() {
        return 6;
    }

    @Override
    public ResourceLocation type() {
        return DoAPIRegistry.TRAY;
    }

    @Override
    public Direction[] unAllowedDirections() {
        return new Direction[]{Direction.DOWN};
    }

    @Override
    public int getSection(Float f, Float y) {
        return 0;

        int nSection;
        float oneS = (float) 1 / 6;

        if (f < oneS) {
            nSection = 0;
        }
        else if(f < oneS*2){
            nSection = 1;
        }
        else if(f < oneS*3){
            nSection = 2;
        }
        else if(f < oneS*4){
            nSection = 3;
        }
        else if(f < oneS*5){
            nSection = 4;
        }
        else nSection = 5;

        return 5 - nSection;

    }
}