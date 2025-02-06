package net._void.civilizations.item;

import net._void.civilizations.Civilizations;
import net._void.civilizations.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CIVILIZATIONS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Civilizations.MOD_ID, "civilizations"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.civilizations"))
                    .icon(() -> new ItemStack(ModItems.EGYPT_COIN)).entries((displayContext, entries) -> {

                        entries.add(ModItems.EGYPT_KEY);
                        entries.add(ModItems.EGYPT_COIN);
                        entries.add(ModBlocks.SMOOTH_LIMESTONE);
                        entries.add(ModBlocks.LIMESTONE);
                        entries.add(ModBlocks.ROUGH_LIMESTONE);
                        entries.add(ModBlocks.LIMESTONE_BRICKS);
                        entries.add(ModBlocks.LIMESTONE_PILLAR);
                        entries.add(ModBlocks.EGYPT_DOOR);
                        entries.add(ModItems.BLANK_PAPYRUS);
                        entries.add(ModItems.PAPYRUS);
                        entries.add(ModBlocks.TRADING_STATION);
                        entries.add(ModBlocks.TOMBSTONE);
                        entries.add(ModBlocks.COFFIN_TOP);
                        entries.add(ModBlocks.COFFIN_BOTTOM);
                        entries.add(ModItems.EGYPT_CROOK);


                    }).build());

    public static void registerItemGroups(){
        Civilizations.LOGGER.info("Registering Item Groups for "+Civilizations.MOD_ID);
    }
}
