package satisfy.bakery.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class StackableJarBlock extends StackableBlock {
    private static final IntegerProperty STACK_PROPERTY = IntegerProperty.create("stack", 1, 4);

    public StackableJarBlock(Properties settings, int maxStack) {
        super(settings, maxStack);
        this.registerDefaultState(this.stateDefinition.any().setValue(STACK_PROPERTY, 1));
    }


    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.isShiftKeyDown() && player.getItemInHand(hand).isEmpty()) {
            if (!world.isClientSide) {
                int stacks = state.getValue(STACK_PROPERTY);
                if (stacks > 1) {
                    world.setBlock(pos, state.setValue(STACK_PROPERTY, stacks - 1), Block.UPDATE_ALL);
                    dropStack(world, pos, new ItemStack(this.asItem()));
                } else {
                    world.removeBlock(pos, false);
                    dropStack(world, pos, new ItemStack(this.asItem()));
                }
                return InteractionResult.sidedSuccess(false);
            }
        } else {
            return super.use(state, world, pos, player, hand, hit);
        }
        return InteractionResult.PASS;
    }

    private void dropStack(Level world, BlockPos pos, ItemStack stack) {
        Block.popResource(world, pos, stack);
    }
}