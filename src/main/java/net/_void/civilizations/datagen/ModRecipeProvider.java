package net._void.civilizations.datagen;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> SMOOTH_LIMESTONE_SMELTABLES = List.of(ModBlocks.LIMESTONE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, SMOOTH_LIMESTONE_SMELTABLES, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_LIMESTONE,
                0.1f, 200, "limestone");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BRICKS, 4).
                pattern("##").
                pattern("##").
                input('#', ModBlocks.SMOOTH_LIMESTONE).
                criterion(hasItem(ModBlocks.SMOOTH_LIMESTONE), conditionsFromItem(ModBlocks.SMOOTH_LIMESTONE)).
                offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_PILLAR, 2).
                pattern("#").
                pattern("#").
                input('#', ModBlocks.SMOOTH_LIMESTONE).
                criterion(hasItem(ModBlocks.SMOOTH_LIMESTONE), conditionsFromItem(ModBlocks.SMOOTH_LIMESTONE)).
                offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BOOK, 1).
                input(Items.LEATHER).
                input(ModItems.PAPYRUS, 3).
                criterion(hasItem(ModItems.PAPYRUS), conditionsFromItem(ModItems.PAPYRUS)).
                criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER)).
                offerTo(exporter, new Identifier(getRecipeName(Items.BOOK) + "papyrus_book"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CHINA_KEY, 1).
                pattern("###").
                pattern("###").
                pattern("###").
                input('#', ModItems.CHINA_KEY_FRAGMENT).
                criterion(hasItem(ModItems.CHINA_KEY_FRAGMENT), conditionsFromItem(ModItems.CHINA_KEY_FRAGMENT)).
                offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.ROME_KEY, 1).
                pattern("###").
                pattern("###").
                pattern("###").
                input('#', ModItems.ROME_KEY_FRAGMENT).
                criterion(hasItem(ModItems.ROME_KEY_FRAGMENT), conditionsFromItem(ModItems.ROME_KEY_FRAGMENT)).
                offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ARTEMIS_CORE, 1).
                pattern("#R#").
                pattern("RCR").
                pattern("#R#").
                input('C', ModItems.GREECE_CORE).
                input('R', ModItems.ARTEMIS_RUNE).
                input('#', Items.SPECTRAL_ARROW).
                criterion(hasItem(ModItems.GREECE_CORE), conditionsFromItem(ModItems.GREECE_CORE)).
                criterion(hasItem(ModItems.ARTEMIS_RUNE), conditionsFromItem(ModItems.ARTEMIS_RUNE)).
                offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ANUBIS_CORE, 1).
                pattern("#R#").
                pattern("RCR").
                pattern("#R#").
                input('C', ModItems.EGYPT_CORE).
                input('R', ModItems.ANUBIS_RUNE).
                input('#', Items.SKELETON_SKULL).
                criterion(hasItem(ModItems.EGYPT_CORE), conditionsFromItem(ModItems.EGYPT_CORE)).
                criterion(hasItem(ModItems.ANUBIS_RUNE), conditionsFromItem(ModItems.ANUBIS_RUNE)).
                offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MERCURY_CORE, 1).
                pattern("#R#").
                pattern("RCR").
                pattern("#R#").
                input('C', ModItems.ROME_CORE).
                input('R', ModItems.MERCURY_RUNE).
                input('#', Items.FEATHER).
                criterion(hasItem(ModItems.ROME_CORE), conditionsFromItem(ModItems.ROME_CORE)).
                criterion(hasItem(ModItems.MERCURY_RUNE), conditionsFromItem(ModItems.MERCURY_RUNE)).
                offerTo(exporter);
    }
}
