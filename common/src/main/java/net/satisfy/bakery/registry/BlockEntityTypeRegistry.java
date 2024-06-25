package net.satisfy.bakery.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.satisfy.bakery.Bakery;
import net.satisfy.bakery.block.entity.CookingPotBlockEntity;
import net.satisfy.bakery.block.entity.CraftingBowlBlockEntity;
import net.satisfy.bakery.block.entity.StoveBlockEntity;

import java.util.function.Supplier;

public class BlockEntityTypeRegistry {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Bakery.MOD_ID, Registries.BLOCK_ENTITY_TYPE);
    public static final RegistrySupplier<BlockEntityType<StoveBlockEntity>> STOVE_BLOCK_ENTITY = create("stove_block", () -> BlockEntityType.Builder.of(StoveBlockEntity::new, ObjectRegistry.BRICK_STOVE.get(), ObjectRegistry.COBBLESTONE_STOVE.get(), ObjectRegistry.MUD_STOVE.get(), ObjectRegistry.GRANITE_STOVE.get(), ObjectRegistry.SANDSTONE_STOVE.get(), ObjectRegistry.STONE_BRICKS_STOVE.get(), ObjectRegistry.RED_NETHER_BRICKS_STOVE.get(), ObjectRegistry.DEEPSLATE_STOVE.get(), ObjectRegistry.QUARTZ_STOVE.get(), ObjectRegistry.END_STOVE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CookingPotBlockEntity>> COOKING_POT_BLOCK_ENTITY = create("cooking_pot", () -> BlockEntityType.Builder.of(CookingPotBlockEntity::new, ObjectRegistry.SMALL_COOKING_POT.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CraftingBowlBlockEntity>> CRAFTING_BOWL_BLOCK_ENTITY = create("crafting_bowl", () -> BlockEntityType.Builder.of(CraftingBowlBlockEntity::new, ObjectRegistry.CRAFTING_BOWL.get()).build(null));

    private static <T extends BlockEntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(path, type);
    }

    public static void init() {
        Bakery.LOGGER.debug("Registering Mod BlockEntities for " + Bakery.MOD_ID);
        BLOCK_ENTITY_TYPES.register();
    }

}
