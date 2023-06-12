package satisfyu.bakery.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import org.apache.logging.log4j.core.config.builder.api.Component;
import satisfyu.bakery.BakeryIdentifier;
import satisfyu.bakery.client.gui.recipebook.StoveRecipeBook;
import satisfyu.bakery.client.gui.handler.StoveGuiHandler;

@Environment(EnvType.CLIENT)
public class StoveGui extends AbstractContainerScreen<StoveGuiHandler> {

    public StoveGui(StoveGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new StoveRecipeBook(),  new BakeryIdentifier("textures/gui/stove_gui.png"));
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    public void renderProgressArrow(PoseStack matrices) {
        int progress = this.menu.getScaledProgress(18);
        this.blit(matrices, leftPos + 93, topPos + 32, 178, 20, progress, 25); //Position Arrow
    }

    @Override
    public void renderBurnIcon(PoseStack matrices, int posX, int posY) {
        if (this.menu.isBeingBurned()) {
            this.blit(matrices, posX + 62, posY + 49, 176, 0, 17, 15); //fire
        }
    }
}