package satisfy.bakery.registry;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import satisfy.bakery.Bakery;
import satisfy.bakery.effect.BakeryEffect;
import satisfy.bakery.effect.SaturatedEffect;
import satisfy.bakery.util.BakeryIdentifier;

import java.util.function.Supplier;

public class EffectRegistry {

    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Bakery.MOD_ID, Registries.MOB_EFFECT);
    private static final Registrar<MobEffect> MOB_EFFECTS_REGISTRAR = MOB_EFFECTS.getRegistrar();

    public static final RegistrySupplier<MobEffect> STUFFED;
    public static final RegistrySupplier<MobEffect> SWEETS;


    private static RegistrySupplier<MobEffect> registerEffect(String name, Supplier<MobEffect> effect) {
        if (Platform.isForge()) {
            return MOB_EFFECTS.register(name, effect);
        }
        return MOB_EFFECTS_REGISTRAR.register(new BakeryIdentifier(name), effect);
    }

    public static void init() {
        Bakery.LOGGER.debug("Mob effects");
        MOB_EFFECTS.register();
    }

    static {
        STUFFED = registerEffect("stuffed", SaturatedEffect::new);
        SWEETS = registerEffect("sweets", () -> new BakeryEffect(MobEffectCategory.BENEFICIAL, 0x00FF00)
                .addAttributeModifier(Attributes.MOVEMENT_SPEED, "812EA0BC-B71A-AF17-836C-1E5F68070C46", 0.2, AttributeModifier.Operation.MULTIPLY_TOTAL)
                .addAttributeModifier(Attributes.ATTACK_SPEED, "1238C173-3FC1-6C89-408D-5F62AB9DCF03", 0.1, AttributeModifier.Operation.MULTIPLY_TOTAL)
                .addAttributeModifier(Attributes.ATTACK_DAMAGE, "A75D3FB4-6BB0-92D9-8C26-C2600A6C4929", 0.0, AttributeModifier.Operation.ADDITION)
        );
    }
}
