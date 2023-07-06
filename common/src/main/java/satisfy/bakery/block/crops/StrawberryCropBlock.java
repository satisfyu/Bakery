package satisfy.bakery.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import satisfy.bakery.registry.ObjectRegistry;

import java.util.List;
import java.util.Random;

public class StrawberryCropBlock extends CropBlock {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);

    public StrawberryCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ObjectRegistry.STRAWBERRY_SEEDS.get();
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int i = state.getValue(AGE);
        boolean bl = i == 4;
        if (!bl && player.getItemInHand(hand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 1) {
            int x = world.random.nextInt(2);
            popResource(world, pos, new ItemStack(ObjectRegistry.STRAWBERRY.get(), x + (bl ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlock(pos, state.setValue(AGE, 1), 2);
            return InteractionResult.sidedSuccess(world.isClientSide);
        } else {
            return super.use(state, world, pos, player, hand, hit);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootContext.Builder builder) {
        List<ItemStack> drops = super.getDrops(state, builder);

        if (state.getValue(AGE) == getMaxAge()) {
            Random random = new Random();
            int appleCount = random.nextInt(2) + 2;
            int seedCount = random.nextInt(2) + 1;

            drops.add(new ItemStack(ObjectRegistry.STRAWBERRY.get(), appleCount));
            for (int i = 0; i < seedCount; i++) {
                drops.add(new ItemStack(ObjectRegistry.STRAWBERRY_SEEDS.get()));
            }
        } else {
            drops.add(new ItemStack(ObjectRegistry.STRAWBERRY_SEEDS.get()));
        }
        return drops;
    }
}

