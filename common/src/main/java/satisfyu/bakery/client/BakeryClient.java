package satisfyu.bakery.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import satisfyu.bakery.client.gui.CookingPotGui;
import satisfyu.bakery.client.gui.StoveGui;
import satisfyu.bakery.client.render.block.ChairRenderer;
import satisfyu.bakery.registry.ArmorRegistry;
import satisfyu.bakery.registry.EntitiesRegistry;
import satisfyu.bakery.registry.ScreenHandlerTypeRegistry;

import static satisfyu.bakery.registry.ObjectRegistry.CAKE_STAND;

@Environment(EnvType.CLIENT)
public class BakeryClient {

    public static boolean rememberedRecipeBookOpen;
    public static boolean rememberedCraftableToggle = true;

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                CAKE_STAND.get()
        );

        ClientStorageTypes.init();

        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.STOVE_SCREEN_HANDLER.get(), StoveGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.COOKING_POT_SCREEN_HANDLER.get(), CookingPotGui::new);
    }

    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayer();
    }

    public static void registerEntityRenderers() {
       EntityRendererRegistry.register(EntitiesRegistry.CHAIR, ChairRenderer::new);
    }

    public static void registerEntityModelLayer() {
        ArmorRegistry.registerArmorModelLayers();
    }
}
