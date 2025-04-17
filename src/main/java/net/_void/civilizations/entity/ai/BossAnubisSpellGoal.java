package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.BossAnubisEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.world.World;

public class BossAnubisSpellGoal  extends Goal {
    private final BossAnubisEntity entity;
    private int timer = 0;

    public BossAnubisSpellGoal(PathAwareEntity mob) {
        entity = ((BossAnubisEntity) mob);
    }

    public boolean canStart() {
        return this.entity.getTarget() != null;
    }

    @Override
    public void start() {
        super.start();
        this.timer = 0;
    }

    @Override
    public void stop() {
        this.entity.setSpelling(false);
        this.entity.spellAnimationTimeout = 0;
        super.stop();
    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity livingEntity = this.entity.getTarget();
        if (livingEntity != null) {
            double d = (double)64.0F;
            if (livingEntity.squaredDistanceTo(this.entity) < (double)4096.0F && this.entity.canSee(livingEntity)) {
                World world = this.entity.getWorld();
                ++this.timer;
                if (this.timer == 20) {
                    this.timer = -300;
                    for(int i=0;i<=3;i++){
                        int x = (int)(Math.random() * 21 - 10);
                        int z = (int)(Math.random() * 21 - 10);
                        HuskEntity husk1 = ((EntityType<HuskEntity>) EntityType.get("minecraft:husk").get()).create(world);
                        husk1.updatePosition(this.entity.getX() + x, 199.5, this.entity.getZ() + z);
                        world.spawnEntity(husk1);
                        husk1.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 0, false, false));
                        int x2 = (int)(Math.random() * 21 - 10);
                        int z2 = (int)(Math.random() * 21 - 10);
                        WitherSkeletonEntity skeleton1 = ((EntityType<WitherSkeletonEntity>) EntityType.get("minecraft:wither_skeleton").get()).create(world);
                        skeleton1.updatePosition(this.entity.getX() + x2, 199.5, this.entity.getZ() + z2);
                        world.spawnEntity(skeleton1);
                        skeleton1.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 0, false, false));
                    }
                }
            } else if (this.timer >= 0) {
                --this.timer;
            }

            this.entity.setSpelling(this.timer > 0);
        }
    }
}
