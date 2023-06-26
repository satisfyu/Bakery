package satisfyu.bakery.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import satisfyu.bakery.client.gui.CookingPotGui;
import satisfyu.bakery.client.gui.StoveGui;
import satisfyu.bakery.client.render.block.ChairRenderer;
import satisfyu.bakery.registry.EntityRegistry;
import satisfyu.bakery.registry.ScreenHandlerTypeRegistry;

import static satisfyu.bakery.registry.ObjectRegistry.*;

@Environment(EnvType.CLIENT)
public class BakeryClient {

    public static boolean rememberedRecipeBookOpen;
    public static boolean rememberedCraftableToggle = true;

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                CAKE_STAND.get(), IRON_TABLE.get(), IRON_CHAIR.get()
        );

        ClientStorageTypes.init();
        RenderTypeRegistry.register(RenderType.translucent(), TRAY.get());
        RenderTypeRegistry.register(RenderType.translucent(), SHELF.get());
        RenderTypeRegistry.register(RenderType.translucent(), CAKE_STAND.get());

        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.STOVE_SCREEN_HANDLER.get(), StoveGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.COOKING_POT_SCREEN_HANDLER.get(), CookingPotGui::new);
    }

    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayer();
    }

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityRegistry.CHAIR, ChairRenderer::new);
    }

    public static void registerEntityModelLayer() {
    }
}
