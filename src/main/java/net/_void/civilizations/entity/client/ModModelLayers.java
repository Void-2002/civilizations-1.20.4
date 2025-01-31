package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer EGYPT_CIVILIAN =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"egypt_civilian"),"main");
    public static final EntityModelLayer EGYPT_NPC =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"egypt_npc"),"main");
    public static final EntityModelLayer EGYPT_BOSS =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"egypt_boss"),"main");
}
