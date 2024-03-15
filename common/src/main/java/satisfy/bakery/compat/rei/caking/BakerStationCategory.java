package satisfy.bakery.compat.rei.caking;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import satisfy.bakery.Bakery;
import satisfy.bakery.registry.ObjectRegistry;

import java.util.List;

public class BakerStationCategory implements DisplayCategory<BakerStationDisplay> {
    public static final CategoryIdentifier<BakerStationDisplay> BAKER_STATION_DISPLAY = CategoryIdentifier.of(Bakery.MOD_ID, "baking_display");

    @Override
    public CategoryIdentifier<BakerStationDisplay> getCategoryIdentifier() {
        return BAKER_STATION_DISPLAY;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("rei.bakery.baking_category");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ObjectRegistry.BAKER_STATION.get());
    }

    @Override
    public int getDisplayHeight() {
        return 64;
    }

    @Override
    public List<Widget> setupDisplay(BakerStationDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - (4 * 18) / 2, bounds.getCenterY() - (18) / 2);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));

        int baseX = startPoint.x - 30; // Input 35 pixels to the left
        int baseY = startPoint.y;
        int size = 18;
        int arrowXOffset = 3 * size; // Arrow 35 pixels to the left, adjusted for initial move
        int arrowYOffset = 0;
        int outputXOffset = arrowXOffset + 58; // Output 3 pixels to the left, adjusted after arrow

        widgets.add(Widgets.createArrow(new Point(baseX + arrowXOffset, baseY + arrowYOffset)).animationDurationTicks(50));
        widgets.add(Widgets.createResultSlotBackground(new Point(baseX + outputXOffset, baseY)));
        widgets.add(Widgets.createSlot(new Point(baseX + outputXOffset, baseY))
                .entries(display.getOutputEntries().get(0))
                .disableBackground()
                .markOutput());

        widgets.add(Widgets.createSlot(new Point(baseX, baseY))
                .entries(display.getInputEntries().get(0))
                .markInput());

        for (int i = 1; i <= 2; i++) {
            if (display.getInputEntries().size() > i) {
                widgets.add(Widgets.createSlot(new Point(baseX + i * size, baseY))
                        .entries(display.getInputEntries().get(i))
                        .markInput());
            }
        }
        return widgets;
    }
}
