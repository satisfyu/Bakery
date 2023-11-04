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
import satisfy.bakery.client.ClientUtil;

@Environment(EnvType.CLIENT)
public class CupcakeDisplayRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, NonNullList<ItemStack> itemStacks) {
        int invSize = 6;

        for (int i = 0; i < invSize; i++) {
            ItemStack stack = itemStacks.get(i);
            if (!stack.isEmpty() && !(stack.getItem() instanceof BlockItem)) {
                matrices.pushPose();
                matrices.scale(0.35f, 0.4f, 0.35f);

                boolean firstColumn = i < invSize / 2;
                boolean secondColumn = !firstColumn;
                int x = i - 1;

                float firstColumnOffset = x * 2.1f;
                float secondColumnOffset = x * 2.1f;
                float yOffset;

                if (firstColumn) {
                    yOffset = 1.3f;
                    firstColumnOffset -= -1.2f;
                } else {
                    yOffset = 0.35f; //
                    secondColumnOffset -= 1.5f;
                }

                float zOffset = -1f;
                float xOffset = firstColumn ? firstColumnOffset : secondColumnOffset;

                matrices.translate(xOffset, yOffset, zOffset);

                matrices.mulPose(Axis.XP.rotationDegrees(90f));
                matrices.translate(-1.2f * i, 1, 0);
                ClientUtil.renderItem(stack, matrices, vertexConsumers, entity);
                matrices.popPose();
            }
        }
    }
}