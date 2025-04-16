package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ai.BossMercuryAttackGoal;
import net._void.civilizations.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
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
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static java.lang.Math.pow;

public class BossMercuryEntity extends AnimalEntity {

    private static final TrackedData<Boolean> ATTACK = DataTracker.registerData(BossMercuryEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    private int timer = 0;

    private static TrackedData<Integer> X = DataTracker.registerData(BossMercuryEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static TrackedData<Integer> Y = DataTracker.registerData(BossMercuryEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static TrackedData<Integer> Z = DataTracker.registerData(BossMercuryEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public void setCoords(int x, int y, int z){
        this.dataTracker.set(X, x);
        this.dataTracker.set(Y, y);
        this.dataTracker.set(Z, z);
    }

    private final ServerBossBar bossBar = new ServerBossBar(Text.translatable("entity.civilizations.boss_mercury"),
            BossBar.Color.YELLOW, BossBar.Style.NOTCHED_20);

    public BossMercuryEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
        this.goalSelector.add(1, new BossMercuryAttackGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createBossAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,1200).
                add(EntityAttributes.GENERIC_ARMOR,5).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,12).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.2f).
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
        timer++;
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 300, 100, false, false));
        if(timer % 50 == 1){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 37, 0, false, false));
        }
        if(timer == 300){
            timer = 0;
            VexEntity customEntity = ((EntityType<VexEntity>) EntityType.get("minecraft:vex").get()).create(this.getWorld());
            customEntity.updatePosition(this.getX() + 1, this.getY(), this.getZ());
            this.getWorld().spawnEntity(customEntity);
            customEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 2, false, false));
            VexEntity customEntity2 = ((EntityType<VexEntity>) EntityType.get("minecraft:vex").get()).create(this.getWorld());
            customEntity2.updatePosition(this.getX() - 1, this.getY(), this.getZ());
            this.getWorld().spawnEntity(customEntity2);
            customEntity2.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 2, false, false));
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACK,false);
        this.dataTracker.startTracking(X,0);
        this.dataTracker.startTracking(Y,0);
        this.dataTracker.startTracking(Z,0);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isOf(DamageTypes.IN_FIRE) ||
                source.isOf(DamageTypes.ON_FIRE) ||
                source.isOf(DamageTypes.LAVA) ||
                source.isOf(DamageTypes.FALL) ||
                source.isOf(DamageTypes.DROWN) ||
                source.isOf(DamageTypes.IN_WALL) ||
                source.isOf(DamageTypes.MOB_PROJECTILE) ||
                source.getAttacker() instanceof VexEntity){
            return false;
        }
        return super.damage(source, amount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        for(double i = -50;i<=100;i++){
            for(double j = -50;j<=100;j++) {
                if (pow(i, 2) + pow(j, 2) <= pow(35, 2)) {
                    this.getWorld().setBlockState(new BlockPos(this.dataTracker.get(X) + (int) i, 198, this.dataTracker.get(Z) + (int) j), Blocks.AIR.getDefaultState());
                    this.getWorld().setBlockState(new BlockPos(this.dataTracker.get(X) + (int) i, 199, this.dataTracker.get(Z) + (int) j), Blocks.AIR.getDefaultState());
                    this.getWorld().setBlockState(new BlockPos(this.dataTracker.get(X) + (int) i, 200, this.dataTracker.get(Z) + (int) j), Blocks.AIR.getDefaultState());
                }
            }
        }
        if(damageSource.getAttacker() instanceof PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 500, 0, false, false));
        }
        ItemEntity itemEntity = new ItemEntity(this.getWorld(),this.dataTracker.get(X) + 0.5, this.dataTracker.get(Y), this.dataTracker.get(Z) + 0.5, new ItemStack(ModItems.MERCURY_BOOTS));
        itemEntity.updatePosition(this.dataTracker.get(X) + 0.5, this.dataTracker.get(Y), this.dataTracker.get(Z) + 0.5);
        this.getWorld().spawnEntity(itemEntity);
        super.onDeath(damageSource);
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
