package satisfy.bakery.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import satisfy.bakery.block.CraftingBowlBlock;

public class CraftingBowlBlockEntity extends BlockEntityType<CraftingBowlBlockEntity> {
    public CraftingBowlBlockEntity() {
        super(CraftingBowlBlockEntity::new, new Block[]{CraftingBowlBlock});
    }

    public BlockEntity create(BlockPos pos, BlockState state) {
        return new CraftingBowlBlockEntity(pos, state);
    }
}
