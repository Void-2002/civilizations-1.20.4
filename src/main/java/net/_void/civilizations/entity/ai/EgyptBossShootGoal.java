package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.EgyptBossEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EgyptBossShootGoal extends Goal {
    private final EgyptBossEntity entity;
    public int cooldown;

    public EgyptBossShootGoal(PathAwareEntity mob) {
        entity = ((EgyptBossEntity) mob);
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
        this.entity.setShooting(false);
        this.entity.shootingAnimationTimeout = 0;
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
                if (this.cooldown == 10) {
                    world.syncWorldEvent((PlayerEntity)null, 1015, this.entity.getBlockPos(), 0);
                }

                if (this.cooldown == 20) {
                    double e = (double)4.0F;
                    Vec3d vec3d = this.entity.getRotationVec(1.0F);
                    double f = livingEntity.getX() - (this.entity.getX() + vec3d.x * (double)4.0F);
                    double g = livingEntity.getBodyY((double)0.5F) - ((double)0.5F + this.entity.getBodyY((double)0.5F));
                    double h = livingEntity.getZ() - (this.entity.getZ() + vec3d.z * (double)4.0F);
                    world.syncWorldEvent((PlayerEntity)null, 1016, this.entity.getBlockPos(), 0);
                    FireballEntity fireballEntity = new FireballEntity(world, this.entity, f, g, h, 1);
                    fireballEntity.setPosition(this.entity.getX() + vec3d.x * (double)4.0F, this.entity.getBodyY((double)0.5F) + (double)0.5F, fireballEntity.getZ() + vec3d.z * (double)4.0F);
                    world.spawnEntity(fireballEntity);
                    this.cooldown = -20;
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }

            this.entity.setShooting(this.cooldown > 10);
        }
    }
}
