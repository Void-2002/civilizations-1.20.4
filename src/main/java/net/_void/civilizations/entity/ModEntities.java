package net._void.civilizations.entity;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.*;
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
    public static final EntityType<EgyptBossEntity> EGYPT_BOSS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "egypt_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EgyptBossEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    public static final EntityType<ChinaCivilianEntity> CHINA_CIVILIAN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "china_civilian"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ChinaCivilianEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
    public static final EntityType<ChinaGuardEntity> CHINA_GUARD = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "china_guard"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ChinaGuardEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
    public static final EntityType<ChinaBossEntity> CHINA_BOSS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "china_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ChinaBossEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    public static final EntityType<NordicCivilianEntity> NORDIC_CIVILIAN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "nordic_civilian"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NordicCivilianEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
    public static final EntityType<NordicBossEntity> NORDIC_BOSS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "nordic_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NordicBossEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    public static final EntityType<GreeceCivilianEntity> GREECE_CIVILIAN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "greece_civilian"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GreeceCivilianEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
    public static final EntityType<GreeceBossEntity> GREECE_BOSS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "greece_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GreeceBossEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    public static final EntityType<RomeCivilianEntity> ROME_CIVILIAN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "rome_civilian"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RomeCivilianEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    public static final EntityType<RomeBossEntity> ROME_BOSS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "rome_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RomeBossEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());
}
