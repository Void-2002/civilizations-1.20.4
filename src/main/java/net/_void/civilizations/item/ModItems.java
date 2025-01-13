package net._void.civilizations.item;

import net._void.civilizations.Civilizations;
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
    //public static final Item novo_item = registerItem("novo_item", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries){
        entries.add(KEY_EGYPT);
        //entires.add(novo item)
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Civilizations.MOD_ID,name),item);
    }

    public static void registerModItems() {
        Civilizations.LOGGER.info("Registering Mod Items for " + Civilizations.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToIngredientTabItemGroup);
    }
}
