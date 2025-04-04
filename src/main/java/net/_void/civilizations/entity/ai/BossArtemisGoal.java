package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.BossArtemisEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BossArtemisGoal extends Goal {
    private final BossArtemisEntity entity;
    public int cooldown;
    public double shotsTaken;

    public BossArtemisGoal(PathAwareEntity mob) {
        entity = ((BossArtemisEntity) mob);
    }

    public boolean canStart() {
        return this.entity.getTarget() != null;
    }

    @Override
    public void start() {
        super.start();
        this.cooldown = 0;
        this.shotsTaken = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.entity.setShooting(false);
        this.entity.shootingAnimationTimeout = 0;
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
                ++this.cooldown;
                this.entity.getLookControl().lookAt(livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ());
                if (this.cooldown == 20) {
                    world.syncWorldEvent((PlayerEntity)null, 1004, this.entity.getBlockPos(), 0);
                    ItemStack itemStack = new ItemStack(Items.BOW);
                    PersistentProjectileEntity persistentProjectileEntity = ProjectileUtil.createArrowProjectile(entity, itemStack, 1);
                    double j = livingEntity.getX() - entity.getX();
                    double e = livingEntity.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
                    double f = livingEntity.getZ() - entity.getZ();
                    double g = Math.sqrt(j * j + f * f);
                    persistentProjectileEntity.setVelocity(j, e + g * (double)0.2F, f, 1.6F, (float)(14 - world.getDifficulty().getId() * 4));
                    persistentProjectileEntity.setOnFireFor(10);
                    persistentProjectileEntity.setCritical(true);
                    world.spawnEntity(persistentProjectileEntity);
                    if(shotsTaken < 4) {
                        shotsTaken++;
                        this.cooldown = 20;
                    }
                    else {
                        shotsTaken = 0;
                        this.cooldown = -100;
                    }
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }

            this.entity.setShooting(this.cooldown > 10);
        }
    }
}
