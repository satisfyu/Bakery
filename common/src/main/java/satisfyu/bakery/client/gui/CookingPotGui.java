package satisfyu.bakery.client.gui;


import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import satisfyu.bakery.BakeryIdentifier;
import satisfyu.bakery.client.gui.handler.CookingPotGuiHandler;
import satisfyu.bakery.client.gui.recipebook.AbstractRecipeBookGUIScreen;
import satisfyu.bakery.client.gui.recipebook.CookingPotRecipeBook;


@Environment(EnvType.CLIENT)
public class CookingPotGui extends AbstractRecipeBookGUIScreen<CookingPotGuiHandler> {
    private static final ResourceLocation BACKGROUND;


    public CookingPotGui(CookingPotGuiHandler handler, Inventory playerInventory, Component title) {
        super(handler, playerInventory, title, new CookingPotRecipeBook(), BACKGROUND);
    }

    public void renderProgressArrow(PoseStack matrices) {
        int progress = this.menu.getScaledProgress(18);
        this.blit(matrices, leftPos + 95, topPos + 14, 178, 15, progress, 30); //Position Arrow
    }

    public void renderBurnIcon(PoseStack matrices, int posX, int posY) {
        if (menu.isBeingBurned()) {
            this.blit(matrices, posX + 124, posY + 56, 176, 0, 17, 15); //fire
        }
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX += 20;
    }

    static {
        BACKGROUND = new BakeryIdentifier("textures/gui/pot_gui.png");
    }
}