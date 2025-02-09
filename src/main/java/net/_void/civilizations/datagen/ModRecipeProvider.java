package net._void.civilizations.datagen;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> SMOOTH_LIMESTONE_SMELTABLES = List.of(ModBlocks.LIMESTONE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, SMOOTH_LIMESTONE_SMELTABLES, RecipeCategory.BUILDING_BLOCKS,ModBlocks.SMOOTH_LIMESTONE,
                0.1f,200,"limestone");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,ModBlocks.LIMESTONE_BRICKS,4).
                pattern("##").
                pattern("##").
                input('#',ModBlocks.SMOOTH_LIMESTONE).
                criterion(hasItem(ModBlocks.SMOOTH_LIMESTONE),conditionsFromItem(ModBlocks.SMOOTH_LIMESTONE)).
                offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,ModBlocks.LIMESTONE_PILLAR,2).
                pattern("#").
                pattern("#").
                input('#',ModBlocks.SMOOTH_LIMESTONE).
                criterion(hasItem(ModBlocks.SMOOTH_LIMESTONE),conditionsFromItem(ModBlocks.SMOOTH_LIMESTONE)).
                offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BOOK,1).
                input(Items.LEATHER).
                input(ModItems.PAPYRUS,3).
                criterion(hasItem(ModItems.PAPYRUS),conditionsFromItem(ModItems.PAPYRUS)).
                criterion(hasItem(Items.LEATHER),conditionsFromItem(Items.LEATHER)).
                offerTo(exporter, new Identifier(getRecipeName(Items.BOOK) + "papyrus_book"));
    }
}
