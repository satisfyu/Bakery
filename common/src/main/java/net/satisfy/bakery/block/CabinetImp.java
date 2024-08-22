package net.satisfy.bakery.block;

import com.mojang.serialization.MapCodec;
import de.cristelknight.doapi.common.block.CabinetBlock;
import de.cristelknight.doapi.common.registry.DoApiSoundEventRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.BaseEntityBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CabinetImp extends CabinetBlock {
    public static final MapCodec<CabinetImp> CODEC = simpleCodec(CabinetImp::new);

    public CabinetImp(Properties settings, Supplier<SoundEvent> openSound, Supplier<SoundEvent> closeSound) {
        super(settings, openSound, closeSound);
    }

    public CabinetImp(Properties settings) {
        super(settings, DoApiSoundEventRegistry.CABINET_OPEN, DoApiSoundEventRegistry.CABINET_CLOSE);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
