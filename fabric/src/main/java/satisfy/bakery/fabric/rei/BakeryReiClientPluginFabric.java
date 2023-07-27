package satisfy.bakery.fabric.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import satisfy.bakery.compat.rei.BakeryREIClientPlugin;


public class BakeryReiClientPluginFabric implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        BakeryREIClientPlugin.registerCategories(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        BakeryREIClientPlugin.registerDisplays(registry);
    }
}
