package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.BossWukongEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static java.lang.Math.pow;

public class BossWukongAttackGoal extends Goal {
    protected final PathAwareEntity mob;
    private final BossWukongEntity entity;
    public int cooldown;
    public int type = 0;

    public BossWukongAttackGoal(PathAwareEntity mob) {
        this.mob = mob;
        entity = ((BossWukongEntity) mob);
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
        switch (type){
            case 1 -> {
                this.entity.setAttacking(false);
                this.entity.attackAnimationTimeout = 0;
            }
            case 2 -> {
                this.entity.setSpelling(false);
                this.entity.spellAnimationTimeout = 0;
            }
        }
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
                World world = this.entity.getWorld();
                if(this.cooldown == 0){
                    if((int)(Math.random() * 6 + 1) == 1) this.type = 2;
                    else this.type = 1;
                }
                ++this.cooldown;
                this.entity.getLookControl().lookAt(livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ());
                if(this.cooldown == 20){
                    switch (type){
                        case 1 -> {
                            if(livingEntity.squaredDistanceTo(this.entity) < (double)25.0F){
                                this.mob.swingHand(Hand.MAIN_HAND);
                                this.mob.tryAttack(livingEntity);
                            }
                            this.cooldown = -20;
                        }
                        case 2 -> {
                            for(double i = -7;i<=7;i++){
                                for(double j = -7;j<=7;j++){
                                    if (pow(i, 2) + pow(j, 2) >= pow(4, 2) && pow(i, 2) + pow(j, 2) <= pow(6, 2)) {
                                        int y = (int) entity.getY();
                                        int done = 0;
                                        int limit = 0;
                                        while(done == 0 && limit <= 7){
                                            if(world.getBlockState(new BlockPos((int) entity.getX() + (int) i, y - 1, (int) entity.getZ() + (int) j)) != Blocks.AIR.getDefaultState() &&
                                                    world.getBlockState(new BlockPos((int) entity.getX() + (int) i, y, (int) entity.getZ() + (int) j)).equals(Blocks.AIR.getDefaultState())){
                                                world.setBlockState(new BlockPos((int) entity.getX() + (int) i, y, (int) entity.getZ() + (int) j), Blocks.FIRE.getDefaultState());
                                                done = 1;
                                            }else if(world.getBlockState(new BlockPos((int) entity.getX() + (int) i, y, (int) entity.getZ() + (int) j)) != Blocks.AIR.getDefaultState()){
                                                y += 1;
                                                limit += 1;
                                            }else if(world.getBlockState(new BlockPos((int) entity.getX() + (int) i, y, (int) entity.getZ() + (int) j)).equals(Blocks.AIR.getDefaultState())){
                                                y -= 1;
                                                limit += 1;
                                            }
                                        }
                                    }
                                }
                            }
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 60, 1, false, false));
                            world.syncWorldEvent((PlayerEntity)null, 1502, this.entity.getBlockPos(), 0);
                            this.cooldown = -20;
                        }
                    }
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }
            switch (type){
                case 1 -> this.entity.setAttacking(this.cooldown > 10);
                case 2 -> this.entity.setSpelling(this.cooldown > 10);
            }
        }
    }
}
