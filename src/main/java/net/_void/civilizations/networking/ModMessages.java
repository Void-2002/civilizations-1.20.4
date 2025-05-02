package net._void.civilizations.networking;

import net._void.civilizations.Civilizations;
import net._void.civilizations.networking.packet.BossMusicPlayS2CPacket;
import net._void.civilizations.networking.packet.TradeC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier BOSS_MUSIC_PLAY = new Identifier(Civilizations.MOD_ID, "boss_music_play");
    public static final Identifier TRADE = new Identifier(Civilizations.MOD_ID, "trade");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(TRADE, TradeC2SPacket::receive);
    }

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(BOSS_MUSIC_PLAY, BossMusicPlayS2CPacket::receive);
    }
}
