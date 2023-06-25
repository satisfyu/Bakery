package satisfyu.bakery.registry;

import de.cristelknight.doapi.api.DoApiAPI;
import de.cristelknight.doapi.api.DoApiPlugin;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import satisfyu.bakery.BakeryIdentifier;

import java.util.Map;
import java.util.Set;

@DoApiPlugin
public class DoAPIRegistry implements DoApiAPI {

    public static final ResourceLocation CAKE_STAND = new BakeryIdentifier("cake_stand");
    public static final ResourceLocation TRAY = new BakeryIdentifier("tray");

    @Override
    public void registerBlocks(Set<Block> blocks) {
        blocks.add(ObjectRegistry.CAKE_STAND.get());
        blocks.add(ObjectRegistry.TRAY.get());

    }

    @Override
    public <T extends LivingEntity> void registerArmor(Map<Item, EntityModel<T>> models, EntityModelSet modelLoader) {

    }
}
