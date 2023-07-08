package satisfy.bakery.block.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import satisfy.bakery.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OatCropBlock extends CropBlock {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 5);

    public OatCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ObjectRegistry.OAT_SEEDS.get();
    }

    @Override
    public int getMaxAge() {
        return 5;
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
    public boolean propagatesSkylightDown(BlockState state, BlockGetter world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootContext.Builder builder) {
        List<ItemStack> drops = new ArrayList<>();

        if (state.getValue(AGE) == getMaxAge()) {
            Random random = new Random();
            int appleCount = random.nextInt(3) + 2;
            int seedCount = random.nextInt(2) + 1;

            drops.add(new ItemStack(ObjectRegistry.OAT.get(), appleCount));
            for (int i = 0; i < seedCount; i++) {
                drops.add(new ItemStack(ObjectRegistry.OAT_SEEDS.get()));
            }
        } else {
            drops.add(new ItemStack(ObjectRegistry.OAT_SEEDS.get()));
        }

        return drops;
    }
}
