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

/**
 *  Glad you've found this class! Please back out asap because thats the worst thing you'll ever see
 */
public class StoveBlockRenderer implements BlockEntityRenderer<StoveBlockEntity> {
    public StoveBlockRenderer(Context context) {
    }

    @Override
    public void render(StoveBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction direction = blockEntity.getBlockState().getValue(StoveBlock.FACING);
        Vec3 baseOffset = new Vec3(0.5, 1.0, 0.5);
        Vec3 directionOffset = Vec3.atLowerCornerOf(direction.getNormal()).scale(0.3);
        double rightOffset = 2 / 16.0;
        double downOffset = (-7 / 16.0) - (1.2 / 16.0) + (1 / 16.0);

        double inputSlotOffsetX = 0;
        double inputSlotOffsetY = 0;
        double inputSlotOffsetZ = 0;
        if (direction == Direction.NORTH) {
            inputSlotOffsetX = rightOffset;
            inputSlotOffsetY = downOffset;
            inputSlotOffsetZ = 0.15;
        } else if (direction == Direction.SOUTH) {
            inputSlotOffsetX = -rightOffset;
            inputSlotOffsetY = downOffset;
            inputSlotOffsetZ = -0.15;
        } else if (direction == Direction.EAST) {
            inputSlotOffsetX = -0.15;
            inputSlotOffsetY = downOffset;
            inputSlotOffsetZ = rightOffset;
        } else if (direction == Direction.WEST) {
            inputSlotOffsetX = 0.15;
            inputSlotOffsetY = downOffset;
            inputSlotOffsetZ = -rightOffset;
        }

        double ySpacing = 0.02;

        int slotIndex = 0;
        for (int slot : blockEntity.getIngredientSlots()) {
            ItemStack stack = blockEntity.getItem(slot);
            if (!stack.isEmpty()) {
                poseStack.pushPose();
                double yOffset = baseOffset.y + downOffset - ySpacing * slotIndex;
                poseStack.translate(baseOffset.x + directionOffset.x + inputSlotOffsetX, yOffset, baseOffset.z + directionOffset.z + inputSlotOffsetZ);

                float rotationAngle = 45f * slotIndex;
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
            double outputSlotOffsetX = 0;
            double outputSlotOffsetY = 0;
            double outputSlotOffsetZ = 0;

            if (direction == Direction.NORTH) {
                outputSlotOffsetX = -0.15;
                outputSlotOffsetY = -0.49;
                outputSlotOffsetZ = 0.15;
            } else if (direction == Direction.SOUTH) {
                outputSlotOffsetX = 0.15;
                outputSlotOffsetY = -0.49;
                outputSlotOffsetZ = -0.15;
            } else if (direction == Direction.EAST) {
                outputSlotOffsetX = -0.15;
                outputSlotOffsetY = -0.49;
                outputSlotOffsetZ = -0.15;
            } else if (direction == Direction.WEST) {
                outputSlotOffsetX = 0.15;
                outputSlotOffsetY = -0.49;
                outputSlotOffsetZ = 0.15;
            }

            poseStack.pushPose();
            poseStack.translate(baseOffset.x + directionOffset.x + outputSlotOffsetX, baseOffset.y + directionOffset.y + outputSlotOffsetY, baseOffset.z + directionOffset.z + outputSlotOffsetZ);
            poseStack.mulPose(Axis.XP.rotationDegrees(90f));
            poseStack.scale(0.3f, 0.3f, 0.3f);
            ClientUtil.renderItem(outputStack, poseStack, bufferSource, blockEntity);
            poseStack.popPose();
        }
    }
}
