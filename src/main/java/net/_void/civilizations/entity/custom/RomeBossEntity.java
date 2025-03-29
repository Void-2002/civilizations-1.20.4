package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ai.RomeBossAttackGoal;
import net._void.civilizations.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
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
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RomeBossEntity extends AnimalEntity {
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(RomeBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    int guardsKilled = 0;
    int guardsSpawned = 0;
    int spawnTick = 0;

    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("General Julius Caesar"),
            BossBar.Color.RED, BossBar.Style.NOTCHED_20);

    public RomeBossEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
        this.goalSelector.add(1, new RomeBossAttackGoal(this, 1, true));
        this.goalSelector.add(2, new WanderAroundGoal(this,1));
        this.targetSelector.add(1, new ActiveTargetGoal(this, RomeGuardEntity.class, false));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createBossAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,800).
                add(EntityAttributes.GENERIC_ARMOR,5).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,9).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.3f).
                add(EntityAttributes.GENERIC_FOLLOW_RANGE,7).
                add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,0.5);
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
        spawnTick++;
        if(guardsSpawned == 0){
            for(double i = this.getX() - 3;i <= this.getX() + 3; i += 3){
                for(double j = this.getZ() - 3;j <= this.getZ() + 3; j += 3){
                    RomeGuardEntity customEntity = ((EntityType<RomeGuardEntity>) EntityType.get("civilizations:rome_guard").get()).create(this.getWorld());
                    customEntity.updatePosition(i, this.getY(), j);
                    this.getWorld().spawnEntity(customEntity);
                }
            }
            guardsSpawned += 9;
        }else if(spawnTick % 240 == 0 && guardsSpawned - guardsKilled <= 4){
            for(int i=0;i<=1;i++){
                for(int j=0;j<=1;j++){
                    RomeGuardEntity customEntity = ((EntityType<RomeGuardEntity>) EntityType.get("civilizations:rome_guard").get()).create(this.getWorld());
                    customEntity.updatePosition(this.getX() - 2 + 4 * i, this.getY(), this.getZ() - 2 + 4 * j);
                    this.getWorld().spawnEntity(customEntity);
                }
            }
            guardsSpawned += 4;
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING,false);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isOf(DamageTypes.IN_FIRE) ||
                source.isOf(DamageTypes.ON_FIRE) ||
                source.isOf(DamageTypes.LAVA) ||
                source.isOf(DamageTypes.FALL) ||
                source.isOf(DamageTypes.DROWN) ||
                source.isOf(DamageTypes.IN_WALL) ||
                source.isOf(DamageTypes.ARROW)){
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
        ItemEntity itemEntity = this.dropItem(ModItems.ROME_SWORD);
        if (itemEntity != null) {
            itemEntity.setCovetedItem();
            itemEntity.setInvulnerable(true);
        }
    }
}
