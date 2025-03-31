package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.ai.EgyptBossAttackGoal;
import net._void.civilizations.entity.ai.EgyptBossShootGoal;
import net._void.civilizations.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EgyptBossEntity extends AnimalEntity {
    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(EgyptBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(EgyptBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState shootingAnimationState = new AnimationState();
    public int shootingAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Pharaoh Khufu"),
            BossBar.Color.YELLOW, BossBar.Style.NOTCHED_20);

    public EgyptBossEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
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
        if(this.isShooting() && shootingAnimationTimeout <= 0) {
            shootingAnimationTimeout = 10;
            shootingAnimationState.start(this.age);
        } else {
            --this.shootingAnimationTimeout;
        }

        if(!this.isShooting()) {
            shootingAnimationState.stop();
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
        this.goalSelector.add(1, new EgyptBossShootGoal(this));
        this.goalSelector.add(1, new EgyptBossAttackGoal(this,1,true));
        this.goalSelector.add(2, new WanderAroundGoal(this,1));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createBossAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,1000).
                add(EntityAttributes.GENERIC_ARMOR,5).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,5).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.3f).
                add(EntityAttributes.GENERIC_FOLLOW_RANGE,100).
                add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2).
                add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,0.5);
    }

    public void setShooting(boolean shooting){
        this.dataTracker.set(SHOOTING, shooting);
    }

    public boolean isShooting() {
        return this.dataTracker.get(SHOOTING);
    }

    public void setAttacking(boolean shooting){
        this.dataTracker.set(ATTACKING, shooting);
    }

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
        return null;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
        if(this.isOnFire()) this.setOnFire(false);
        if (this.getVehicle() instanceof BoatEntity boatEntity) {
            boatEntity.kill();
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHOOTING,false);
        this.dataTracker.startTracking(ATTACKING,false);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isOf(DamageTypes.EXPLOSION) ||
           source.isOf(DamageTypes.PLAYER_EXPLOSION) ||
           source.isOf(DamageTypes.UNATTRIBUTED_FIREBALL) ||
           source.isOf(DamageTypes.FIREBALL) ||
           source.isOf(DamageTypes.IN_FIRE) ||
           source.isOf(DamageTypes.ON_FIRE) ||
           source.isOf(DamageTypes.LAVA) ||
           source.isOf(DamageTypes.FALL) ||
           source.isOf(DamageTypes.DROWN) ||
           source.isOf(DamageTypes.IN_WALL)){
            return false;
        }
        return super.damage(source, amount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
    }

    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        super.dropEquipment(source, lootingMultiplier, allowDrops);
        ItemEntity itemEntity = this.dropItem(Items.TOTEM_OF_UNDYING);
        if (itemEntity != null) {
            itemEntity.setCovetedItem();
            itemEntity.setInvulnerable(true);
        }
        ItemEntity itemEntity2 = this.dropItem(Items.ENCHANTED_GOLDEN_APPLE);
        if (itemEntity2 != null) {
            itemEntity2.setCovetedItem();
            itemEntity2.setInvulnerable(true);
        }
        ItemEntity itemEntity3 = this.dropItem(ModItems.EGYPT_CROOK);
        if (itemEntity3 != null) {
            itemEntity3.setCovetedItem();
            itemEntity3.setInvulnerable(true);
        }
    }
}
