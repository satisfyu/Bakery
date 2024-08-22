package net.satisfy.bakery.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.satisfy.bakery.util.BakeryIdentifier;

public class TagsRegistry {

    public static final TagKey<Item> IGNORE_BLOCK_ITEM = TagKey.create(Registries.ITEM, BakeryIdentifier.of("ignore_block_item"));
    public static final TagKey<Block> CAN_NOT_CONNECT = TagKey.create(Registries.BLOCK, BakeryIdentifier.of("can_not_connect"));
    public static final TagKey<Item> BREAD = TagKey.create(Registries.ITEM, BakeryIdentifier.of("bread"));
    public static final TagKey<Item> KNIVES = TagKey.create(Registries.ITEM, BakeryIdentifier.of("knives"));
}
