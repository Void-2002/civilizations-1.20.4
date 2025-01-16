package net._void.civilizations.datagen;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.EGYPT_KEY_DOOR).
                add(ModBlocks.EGYPT_DOOR);
                //.forceAddTag();

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).
                add(ModBlocks.SMOOTH_LIMESTONE).
                add(ModBlocks.LIMESTONE).
                add(ModBlocks.LIMESTONE_BRICKS).
                add(ModBlocks.LIMESTONE_PILLAR);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).
                add(ModBlocks.SMOOTH_LIMESTONE).
                add(ModBlocks.LIMESTONE);
    }
}
