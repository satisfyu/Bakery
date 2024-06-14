package net.satisfy.bakery.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.bakery.client.model.WanderingBakerModel;
import net.satisfy.bakery.entity.WanderingBakerEntity;
import net.satisfy.bakery.util.BakeryIdentifier;
import org.jetbrains.annotations.NotNull;

@Environment(value = EnvType.CLIENT)
public class WanderingBakerRenderer<T extends WanderingBakerEntity> extends MobRenderer<T, WanderingBakerModel<T>> {
    private static final ResourceLocation TEXTURE = new BakeryIdentifier("textures/entity/wandering_baker.png");

    public WanderingBakerRenderer(EntityRendererProvider.Context context) {
        super(context, new WanderingBakerModel<>(context.bakeLayer(WanderingBakerModel.LAYER_LOCATION)), 0.7f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(WanderingBakerEntity entity) {
        return TEXTURE;
    }

}
