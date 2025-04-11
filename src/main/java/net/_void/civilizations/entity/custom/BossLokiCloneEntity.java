package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ai.BossLokiCloneAttackGoal;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BossLokiCloneEntity extends AnimalEntity {
    private static final TrackedData<Boolean> ATTACK = DataTracker.registerData(BossLokiCloneEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public BossLokiCloneEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;
            attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }
        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new BossLokiCloneAttackGoal(this, 1, true));

        this.targetSelector.add(1, new RevengeGoal(this));
    }

    public static DefaultAttributeContainer.Builder createBossAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,20).
                add(EntityAttributes.GENERIC_ARMOR,5).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,10).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.0f).
                add(EntityAttributes.GENERIC_FOLLOW_RANGE,75).
                add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,2);
    }

    public void setAttacking(boolean attacking){
        this.dataTracker.set(ATTACK, attacking);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACK);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return super.getHurtSound(source);
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return super.getDeathSound();
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        if(this.isOnFire()) this.setOnFire(false);
        if (this.getVehicle() instanceof BoatEntity boatEntity) {
            boatEntity.kill();
        }
        PlayerEntity player = this.getWorld().getClosestPlayer(this.getX(), this.getY(), this.getZ(), 75, true);
        if(player != null){
            this.getLookControl().lookAt(player.getX(), player.getEyeY(), player.getZ());
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACK,false);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isOf(DamageTypes.IN_FIRE) ||
                source.isOf(DamageTypes.ON_FIRE) ||
                source.isOf(DamageTypes.LAVA) ||
                source.isOf(DamageTypes.DROWN) ||
                source.isOf(DamageTypes.IN_WALL)){
            return false;
        }
        if(source.getAttacker() instanceof PlayerEntity player && amount >= 3){
            int i = (int)(Math.random() * 6 + 1);
            switch(i){
                case 1 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 80));
                case 2 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 80));
                case 3 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80));
                case 4 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 80));
                case 5 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80));
                case 6 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 80));
            }
        }
        return super.damage(source, amount);
    }
}
