package net._void.civilizations.datagen;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIMESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_LIMESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROUGH_LIMESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIMESTONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LIMESTONE_PILLAR);
        blockStateModelGenerator.registerDoor(ModBlocks.EGYPT_DOOR);

        blockStateModelGenerator.registerSimpleState(ModBlocks.TRADING_STATION);
        blockStateModelGenerator.registerSimpleState(ModBlocks.TOMBSTONE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.COFFIN_TOP);
        blockStateModelGenerator.registerSimpleState(ModBlocks.COFFIN_BOTTOM);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.EGYPT_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGYPT_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PAPYRUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHINA_KEY_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHINA_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHINA_BATTLE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CHINA_COIN, Models.GENERATED);

    }
}
