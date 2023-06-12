package satisfyu.bakery.util.api;

import de.cristelknight.doapi.api.DoApiAPI;
import de.cristelknight.doapi.api.DoApiPlugin;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.Set;

@DoApiPlugin
public class BakeryDoAPI implements DoApiAPI {
    @Override
    public void registerBlocks(Set<Block> set) {
    }

    @Override
    public <T extends LivingEntity> void registerArmor(Map<Item, EntityModel<T>> map, EntityModelSet entityModelSet) {

    }
}
