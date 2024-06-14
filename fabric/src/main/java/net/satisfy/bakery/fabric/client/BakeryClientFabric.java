package net.satisfy.bakery.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.satisfy.bakery.client.BakeryClient;

public class BakeryClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BakeryClient.preInitClient();
        BakeryClient.initClient();
    }
}
