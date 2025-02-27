package net._void.civilizations.entity.ai;

import net._void.civilizations.entity.custom.ChinaGuardEntity;
import net._void.civilizations.entity.custom.EgyptBossEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ChinaGuardShootGoal extends Goal {
    private final ChinaGuardEntity entity;
    public int cooldown;
    public double shotsTaken;

    public ChinaGuardShootGoal(PathAwareEntity mob) {
        entity = ((ChinaGuardEntity) mob);
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
            double d = (double)64.0F;
            if (livingEntity.squaredDistanceTo(this.entity) < (double)4096.0F && this.entity.canSee(livingEntity)) {
                World world = this.entity.getWorld();
                ++this.cooldown;
                if (this.cooldown == 20) {
                    double e = (double)4.0F;
                    Vec3d vec3d = this.entity.getRotationVec(1.0F);
                    double f = livingEntity.getX() - (this.entity.getX() + vec3d.x * (double)4.0F);
                    double g = livingEntity.getBodyY((double)0.5F) - ((double)0.5F + this.entity.getBodyY((double)0.5F));
                    double h = livingEntity.getZ() - (this.entity.getZ() + vec3d.z * (double)4.0F);

                    this.entity.getWorld().playSoundAtBlockCenter(entity.getBlockPos(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.HOSTILE, 1.0F,1.2F, false);

                    ArrowEntity arrowEntity = new ArrowEntity(EntityType.ARROW,entity.getWorld());
                    arrowEntity.setVelocity(f,g,h);
                    arrowEntity.setPosition(this.entity.getX() + vec3d.x * (double)2.0F, this.entity.getBodyY((double)0.5F) + (double)0.5F, arrowEntity.getZ() + vec3d.z * (double)2.0F);
                    world.spawnEntity(arrowEntity);

                    if(shotsTaken < 2) {
                        shotsTaken++;
                        this.cooldown = 10;
                    }
                    else {
                        shotsTaken = 0;
                        this.cooldown = -80;
                    }
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }

            this.entity.setShooting(this.cooldown > 10);
        }
    }
}
