package satisfy.bakery.registry;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import satisfy.bakery.util.BakeryIdentifier;

public class TagsRegistry {

    public static final TagKey<Item> IGNORE_BLOCK_ITEM = TagKey.create(Registry.ITEM.key(), new BakeryIdentifier("ignore_block_item"));
    public static final TagKey<Block> ALLOWS_COOKING = TagKey.create(Registry.BLOCK_REGISTRY, new BakeryIdentifier("allows_cooking"));
    public static final TagKey<Block> CAN_NOT_CONNECT = TagKey.create(Registry.BLOCK.key(), new BakeryIdentifier("can_not_connect"));
    public static final TagKey<Item> JAMS = TagKey.create(Registry.ITEM.key(), new BakeryIdentifier("jams"));
    public static final TagKey<Item> FAUCET = TagKey.create(Registry.ITEM.key(), new BakeryIdentifier("faucet"));
    public static final TagKey<Item> KNIVES = TagKey.create(Registry.ITEM.key(), new BakeryIdentifier("knives"));
    public static final TagKey<Item> CONTAINER = TagKey.create(Registry.ITEM.key(), new BakeryIdentifier("container"));

}
