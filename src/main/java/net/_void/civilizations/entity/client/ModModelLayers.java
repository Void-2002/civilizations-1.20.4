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

    public static final EntityModelLayer CHINA_CIVILIAN =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"china_civilian"),"main");
    public static final EntityModelLayer CHINA_GUARD =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"china_guard"),"main");
    public static final EntityModelLayer CHINA_BOSS =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"china_boss"),"main");

    public static final EntityModelLayer NORDIC_CIVILIAN =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"nordic_civilian"),"main");
    public static final EntityModelLayer NORDIC_BOSS =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"nordic_boss"),"main");

    public static final EntityModelLayer GREECE_CIVILIAN =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"greece_civilian"),"main");
    public static final EntityModelLayer GREECE_BOSS =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"greece_boss"),"main");

    public static final EntityModelLayer ROME_CIVILIAN =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"rome_civilian"),"main");
    public static final EntityModelLayer ROME_BOSS =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"rome_boss"),"main");
    public static final EntityModelLayer ROME_GUARD =
            new EntityModelLayer(new Identifier(Civilizations.MOD_ID,"rome_guard"),"main");
}
