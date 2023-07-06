package satisfy.bakery.util;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import satisfy.bakery.Bakery;

public class LoottableInjector {
    public static void InjectLoot(ResourceLocation id, LootEvent.LootTableModificationContext context) {
        String prefix = "minecraft:chests/";
        String name = id.toString();

        if (name.startsWith(prefix)) {
            String file = name.substring(name.indexOf(prefix) + prefix.length());
            switch (file) {
                case "village_taiga_house", "woodland_mansion", "pillager_outpost",
                        "village_desert_house", "village/plains_house", "village_savanna_house" ->
                        context.addPool(getPool(file));
                default -> {
                }
            }
        }
    }

    public static LootPool getPool(String entryName) {
        return LootPool.lootPool().add(getPoolEntry(entryName)).build();
    }

    @SuppressWarnings("rawtypes")
    private static LootPoolEntryContainer.Builder getPoolEntry(String name) {
        ResourceLocation table = new ResourceLocation(Bakery.MOD_ID, "chests/" + name);
        return LootTableReference.lootTableReference(table);
    }
}

