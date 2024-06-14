package net.satisfy.bakery.client;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.satisfy.bakery.client.model.WanderingBakerModel;
import net.satisfy.bakery.registry.ObjectRegistry;
import net.satisfy.bakery.client.render.WanderingBakerRenderer;
import net.satisfy.bakery.registry.EntityTypeRegistry;

@Environment(EnvType.CLIENT)
public class BakeryClient {

    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(),
                ObjectRegistry.CAKE_STAND.get(), ObjectRegistry.IRON_TABLE.get(), ObjectRegistry.IRON_CHAIR.get(), ObjectRegistry.JAR.get(), ObjectRegistry.SWEETBERRY_JAM.get(), ObjectRegistry.CHOCOLATE_JAM.get(),
                ObjectRegistry.STRAWBERRY_JAM.get(), ObjectRegistry.GLOWBERRY_JAM.get(), ObjectRegistry.APPLE_JAM.get(), ObjectRegistry.CAKE_DISPLAY.get(), ObjectRegistry.SMALL_COOKING_POT.get(),
                ObjectRegistry.IRON_BENCH.get(), ObjectRegistry.BAKER_STATION.get(), ObjectRegistry.TRAY.get()
        );

        ClientStorageTypes.init();
        RenderTypeRegistry.register(RenderType.translucent(), ObjectRegistry.CAKE_STAND.get());
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
