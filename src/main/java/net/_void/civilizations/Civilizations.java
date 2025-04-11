package net._void.civilizations;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.block.entity.ModBlockEntities;
import net._void.civilizations.effect.ModEffects;
import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.custom.*;
import net._void.civilizations.item.ModItemGroups;
import net._void.civilizations.item.ModItems;
import net._void.civilizations.screen.ModScreenHandlers;
import net._void.civilizations.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Civilizations implements ModInitializer {
	public static final String MOD_ID = "civilizations";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModEffects.registerEffects();
		ModSounds.registerSounds();

		FabricDefaultAttributeRegistry.register(ModEntities.EGYPT_CIVILIAN, EgyptCivilianEntity.createCivilianAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.EGYPT_NPC, EgyptNpcEntity.createNpcAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.EGYPT_BOSS, EgyptBossEntity.createBossAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.CHINA_CIVILIAN, ChinaCivilianEntity.createCivilianAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CHINA_GUARD, ChinaGuardEntity.createGuardAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CHINA_BOSS, ChinaBossEntity.createBossAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.NORDIC_CIVILIAN, NordicCivilianEntity.createCivilianAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.NORDIC_BOSS, NordicBossEntity.createBossAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.GREECE_CIVILIAN, GreeceCivilianEntity.createCivilianAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.GREECE_BOSS, GreeceBossEntity.createBossAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.ROME_CIVILIAN, RomeCivilianEntity.createCivilianAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ROME_BOSS, RomeBossEntity.createBossAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ROME_GUARD, RomeGuardEntity.createCivilianAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.BOSS_ARTEMIS, BossArtemisEntity.createBossAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BOSS_ANUBIS, BossAnubisEntity.createBossAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BOSS_MERCURY, BossMercuryEntity.createBossAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BOSS_LOKI, BossLokiEntity.createBossAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BOSS_LOKI_CLONE, BossLokiCloneEntity.createBossAttributes());
	}
}