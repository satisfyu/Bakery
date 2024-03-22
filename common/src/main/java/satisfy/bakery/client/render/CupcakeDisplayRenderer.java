package satisfy.bakery.client.render;

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
public class CupcakeDisplayRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, NonNullList<ItemStack> itemStacks) {
        for (int i = 0; i < itemStacks.size(); i++) {
            ItemStack stack = itemStacks.get(i);
            if (stack.isEmpty() || stack.getItem() instanceof BlockItem) continue;

            matrices.pushPose();
            matrices.scale(0.3f, 0.4f, 0.3f);

            float xOffset = (i % 3) * 2.1f;
            float yOffset = i < 3 ? 1.3f : 0.35f;
            float zOffset = -1f;

            xOffset += i < 3 ? -0.9f : 2.7f;

            matrices.translate(xOffset, yOffset, zOffset);
            matrices.mulPose(Axis.XP.rotationDegrees(90f));
            matrices.translate(-1.2f * i, 1, 0);

            ClientUtil.renderItem(stack, matrices, vertexConsumers, entity);
            matrices.popPose();
        }
    }
}
