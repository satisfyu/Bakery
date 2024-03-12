package satisfy.bakery.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import satisfy.bakery.client.gui.CookingPotGui;
import satisfy.bakery.client.gui.StoveGui;
import satisfy.bakery.client.model.WanderingBakerModel;
import satisfy.bakery.client.render.block.StoveBlockRenderer;
import satisfy.bakery.client.render.entity.WanderingBakerRenderer;
import satisfy.bakery.registry.BlockEntityRegistry;
import satisfy.bakery.registry.EntityRegistry;
import satisfy.bakery.registry.ScreenHandlerTypeRegistry;

import static satisfy.bakery.registry.ObjectRegistry.*;

@Environment(EnvType.CLIENT)
public class BakeryClient {

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                CAKE_STAND.get(), IRON_TABLE.get(), IRON_CHAIR.get(), JAR.get(), SWEETBERRY_JAM.get(), CHOCOLATE_JAM.get(),
                STRAWBERRY_JAM.get(), GLOWBERRY_JAM.get(), APPLE_JAM.get(), OAT_CROP.get(), STRAWBERRY_CROP.get(), WILD_STRAWBERRIES.get(),
                CAKE_DISPLAY.get(), CRAFTING_BOWL.get(), BRICK_STOVE.get(), DEEPSLATE_STOVE.get(), MUD_STOVE.get(), GRANITE_STOVE.get(),
                QUARTZ_STOVE.get(), RED_NETHER_BRICKS_STOVE.get(), END_STOVE.get(), SANDSTONE_STOVE.get(), COBBLESTONE_STOVE.get(),
                STONE_BRICKS_STOVE.get(), BAKER_STATION.get()
        );


        ClientStorageTypes.init();
        RenderTypeRegistry.register(RenderType.translucent(), TRAY.get());
        RenderTypeRegistry.register(RenderType.translucent(), CAKE_STAND.get());
        RenderTypeRegistry.register(RenderType.translucent(), CAKE_DISPLAY.get());
        RenderTypeRegistry.register(RenderType.translucent(), CUPCAKE_DISPLAY.get());
        RenderTypeRegistry.register(RenderType.translucent(), WALL_DISPLAY.get());
        BlockEntityRendererRegistry.register(BlockEntityRegistry.STOVE_BLOCK_ENTITY.get(), StoveBlockRenderer::new);

        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.STOVE_SCREEN_HANDLER.get(), StoveGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypeRegistry.COOKING_POT_SCREEN_HANDLER.get(), CookingPotGui::new);

    }


    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayer();
    }

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityRegistry.WANDERING_BAKER, WanderingBakerRenderer::new);
    }

    public static void registerEntityModelLayer() {
        EntityModelLayerRegistry.register(WanderingBakerModel.LAYER_LOCATION, WanderingBakerModel::getTexturedModelData);

    }
}
