package satisfy.bakery.forge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import satisfy.bakery.Bakery;
import satisfy.bakery.client.BakeryClient;

@Mod.EventBusSubscriber(modid = Bakery.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BakeryClientForge {

    @SubscribeEvent
    public static void beforeClientSetup(RegisterEvent event) {
        BakeryClient.preInitClient();
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BakeryClient.initClient();
    }
}
