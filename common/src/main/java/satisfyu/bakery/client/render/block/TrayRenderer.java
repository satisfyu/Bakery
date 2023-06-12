package satisfyu.bakery.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import de.cristelknight.doapi.block.entity.StorageBlockEntity;
import de.cristelknight.doapi.client.render.block.storage.StorageTypeRenderer;
import satisfyu.candlelight.client.ClientUtil;

@Environment(EnvType.CLIENT)
public class TrayRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, NonNullList<ItemStack> itemStacks) {
        matrices.translate(0, 0.3, -0.25);
        matrices.scale(0.5f, 0.5f, 0.5f);
        for (int i = 0; i < itemStacks.size(); i++) {
            ItemStack stack = itemStacks.get(i);
            if(!stack.isEmpty()) {
                matrices.pushPose();

                if (stack.getItem() instanceof BlockItem blockItem) {
                    matrices.translate(-0.5f, -0.5f, 0f);
                    ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                } else {
                    matrices.translate(0f, 0f, 0.2f * i);
                    matrices.mulPose(Vector3f.YN.rotationDegrees(22.5f));
                    ClientUtil.renderItem(stack, matrices, vertexConsumers, entity);
                }
                matrices.popPose();
            }
        }
    }
}
