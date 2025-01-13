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
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SANDSTONE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SANDSTONE_PILLAR);
        blockStateModelGenerator.registerDoor(ModBlocks.EGYPT_DOOR);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.KEY_EGYPT, Models.GENERATED);
    }
}
