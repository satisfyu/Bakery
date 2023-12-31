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
        int invSize = 6;

        for (int i = 0; i < invSize; i++) {
            ItemStack stack = itemStacks.get(i);
            if (!stack.isEmpty() && !(stack.getItem() instanceof BlockItem)) {
                matrices.pushPose();
                matrices.scale(0.4f, 0.4f, 0.4f);

                boolean firstColumn = i < invSize / 2;
                int xNeedsToBeChanged = i + 1;

                matrices.translate(xNeedsToBeChanged, firstColumn ? 1.15f : 0.25f, -0.45f);


                matrices.mulPose(Axis.XP.rotationDegrees(90f));
                ClientUtil.renderItem(stack, matrices, vertexConsumers, entity);
                matrices.popPose();
            }
        }
    }
}