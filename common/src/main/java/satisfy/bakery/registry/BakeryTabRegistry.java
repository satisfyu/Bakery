package satisfy.bakery.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import satisfy.bakery.Bakery;

public class BakeryTabRegistry {
    public static final DeferredRegister<CreativeModeTab> BAKERY_TABS = DeferredRegister.create(Bakery.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> BAKERY_TAB = BAKERY_TABS.register("bakery", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ObjectRegistry.BREAD_CRATE.get()))
            .title(Component.translatable("creativetab.bakery.tab"))
            .displayItems((parameters, out) -> {
                out.accept(ObjectRegistry.STRAWBERRY_SEEDS.get());
                out.accept(ObjectRegistry.STRAWBERRY.get());
                out.accept(ObjectRegistry.OAT_SEEDS.get());
                out.accept(ObjectRegistry.OAT.get());
                out.accept(ObjectRegistry.STRAWBERRY_CRATE.get());
                out.accept(ObjectRegistry.OAT_CRATE.get());
                out.accept(ObjectRegistry.OAT_BLOCK.get());
                out.accept(ObjectRegistry.OAT_STAIRS.get());
                out.accept(ObjectRegistry.OAT_SLAB.get());
                out.accept(ObjectRegistry.KITCHEN_SINK.get());
                out.accept(ObjectRegistry.BRICK_STOVE.get());
                out.accept(ObjectRegistry.BAKER_STATION.get());
                out.accept(ObjectRegistry.BRICK_COUNTER.get());
                out.accept(ObjectRegistry.DRAWER.get());
                out.accept(ObjectRegistry.CABINET.get());
                out.accept(ObjectRegistry.WALL_CABINET.get());
                out.accept(ObjectRegistry.IRON_TABLE.get());
                out.accept(ObjectRegistry.IRON_CHAIR.get());
                out.accept(ObjectRegistry.STREET_SIGN.get());
                out.accept(ObjectRegistry.CAKE_STAND.get());
                out.accept(ObjectRegistry.CAKE_DISPLAY.get());
                out.accept(ObjectRegistry.CUPCAKE_DISPLAY.get());
                out.accept(ObjectRegistry.WALL_DISPLAY.get());
                out.accept(ObjectRegistry.BREADBOX.get());
                out.accept(ObjectRegistry.TRAY.get());
                out.accept(ObjectRegistry.BREAD_CRATE.get());
                out.accept(ObjectRegistry.CHOCOLATE_BOX.get());
                out.accept(ObjectRegistry.ROLLING_PIN.get());
                out.accept(ObjectRegistry.BREAD_KNIFE.get());
                out.accept(ObjectRegistry.SMALL_COOKING_POT.get());
                out.accept(ObjectRegistry.JAR.get());
                out.accept(ObjectRegistry.CRAFTING_BOWL.get());
                out.accept(ObjectRegistry.CAKE_DOUGH.get());
                out.accept(ObjectRegistry.SWEET_DOUGH.get());
                out.accept(ObjectRegistry.DOUGH.get());
                out.accept(ObjectRegistry.YEAST.get());
                out.accept(ObjectRegistry.CROISSANT.get());
                out.accept(ObjectRegistry.CRUSTY_BREAD_BLOCK.get());
                out.accept(ObjectRegistry.BREAD_BLOCK.get());
                out.accept(ObjectRegistry.BAGUETTE_BLOCK.get());
                out.accept(ObjectRegistry.TOAST_BLOCK.get());
                out.accept(ObjectRegistry.BRAIDED_BREAD_BLOCK.get());
                out.accept(ObjectRegistry.BUN_BLOCK.get());
                out.accept(ObjectRegistry.VEGETABLE_SANDWICH.get());
                out.accept(ObjectRegistry.SANDWICH.get());
                out.accept(ObjectRegistry.STRAWBERRY_CAKE_SLICE.get());
                out.accept(ObjectRegistry.SWEETBERRY_CAKE_SLICE.get());
                out.accept(ObjectRegistry.CHOCOLATE_CAKE_SLICE.get());
                out.accept(ObjectRegistry.CHERRY_GATEAU_SLICE.get());
                out.accept(ObjectRegistry.BUNDT_CAKE_SLICE.get());
                out.accept(ObjectRegistry.LINZER_TART_SLICE.get());
                out.accept(ObjectRegistry.APPLE_PIE_SLICE.get());
                out.accept(ObjectRegistry.GLOWBERRY_PIE_SLICE.get());
                out.accept(ObjectRegistry.CHOCOLATE_TART_SLICE.get());
                out.accept(ObjectRegistry.STRAWBERRY_CAKE.get());
                out.accept(ObjectRegistry.SWEETBERRY_CAKE.get());
                out.accept(ObjectRegistry.CHOCOLATE_CAKE.get());
                out.accept(ObjectRegistry.CHERRY_GATEAU.get());
                out.accept(ObjectRegistry.BUNDT_CAKE.get());
                out.accept(ObjectRegistry.LINZER_TART.get());
                out.accept(ObjectRegistry.APPLE_PIE.get());
                out.accept(ObjectRegistry.GLOWBERRY_TART.get());
                out.accept(ObjectRegistry.CHOCOLATE_TART.get());
                out.accept(ObjectRegistry.PUDDING_SLICE.get());
                out.accept(ObjectRegistry.STRAWBERRY_GLAZED_COOKIE.get());
                out.accept(ObjectRegistry.SWEETBERRY_GLAZED_COOKIE.get());
                out.accept(ObjectRegistry.CHOCOLATE_GLAZED_COOKIE.get());
                out.accept(ObjectRegistry.STRAWBERRY_CUPCAKE.get());
                out.accept(ObjectRegistry.SWEETBERRY_CUPCAKE.get());
                out.accept(ObjectRegistry.APPLE_CUPCAKE.get());
                out.accept(ObjectRegistry.JAM_ROLL.get());
                out.accept(ObjectRegistry.CORNET.get());
                out.accept(ObjectRegistry.PUDDING.get());
                out.accept(ObjectRegistry.WAFFLE_BLOCK.get());
                out.accept(ObjectRegistry.MISSLILITU_BISCUIT.get());
                out.accept(ObjectRegistry.CHOCOLATE_TRUFFLE.get());
                out.accept(ObjectRegistry.STRAWBERRY_JAM.get());
                out.accept(ObjectRegistry.SWEETBERRY_JAM.get());
                out.accept(ObjectRegistry.GLOWBERRY_JAM.get());
                out.accept(ObjectRegistry.APPLE_JAM.get());
                out.accept(ObjectRegistry.CHOCOLATE_JAM.get());
                out.accept(ObjectRegistry.STONE_BRICKS_STOVE.get());
                out.accept(ObjectRegistry.COBBLESTONE_STOVE.get());
                out.accept(ObjectRegistry.DEEPSLATE_STOVE.get());
                out.accept(ObjectRegistry.GRANITE_STOVE.get());
                out.accept(ObjectRegistry.MUD_STOVE.get());
                out.accept(ObjectRegistry.SANDSTONE_STOVE.get());
                out.accept(ObjectRegistry.END_STOVE.get());
                out.accept(ObjectRegistry.RED_NETHER_BRICKS_STOVE.get());
                out.accept(ObjectRegistry.QUARTZ_STOVE.get());
                out.accept(ObjectRegistry.BAKERY_STANDARD.get());

            })
            .build());

    public static void init() {
        BAKERY_TABS.register();
    }
}
