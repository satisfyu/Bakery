package satisfy.bakery.client.model;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.npc.WanderingTrader;
import satisfy.bakery.util.BakeryIdentifier;

@Environment(EnvType.CLIENT)
public class WanderingBakerModel<T extends WanderingTrader> extends HumanoidModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new BakeryIdentifier("wandering_baker"), "main");
    
    public WanderingBakerModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, -23.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(1.0F))
                .texOffs(16, 20).addBox(-4.0F, -23.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(1.0F))
                .texOffs(0, 38).addBox(-4.0F, -23.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(1.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition brim = head.addOrReplaceChild("brim", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition arms_r1 = arms.addOrReplaceChild("arms_r1", CubeListBuilder.create().texOffs(32, 46).mirror().addBox(6.0F, -12.0F, -16.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, -1.2654F, 0.0F, 0.0F));

        PartDefinition arms_r2 = arms.addOrReplaceChild("arms_r2", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(20.0F, -7.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

        PartDefinition arms_r3 = arms.addOrReplaceChild("arms_r3", CubeListBuilder.create().texOffs(44, 22).addBox(-9.0F, -19.0F, 12.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 0.6545F, 0.0F, 0.0F));

        PartDefinition RightLeg = body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

        PartDefinition LeftLeg = body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(2.0F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}