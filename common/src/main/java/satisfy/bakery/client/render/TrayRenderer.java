package satisfy.bakery.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.cristelknight.doapi.client.ClientUtil;
import de.cristelknight.doapi.client.render.block.storage.api.StorageTypeRenderer;
import de.cristelknight.doapi.common.block.entity.StorageBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import satisfy.bakery.registry.ObjectRegistry;

@Environment(EnvType.CLIENT)
public class TrayRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, NonNullList<ItemStack> itemStacks) {
        matrices.translate(0f, 0.3, 0.2f);
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.mulPose(Axis.YP.rotationDegrees(90.0f));
        for (int i = 0; i < itemStacks.size(); i++) {
            ItemStack stack = itemStacks.get(i);
            if (!stack.isEmpty()) {
                matrices.pushPose();
                if (stack.getItem() instanceof BlockItem blockItem) {
                    renderItem(blockItem, matrices, vertexConsumers, entity);
                } else {
                    renderNonBlockItem(stack, i, matrices, vertexConsumers, entity);
                }
                matrices.popPose();
            }
        }
    }

    private void renderItem(BlockItem blockItem, PoseStack matrices, MultiBufferSource vertexConsumers, StorageBlockEntity entity) {
        float translateZ = blockItem.getBlock() == ObjectRegistry.BREAD_BLOCK.get() || blockItem.getBlock() == ObjectRegistry.BRAIDED_BREAD_BLOCK.get() || blockItem.getBlock() == ObjectRegistry.TOAST_BLOCK.get() ? 1.0f : -1f;
        float translateY = -0.5f;
        float translateX = blockItem.getBlock() == ObjectRegistry.BAGUETTE_BLOCK.get() ? -0.3f : -0.6f;
        float scale = blockItem.getBlock() == ObjectRegistry.BAGUETTE_BLOCK.get() ? 1.4f : 2f;
        matrices.translate(translateX, translateY, translateZ);
        matrices.scale(scale, scale, scale);
        matrices.mulPose(Axis.YP.rotationDegrees(90.0f));
        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
    }

    private void renderNonBlockItem(ItemStack stack, int index, PoseStack matrices, MultiBufferSource vertexConsumers, StorageBlockEntity entity) {
        matrices.translate(0.2f * index, 0, 0);
        matrices.mulPose(Axis.YN.rotationDegrees(66.0f));
        ClientUtil.renderItem(stack, matrices, vertexConsumers, entity);
    }
}
