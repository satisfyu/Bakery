package satisfy.bakery.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import satisfyu.bakery.Bakery;
import satisfyu.bakery.client.BakeryClient;

@Mod(Bakery.MOD_ID)
public class BakeryForge {
    public BakeryForge() {
        EventBuses.registerModEventBus(Bakery.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Bakery.init();
        BakeryClient.initClient();

    }
}
