package satisfy.bakery.registry;

import de.cristelknight.doapi.common.util.datafixer.DataFixers;
import de.cristelknight.doapi.common.util.datafixer.StringPairs;
import satisfy.bakery.Bakery;

public class DataFixerRegistry {

    public static void init() {
        StringPairs p = DataFixers.getOrCreate(Bakery.MOD_ID);
        p.add("bakery:wild_strawberries", "farm_and_charm:wild_strawberries");
        p.add("bakery:strawberry_crop", "farm_and_charm:strawberry_crop");
        p.add("bakery:strawberry_seeds", "farm_and_charm:strawberry_seeds");
        p.add("bakery:strawberry", "farm_and_charm:strawberry");
        p.add("bakery:oat_crop", "farm_and_charm:oat_crop");
        p.add("bakery:oat_seeds", "farm_and_charm:oat_seeds");
        p.add("bakery:oat", "farm_and_charm:oat");
        p.add("bakery:crafting_bowl", "farm_and_charm:crafting_bowl");
        p.add("bakery:small_cooking_pot", "farm_and_charm:small_cooking_pot");
        p.add("bakery:strawberry_crate", "farm_and_charm:strawberry_bag");
        p.add("bakery:oat_block", "farm_and_charm:oat_ball");
        p.add("bakery:oat_crate", "brewery:oat_ball");
        p.add("bakery:brick_stove", "farm_and_charm:stove");
        p.add("bakery:mud_stove", "farm_and_charm:stove");
        p.add("bakery:end_stove", "farm_and_charm:stove");
        p.add("bakery:deepslate_stove", "farm_and_charm:stove");
        p.add("bakery:cobblestone_stove", "farm_and_charm:stove");
        p.add("bakery:stone_bricks_stove", "farm_and_charm:stove");
        p.add("bakery:sandstone_stove", "farm_and_charm:stove");
        p.add("bakery:granite_stove", "farm_and_charm:stove");
        p.add("bakery:red_nether_bricks_stove", "farm_and_charm:stove");
        p.add("bakery:bamboo_stove", "farm_and_charm:stove");
    }
}
