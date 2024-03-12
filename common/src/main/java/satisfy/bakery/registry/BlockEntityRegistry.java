package satisfy.bakery.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import satisfy.bakery.Bakery;
import satisfy.bakery.entity.StorageBlockEntity;
import satisfy.bakery.entity.CookingPotBlockEntity;
import satisfy.bakery.entity.StoveBlockEntity;

import java.util.function.Supplier;

public class BlockEntityRegistry {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Bakery.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<StorageBlockEntity>> CABINET_BLOCK_ENTITY = create("cabinet", () -> BlockEntityType.Builder.of(StorageBlockEntity::new, ObjectRegistry.CABINET.get(), ObjectRegistry.DRAWER.get(), ObjectRegistry.WALL_CABINET.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<StoveBlockEntity>> STOVE_BLOCK_ENTITY
            = create("stove_block", () -> BlockEntityType.Builder.of(StoveBlockEntity::new,
            ObjectRegistry.BRICK_STOVE.get(), ObjectRegistry.COBBLESTONE_STOVE.get(), ObjectRegistry.MUD_STOVE.get(), ObjectRegistry.GRANITE_STOVE.get(),
            ObjectRegistry.SANDSTONE_STOVE.get(), ObjectRegistry.STONE_BRICKS_STOVE.get(), ObjectRegistry.RED_NETHER_BRICKS_STOVE.get(),
            ObjectRegistry.DEEPSLATE_STOVE.get(), ObjectRegistry.QUARTZ_STOVE.get(), ObjectRegistry.END_STOVE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CookingPotBlockEntity>> COOKING_POT_BLOCK_ENTITY = create("cooking_pot", () -> BlockEntityType.Builder.of(CookingPotBlockEntity::new, ObjectRegistry.SMALL_COOKING_POT.get()).build(null));


    private static <T extends BlockEntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(path, type);
    }

    public static void init() {
        Bakery.LOGGER.debug("Registering Mod BlockEntities for " + Bakery.MOD_ID);
        BLOCK_ENTITY_TYPES.register();
    }

}
