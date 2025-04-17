package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.BossLokiCloneEntity;
import net._void.civilizations.entity.custom.BossLokiEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BossLokiAttackGoal extends Goal {
    protected final PathAwareEntity mob;
    private final BossLokiEntity entity;
    public int cooldown;
    public int type = 0;

    public BossLokiAttackGoal(PathAwareEntity mob) {
        this.mob = mob;
        entity = ((BossLokiEntity) mob);
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
                this.entity.setPotioning(false);
                this.entity.potionAnimationTimeout = 0;
            }
            case 3 -> {
                this.entity.setCloning(false);
                this.entity.cloneAnimationTimeout = 0;
            }
            case 4 -> {
                this.entity.setSummoning(false);
                this.entity.summonAnimationTimeout = 0;
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
                    this.type = (int)(Math.random() * 4 + 1);
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
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100));
                            world.syncWorldEvent((PlayerEntity)null, 1033, this.entity.getBlockPos(), 0);
                            this.cooldown = -60;
                        }
                        case 3 -> {
                            for(int i=-1;i<=1;i+=2){
                                BossLokiCloneEntity customEntity = ((EntityType<BossLokiCloneEntity>) EntityType.get("civilizations:boss_loki_clone").get()).create(world);
                                customEntity.updatePosition(entity.getX() + i,entity.getY(),entity.getZ());
                                world.spawnEntity(customEntity);
                            }
                            for(int i=-1;i<=1;i+=2){
                                BossLokiCloneEntity customEntity = ((EntityType<BossLokiCloneEntity>) EntityType.get("civilizations:boss_loki_clone").get()).create(world);
                                customEntity.updatePosition(entity.getX(),entity.getY(),entity.getZ() + i);
                                world.spawnEntity(customEntity);
                            }
                            world.syncWorldEvent((PlayerEntity)null, 1503, this.entity.getBlockPos(), 0);
                            this.cooldown = -100;
                        }
                        case 4 -> {
                            for(int i=-1;i<=1;i+=2){
                                for(int j=-1;j<=1;j+=2){
                                    int r = (int)(Math.random() * 3 + 1);
                                    switch (r){
                                        case 1 ->{
                                            SilverfishEntity customEntity = ((EntityType<SilverfishEntity>) EntityType.get("minecraft:silverfish").get()).create(world);
                                            customEntity.updatePosition(entity.getX() + i, entity.getY(),entity.getZ() + j);
                                            world.spawnEntity(customEntity);
                                            customEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 1, false, false));
                                            customEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 4, false, false));
                                        }
                                        case 2 ->{
                                            SpiderEntity customEntity = ((EntityType<SpiderEntity>) EntityType.get("minecraft:spider").get()).create(world);
                                            customEntity.updatePosition(entity.getX() + i, entity.getY(),entity.getZ() + j);
                                            world.spawnEntity(customEntity);
                                            customEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 1800, 0, false, false));
                                        }
                                        case 3 ->{
                                            CreeperEntity customEntity = ((EntityType<CreeperEntity>) EntityType.get("minecraft:creeper").get()).create(world);
                                            customEntity.updatePosition(entity.getX() + i, entity.getY(),entity.getZ() + j);
                                            world.spawnEntity(customEntity);
                                            customEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800, 1, false, false));
                                        }
                                    }
                                }
                            }
                            world.syncWorldEvent((PlayerEntity)null, 1502, this.entity.getBlockPos(), 0);
                            this.cooldown = -80;
                        }
                    }
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }
            switch (type){
                case 1 -> this.entity.setAttacking(this.cooldown > 10);
                case 2 -> this.entity.setPotioning(this.cooldown > 10);
                case 3 -> this.entity.setCloning(this.cooldown > 10);
                case 4 -> this.entity.setSummoning(this.cooldown > 10);
            }
        }
    }
}
