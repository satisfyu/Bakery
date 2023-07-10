package satisfy.bakery.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import de.cristelknight.doapi.client.render.block.storage.StorageTypeRenderer;
import de.cristelknight.doapi.common.block.entity.StorageBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import satisfy.bakery.client.ClientUtil;
import satisfy.bakery.registry.ObjectRegistry;

@Environment(EnvType.CLIENT)
public class TrayRenderer implements StorageTypeRenderer {
    @Override
    public void render(StorageBlockEntity entity, PoseStack matrices, MultiBufferSource vertexConsumers, NonNullList<ItemStack> itemStacks) {
        matrices.translate(-0.25, 0.3, 0);
        matrices.scale(0.5f, 0.5f, 0.5f);
        for (int i = 0; i < itemStacks.size(); i++) {
            ItemStack stack = itemStacks.get(i);
            if (!stack.isEmpty()) {
                matrices.pushPose();
                if (stack.getItem() instanceof BlockItem blockItem) {
                    if (blockItem.getBlock() == ObjectRegistry.CRUSTY_BREAD_BLOCK.get()) {
                        matrices.translate(-0.5f, -0.5f, -1f);
                        matrices.scale(2f, 2f, 2f);
                        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                    } else if (blockItem.getBlock() == ObjectRegistry.BUN_BLOCK.get()) {
                        matrices.translate(-0.5f, -0.5f, -1f);
                        matrices.scale(2f, 2f, 2f);
                        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                    } else if (blockItem.getBlock() == ObjectRegistry.BREAD_BLOCK.get()) {
                        matrices.translate(-0.5f, -0.5f, -1f);
                        matrices.scale(2f, 2f, 2f);
                        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                    } else if (blockItem.getBlock() == ObjectRegistry.BRAIDED_BREAD_BLOCK.get()) {
                        matrices.translate(-0.5f, -0.5f, -1f);
                        matrices.scale(2f, 2f, 2f);
                        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                    } else if (blockItem.getBlock() == ObjectRegistry.TOAST_BLOCK.get()) {
                        matrices.translate(-0.5f, -0.5f, -1f);
                        matrices.scale(2f, 2f, 2f);
                        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                    } else if (blockItem.getBlock() == ObjectRegistry.BAGUETTE_BLOCK.get()) {
                        matrices.translate(-0.25f, -0.5f, -0.8f);
                        matrices.scale(1.5f, 1.5f, 1.5f);
                        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                    } else {
                        matrices.translate(0.1, -0.5f, -0.4f);
                        matrices.scale(0.8f, 0.8f, 0.8f);
                        ClientUtil.renderBlockFromItem(blockItem, matrices, vertexConsumers, entity);
                    }
                } else {
                    matrices.translate(0.3f * i, 0, 0);
                    matrices.mulPose(Vector3f.YN.rotationDegrees(45.0f));
                    ClientUtil.renderItem(stack, matrices, vertexConsumers, entity);
                }

                matrices.popPose();
            }
        }
    }
}
