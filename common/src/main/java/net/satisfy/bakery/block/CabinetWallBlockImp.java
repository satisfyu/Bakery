package net.satisfy.bakery.block;

import com.mojang.serialization.MapCodec;
import de.cristelknight.doapi.common.block.CabinetWallBlock;
import de.cristelknight.doapi.common.registry.DoApiSoundEventRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.BaseEntityBlock;
import org.jetbrains.annotations.NotNull;

public class CabinetWallBlockImp extends CabinetWallBlock {
    public static final MapCodec<CabinetWallBlockImp> CODEC = simpleCodec(CabinetWallBlockImp::new);

    public CabinetWallBlockImp(Properties settings, RegistrySupplier<SoundEvent> openSound, RegistrySupplier<SoundEvent> closeSound) {
        super(settings, openSound, closeSound);
    }

    public CabinetWallBlockImp(Properties settings) {
        super(settings, DoApiSoundEventRegistry.CABINET_OPEN, DoApiSoundEventRegistry.CABINET_CLOSE);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
