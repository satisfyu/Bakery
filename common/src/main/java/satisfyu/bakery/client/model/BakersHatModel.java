package satisfyu.bakery.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import satisfyu.bakery.util.BakeryIdentifier;

@Environment(EnvType.CLIENT)
public class BakersHatModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new BakeryIdentifier("bakers_hat"), "main");
    private final ModelPart cookHat;

    public BakersHatModel(ModelPart root) {
        this.cookHat = root.getChild("cooking_hat");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition cookHat = modelPartData.addOrReplaceChild("cooking_hat", CubeListBuilder.create(), PartPose.offset(7.875F, 23.0F, -7.875F));

        cookHat.addOrReplaceChild("top_part", CubeListBuilder.create().texOffs(1, 1).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.875F, 0.0F, 7.875F));

        cookHat.addOrReplaceChild("bottom_part", CubeListBuilder.create().texOffs(1, 21).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(4, 2).addBox(-4.5F, -0.9F, -4.5F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.875F, 0.0F, 7.875F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.cookHat.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}