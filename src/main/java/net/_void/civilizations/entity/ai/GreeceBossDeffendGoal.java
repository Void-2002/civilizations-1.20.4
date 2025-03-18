package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.GreeceBossEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.text.Text;

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
            if (livingEntity.squaredDistanceTo(this.entity) < (double)4096.0F && this.entity.canSee(livingEntity)) {
                ++this.cooldown;
                this.cooldown = -160;
            }else if (this.cooldown > 0) {
                --this.cooldown;
            }
            this.entity.setDeffending(this.cooldown > 10);
        }
    }
}
