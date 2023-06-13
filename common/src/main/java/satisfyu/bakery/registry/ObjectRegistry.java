package satisfyu.bakery.registry;

import de.cristelknight.doapi.Util;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;
import satisfyu.bakery.Bakery;
import satisfyu.bakery.BakeryIdentifier;
import satisfyu.bakery.block.*;
import satisfyu.bakery.block.crops.StrawberryCropBlock;
import satisfyu.bakery.block.crops.WildBush;
import satisfyu.bakery.block.crops.WildBushJungle;
import satisfyu.bakery.item.BakersHatItem;
import satisfyu.bakery.item.TooltipItem;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Bakery.MOD_ID, Registry.ITEM_REGISTRY);
    public static final Registrar<Item> ITEM_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Bakery.MOD_ID, Registry.BLOCK_REGISTRY);
    public static final Registrar<Block> BLOCK_REGISTRAR = BLOCKS.getRegistrar();


    //jam
    public static final RegistrySupplier<Block> JAR = registerWithItem("jar", () -> new StackableBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion().sound(SoundType.GLASS)));
    public static final RegistrySupplier<Block> STRAWBERRY_JAM = registerWithItem("strawberry_jam", () -> new StackableBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion().sound(SoundType.GLASS)));
    public static final RegistrySupplier<Block> CHERRY_JAM = registerWithItem("cherry_jam", () -> new StackableBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion()));
    public static final RegistrySupplier<Block> SWEETBERRY_JAM = registerWithItem("sweetberry_jam", () -> new StackableBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion().sound(SoundType.GLASS)));
    public static final RegistrySupplier<Block> GRAPE_JAM = registerWithItem("grape_jam", () -> new StackableBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion().sound(SoundType.GLASS)));
    public static final RegistrySupplier<Block> APPLE_JAM = registerWithItem("apple_jam", () -> new StackableBlock(BlockBehaviour.Properties.of(Material.GLASS).instabreak().noOcclusion()));
    //stove
    public static final RegistrySupplier<Block> COBBLESTONE_STOVE = registerWithItem("cobblestone_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> SANDSTONE_STOVE = registerWithItem("sandstone_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> STONE_BRICKS_STOVE = registerWithItem("stone_bricks_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> DEEPSLATE_STOVE = registerWithItem("deepslate_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> GRANITE_STOVE = registerWithItem("granite_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> END_STOVE = registerWithItem("end_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> MUD_STOVE = registerWithItem("mud_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> QUARTZ_STOVE = registerWithItem("quartz_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));
    public static final RegistrySupplier<Block> RED_NETHER_BRICKS_STOVE = registerWithItem("red_nether_bricks_stove", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlock.LIT) ? 13 : 0)));

    public static final RegistrySupplier<Block> SMALL_COOKING_POT = registerWithItem("small_cooking_pot", () -> new CookingPotBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion()));

    //placeable food
    public static final RegistrySupplier<Block> CRUSTY_BREAD = registerWithItem("crusty_bread", () -> new BreadBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).noOcclusion()));
    //public static final RegistrySupplier<Block> STRAWBERRY_CAKE = registerWithItem("strawberry_cake", () -> new CakeBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.STRAWBERRY_CAKE_SLICE));
    //public static final RegistrySupplier<Block> SWEETBERRY_CAKE = registerWithItem("sweetberry_cake", () -> new CakeBlock((BlockBehaviour.Properties.copy(Blocks.CAKE)), ObjectRegistry.SWEETBERRY_CAKE_SLICE));
    //public static final RegistrySupplier<Block> CHOCOLATE_CAKE = registerWithItem("chocolate_cake", () -> new CakeBlock((BlockBehaviour.Properties.copy(Blocks.CAKE)), ObjectRegistry.CHOCOLATE_CAKE_SLICE));
    public static final RegistrySupplier<Block> CHOCOLATE_BOX = registerWithItem("chocolate_box", () -> new ChocolateBoxBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));
//TODO Models
    public static final RegistrySupplier<Item> BUNDT_CAKE = registerItem("bundt_cake", () -> new Item(getSettings().food(Foods.COOKED_BEEF)));
    public static final RegistrySupplier<Item> PUDDING = registerItem("pudding", () -> new Item(getSettings().food(Foods.COOKED_BEEF)));
    public static final RegistrySupplier<Item> QUICHE = registerItem("quiche", () -> new Item(getSettings().food(Foods.COOKED_BEEF)));

    //Cake Slices
    public static final RegistrySupplier<Item> STRAWBERRY_CAKE_SLICE = registerItem("strawberry_cake_slice", () -> new Item(getSettings().food(Foods.BREAD)));
    public static final RegistrySupplier<Item> SWEETBERRY_CAKE_SLICE = registerItem("sweetberry_cake_slice", () -> new Item(getSettings().food(Foods.BREAD)));
    public static final RegistrySupplier<Item> CHOCOLATE_CAKE_SLICE = registerItem("chocolate_cake_slice", () -> new Item(getSettings().food(Foods.BREAD)));
    public static final RegistrySupplier<Item> APPLE_PIE_SLICE = registerItem("apple_pie_slice", () -> new Item(getSettings().food(Foods.BREAD)));
