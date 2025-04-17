package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.GreeceBossEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;

public class GreeceBossDeffendGoal extends Goal {
    private final GreeceBossEntity entity;
    public int cooldown;

    public GreeceBossDeffendGoal(PathAwareEntity mob) {
        entity = ((GreeceBossEntity) mob);
    }

    public boolean canStart() {
        return this.entity.getTarget() != null;
    }

    @Override
    public void start() {
        super.start();
        this.cooldown = 0;
    }

    @Override
    public void stop() {
        this.entity.setDeffending(false);
        this.entity.deffendAnimationTimeout = 0;
        super.stop();
    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity livingEntity = this.entity.getTarget();
        if (livingEntity != null) {
            ++this.cooldown;
            if (this.cooldown == 20) {
                this.cooldown = -200;
            }
            this.entity.setDeffending(this.cooldown > -80);
        }
    }
}
