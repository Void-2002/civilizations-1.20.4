package net._void.civilizations.item;

import net._void.civilizations.Civilizations;
import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.item.custom.Key_egypt;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item KEY_EGYPT = registerItem("key_egypt",new Key_egypt(new FabricItemSettings()));
    public static final Item COIN_EGYPT = registerItem("coin_egypt", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(COIN_EGYPT);
    }

    private static void addItemsToToolTabItemGroup(FabricItemGroupEntries entries){
        entries.add(KEY_EGYPT);
    }

    private static void addItemsToBuildTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModBlocks.SMOOTH_LIMESTONE);
        entries.add(ModBlocks.LIMESTONE);
        entries.add(ModBlocks.ROUGH_LIMESTONE);
        entries.add(ModBlocks.SANDSTONE_BRICKS);
        entries.add(ModBlocks.SANDSTONE_PILLAR);
    }

    private static void addItemsToRedstoneTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModBlocks.EGYPT_DOOR);
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
    }
}
