package satisfy.bakery.registry;

import de.cristelknight.doapi.common.block.entity.CabinetBlockEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import satisfy.bakery.Bakery;
import satisfy.bakery.entity.SmallCookingPotBlockEntity;
import satisfy.bakery.entity.WanderingBakerEntity;
import satisfy.bakery.util.BakeryIdentifier;
import satisfy.farm_and_charm.FarmAndCharm;
import satisfy.farm_and_charm.entity.StoveBlockEntity;

import java.util.function.Supplier;

public enum EntityTypeRegistry {
    ;

    private static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(FarmAndCharm.MOD_ID, Registries.BLOCK_ENTITY_TYPE).getRegistrar();
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(FarmAndCharm.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<SmallCookingPotBlockEntity>> SMALL_COOKING_POT_BLOCK_ENTITY = registerBlockEntity("small_cooking_pot", () -> BlockEntityType.Builder.of(SmallCookingPotBlockEntity::new, ObjectRegistry.SMALL_COOKING_POT.get()).build(null));

    public static final RegistrySupplier<EntityType<WanderingBakerEntity>> WANDERING_BAKER = registerEntityType(
            () -> EntityType.Builder.of(WanderingBakerEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.95f)
                    .clientTrackingRange(10)
                    .build(new BakeryIdentifier("wandering_baker").toString()));



    private static <T extends BlockEntityType<?>> RegistrySupplier<T> registerBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(new BakeryIdentifier(path), type);
    }

    private static <T extends EntityType<?>> RegistrySupplier<T> registerEntityType(final Supplier<T> type) {
        return ENTITY_TYPES.register(new BakeryIdentifier("wandering_baker"), type);
    }

    public static void init() {
        ENTITY_TYPES.register();
        Bakery.LOGGER.debug("Registering Mod Entities and Block Entities for " + Bakery.MOD_ID);
    }
}
