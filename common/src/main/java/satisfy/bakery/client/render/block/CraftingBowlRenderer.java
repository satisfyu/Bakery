package satisfy.bakery.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import satisfy.bakery.block.CraftingBowlBlock;
import satisfy.bakery.entity.CraftingBowlBlockEntity;
import satisfy.bakery.util.BakeryIdentifier;
import satisfy.bakery.util.ClientUtil;

import java.util.List;
import java.util.Random;

public class CraftingBowlRenderer implements BlockEntityRenderer<CraftingBowlBlockEntity> {
    private static final ResourceLocation TEXTURE = new BakeryIdentifier("textures/entity/crafting_bowl.png");
    private final ModelPart bowl;
    private final ModelPart swing;

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new BakeryIdentifier("crafting_bowl"), "main");

    public CraftingBowlRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart modelPart = context.bakeLayer(LAYER_LOCATION);
        this.bowl = modelPart.getChild("bowl");
        this.swing = modelPart.getChild("swing");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bowl = partdefinition.addOrReplaceChild("bowl", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -8.05F, 3.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -3.0F, 12.0F, -8.0F, -5.0F, -8.0F, new CubeDeformation(0.0F))
                .texOffs(30, 0).addBox(-13.0F, -7.0F, 3.0F, 10.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        PartDefinition swing = partdefinition.addOrReplaceChild("swing", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 21.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition swing_r1 = swing.addOrReplaceChild("swing_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.2556F, 0.5F, 1.125F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, -5).addBox(1.3444F, 0.5F, -1.375F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(10, 18).addBox(0.8444F, -5.5F, 0.625F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5444F, -2.8143F, -1.125F, 0.0F, 0.0F, -0.7854F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }

    @Override
    public void render(CraftingBowlBlockEntity entity, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        if (!entity.hasLevel() || !(entity.getBlockState().getBlock() instanceof CraftingBowlBlock)) return;

        List<ItemStack> ingredients = entity.getItems();
        if (ingredients.isEmpty()) return;

        matrixStack.pushPose();
        setupInitialTransform(matrixStack, entity);

        Random random = new Random(entity.getBlockPos().hashCode());
        float angleOffset = 360f / ingredients.size();

        for (int index = 0; index < ingredients.size(); index++) {
            ItemStack stack = ingredients.get(index);
            matrixStack.pushPose();
            Vector3f position = calculateItemPosition(index, angleOffset, ingredients.size());
            applyItemTransform(matrixStack, position, angleOffset * index);
            renderItems(matrixStack, bufferSource, entity, stack, random);
            matrixStack.popPose();
        }

        matrixStack.popPose();
    }

    private void setupInitialTransform(PoseStack matrixStack, CraftingBowlBlockEntity entity) {
        matrixStack.scale(0.45F, 0.45F, 0.45F);
        matrixStack.translate(1.0f, 0.3F, 1.0f);
    }

    private Vector3f calculateItemPosition(int index, float angleOffset, int itemCount) {
        if (itemCount == 1) return new Vector3f(0, 0.3f, 0);
        double angleRad = Math.toRadians(angleOffset * index);
        return new Vector3f((float) (0.125 * Math.cos(angleRad)), 0.3f, (float) (0.125 * Math.sin(angleRad)));
    }

    private void applyItemTransform(PoseStack matrixStack, Vector3f position, float angle) {
        Quaternionf rotation = new Quaternionf().rotateY(angle + 35).rotateX(65);
        matrixStack.translate(position.x, position.y, position.z);
        matrixStack.mulPose(rotation);
    }

    private void renderItems(PoseStack matrixStack, MultiBufferSource bufferSource, CraftingBowlBlockEntity entity, ItemStack stack, Random random) {
        for (int i = 0; i <= stack.getCount() / 8; i++) {
            matrixStack.pushPose();
            Vector3f offset = offsetRandomly(random, 1 / 16f);
            matrixStack.translate(offset.x, offset.y, offset.z);
            ClientUtil.renderItem(stack, matrixStack, bufferSource, entity);
            matrixStack.popPose();
        }
    }

    private Vector3f offsetRandomly(Random random, float radius) {
        return new Vector3f((random.nextFloat() - 0.5f) * 2 * radius, (random.nextFloat() - 0.5f) * 2 * radius, (random.nextFloat() - 0.5f) * 2 * radius);
    }
}