package net._void.civilizations.sound;

import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class CustomSoundInstance extends MovingSoundInstance {
    private final LivingEntity entity;

    public CustomSoundInstance(LivingEntity entity, SoundEvent soundEvent, SoundCategory soundCategory) {
        super(soundEvent, soundCategory, SoundInstance.createRandom());
        this.entity = entity;
        this.volume = 1.0f;
        this.pitch = 1.0f;
        this.repeat = true;
        this.setPositionToEntity();
    }

    @Override
    public void tick() {
        if (this.entity == null || this.entity.isRemoved() || this.entity.isDead()) {
            this.setDone();
            return;
        }
        this.setPositionToEntity();
    }

    @Override
    public boolean shouldAlwaysPlay() {
        return true;
    }

    private void setPositionToEntity() {
        this.x = this.entity.getX();
        this.y = this.entity.getY();
        this.z = this.entity.getZ();
    }
}
