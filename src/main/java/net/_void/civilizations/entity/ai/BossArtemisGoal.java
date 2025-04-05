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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;

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
                    persistentProjectileEntity.setOnFireFor(100);
                    persistentProjectileEntity.setCritical(true);
                    persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)2.0F);
                    PersistentProjectileEntity persistentProjectileEntity2 = ProjectileUtil.createArrowProjectile(entity, itemStack, 1);
                    Vec3d vec3d = entity.getOppositeRotationVector(1.0F);
                    Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(10 * ((float)Math.PI / 180F)), vec3d.x, vec3d.y, vec3d.z);
                    Vec3d vec3d2 = entity.getRotationVec(1.0F);
                    Vector3f vector3f = vec3d2.toVector3f().rotate(quaternionf);
                    persistentProjectileEntity2.setVelocity((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), 1.6F, 1.0F);
                    persistentProjectileEntity2.setOnFireFor(100);
                    persistentProjectileEntity2.setCritical(true);
                    persistentProjectileEntity2.setDamage(persistentProjectileEntity2.getDamage() + (double)2.0F);
                    PersistentProjectileEntity persistentProjectileEntity3 = ProjectileUtil.createArrowProjectile(entity, itemStack, 1);
                    Quaternionf quaternionf2 = (new Quaternionf()).setAngleAxis((double)(-10 * ((float)Math.PI / 180F)), vec3d.x, vec3d.y, vec3d.z);
                    Vector3f vector3f2 = vec3d2.toVector3f().rotate(quaternionf2);
                    persistentProjectileEntity3.setVelocity((double)vector3f2.x(), (double)vector3f2.y(), (double)vector3f2.z(), 1.6F, 1.0F);
                    persistentProjectileEntity3.setOnFireFor(100);
                    persistentProjectileEntity3.setCritical(true);
                    persistentProjectileEntity3.setDamage(persistentProjectileEntity3.getDamage() + (double)2.0F);

                    world.spawnEntity(persistentProjectileEntity);
                    world.spawnEntity(persistentProjectileEntity2);
                    world.spawnEntity(persistentProjectileEntity3);
                    if(shotsTaken < 2) {
                        shotsTaken++;
                        this.cooldown = 15;
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
