package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ai.GreeceBossAttackGoal;
import net._void.civilizations.entity.ai.GreeceBossDeffendGoal;
import net._void.civilizations.item.ModItems;
import net._void.civilizations.networking.ModMessages;
import net._void.civilizations.sound.CustomSoundInstance;
import net._void.civilizations.sound.ModSounds;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
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
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GreeceBossEntity extends AnimalEntity {
    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(GreeceBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> DEFFENDING = DataTracker.registerData(GreeceBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    public final AnimationState deffendAnimationState = new AnimationState();
    public int deffendAnimationTimeout = 0;

    private final ServerBossBar bossBar = new ServerBossBar(Text.translatable("entity.civilizations.greece_boss"),
            BossBar.Color.WHITE, BossBar.Style.NOTCHED_20);

    public GreeceBossEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
        if(!this.isDeffending()){
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
        if(this.isDeffending() && deffendAnimationTimeout <= 0) {
            deffendAnimationTimeout = 140;
            deffendAnimationState.start(this.age);
        } else {
            --this.deffendAnimationTimeout;
        }
        if(!this.isDeffending()) {
            deffendAnimationState.stop();
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
        for(PlayerEntity player : this.getWorld().getPlayers()){
            if(player.squaredDistanceTo(this)<=(double)2500.0F){
                if(!this.getWorld().isClient() && this.isAlive()){
                    PacketByteBuf buffer = PacketByteBufs.create();
                    buffer.writeString("GreeceBossPlay");
                    ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.BOSS_MUSIC_PLAY, buffer);
                }
            }else{
                if(!this.getWorld().isClient()){
                    PacketByteBuf buffer = PacketByteBufs.create();
                    buffer.writeString("GreeceBossStop");
                    ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.BOSS_MUSIC_PLAY, buffer);
                }
            }
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new GreeceBossDeffendGoal(this));
        this.goalSelector.add(1, new GreeceBossAttackGoal(this, 1, true));
        this.goalSelector.add(2, new WanderAroundGoal(this,1));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createBossAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,800).
                add(EntityAttributes.GENERIC_ARMOR,5).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,9).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.3f).
                add(EntityAttributes.GENERIC_FOLLOW_RANGE,75).
                add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,0.5);
    }

    public void setAttacking(boolean shooting){
        this.dataTracker.set(ATTACKING, shooting);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    public void setDeffending(boolean shooting){
        this.dataTracker.set(DEFFENDING, shooting);
    }

    public boolean isDeffending() {
        return this.dataTracker.get(DEFFENDING);
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
        this.dataTracker.startTracking(ATTACKING,false);
        this.dataTracker.startTracking(DEFFENDING,false);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(isDeffending()){
            LivingEntity attacker = (LivingEntity) source.getAttacker();
            if(attacker != null){
                attacker.damage(this.getDamageSources().mobAttack(this), amount);
                if(this.getWorld().isClient()){
                    attacker.playSound(SoundEvents.ITEM_SHIELD_BLOCK,1.0F,1.0F);
                }
            }
            return false;
        }
        if(source.isOf(DamageTypes.IN_FIRE) ||
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
        for(PlayerEntity player : this.getWorld().getPlayers()){
            if(player.squaredDistanceTo(this)<=(double)2500.0F){
                if(!this.getWorld().isClient()){
                    PacketByteBuf buffer = PacketByteBufs.create();
                    buffer.writeString("GreeceBossStop");
                    ServerPlayNetworking.send((ServerPlayerEntity) player, ModMessages.BOSS_MUSIC_PLAY, buffer);
                }
            }
        }
        super.onDeath(damageSource);
    }

    @Override
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        super.dropEquipment(source, lootingMultiplier, allowDrops);
        ItemEntity itemEntity = this.dropItem(ModItems.GREECE_SWORD);
        if (itemEntity != null) {
            itemEntity.setCovetedItem();
            itemEntity.setInvulnerable(true);
        }
        ItemEntity itemEntity2 = this.dropItem(ModItems.GREECE_CORE);
        if (itemEntity2 != null) {
            itemEntity2.setCovetedItem();
            itemEntity2.setInvulnerable(true);
        }
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }
}
