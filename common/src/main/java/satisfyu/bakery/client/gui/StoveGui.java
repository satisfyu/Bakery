package satisfyu.bakery.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.bakery.BakeryIdentifier;
import satisfyu.bakery.client.gui.handler.StoveGuiHandler;
import satisfyu.bakery.client.gui.recipebook.AbstractRecipeBookGUIScreen;
import satisfyu.bakery.client.gui.recipebook.StoveRecipeBook;

@Environment(EnvType.CLIENT)
public class StoveGui extends AbstractRecipeBookGUIScreen<StoveGuiHandler> {
    private static final ResourceLocation BG = new BakeryIdentifier("textures/gui/stove_gui.png");

    public StoveGui(StoveGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new StoveRecipeBook(), BG);
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