package net._void.civilizations.item;

import net._void.civilizations.Civilizations;
import net._void.civilizations.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CIVILIZATIONS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Civilizations.MOD_ID, "civilizations"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.civilizations"))
                    .icon(() -> new ItemStack(ModItems.COIN_EGYPT)).entries((displayContext, entries) -> {

                        entries.add(ModItems.KEY_EGYPT);
                        entries.add(ModItems.COIN_EGYPT);
                        entries.add(ModBlocks.SMOOTH_LIMESTONE);
                        entries.add(ModBlocks.LIMESTONE);
                        entries.add(ModBlocks.ROUGH_LIMESTONE);
                        entries.add(ModBlocks.SANDSTONE_BRICKS);
                        entries.add(ModBlocks.SANDSTONE_PILLAR);
                        entries.add(ModBlocks.EGYPT_DOOR);




                    }).build());

    public static void registerItemGroups(){
        Civilizations.LOGGER.info("Registering Item Groups for "+Civilizations.MOD_ID);
    }
}
