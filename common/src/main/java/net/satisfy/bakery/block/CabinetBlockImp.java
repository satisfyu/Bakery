package net.satisfy.bakery.block;

import com.mojang.serialization.MapCodec;
import de.cristelknight.doapi.common.block.CabinetBlock;
import de.cristelknight.doapi.common.registry.DoApiSoundEventRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.BaseEntityBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CabinetBlockImp extends CabinetBlock {
    public static final MapCodec<CabinetBlockImp> CODEC = simpleCodec(CabinetBlockImp::new);

    public CabinetBlockImp(Properties settings, Supplier<SoundEvent> openSound, Supplier<SoundEvent> closeSound) {
        super(settings, openSound, closeSound);
    }

    public CabinetBlockImp(Properties settings) {
        super(settings, DoApiSoundEventRegistry.CABINET_OPEN, DoApiSoundEventRegistry.CABINET_CLOSE);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
