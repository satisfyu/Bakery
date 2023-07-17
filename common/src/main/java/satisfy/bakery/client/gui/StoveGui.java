package satisfy.bakery.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfy.bakery.client.gui.handler.StoveGuiHandler;
import satisfy.bakery.client.recipebook.StoveRecipeBook;
import satisfy.bakery.util.BakeryIdentifier;

@Environment(EnvType.CLIENT)
public class StoveGui extends AbstractRecipeBookGUIScreen<StoveGuiHandler> {
    private static final ResourceLocation BG = new BakeryIdentifier("textures/gui/stove_gui.png");

    public StoveGui(StoveGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new StoveRecipeBook(), BG);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }

    @Override
    public void renderProgressArrow(GuiGraphics guiGraphics) {
        int progress = this.menu.getScaledProgress(18);
        guiGraphics.fill(leftPos + 93, topPos + 32, 178, 20, progress, 25); //Position Arrow
    }

    @Override
    public void renderBurnIcon(GuiGraphics guiGraphics, int posX, int posY) {
        if (this.menu.isBeingBurned()) {
            guiGraphics.fill(posX + 62, posY + 49, 176, 0, 17, 15); //fire
        }
    }
}