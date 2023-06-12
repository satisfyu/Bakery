package satisfyu.bakery.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import satisfyu.bakery.Bakery;
import satisfyu.bakery.BakeryIdentifier;
import satisfyu.bakery.block.entity.ChairEntity;


import java.util.function.Supplier;

public class EntitiesRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Bakery.MOD_ID, Registry.ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<EntityType<ChairEntity>> CHAIR = create("chair", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC).sized(0.001F, 0.001F).build(new BakeryIdentifier("chair").toString()));

    public static <T extends EntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(new BakeryIdentifier(path), type);
    }

    public static void init() {
        Bakery.LOGGER.debug("Registering Entities for " + Bakery.MOD_ID);
        ENTITY_TYPES.register();
    }
}
