package satisfyu.bakery.registry;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import satisfyu.bakery.BakeryIdentifier;

public class TagsRegistry {

    public static final TagKey<Block> ALLOWS_COOKING = TagKey.create(Registry.BLOCK_REGISTRY, new BakeryIdentifier("allows_cooking"));
    public static final TagKey<Block> CAN_NOT_CONNECT = TagKey.create(Registry.BLOCK.key(), new BakeryIdentifier("can_not_connect"));
    public static final TagKey<Item> JAMS = TagKey.create(Registry.ITEM.key(), new BakeryIdentifier("jams"));
}
