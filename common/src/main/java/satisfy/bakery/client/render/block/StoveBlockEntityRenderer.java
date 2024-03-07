package satisfy.bakery.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import satisfy.bakery.block.StoveBlock;
import satisfy.bakery.entity.StoveBlockEntity;
import satisfy.bakery.util.ClientUtil;

public class StoveBlockEntityRenderer implements BlockEntityRenderer<StoveBlockEntity> {
    public StoveBlockEntityRenderer(Context context) {
    }

    @Override
    public void render(StoveBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction direction = blockEntity.getBlockState().getValue(StoveBlock.FACING);
        Vec3 baseOffset = new Vec3(0.5, 1.0, 0.5);
        Vec3 directionOffset = Vec3.atLowerCornerOf(direction.getNormal()).scale(0.3);
        // Anpassung um 2 Pixel nach rechts
        double rightOffset = 2 / 16.0; // Umrechnung von Pixeln in Minecraft-Einheiten
        double downOffset = (-7 / 16.0) - (1.2 / 16.0); // Bereits angepasster Wert nach unten

        Vec3 inputSlotOffset = new Vec3(0, 0, 0);
        switch (direction) {
            case NORTH -> inputSlotOffset = new Vec3(rightOffset, downOffset, 0.15); // Anpassung für Rechtsverschiebung
            case SOUTH -> inputSlotOffset = new Vec3(-rightOffset, downOffset, -0.15); // Spiegelung für SOUTH
            case EAST -> inputSlotOffset = new Vec3(0.15, downOffset, rightOffset); // Korrektur für EAST
            case WEST -> inputSlotOffset = new Vec3(-0.15, downOffset, -rightOffset); // Korrektur für WEST
        }

        double ySpacing = 0.02; // Beibehalten des engeren Abstands auf der Y-Achse

        int slotIndex = 0;
        for (int slot : blockEntity.getIngredientSlots()) {
            ItemStack stack = blockEntity.getItem(slot);
            if (!stack.isEmpty()) {
                poseStack.pushPose();
                double yOffset = baseOffset.y + downOffset - ySpacing * slotIndex; // Anpassung für den engeren Abstand auf der Y-Achse
                poseStack.translate(baseOffset.x + directionOffset.x + inputSlotOffset.x, yOffset, baseOffset.z + directionOffset.z + inputSlotOffset.z);

                float rotationAngle = 45f * slotIndex; // Rotation beibehalten
                poseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));

                poseStack.mulPose(Axis.XP.rotationDegrees(90f));
                poseStack.scale(0.3f, 0.3f, 0.3f);
                ClientUtil.renderItem(stack, poseStack, bufferSource, blockEntity);
                poseStack.popPose();

                slotIndex++;
            }
        }

        ItemStack outputStack = blockEntity.getItem(blockEntity.getOutputSlot());
        if (!outputStack.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.7, 0.45, 0.65);
            poseStack.mulPose(Axis.XP.rotationDegrees(90f));
            poseStack.scale(0.3f, 0.3f, 0.3f);
            ClientUtil.renderItem(outputStack, poseStack, bufferSource, blockEntity);
            poseStack.popPose();
        }
    }
}
