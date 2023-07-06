package satisfy.bakery.event;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTables;
import satisfy.bakery.util.LoottableInjector;

public class CommonEvents {

    public static void init() {
        LootEvent.MODIFY_LOOT_TABLE.register(CommonEvents::onModifyLootTable);
    }

    public static void onModifyLootTable(LootTables tables, ResourceLocation id, LootEvent.LootTableModificationContext context, boolean builtin) {
        LoottableInjector.InjectLoot(id, context);
    }
}
