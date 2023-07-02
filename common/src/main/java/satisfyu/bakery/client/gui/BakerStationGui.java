package satisfyu.bakery.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import de.cristelknight.doapi.client.recipebook.screen.AbstractRecipeBookGUIScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.bakery.client.gui.handler.BakerStationGuiHandler;
import satisfyu.bakery.client.recipebook.BakerStationRecipeBook;
import satisfyu.bakery.util.BakeryIdentifier;

public class BakerStationGui extends AbstractRecipeBookGUIScreen<BakerStationGuiHandler> {

    private static final ResourceLocation BG = new BakeryIdentifier("textures/gui/bakerstation.png");

    public BakerStationGui(BakerStationGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title, new BakerStationRecipeBook(), BG);
    }

    @Override
    protected void init() {
        this.titleLabelX += 2;
        this.titleLabelY += -3;
        super.init();
    }

    protected void renderProgressArrow(PoseStack matrices) {
        final int progressX = this.menu.getShakeXProgress();
        this.blit(matrices, leftPos + 94, topPos + 45, 177, 26, progressX, 10);
        final int progressY = this.menu.getShakeYProgress();
        this.blit(matrices, leftPos + 96, topPos + 22 + 20 - progressY, 179, 2 + 20 - progressY, 15, progressY);
    }
}

