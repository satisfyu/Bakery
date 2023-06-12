package satisfyu.bakery.registry;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import satisfyu.bakery.client.model.BakersHatModel;

import java.util.Map;

public class ArmorRegistry {

    public static void registerArmorModelLayers() {
        EntityModelLayerRegistry.register(BakersHatModel.LAYER_LOCATION, BakersHatModel::getTexturedModelData);
    }

    public static  <T extends LivingEntity> void registerArmorModels(Map<Item, EntityModel<T>> models, EntityModelSet modelLoader) {
        models.put(ObjectRegistry.BAKERS_HAT.get(), new BakersHatModel<>(modelLoader.bakeLayer(BakersHatModel.LAYER_LOCATION)));
    }
}