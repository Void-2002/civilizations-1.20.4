package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.ai.EgyptCivilianAttackGoal;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EgyptBossEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private final ServerBossBar bossBar = new ServerBossBar(Text.literal("Pharaoh Khufu"),
            BossBar.Color.YELLOW, BossBar.Style.NOTCHED_20);

    public EgyptBossEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
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
        //this.goalSelector.add(1, new EgyptBossAttackGoal(this,1,true));
        this.goalSelector.add(2, new WanderAroundGoal(this,1));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 5));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createBossAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,1000).
                add(EntityAttributes.GENERIC_ARMOR,5).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,10).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.3f).
                add(EntityAttributes.GENERIC_FOLLOW_RANGE,100).
                add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,0.5);
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
    }
}
