package satisfy.bakery.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import satisfyu.bakery.client.BakeryClient;

public class BakeryClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BakeryClient.preInitClient();
        BakeryClient.initClient();
    }
}
