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
public class BreadBoxRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, NonNullList<ItemStack> itemStacks) {
        matrices.translate(-0.25, 0.3, 0);
        matrices.scale(0.5f, 0.5f, 0.5f);
        for (int i = 0; i < itemStacks.size(); i++) {
            ItemStack stack = itemStacks.get(i);
            if (!stack.isEmpty()) {
                matrices.pushPose();
                if (stack.getItem() instanceof BlockItem blockItem) {
                    renderBlockItem(blockItem, matrices, vertexConsumers, entity);
                } else {
                    renderNonBlockItem(stack, i, matrices, vertexConsumers, entity);
                }
                matrices.popPose();
            }
        }
    }

    private void renderBlockItem(BlockItem blockItem, PoseStack matrices, MultiBufferSource vertexConsumers, StorageBlockEntity entity) {
        float translateX = -0.5f, translateY = -0.5f, translateZ, scale;
        boolean rotate = blockItem.getBlock() != ObjectRegistry.CRUSTY_BREAD_BLOCK.get() && blockItem.getBlock() != ObjectRegistry.BUN_BLOCK.get();
        if (rotate) {
            translateZ = 0.93f;
            matrices.mulPose(Axis.YP.rotationDegrees(90.0f));
        } else {
            translateZ = -0.9f;
        }
        scale = blockItem.getBlock() == ObjectRegistry.BAGUETTE_BLOCK.get() ? 1.5f : 2f;
        matrices.translate(translateX, translateY, translateZ);
        matrices.scale(scale, scale, scale);
        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
    }

    private void renderNonBlockItem(ItemStack stack, int index, PoseStack matrices, MultiBufferSource vertexConsumers, StorageBlockEntity entity) {
        matrices.translate(0.3f * index, 0, 0);
        matrices.mulPose(Axis.YN.rotationDegrees(45.0f));
        ClientUtil.renderItem(stack, matrices, vertexConsumers, entity);
    }
}
