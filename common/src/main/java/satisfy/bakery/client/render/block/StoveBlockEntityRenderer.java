package satisfy.bakery.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
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
        Vec3 offset = new Vec3(0.5, 1.0, 0.5).add(Vec3.atLowerCornerOf(direction.getNormal()).scale(0.3));
        for (int slot : blockEntity.getIngredientSlots()) {
            ItemStack stack = blockEntity.getItem(slot);
            if (!stack.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(offset.x, offset.y, offset.z);
                poseStack.scale(0.5f, 0.5f, 0.5f);
                ClientUtil.renderItem(stack, poseStack, bufferSource, blockEntity);
                poseStack.popPose();
                offset = offset.add(0, -0.25, 0);
            }
        }

        ItemStack outputStack = blockEntity.getItem(blockEntity.getOutputSlot());
        if (!outputStack.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.5, 1.25, 0.5);
            poseStack.scale(0.5f, 0.5f, 0.5f);
            ClientUtil.renderItem(outputStack, poseStack, bufferSource, blockEntity);
            poseStack.popPose();
        }
    }
}