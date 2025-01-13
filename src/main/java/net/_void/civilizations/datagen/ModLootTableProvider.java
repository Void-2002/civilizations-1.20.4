package net._void.civilizations.datagen;

import net._void.civilizations.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.LIMESTONE);
        addDrop(ModBlocks.SMOOTH_LIMESTONE);
        addDrop(ModBlocks.SANDSTONE_PILLAR);
        addDrop(ModBlocks.SANDSTONE_BRICKS);
    }
}
