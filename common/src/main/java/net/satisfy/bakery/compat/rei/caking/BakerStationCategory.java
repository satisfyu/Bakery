package net.satisfy.bakery.compat.rei.caking;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.satisfy.bakery.Bakery;
import net.satisfy.bakery.registry.ObjectRegistry;

import java.util.Collections;
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
        Point startPoint = new Point(bounds.getCenterX() - (4 * 18) / 2, bounds.getCenterY() - 18 / 2);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));

        int baseX = startPoint.x - 30;
        int baseY = startPoint.y;
        int size = 18;
        int arrowXOffset = 3 * size;
        int arrowYOffset = 0;
        int outputXOffset = arrowXOffset + 58;
        int knifeXOffset = arrowXOffset - size + 25; // Adjust for knife display
        int knifeYOffset = -19; // Display above the arrow

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

        EntryStack<?> output = display.getOutputEntries().get(0).get(0);
        if (output.equals(EntryStacks.of(ObjectRegistry.APPLE_CUPCAKE.get())) ||
                output.equals(EntryStacks.of(ObjectRegistry.STRAWBERRY_CUPCAKE.get())) ||
                output.equals(EntryStacks.of(ObjectRegistry.SWEETBERRY_CUPCAKE.get()))) {
            widgets.add(Widgets.createSlot(new Point(baseX + knifeXOffset, baseY + knifeYOffset))
                    .entries(Collections.singletonList(EntryStacks.of(ObjectRegistry.BREAD_KNIFE.get())))
                    .disableBackground()
                    .markInput());
        }

        return widgets;
    }
}

