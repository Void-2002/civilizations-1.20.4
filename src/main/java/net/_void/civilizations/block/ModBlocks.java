package net._void.civilizations.block;

import net._void.civilizations.Civilizations;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block SANDSTONE_PILLAR = registerBlock("sandstone_pillar", new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
    public static final Block SANDSTONE_BRICKS = registerBlock("sandstone_bricks", new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
    public static final Block LIMESTONE = registerBlock("limestone", new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE).strength(2.0F)));
    public static final Block SMOOTH_LIMESTONE = registerBlock("smooth_limestone", new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE).strength(4.0F)));
    public static final Block ROUGH_LIMESTONE = registerBlock("rough_limestone", new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE).strength(-1.0F, 3600000.0F)));
    public static final Block EGYPT_DOOR = registerBlock("egypt_door", new DoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_DOOR).strength(-1.0F).instrument(Instrument.BASEDRUM), BlockSetType.IRON));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK,new Identifier(Civilizations.MOD_ID,name),block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Civilizations.MOD_ID,name),
                new BlockItem(block,new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        Civilizations.LOGGER.info("Registering ModBlocks for "+Civilizations.MOD_ID);
    }
}
