package satisfy.bakery.client.gui;

import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfy.bakery.client.gui.handler.BakerStationGuiHandler;
import satisfy.bakery.client.recipebook.BakerStationRecipeBook;
import satisfy.bakery.util.BakeryIdentifier;

public class BakerStationGui extends AbstractRecipeBookGUIScreen<BakerStationGuiHandler> {

    public static final ResourceLocation BG = new BakeryIdentifier("textures/gui/bakerstation.png");

    public static final int ARROW_X = 94;
    public static final int ARROW_Y = 45;

    public BakerStationGui(BakerStationGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new BakerStationRecipeBook(), BG);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }

    protected void renderProgressArrow(GuiGraphics guiGraphics) {
        final int progressX = this.menu.getShakeXProgress();
        guiGraphics.blit(BG, leftPos + ARROW_X, topPos + ARROW_Y, 177, 26, progressX, 10);
    }
}

