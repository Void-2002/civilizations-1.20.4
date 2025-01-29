package net._void.civilizations.screen;

import net._void.civilizations.Civilizations;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<TradingStationScreenHandler> TRADING_STATION_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Civilizations.MOD_ID, "trading_station"),
                    new ExtendedScreenHandlerType<>(TradingStationScreenHandler::new));

    public static final ScreenHandlerType<TombstoneScreenHandler> TOMBSTONE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Civilizations.MOD_ID, "tombstone"),
                    new ExtendedScreenHandlerType<>(TombstoneScreenHandler::new));

    public static void registerScreenHandlers() {
        Civilizations.LOGGER.info("Registering Screen Handlers for " + Civilizations.MOD_ID);
    }
}
