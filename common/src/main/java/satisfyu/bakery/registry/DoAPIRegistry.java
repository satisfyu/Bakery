package satisfyu.bakery.registry;

import com.mojang.datafixers.util.Pair;
import de.cristelknight.doapi.api.DoApiAPI;
import de.cristelknight.doapi.api.DoApiPlugin;
import de.cristelknight.doapi.client.render.feature.FullCustomArmor;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import satisfyu.bakery.util.BakeryIdentifier;

import java.util.Map;
import java.util.Set;

@DoApiPlugin
public class DoAPIRegistry implements DoApiAPI {

    public static final ResourceLocation CAKE_STAND = new BakeryIdentifier("cake_stand");
    public static final ResourceLocation TRAY = new BakeryIdentifier("tray");
    public static final ResourceLocation SHELF = new BakeryIdentifier("shelf");

    @Override
    public void registerBlocks(Set<Block> blocks) {
        blocks.add(ObjectRegistry.CAKE_STAND.get());
        blocks.add(ObjectRegistry.TRAY.get());
        blocks.add(ObjectRegistry.SHELF.get());

    }

    @Override
    public <T extends LivingEntity> void registerHat(Map<Item, EntityModel<T>> models, EntityModelSet modelLoader) {

    }

    @Override
    public <T extends LivingEntity> void registerArmor(Map<FullCustomArmor, Pair<HumanoidModel<T>, HumanoidModel<T>>> models, EntityModelSet modelLoader) {

    }
}
