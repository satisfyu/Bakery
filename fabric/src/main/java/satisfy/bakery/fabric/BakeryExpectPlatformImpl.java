package satisfy.bakery.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class BakeryExpectPlatformImpl {
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
