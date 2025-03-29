package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.ai.RomeGuardAttackGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RomeGuardEntity extends AnimalEntity {
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(RomeGuardEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public RomeGuardEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
            attackAnimationTimeout = 10;
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
        this.goalSelector.add(1, new RomeGuardAttackGoal(this,1,true));
        this.goalSelector.add(2, new WanderAroundGoal(this,1));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 5));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal(this, RomeBossEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createCivilianAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,20).
                add(EntityAttributes.GENERIC_ARMOR,1).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,2).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.25f).
                add(EntityAttributes.GENERIC_FOLLOW_RANGE,40);
    }

    public void setAttacking(boolean attacking){
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
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
        RomeGuardEntity baby = ModEntities.ROME_GUARD.create(world);
        RomeGuardVariant variant = Util.getRandom(RomeGuardVariant.values(), this.random);
        baby.setVariant(variant);
        return baby;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING,false);
        this.dataTracker.startTracking(DATA_ID_TYPE_VARIANT,1);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));
    }

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(RomeGuardEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                 SpawnReason spawnReason, @Nullable EntityData entityData,
                                 @Nullable NbtCompound entityNbt) {
        RomeGuardVariant variant = Util.getRandom(RomeGuardVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public RomeGuardVariant getVariant() {
        return RomeGuardVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(RomeGuardVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        if(damageSource.getAttacker() instanceof RomeBossEntity boss){
            boss.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 2));
            boss.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 4));
            boss.guardsKilled += 1;
        }
    }
}
