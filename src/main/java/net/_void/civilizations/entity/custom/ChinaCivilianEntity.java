package net._void.civilizations.entity.custom;

import dev.architectury.platform.Mod;
import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.ai.ChinaCivilianAttackGoal;
import net._void.civilizations.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
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

import java.util.*;

public class ChinaCivilianEntity extends AnimalEntity {

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(ChinaCivilianEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    Entity trader;
    int trading = 0;
    int tradingDuration = 0;
    Map<Item, Integer> outputItems = new HashMap<Item, Integer>() {{
            put(Items.CHAINMAIL_HELMET,1);
            put(Items.CHAINMAIL_CHESTPLATE,1);
            put(Items.CHAINMAIL_LEGGINGS,1);
            put(Items.CHAINMAIL_BOOTS,1);
            put(Items.IRON_HORSE_ARMOR,1);
            put(Items.SADDLE,1);
            put(Items.NAME_TAG,1);
            put(Items.MAP,1);
            put(Items.COPPER_INGOT,3);
            put(Items.BRICK,8);
            put(Items.PAPER,6);
            put(Items.INK_SAC,5);
            put(Items.STRING,4);
            put(Items.LEATHER,2);
            put(Items.ARROW,5);
            put(Items.POPPY,4);
            put(Items.RED_TULIP,4);
            put(Items.WHITE_TULIP,4);
            put(Items.LILY_OF_THE_VALLEY,4);
            put(Items.BAMBOO,6);
            put(Items.STRIPPED_MANGROVE_LOG,4);
            put(Items.SMOOTH_QUARTZ,5);
            put(Items.POLISHED_BLACKSTONE_BRICKS,4);
            put(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE,1);
            put(Items.LOOM,1);
            put(Items.CARTOGRAPHY_TABLE,1);
            put(Items.SMITHING_TABLE,1);
            put(Items.NOTE_BLOCK,2);
            put(Items.JUKEBOX,1);
            put(ModItems.HIGH_MOUNTAINS_AND_FLOWING_WATER_MUSIC_DISC,1);
            put(ModItems.AUTUMN_MOON_OVER_HAN_PALACE_MUSIC_DISC,1);
        }};

    public ChinaCivilianEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
        this.goalSelector.add(1, new ChinaCivilianAttackGoal(this,1,true));
        this.goalSelector.add(2, new WanderAroundGoal(this,1));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 5));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
    }

    public static DefaultAttributeContainer.Builder createCivilianAttributes(){
        return MobEntity.createMobAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH,40).
                add(EntityAttributes.GENERIC_ARMOR,2).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE,3).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.25f).
                add(EntityAttributes.GENERIC_FOLLOW_RANGE,20);
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
        ChinaCivilianEntity baby = ModEntities.CHINA_CIVILIAN.create(world);
        ChinaCivilianVariant variant = Util.getRandom(ChinaCivilianVariant.values(), this.random);
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
            DataTracker.registerData(ChinaCivilianEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                 SpawnReason spawnReason, @Nullable EntityData entityData,
                                 @Nullable NbtCompound entityNbt) {
        ChinaCivilianVariant variant = Util.getRandom(ChinaCivilianVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public ChinaCivilianVariant getVariant() {
        return ChinaCivilianVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(ChinaCivilianVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    protected void mobTick() {
        if(trading >= 1){
            tradingDuration += 1;
            this.getMoveControl().moveTo(this.getX(),this.getY(),this.getZ(),1);
            this.lookAtEntity(trader, 5, 5);
            if(tradingDuration == 50){
                ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.HAPPY_VILLAGER,this.getX(),
                        this.getEyeY(), this.getZ(), 10, 0.2, 0.2, 0.2, 1);
            }
            if(tradingDuration == 100){
                ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.HAPPY_VILLAGER,this.getX(),
                        this.getEyeY(), this.getZ(), 10, 0.2, 0.2, 0.2, 1);
                if(trading == 2){
                    dropStack(new ItemStack(ModItems.CHINA_COIN,2));
                }else if(trading == 3){
                    dropStack(new ItemStack(ModItems.CHINA_COIN,10));
                } else{
                    Random generator = new Random();
                    Object[] values = outputItems.keySet().toArray();
                    Item randomItem = (Item) values[generator.nextInt(values.length)];
                    dropStack(new ItemStack(randomItem,outputItems.get(randomItem)));
                }
                tradingDuration = 0;
                trading = 0;
            }
        }
        super.mobTick();
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if(!this.getWorld().isClient()){
            ItemStack itemStack = player.getMainHandStack();
            Item item = itemStack.getItem();
            if ((item.equals(Items.IRON_INGOT) || item.equals(ModItems.CHINA_COIN) || item.equals(ModItems.CHINA_KEY)) && trading == 0){
                trader = player;
                trading = 1;
                if(item.equals(Items.IRON_INGOT)) trading = 2;
                if(item.equals(ModItems.CHINA_KEY)) trading = 3;
                itemStack.setCount(itemStack.getCount()-1);
                ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.HAPPY_VILLAGER,this.getX(),
                        this.getEyeY(), this.getZ(), 10, 0.2, 0.2, 0.2, 1);
                return ActionResult.SUCCESS;
            }
        }
        return super.interactMob(player, hand);
    }
}
