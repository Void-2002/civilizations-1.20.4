package net._void.civilizations.entity;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.EgyptCivilianEntity;
import net._void.civilizations.entity.custom.EgyptNpcEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<EgyptCivilianEntity> EGYPT_CIVILIAN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "egypt_civilian"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EgyptCivilianEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
    public static final EntityType<EgyptNpcEntity> EGYPT_NPC = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "egypt_npc"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EgyptNpcEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
}
