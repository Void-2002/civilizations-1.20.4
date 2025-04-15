package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ai.BossArtemisGoal;
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
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static java.lang.Math.pow;

public class BossArtemisEntity extends AnimalEntity {

    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(BossArtemisEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState shootingAnimationState = new AnimationState();
    public int shootingAnimationTimeout = 0;

    private static TrackedData<Integer> X = DataTracker.registerData(BossArtemisEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static TrackedData<Integer> Y = DataTracker.registerData(BossArtemisEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static TrackedData<Integer> Z = DataTracker.registerData(BossArtemisEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private int damageTaken = 0;

    private static TrackedData<Integer> MODE = DataTracker.registerData(BossArtemisEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public void setCoords(int x, int y, int z){
        this.dataTracker.set(X, x);
        this.dataTracker.set(Y, y);
        this.dataTracker.set(Z, z);
    }

    public void setMode(int mode){
        this.dataTracker.set(MODE, mode);
    }

    public int getMode(){
        return this.dataTracker.get(MODE);
    }

    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Artemis"),
            BossBar.Color.GREEN, BossBar.Style.NOTCHED_20);

    public BossArtemisEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
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
        this.goalSelector.add(1, new BossArtemisGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createBossAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,1200).
                add(EntityAttributes.GENERIC_ARMOR,5).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,9).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.3f).
                add(EntityAttributes.GENERIC_FOLLOW_RANGE,75).
                add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,2);
    }

    public void setShooting(boolean shooting){
        this.dataTracker.set(SHOOTING, shooting);
    }

    public boolean isShooting() {
        return this.dataTracker.get(SHOOTING);
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
        if(this.getTarget() instanceof PlayerEntity player){
            if(player.getY() > this.getY() + 3 || player.getY() < this.getY() - 3){
                setMode(1);
            }else setMode(0);
        }
        if(damageTaken >= 220){
            damageTaken = 0;
            RavagerEntity customEntity = ((EntityType<RavagerEntity>) EntityType.get("minecraft:ravager").get()).create(this.getWorld());
            customEntity.updatePosition(this.getX(), this.getY(), this.getZ());
            this.getWorld().spawnEntity(customEntity);
            customEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 0, false, false));
            RavagerEntity customEntity2 = ((EntityType<RavagerEntity>) EntityType.get("minecraft:ravager").get()).create(this.getWorld());
            customEntity2.updatePosition(this.getX(), this.getY(), this.getZ());
            this.getWorld().spawnEntity(customEntity2);
            customEntity2.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 0, false, false));
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHOOTING,false);
        this.dataTracker.startTracking(X,0);
        this.dataTracker.startTracking(Y,0);
        this.dataTracker.startTracking(Z,0);
        this.dataTracker.startTracking(MODE,0);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isOf(DamageTypes.IN_FIRE) ||
                source.isOf(DamageTypes.ON_FIRE) ||
                source.isOf(DamageTypes.LAVA) ||
                source.isOf(DamageTypes.FALL) ||
                source.isOf(DamageTypes.DROWN) ||
                source.isOf(DamageTypes.IN_WALL)){
            return false;
        }
        LivingEntity attacker = (LivingEntity) source.getAttacker();
        if(attacker instanceof PlayerEntity){
            damageTaken += amount;
        }
        if(attacker instanceof RavagerEntity ravager){
            BlockPos pos = ravager.getBlockPos();
            ravager.kill();
            RavagerEntity customEntity = ((EntityType<RavagerEntity>) EntityType.get("minecraft:ravager").get()).create(this.getWorld());
            customEntity.updatePosition(pos.getX(), pos.getY(), pos.getZ());
            this.getWorld().spawnEntity(customEntity);
            customEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 0, false, false));
            return false;
        }
        return super.damage(source, amount);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        for(double i = -50;i<=100;i++){
            for(double j = -50;j<=100;j++) {
                if (pow(i, 2) + pow(j, 2) <= pow(35, 2)) {
                    this.getWorld().setBlockState(new BlockPos(this.dataTracker.get(X) + (int) i, 200, this.dataTracker.get(Z) + (int) j), Blocks.AIR.getDefaultState());
                }
            }
        }
        if(damageSource.getAttacker() instanceof PlayerEntity player){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 500, 0, false, false));
        }
        ItemEntity itemEntity = new ItemEntity(this.getWorld(),this.dataTracker.get(X) + 0.5, this.dataTracker.get(Y), this.dataTracker.get(Z) + 0.5, new ItemStack(ModItems.ARTEMIS_BOW));
        itemEntity.updatePosition(this.dataTracker.get(X) + 0.5, this.dataTracker.get(Y), this.dataTracker.get(Z) + 0.5);
        this.getWorld().spawnEntity(itemEntity);
        super.onDeath(damageSource);
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
