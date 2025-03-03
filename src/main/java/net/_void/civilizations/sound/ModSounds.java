package net._void.civilizations.sound;

import net._void.civilizations.Civilizations;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent HIGH_MOUNTAINS_AND_FLOWING_WATER = registerSoundEvent("high_mountains_and_flowing_water");
    public static final SoundEvent AUTUMN_MOON_OVER_HAN_PALACE = registerSoundEvent("autumn_moon_over_han_palace");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Civilizations.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Civilizations.LOGGER.info("Registering Mod Sounds for " + Civilizations.MOD_ID);
    }
}
