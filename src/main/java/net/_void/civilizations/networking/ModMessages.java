package net._void.civilizations.networking;

import net._void.civilizations.Civilizations;
import net._void.civilizations.networking.packet.BossMusicPlayS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier BOSS_MUSIC_PLAY = new Identifier(Civilizations.MOD_ID, "boss_music_play");

    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(BOSS_MUSIC_PLAY, BossMusicPlayS2CPacket::receive);
    }
}
