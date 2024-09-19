package net.satisfy.bakery.block;

import de.cristelknight.doapi.common.block.FacingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.satisfy.bakery.registry.ObjectRegistry;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"deprecation"})
public class BakerStationBlock extends FacingBlock {
    public BakerStationBlock(Properties settings) {
        super(settings);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.isEmptyBlock(pos.above());
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        InteractionHand hand = player.getUsedItemHand();
        if (!world.isClientSide && hand == InteractionHand.MAIN_HAND) {
            ItemStack itemStack = player.getItemInHand(hand);
            if (itemStack.is(ObjectRegistry.CAKE_DOUGH.get())) {
                BlockPos blockAbove = pos.above();
                if (world.isEmptyBlock(blockAbove)) {
                    world.setBlock(blockAbove, ObjectRegistry.BLANK_CAKE.get().defaultBlockState(), 3);
                    world.playSound(null, pos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    world.levelEvent(2001, blockAbove, Block.getId(ObjectRegistry.BLANK_CAKE.get().defaultBlockState()));
                    if (!player.isCreative()) {
                        itemStack.shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                }
            } else {
                player.displayClientMessage(Component.translatable("tooltip.bakery.cakedoughonstation"), true);
                return InteractionResult.PASS;
            }
        }
        return InteractionResult.PASS;
    }
}