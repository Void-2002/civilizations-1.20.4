package net._void.civilizations.datagen;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

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
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CHINA_CHEST);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.GREECE_CHEST);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ROME_CHEST);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.EGYPT_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGYPT_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PAPYRUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHINA_KEY_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHINA_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.NORDIC_BATTLE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CHINA_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHINA_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HIGH_MOUNTAINS_AND_FLOWING_WATER_MUSIC_DISC, Models.GENERATED);
        itemModelGenerator.register(ModItems.AUTUMN_MOON_OVER_HAN_PALACE_MUSIC_DISC, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREECE_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREECE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GREECE_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROME_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROME_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROME_KEY_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROME_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BOOK_SHARPNESS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_POWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_FIRE_ASPECT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_UNBREAKING, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_PROTECTION, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_FEATHER_FALLING, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_THORNS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_FROST_WALKER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_DEPTH_STRIDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_EFFICIENCY, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOOK_FORTUNE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GREECE_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARTEMIS_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARTEMIS_RUNE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EGYPT_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANUBIS_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANUBIS_RUNE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROME_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MERCURY_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MERCURY_RUNE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHINA_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WUKONG_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WUKONG_RUNE, Models.GENERATED);
        itemModelGenerator.register(ModItems.NORDIC_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOKI_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOKI_RUNE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANUBIS_ANKH, Models.GENERATED);
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MERCURY_BOOTS));
        itemModelGenerator.register(ModItems.LOKI_NECKLACE, Models.GENERATED);
    }
}
