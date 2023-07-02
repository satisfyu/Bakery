package satisfyu.bakery.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.MenuType;
import satisfyu.bakery.Bakery;
import satisfyu.bakery.client.gui.handler.BakerStationGuiHandler;
import satisfyu.bakery.client.gui.handler.CookingPotGuiHandler;
import satisfyu.bakery.client.gui.handler.StoveGuiHandler;

import java.util.function.Supplier;


public class ScreenHandlerTypeRegistry {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Bakery.MOD_ID, Registry.MENU_REGISTRY);

    public static final RegistrySupplier<MenuType<StoveGuiHandler>> STOVE_SCREEN_HANDLER = create("stove_gui_handler", () -> new MenuType<>(StoveGuiHandler::new));
    public static final RegistrySupplier<MenuType<CookingPotGuiHandler>> COOKING_POT_SCREEN_HANDLER = create("cooking_pot_gui_handler", () -> new MenuType<>(CookingPotGuiHandler::new));
    public static final RegistrySupplier<MenuType<BakerStationGuiHandler>> BAKER_STATION_SCREEN_HANDLER = create("baker_station_gui_handler", () -> new MenuType<>(BakerStationGuiHandler::new));


    public static void init() {
        MENU_TYPES.register();
    }

    private static <T extends MenuType<?>> RegistrySupplier<T> create(String name, Supplier<T> type) {
        return MENU_TYPES.register(name, type);
    }
}
