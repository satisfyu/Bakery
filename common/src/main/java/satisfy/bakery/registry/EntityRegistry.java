package satisfy.bakery.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import satisfy.bakery.Bakery;
import satisfy.bakery.util.BakeryIdentifier;

import java.util.function.Supplier;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Bakery.MOD_ID, Registry.ENTITY_TYPE_REGISTRY);

    public static <T extends EntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(new BakeryIdentifier(path), type);
    }

    public static void init() {
        Bakery.LOGGER.debug("Registering Entities for " + Bakery.MOD_ID);
        ENTITY_TYPES.register();
    }
}
