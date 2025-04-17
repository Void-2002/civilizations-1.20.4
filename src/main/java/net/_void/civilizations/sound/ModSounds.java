package net._void.civilizations.sound;

import net._void.civilizations.Civilizations;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent HIGH_MOUNTAINS_AND_FLOWING_WATER = registerSoundEvent("high_mountains_and_flowing_water");
    public static final SoundEvent AUTUMN_MOON_OVER_HAN_PALACE = registerSoundEvent("autumn_moon_over_han_palace");
    public static final SoundEvent EGYPT_BOSS_MUSIC = registerSoundEvent("egypt_boss_music");
    public static final SoundEvent EGYPT_GOD_MUSIC = registerSoundEvent("egypt_god_music");
    public static final SoundEvent CHINA_BOSS_MUSIC = registerSoundEvent("china_boss_music");
    public static final SoundEvent CHINA_GOD_MUSIC = registerSoundEvent("china_god_music");
    public static final SoundEvent NORDIC_BOSS_MUSIC = registerSoundEvent("nordic_boss_music");
    public static final SoundEvent NORDIC_GOD_MUSIC = registerSoundEvent("nordic_god_music");
    public static final SoundEvent GREECE_BOSS_MUSIC = registerSoundEvent("greece_boss_music");
    public static final SoundEvent GREECE_GOD_MUSIC = registerSoundEvent("greece_god_music");
    public static final SoundEvent ROME_BOSS_MUSIC = registerSoundEvent("rome_boss_music");
    public static final SoundEvent ROME_GOD_MUSIC = registerSoundEvent("rome_god_music");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Civilizations.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Civilizations.LOGGER.info("Registering Mod Sounds for " + Civilizations.MOD_ID);
    }
}
