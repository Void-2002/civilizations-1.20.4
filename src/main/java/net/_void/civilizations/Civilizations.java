package net._void.civilizations;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.client.EgyptCivilianModel;
import net._void.civilizations.entity.custom.EgyptCivilianEntity;
import net._void.civilizations.item.ModItemGroups;
import net._void.civilizations.item.ModItems;
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
		FabricDefaultAttributeRegistry.register(ModEntities.EGYPT_CIVILIAN, EgyptCivilianEntity.createCivilianAttributes());
	}
}