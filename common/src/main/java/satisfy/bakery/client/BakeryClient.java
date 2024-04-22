package satisfy.bakery.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import satisfy.bakery.client.model.WanderingBakerModel;
import satisfy.bakery.client.render.WanderingBakerRenderer;
import satisfy.bakery.registry.EntityTypeRegistry;

import static satisfy.bakery.registry.ObjectRegistry.*;

@Environment(EnvType.CLIENT)
public class BakeryClient {

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                CAKE_STAND.get(), IRON_TABLE.get(), IRON_CHAIR.get(), JAR.get(), SWEETBERRY_JAM.get(), CHOCOLATE_JAM.get(),
                STRAWBERRY_JAM.get(), GLOWBERRY_JAM.get(), APPLE_JAM.get(), CAKE_DISPLAY.get(), SMALL_COOKING_POT.get(),
                IRON_BENCH.get(), BAKER_STATION.get()
        );

        ClientStorageTypes.init();
        RenderTypeRegistry.register(RenderType.translucent(), TRAY.get());
        RenderTypeRegistry.register(RenderType.translucent(), CAKE_STAND.get());
        RenderTypeRegistry.register(RenderType.translucent(), CAKE_DISPLAY.get());
        RenderTypeRegistry.register(RenderType.translucent(), CUPCAKE_DISPLAY.get());
        RenderTypeRegistry.register(RenderType.translucent(), WALL_DISPLAY.get());
    }

    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayer();
    }

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityTypeRegistry.WANDERING_BAKER, WanderingBakerRenderer::new);
    }

    public static void registerEntityModelLayer() {
        EntityModelLayerRegistry.register(WanderingBakerModel.LAYER_LOCATION, WanderingBakerModel::getTexturedModelData);
    }
}
