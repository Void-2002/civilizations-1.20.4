package net._void.civilizations.item;

import net._void.civilizations.Civilizations;
import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.item.custom.*;
import net._void.civilizations.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item EGYPT_KEY = registerItem("egypt_key",new EgyptKey(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item EGYPT_COIN = registerItem("egypt_coin", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item PAPYRUS = registerItem("papyrus", new Item(new FabricItemSettings()));
    public static final Item EGYPT_CROOK = registerItem("egypt_crook", new EgyptCrook(3, -2.0F, new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item CHINA_KEY_FRAGMENT = registerItem("china_key_fragment", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item CHINA_KEY = registerItem("china_key", new ChinaKey(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item NORDIC_BATTLE_AXE = registerItem("nordic_battle_axe", new NordicBattleAxe(5, -2.8F, new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item CHINA_COIN = registerItem("china_coin", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item CHINA_SWORD = registerItem("china_sword", new SwordItem(ToolMaterials.NETHERITE, 2, -1.5F, new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item HIGH_MOUNTAINS_AND_FLOWING_WATER_MUSIC_DISC = registerItem("high_mountains_music_disc",
            new MusicDiscItem(7, ModSounds.HIGH_MOUNTAINS_AND_FLOWING_WATER, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 382));
    public static final Item AUTUMN_MOON_OVER_HAN_PALACE_MUSIC_DISC = registerItem("autumn_moon_music_disc",
            new MusicDiscItem(7, ModSounds.AUTUMN_MOON_OVER_HAN_PALACE, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 312));
    public static final Item GREECE_KEY = registerItem("greece_key", new GreeceKey(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item GREECE_SWORD = registerItem("greece_sword", new SwordItem(ToolMaterials.NETHERITE, 4, -2.0F, new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item GREECE_COIN = registerItem("greece_coin", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item ROME_COIN = registerItem("rome_coin", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item ROME_KEY = registerItem("rome_key", new RomeKey(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item ROME_KEY_FRAGMENT = registerItem("rome_key_fragment", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item ROME_SWORD = registerItem("rome_sword", new SwordItem(ToolMaterials.NETHERITE, 1, -1.0F, new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));

    public static final Item BOOK_SHARPNESS = registerItem("sharpness_book", new CustomEnchantedBookItem("sharpness", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_POWER = registerItem("power_book", new CustomEnchantedBookItem("power", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_FIRE_ASPECT = registerItem("fire_aspect_book", new CustomEnchantedBookItem("fire_aspect", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_UNBREAKING = registerItem("unbreaking_book", new CustomEnchantedBookItem("unbreaking", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_PROTECTION = registerItem("protection_book", new CustomEnchantedBookItem("protection", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_FEATHER_FALLING = registerItem("feather_falling_book", new CustomEnchantedBookItem("feather_falling", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_THORNS = registerItem("thorns_book", new CustomEnchantedBookItem("thorns", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_FROST_WALKER = registerItem("frost_walker_book", new CustomEnchantedBookItem("frost_walker", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_DEPTH_STRIDER = registerItem("depth_strider_book", new CustomEnchantedBookItem("depth_strider", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_EFFICIENCY = registerItem("efficiency_book", new CustomEnchantedBookItem("efficiency", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item BOOK_FORTUNE = registerItem("fortune_book", new CustomEnchantedBookItem("fortune", new FabricItemSettings().rarity(Rarity.UNCOMMON).maxCount(1)));

    public static final Item GREECE_CORE = registerItem("greece_core", new Item(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item ARTEMIS_CORE = registerItem("artemis_core", new ArtemisCore(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item ARTEMIS_RUNE = registerItem("artemis_rune", new Item(new FabricItemSettings().rarity(Rarity.RARE)));

    public static final Item EGYPT_CORE = registerItem("egypt_core", new Item(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item ANUBIS_CORE = registerItem("anubis_core", new AnubisCore(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item ANUBIS_RUNE = registerItem("anubis_rune", new Item(new FabricItemSettings().rarity(Rarity.RARE)));

    public static final Item ROME_CORE = registerItem("rome_core", new Item(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item MERCURY_CORE = registerItem("mercury_core", new MercuryCore(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item MERCURY_RUNE = registerItem("mercury_rune", new Item(new FabricItemSettings().rarity(Rarity.RARE)));

    public static final Item CHINA_CORE = registerItem("china_core", new Item(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item WUKONG_CORE = registerItem("wukong_core", new WukongCore(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item WUKONG_RUNE = registerItem("wukong_rune", new Item(new FabricItemSettings().rarity(Rarity.RARE)));

    public static final Item NORDIC_CORE = registerItem("nordic_core", new Item(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item LOKI_CORE = registerItem("loki_core", new LokiCore(new FabricItemSettings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item LOKI_RUNE = registerItem("loki_rune", new Item(new FabricItemSettings().rarity(Rarity.RARE)));

    public static final Item ARTEMIS_BOW = registerItem("artemis_bow", new ArtemisBowItem(new Item.Settings().maxDamage(2031).rarity(Rarity.EPIC)));
    public static final Item ANUBIS_ANKH = registerItem("anubis_ankh", new AnubisAnkhItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item MERCURY_BOOTS = registerItem("mercury_boots", new MercuryBoots(ModArmorMaterials.MERCURY, ArmorItem.Type.BOOTS, new FabricItemSettings().maxDamage(481).rarity(Rarity.EPIC)));
    public static final Item LOKI_NECKLACE = registerItem("loki_necklace", new LokiNecklaceItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item WUKONG_SWORD = registerItem("wukong_sword", new WukongSwordItem(6, -1.5F, new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(EGYPT_COIN);
        entries.add(PAPYRUS);
        entries.add(CHINA_KEY_FRAGMENT);
        entries.add(CHINA_COIN);
        entries.add(GREECE_COIN);
        entries.add(ROME_COIN);
        entries.add(ROME_KEY_FRAGMENT);
        entries.add(BOOK_SHARPNESS);
        entries.add(BOOK_POWER);
        entries.add(BOOK_FIRE_ASPECT);
        entries.add(BOOK_UNBREAKING);
        entries.add(BOOK_PROTECTION);
        entries.add(BOOK_FEATHER_FALLING);
        entries.add(BOOK_THORNS);
        entries.add(BOOK_FROST_WALKER);
        entries.add(BOOK_DEPTH_STRIDER);
        entries.add(BOOK_EFFICIENCY);
        entries.add(BOOK_FORTUNE);

    }

    private static void addItemsToToolTabItemGroup(FabricItemGroupEntries entries){
        entries.add(EGYPT_KEY);
        entries.add(CHINA_KEY);
        entries.add(GREECE_KEY);
        entries.add(ROME_KEY);
        entries.add(HIGH_MOUNTAINS_AND_FLOWING_WATER_MUSIC_DISC);
        entries.add(AUTUMN_MOON_OVER_HAN_PALACE_MUSIC_DISC);
    }

    private static void addItemsToBuildTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModBlocks.SMOOTH_LIMESTONE);
        entries.add(ModBlocks.LIMESTONE);
        entries.add(ModBlocks.ROUGH_LIMESTONE);
        entries.add(ModBlocks.LIMESTONE_BRICKS);
        entries.add(ModBlocks.LIMESTONE_PILLAR);
    }

    private static void addItemsToRedstoneTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModBlocks.EGYPT_DOOR);
    }

    private static void addItemsToFunctionalTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModBlocks.TRADING_STATION);
        entries.add(ModBlocks.TOMBSTONE);
        entries.add(ModBlocks.COFFIN_TOP);
        entries.add(ModBlocks.COFFIN_BOTTOM);
        entries.add(ModBlocks.CHINA_CHEST);
        entries.add(ModBlocks.GREECE_CHEST);
        entries.add(ModBlocks.ROME_CHEST);
    }

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModItems.EGYPT_CROOK);
        entries.add(ModItems.NORDIC_BATTLE_AXE);
        entries.add(ModItems.CHINA_SWORD);
        entries.add(ModItems.GREECE_SWORD);
        entries.add(ModItems.ROME_SWORD);
        entries.add(ModItems.ARTEMIS_BOW);
        entries.add(ModItems.MERCURY_BOOTS);
        entries.add(ModItems.WUKONG_SWORD);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Civilizations.MOD_ID,name),item);
    }

    public static void registerModItems() {
        Civilizations.LOGGER.info("Registering Mod Items for " + Civilizations.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBuildTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(ModItems::addItemsToRedstoneTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItems::addItemsToFunctionalTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatTabItemGroup);
    }
}
