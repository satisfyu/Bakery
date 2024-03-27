package satisfy.bakery.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import satisfy.bakery.util.ClientUtil;
import de.cristelknight.doapi.client.render.block.storage.StorageTypeRenderer;
import de.cristelknight.doapi.common.block.entity.StorageBlockEntity;

public class WallDisplayRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack poseStack, MultiBufferSource buffer, NonNullList<ItemStack> items) {
        for (int i = 0; i < Math.min(items.size(), 4); i++) {
            ItemStack stack = items.get(i);
            if (stack.getItem() == Items.BREAD) {
                poseStack.pushPose();

                float scale = 0.4f;
                poseStack.scale(scale, scale, scale);

                float xBaseOffset = -0.5f;
                float yBaseOffset = i < 2 ? 0.82f : 0.16f;
                float zBaseOffset = 0.5f;

                float xSpacing = 1f;
                float ySpacing = -1.75f;
                float xOffset = xBaseOffset + (i % 2) * xSpacing;
                float yOffset = yBaseOffset - (i / 2) * ySpacing;

                poseStack.translate(xOffset, yOffset, zBaseOffset);

                ClientUtil.renderItem(stack, poseStack, buffer, entity);

                poseStack.popPose();
                continue;
            }
            if (!(stack.getItem() instanceof BlockItem)) continue;

            poseStack.pushPose();

            float scale = 0.75f;
            poseStack.scale(scale, scale, scale);

            float xBaseOffset = -0.75f;
            float yBaseOffset = i < 2 ? 0.75f : 0.16f;
            float zBaseOffset = -0.19f;

            float xSpacing = 0.52f;
            float xOffset = xBaseOffset + (i % 2) * xSpacing;

            poseStack.translate(xOffset, yBaseOffset, zBaseOffset);

            ClientUtil.renderBlockFromItem((BlockItem)stack.getItem(), poseStack, buffer, entity);

            poseStack.popPose();
        }
    }
}
