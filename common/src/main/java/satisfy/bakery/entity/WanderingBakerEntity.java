package satisfy.bakery.entity;

import de.cristelknight.doapi.common.util.VillagerUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import satisfy.bakery.registry.ObjectRegistry;

import java.util.HashMap;

public class WanderingBakerEntity extends WanderingTrader {
    public static final HashMap<Integer, VillagerTrades.ItemListing[]> TRADES = createTrades();

    public WanderingBakerEntity(EntityType<? extends WanderingBakerEntity> entityType, Level world) {
        super(entityType, world);
    }

    private static HashMap<Integer, VillagerTrades.ItemListing[]> createTrades() {
        HashMap<Integer, VillagerTrades.ItemListing[]> trades = new HashMap<>();
        trades.put(1, new VillagerTrades.ItemListing[]{
                new VillagerUtil.SellItemFactory(net.satisfy.farm_and_charm.registry.ObjectRegistry.DOUGH.get(), 2, 4, 8, 15),
                new VillagerUtil.SellItemFactory(ObjectRegistry.SWEET_DOUGH.get(), 2, 4, 8, 15),
                new VillagerUtil.SellItemFactory(ObjectRegistry.JAR.get(), 4, 2, 8, 1),
                new VillagerUtil.SellItemFactory(ObjectRegistry.TRAY.get(), 12, 1, 8, 25),
                new VillagerUtil.SellItemFactory(ObjectRegistry.BREADBOX.get(), 15, 1, 8, 30),
                new VillagerUtil.SellItemFactory(ObjectRegistry.CAKE_STAND.get(), 15, 1, 8, 30),
                new VillagerUtil.SellItemFactory(net.satisfy.farm_and_charm.registry.ObjectRegistry.STOVE.get(), 25, 1, 8, 40),
                new VillagerUtil.SellItemFactory(ObjectRegistry.SMALL_COOKING_POT.get(), 8, 1, 8, 15),
                new VillagerUtil.SellItemFactory(ObjectRegistry.BAGUETTE_BLOCK.get(), 4, 2, 8, 5),
                new VillagerUtil.SellItemFactory(ObjectRegistry.CRUSTY_BREAD_BLOCK.get(), 4, 2, 8, 5),
                new VillagerUtil.SellItemFactory(ObjectRegistry.BRAIDED_BREAD_BLOCK.get(), 4, 1, 8, 5),
                new VillagerUtil.SellItemFactory(ObjectRegistry.SANDWICH.get(), 6, 2, 8, 10),
                new VillagerUtil.SellItemFactory(ObjectRegistry.APPLE_CUPCAKE.get(), 2, 1, 8, 5),
                new VillagerUtil.SellItemFactory(ObjectRegistry.APPLE_PIE.get(), 6, 1, 8, 5),
                new VillagerUtil.SellItemFactory(ObjectRegistry.APPLE_PIE_SLICE.get(), 3, 2, 8, 5),
                new VillagerUtil.SellItemFactory(ObjectRegistry.CHOCOLATE_TRUFFLE.get(), 1, 1, 8, 2),
        });
        return trades;
    }

    @Override
    protected void updateTrades() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
        }
        this.addOffersFromItemListings(this.offers, TRADES.get(1), 8);
    }

}