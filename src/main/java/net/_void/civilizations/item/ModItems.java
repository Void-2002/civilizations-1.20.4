package net._void.civilizations.item;

import net._void.civilizations.Civilizations;
import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.item.custom.ChinaBattleAxe;
import net._void.civilizations.item.custom.ChinaKey;
import net._void.civilizations.item.custom.EgyptCrook;
import net._void.civilizations.item.custom.EgyptKey;
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
    public static final Item CHINA_BATTLE_AXE = registerItem("china_battle_axe", new ChinaBattleAxe(9, -3.0F, new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item CHINA_COIN = registerItem("china_coin", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item CHINA_SWORD = registerItem("china_sword", new SwordItem(ToolMaterials.NETHERITE, 2, -1.5F, new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item HIGH_MOUNTAINS_AND_FLOWING_WATER_MUSIC_DISC = registerItem("high_mountains_music_disc",
            new MusicDiscItem(7, ModSounds.HIGH_MOUNTAINS_AND_FLOWING_WATER, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 382));
    public static final Item AUTUMN_MOON_OVER_HAN_PALACE_MUSIC_DISC = registerItem("autumn_moon_music_disc",
            new MusicDiscItem(7, ModSounds.AUTUMN_MOON_OVER_HAN_PALACE, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 312));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(EGYPT_COIN);
        entries.add(PAPYRUS);
        entries.add(CHINA_KEY_FRAGMENT);
        entries.add(CHINA_COIN);
    }

    private static void addItemsToToolTabItemGroup(FabricItemGroupEntries entries){
        entries.add(EGYPT_KEY);
        entries.add(CHINA_KEY);
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
    }

    private static void addItemsToCombatTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModItems.EGYPT_CROOK);
        entries.add(ModItems.CHINA_BATTLE_AXE);
        entries.add(ModItems.CHINA_SWORD);
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
