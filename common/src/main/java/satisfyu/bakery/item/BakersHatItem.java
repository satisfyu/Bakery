package satisfyu.bakery.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import satisfyu.bakery.BakeryIdentifier;
import satisfyu.bakery.registry.ArmorMaterialRegistry;

public class BakersHatItem extends CustomModelArmorItem {
    public BakersHatItem(Item.Properties settings) {
        super(ArmorMaterialRegistry.BAKER_ARMOR, EquipmentSlot.HEAD, settings);
    }

    @Override
    public ResourceLocation getTexture() {
        return new BakeryIdentifier("textures/item/cooking_hat.png");
    }

    @Override
    public Float getOffset() {
        return -1.78f;
    }
}
