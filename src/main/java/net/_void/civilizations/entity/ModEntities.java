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
    public static final EntityType<RomeGuardEntity> ROME_GUARD = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "rome_guard"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RomeGuardEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 2f)).build());

    public static final EntityType<BossArtemisEntity> BOSS_ARTEMIS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "boss_artemis"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BossArtemisEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 4f)).build());
    public static final EntityType<BossAnubisEntity> BOSS_ANUBIS = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "boss_anubis"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BossAnubisEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 4f)).build());
    public static final EntityType<BossMercuryEntity> BOSS_MERCURY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "boss_mercury"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BossMercuryEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 4f)).build());
    public static final EntityType<BossLokiEntity> BOSS_LOKI = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "boss_loki"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BossLokiEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 4f)).build());
    public static final EntityType<BossLokiCloneEntity> BOSS_LOKI_CLONE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Civilizations.MOD_ID, "boss_loki_clone"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BossLokiCloneEntity::new)
                    .dimensions(EntityDimensions.fixed(2f, 4f)).build());
}
