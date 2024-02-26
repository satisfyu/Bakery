package satisfy.bakery.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.cristelknight.doapi.client.render.block.storage.StorageTypeRenderer;
import de.cristelknight.doapi.common.block.entity.StorageBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import satisfy.bakery.util.ClientUtil;

@Environment(EnvType.CLIENT)
public class WallDisplayRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, NonNullList<ItemStack> itemStacks) {
        int invSize = 4;

        for (int i = 0; i < invSize; i++) {
            ItemStack stack = itemStacks.get(i);
            if (stack.getItem() instanceof BlockItem blockItem) {
                matrices.pushPose();
                matrices.scale(0.6f, 0.6f, 0.6f);

                boolean firstColumn = i < 2;
                int x = i % 2;

                float xOffset = (x - 0.5f) * 1.85f;
                float yOffset;
                if (i < 2) {
                    yOffset = -0.16f;
                    xOffset -= -0.1f;
                } else {
                    yOffset = -0.8f;
                    xOffset -= -2.5f;
                }
                float zOffset;
                if (i < 2) {
                    zOffset = -0.1f;
                } else {
                    zOffset = -0.1f;
                }

                matrices.translate(xOffset, yOffset, zOffset);

                matrices.mulPose(Axis.YN.rotationDegrees(0f));
                matrices.translate(-1.2f * i, 1, 0);
                ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                matrices.popPose();
            }
        }
    }
}
