package satisfyu.bakery.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SweetsEffect extends MobEffect {
    public SweetsEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x00FF00);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        int stackCount = amplifier + 1;

        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 15, stackCount - 1));
        entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 15, stackCount - 1));
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 15, stackCount - 1));

        if (stackCount >= 5 && stackCount <= 10 && shouldApplyNausea(entity.getRandom())) {
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
            entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200));
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 200));
            if (entity instanceof Player) {
                spawnNauseaParticles((Player) entity);
            }
        }
    }

    private boolean shouldApplyNausea(RandomSource random) {
        return random.nextFloat() <= 0.25F;
    }

    private void spawnNauseaParticles(Player player) {
        int particleCount = 25;
        int durationTicks = 5 * 20;
        int delayTicks = durationTicks / particleCount;

        Level level = player.getCommandSenderWorld();

        for (int i = 0; i < particleCount; i++) {
            double offsetX = player.getRandom().nextGaussian() * 0.02;
            double offsetY = player.getRandom().nextGaussian() * 0.02;
            double offsetZ = player.getRandom().nextGaussian() * 0.02;
            level.addParticle(ParticleTypes.ITEM_SLIME, player.getX(), player.getY() + player.getEyeHeight(), player.getZ(), offsetX, offsetY, offsetZ);

            try {
                Thread.sleep(delayTicks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
