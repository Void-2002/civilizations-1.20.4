package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.BossMercuryEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BossMercuryAttackGoal extends Goal {
    private final BossMercuryEntity entity;
    public int cooldown;

    public BossMercuryAttackGoal(PathAwareEntity mob) {
        entity = ((BossMercuryEntity) mob);
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
        this.entity.setAttacking(false);
        this.entity.attackAnimationTimeout = 0;
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
                ++this.cooldown;
                this.entity.getLookControl().lookAt(livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ());
                if (this.cooldown == 20) {
                    double e = (double)4.0F;
                    Vec3d vec3d = this.entity.getRotationVec(1.0F);
                    double f = livingEntity.getX() - (this.entity.getX() + vec3d.x * (double)4.0F);
                    double g = livingEntity.getBodyY((double)0.5F) - ((double)0.5F + this.entity.getBodyY((double)0.5F));
                    double h = livingEntity.getZ() - (this.entity.getZ() + vec3d.z * (double)4.0F);
                    world.syncWorldEvent((PlayerEntity)null, 1033, this.entity.getBlockPos(), 0);
                    ShulkerBulletEntity bulletEntity = new ShulkerBulletEntity(world, this.entity, livingEntity, entity.getMovementDirection().getAxis());
                    bulletEntity.setPosition(this.entity.getX() + vec3d.x * (double)2.0F, this.entity.getBodyY((double)0.5F) + (double)0.5F, bulletEntity.getZ() + vec3d.z * (double)2.0F);
                    world.spawnEntity(bulletEntity);
                    ShulkerBulletEntity bulletEntity2 = new ShulkerBulletEntity(world, this.entity, livingEntity, entity.getMovementDirection().getAxis());
                    bulletEntity2.setPosition(this.entity.getX() + vec3d.x * (double)2.0F, this.entity.getBodyY((double)0.5F) + (double)0.5F, bulletEntity.getZ() + vec3d.z * (double)2.0F);
                    world.spawnEntity(bulletEntity2);
                    this.cooldown = -20;
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }

            this.entity.setAttacking(this.cooldown > 10);
        }
    }
}
