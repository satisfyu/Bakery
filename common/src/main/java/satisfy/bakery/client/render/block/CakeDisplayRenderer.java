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
public class CakeDisplayRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, NonNullList<ItemStack> itemStacks) {
        matrices.translate(-0.13, 0.335, 0.125);
        matrices.scale(0.9f, 0.9f, 0.9f);
        for (int i = 0; i < itemStacks.size(); i++) {
            if (i >= 6) {
                continue;
            }
            ItemStack stack = itemStacks.get(i);
                matrices.pushPose();
                int line = i >= 3 ? 2 : 1;
                float x = -0.35f * (i % 3);
                float y = -0.33f * line;

                matrices.translate(x, y, 0f);
                matrices.mulPose(Axis.XN.rotationDegrees(90));
                ClientUtil.renderItem(stack, matrices, vertexConsumers, entity);
                matrices.popPose();
        }
    }
}