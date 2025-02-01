package net._void.civilizations.util;

import net._void.civilizations.Civilizations;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> EGYPT_KEY_OPENABLE =
                createTag("egypt_key_openable");
        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Civilizations.MOD_ID,name));
        }
    }

    public static class Items{

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Civilizations.MOD_ID,name));
        }
    }
}
