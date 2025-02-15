package net._void.civilizations.datagen;

import net._void.civilizations.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.SMOOTH_LIMESTONE,drops(ModBlocks.SMOOTH_LIMESTONE,ModBlocks.LIMESTONE));
        addDrop(ModBlocks.LIMESTONE);
        addDrop(ModBlocks.LIMESTONE_PILLAR);
        addDrop(ModBlocks.LIMESTONE_BRICKS);
        addDrop(ModBlocks.TOMBSTONE);
        addDrop(ModBlocks.CHINA_LANTERN);
        addDrop(ModBlocks.CHINA_STRING);
    }
}
