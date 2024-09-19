package net.satisfy.bakery.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.satisfy.bakery.Bakery;
import net.satisfy.bakery.registry.CompostableRegistry;

@Mod(Bakery.MOD_ID)
public class BakeryNeoForge {
    public BakeryNeoForge(IEventBus modBus) {
        Bakery.init();
        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(CompostableRegistry::registerCompostable);
    }
}
