package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.BossMercuryEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;

public class BossMercuryMoveGoal extends Goal {
    private final BossMercuryEntity entity;
    private int timer = 0;

    public BossMercuryMoveGoal(PathAwareEntity mob) {
        entity = ((BossMercuryEntity) mob);
    }

    @Override
    public boolean canStart() {
        return true;
    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        timer++;
        if(timer == 100){
            timer = 0;
        }
        this.entity.getNavigation().startMovingTo(entity.getX(), 202 + timer%20, entity.getZ(), 1);
    }
}
