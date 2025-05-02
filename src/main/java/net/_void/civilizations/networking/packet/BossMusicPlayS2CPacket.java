package net._void.civilizations.networking.packet;

import net._void.civilizations.sound.CustomSoundInstance;
import net._void.civilizations.sound.ModSounds;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;

import java.util.HashMap;
import java.util.Map;

public class BossMusicPlayS2CPacket {
    private static Map<String, Map<String, CustomSoundInstance>> map = new HashMap<>();

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender){
        if(client.player == null) return;
        switch(buf.readString()){
            case ("EgyptBossPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("EgyptBoss", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.EGYPT_BOSS_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("EgyptBoss")){
                    map.get(client.player.getUuidAsString()).put("EgyptBoss",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.EGYPT_BOSS_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("EgyptBoss"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("EgyptBoss"));
                }
            }
            case ("EgyptBossStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("EgyptBoss")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("EgyptBoss"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("EgyptBoss"));
                        map.get(client.player.getUuidAsString()).remove("EgyptBoss");
                    }
                }
            }
            case ("ChinaBossPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("ChinaBoss", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.CHINA_BOSS_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("ChinaBoss")){
                    map.get(client.player.getUuidAsString()).put("ChinaBoss",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.CHINA_BOSS_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("ChinaBoss"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("ChinaBoss"));
                }
            }
            case ("ChinaBossStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("ChinaBoss")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("ChinaBoss"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("ChinaBoss"));
                        map.get(client.player.getUuidAsString()).remove("ChinaBoss");
                    }
                }
            }
            case ("NordicBossPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("NordicBoss", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.NORDIC_BOSS_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("NordicBoss")){
                    map.get(client.player.getUuidAsString()).put("NordicBoss",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.NORDIC_BOSS_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("NordicBoss"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("NordicBoss"));
                }
            }
            case ("NordicBossStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("NordicBoss")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("NordicBoss"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("NordicBoss"));
                        map.get(client.player.getUuidAsString()).remove("NordicBoss");
                    }
                }
            }
            case ("RomeBossPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("RomeBoss", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.ROME_BOSS_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("RomeBoss")){
                    map.get(client.player.getUuidAsString()).put("RomeBoss",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.ROME_BOSS_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("RomeBoss"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("RomeBoss"));
                }
            }
            case ("RomeBossStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("RomeBoss")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("RomeBoss"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("RomeBoss"));
                        map.get(client.player.getUuidAsString()).remove("RomeBoss");
                    }
                }
            }
            case ("GreeceBossPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("EgyptBoss", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.GREECE_BOSS_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("GreeceBoss")){
                    map.get(client.player.getUuidAsString()).put("GreeceBoss",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.GREECE_BOSS_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("GreeceBoss"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("GreeceBoss"));
                }
            }
            case ("GreeceBossStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("GreeceBoss")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("GreeceBoss"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("GreeceBoss"));
                        map.get(client.player.getUuidAsString()).remove("GreeceBoss");
                    }
                }
            }
            case ("EgyptGodPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("EgyptBoss", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.EGYPT_GOD_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("EgyptGod")){
                    map.get(client.player.getUuidAsString()).put("EgyptGod",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.EGYPT_GOD_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("EgyptGod"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("EgyptGod"));
                }
            }
            case ("EgyptGodStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("EgyptGod")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("EgyptGod"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("EgyptGod"));
                        map.get(client.player.getUuidAsString()).remove("EgyptGod");
                    }
                }
            }
            case ("ChinaGodPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("ChinaGod", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.CHINA_GOD_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("ChinaGod")){
                    map.get(client.player.getUuidAsString()).put("ChinaGod",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.CHINA_GOD_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("ChinaGod"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("ChinaGod"));
                }
            }
            case ("ChinaGodStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("ChinaGod")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("ChinaGod"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("ChinaGod"));
                        map.get(client.player.getUuidAsString()).remove("ChinaGod");
                    }
                }
            }
            case ("NordicGodPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("NordicGod", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.NORDIC_GOD_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("NordicGod")){
                    map.get(client.player.getUuidAsString()).put("NordicGod",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.NORDIC_GOD_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("NordicGod"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("NordicGod"));
                }
            }
            case ("NordicGodStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("NordicGod")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("NordicGod"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("NordicGod"));
                        map.get(client.player.getUuidAsString()).remove("NordicGod");
                    }
                }
            }
            case ("RomeGodPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("RomeGod", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.ROME_GOD_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("RomeGod")){
                    map.get(client.player.getUuidAsString()).put("RomeGod",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.ROME_GOD_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("RomeGod"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("RomeGod"));
                }
            }
            case ("RomeGodStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("RomeGod")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("RomeGod"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("RomeGod"));
                        map.get(client.player.getUuidAsString()).remove("RomeGod");
                    }
                }
            }
            case ("GreeceGodPlay") ->{
                if (!map.containsKey(client.player.getUuidAsString())){
                    map.put(client.player.getUuidAsString(), new HashMap<>(){{
                        put("GreeceGod", new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.GREECE_GOD_MUSIC, SoundCategory.MASTER));
                    }});
                }else if (!map.get(client.player.getUuidAsString()).containsKey("GreeceGod")){
                    map.get(client.player.getUuidAsString()).put("GreeceGod",  new CustomSoundInstance(MinecraftClient.getInstance().player, ModSounds.GREECE_GOD_MUSIC, SoundCategory.MASTER));
                }else if(!client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("GreeceGod"))){
                    client.getSoundManager().play(map.get(client.player.getUuidAsString()).get("GreeceGod"));
                }
            }
            case ("GreeceGodStop") ->{
                if (map.containsKey(client.player.getUuidAsString()) && map.get(client.player.getUuidAsString()).containsKey("GreeceGod")){
                    if(client.getSoundManager().isPlaying(map.get(client.player.getUuidAsString()).get("GreeceGod"))) {
                        client.getSoundManager().stop(map.get(client.player.getUuidAsString()).get("GreeceGod"));
                        map.get(client.player.getUuidAsString()).remove("GreeceGod");
                    }
                }
            }
        }
    }
}
