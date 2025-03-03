package net._void.civilizations.datagen;

import net._void.civilizations.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(ModItems.HIGH_MOUNTAINS_AND_FLOWING_WATER_MUSIC_DISC)
                .add(ModItems.AUTUMN_MOON_OVER_HAN_PALACE_MUSIC_DISC);

        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.HIGH_MOUNTAINS_AND_FLOWING_WATER_MUSIC_DISC)
                .add(ModItems.AUTUMN_MOON_OVER_HAN_PALACE_MUSIC_DISC);
    }
}
