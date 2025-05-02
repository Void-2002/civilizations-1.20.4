package net._void.civilizations;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.client.*;
import net._void.civilizations.networking.ModMessages;
import net._void.civilizations.screen.ModScreenHandlers;
import net._void.civilizations.screen.TradingStationScreen;
import net._void.civilizations.screen.TombstoneScreen;
import net._void.civilizations.sound.CustomSoundInstance;
import net._void.civilizations.sound.ModSounds;
import net._void.civilizations.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.SoundCategory;

public class CivilizationsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EGYPT_DOOR, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.EGYPT_CIVILIAN, EgyptCivilianRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EGYPT_CIVILIAN, EgyptCivilianModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.EGYPT_NPC, EgyptNpcRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EGYPT_NPC, EgyptNpcModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.EGYPT_BOSS, EgyptBossRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EGYPT_BOSS, EgyptBossModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CHINA_CIVILIAN, ChinaCivilianRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CHINA_CIVILIAN, ChinaCivilianModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CHINA_GUARD, ChinaGuardRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CHINA_GUARD, ChinaGuardModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.CHINA_BOSS, ChinaBossRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CHINA_BOSS, ChinaBossModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.NORDIC_CIVILIAN, NordicCivilianRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.NORDIC_CIVILIAN, NordicCivilianModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.NORDIC_BOSS, NordicBossRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.NORDIC_BOSS, NordicBossModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.GREECE_CIVILIAN, GreeceCivilianRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GREECE_CIVILIAN, GreeceCivilianModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.GREECE_BOSS, GreeceBossRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GREECE_BOSS, GreeceBossModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ROME_CIVILIAN, RomeCivilianRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ROME_CIVILIAN, RomeCivilianModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ROME_BOSS, RomeBossRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ROME_BOSS, RomeBossModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.ROME_GUARD, RomeGuardRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ROME_GUARD, RomeGuardModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BOSS_ARTEMIS, BossArtemisRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOSS_ARTEMIS, BossArtemisModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BOSS_ANUBIS, BossAnubisRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOSS_ANUBIS, BossAnubisModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BOSS_MERCURY, BossMercuryRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOSS_MERCURY, BossMercuryModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BOSS_LOKI, BossLokiRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOSS_LOKI, BossLokiModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BOSS_LOKI_CLONE, BossLokiCloneRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOSS_LOKI_CLONE, BossLokiCloneModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BOSS_WUKONG, BossWukongRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BOSS_WUKONG, BossWukongModel::getTexturedModelData);

        HandledScreens.register(ModScreenHandlers.TRADING_STATION_SCREEN_HANDLER, TradingStationScreen::new);
        HandledScreens.register(ModScreenHandlers.TOMBSTONE_SCREEN_HANDLER, TombstoneScreen::new);

        ModModelPredicates.registerModModels();
        ModMessages.registerS2CPackets();

    }
}
