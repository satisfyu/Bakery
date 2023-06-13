package satisfyu.bakery.registry;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import satisfyu.bakery.Bakery;
import satisfyu.bakery.BakeryIdentifier;
import satisfyu.bakery.effect.SaturatedEffect;

import java.util.function.Supplier;

public class EffectRegistry {

    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Bakery.MOD_ID, Registry.MOB_EFFECT_REGISTRY);
    private static final Registrar<MobEffect> MOB_EFFECTS_REGISTRAR = MOB_EFFECTS.getRegistrar();

    public static final RegistrySupplier<MobEffect> SATURATED;


    private static RegistrySupplier<MobEffect> registerEffect(String name, Supplier<MobEffect> effect){
        if(Platform.isForge()){
            return MOB_EFFECTS.register(name, effect);
        }
        return MOB_EFFECTS_REGISTRAR.register(new BakeryIdentifier(name), effect);
    }

    public static void init(){
        Bakery.LOGGER.debug("Mob effects");
        MOB_EFFECTS.register();
    }

    static {
        SATURATED = registerEffect("saturated", SaturatedEffect::new);
    }
}
