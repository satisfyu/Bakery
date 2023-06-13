package satisfyu.bakery;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import satisfyu.bakery.registry.*;

import java.util.function.Supplier;

public class Bakery {
    public static final String MOD_ID = "bakery";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final CreativeModeTab BAKERY_TAB = CreativeTabRegistry.create(new ResourceLocation(MOD_ID, "bakery_tab"), () ->
            new ItemStack(ObjectRegistry.BAKERS_HAT.get()));

    public static void init() {
        ObjectRegistry.init();
        BlockEntityRegistry.init();
        EffectRegistry.init();
        EntitiesRegistry.init();
        RecipeTypeRegistry.init();
        SoundEventsRegistry.init();
        ScreenHandlerTypeRegistry.init();
    }

    public static void commonInit(){
        CompostableItemsRegistry.init();
    }
}

