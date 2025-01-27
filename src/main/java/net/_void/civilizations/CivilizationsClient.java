package net._void.civilizations;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.client.EgyptCivilianModel;
import net._void.civilizations.entity.client.ModModelLayers;
import net._void.civilizations.entity.client.EgyptCivilianRenderer;
import net._void.civilizations.screen.ModScreenHandlers;
import net._void.civilizations.screen.TradingStationScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class CivilizationsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EGYPT_DOOR, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.EGYPT_CIVILIAN, EgyptCivilianRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.EGYPT_CIVILIAN, EgyptCivilianModel::getTexturedModelData);

        HandledScreens.register(ModScreenHandlers.TRADING_STATION_SCREEN_HANDLER, TradingStationScreen::new);
    }
}
