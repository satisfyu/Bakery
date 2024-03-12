package satisfy.bakery.block;

import de.cristelknight.doapi.common.block.FacingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import satisfy.bakery.registry.ObjectRegistry;

public class BakerStationBlock extends FacingBlock {
    public BakerStationBlock(Properties settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings({"deprecation"})
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide && hand == InteractionHand.MAIN_HAND) {
            ItemStack itemStack = player.getItemInHand(hand);
            if (itemStack.is(ObjectRegistry.CAKE_DOUGH.get())) {
                BlockPos blockAbove = pos.above();
                if (world.isEmptyBlock(blockAbove)) {
                    world.setBlock(blockAbove, ObjectRegistry.BLANK_CAKE.get().defaultBlockState(), 3);
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}