//TODO Pudding Slice, Bundt Cake Slice
    //Crafting Ingredients
    public static final RegistrySupplier<Item> CHOCOLATE = registerItem("chocolate", () -> new TooltipItem(getSettings().food(Foods.APPLE)));
    public static final RegistrySupplier<Item> DOUGH = registerItem("dough", () -> new TooltipItem(getSettings()));
    public static final RegistrySupplier<Item> BUTTER = registerItem("butter", () -> new TooltipItem(getSettings()));

    //Pastry
    public static final RegistrySupplier<Item> APPLE_CUPCAKE = registerItem("apple_cupcake", () -> new Item(getSettings().food(Foods.GOLDEN_CARROT)));
    public static final RegistrySupplier<Item> CHOCOLATE_BREAD = registerItem("chocolate_bread", () -> new Item(getSettings().food(Foods.BREAD)));
    public static final RegistrySupplier<Item> TOAST = registerItem("toast", () -> new Item(getSettings().food(Foods.BEETROOT_SOUP)));
    public static final RegistrySupplier<Item> DONUT = registerItem("donut", () -> new Item(getSettings().food(Foods.CARROT)));
    public static final RegistrySupplier<Item> MILK_BREAD = registerItem("milk_bread", () -> new Item(getSettings().food(Foods.COOKIE)));
    public static final RegistrySupplier<Item> JAM_SANDWICH = registerItem("jam_sandwich", () -> new Item(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistrySupplier<Item> SANDWICH = registerItem("sandwich", () -> new Item(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistrySupplier<Item> WAFFLE = registerItem("waffle", () -> new Item(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistrySupplier<Item> STRAWBERRY_GLAZED_COOKIE = registerItem("strawberry_glazed_cookie", () -> new Item(getSettings().food(Foods.BREAD)));
    public static final RegistrySupplier<Item> SWEETBERRY_GLAZED_COOKIE = registerItem("sweetberry_glazed_cookie", () -> new Item(getSettings().food(Foods.BREAD)));
    public static final RegistrySupplier<Item> CHOCOLATE_GLAZED_COOKIE = registerItem("chocolate_glazed_cookie", () -> new Item(getSettings().food(Foods.BREAD)));
    public static final RegistrySupplier<Item> CROISSANT = registerItem("croissant", () -> new Item(getSettings().food(Foods.BREAD)));
    public static final RegistrySupplier<Item> STRAWBERRY_CHOCOLATE = registerItem("strawberry_chocolate", () -> new Item(getSettings().food(Foods.BREAD)));

    //Crops
    public static final RegistrySupplier<Block> STRAWBERRY_CROP = registerWithoutItem("strawberry_crop", () -> new StrawberryCropBlock(getBushSettings()));
    public static final RegistrySupplier<Item> STRAWBERRY_SEEDS = registerItem("tomato_seeds", () -> new BlockItem(STRAWBERRY_CROP.get(), getSettings()));
    public static final RegistrySupplier<Item> STRAWBERRY = registerItem("strawberry", () -> new TooltipItem(getSettings().food(Foods.BEETROOT)));
    public static final RegistrySupplier<Block> STRAWBERRY_WILD_TAIGA = registerWithoutItem("strawberry_wild_taiga", () -> new WildBush(getBushSettings()));
    public static final RegistrySupplier<Block> STRAWBERRY_WILD_JUNGLE = registerWithoutItem("strawberry_wild_jungle", () -> new WildBushJungle(getBushSettings()));
//todo oat crop
    //Blocks
    public static final RegistrySupplier<Block> STRAWBERRY_CRATE = registerWithItem("strawberry_crate", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> OAT_BLOCK = registerWithItem("oat_block", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> CAKE_STAND = registerWithItem("cake_stand", () -> new CakeStandBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> TRAY = registerWithItem("tray", () -> new TrayBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> STREET_SIGN = registerWithItem("street_sign", () -> new BoardBlock(BlockBehaviour.Properties.of(Material.DECORATION), true));
    public static final RegistrySupplier<Block> IRON_CHAIR = registerWithItem("iron_chair", () -> new ChairBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> IRON_TABLE = registerWithItem("iron_table", () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    //Armor
    public static final RegistrySupplier<Item> BAKERS_HAT = registerItem("bakers_hat", () -> new BakersHatItem(getSettings().rarity(Rarity.COMMON)));
//todo bakers apron


    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties().tab(Bakery.BAKERY_TAB);
        consumer.accept(settings);
        return settings;
    }

    private static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }


    public static void init() {
        Bakery.LOGGER.debug("Registering Mod Block and Items for " + Bakery.MOD_ID);
        ITEMS.register();
        BLOCKS.register();
    }

    public static void commonInit() {
        //FuelRegistry.register();

    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block) {
        return registerWithItem(name, block, Bakery.BAKERY_TAB);
    }

    public static <T extends Block> RegistrySupplier<T> registerWithItem(String name, Supplier<T> block, @Nullable CreativeModeTab tab) {
        return Util.registerWithItem(BLOCKS, BLOCK_REGISTRAR, ITEMS, ITEM_REGISTRAR, new BakeryIdentifier(name), block, tab);
    }

    public static <T extends Block> RegistrySupplier<T> registerWithoutItem(String path, Supplier<T> block) {
        return Util.registerWithoutItem(BLOCKS, BLOCK_REGISTRAR, new BakeryIdentifier(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return Util.registerItem(ITEMS, ITEM_REGISTRAR, new BakeryIdentifier(path), itemSupplier);
    }

    private static BlockBehaviour.Properties getBushSettings() {
        return BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH);
    }

}

