package net._void.civilizations.entity.custom;

import net._void.civilizations.entity.ModEntities;
import net._void.civilizations.entity.ai.GreeceCivilianAttackGoal;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GreeceCivilianEntity extends AnimalEntity{

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(GreeceCivilianEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    Entity trader;
    int trading = 0;
    int tradingDuration = 0;
    Map<Item, Integer> farmItems = new HashMap<Item, Integer>() {{
        put(Items.SUGAR_CANE,7);
        put(Items.WHEAT,9);
        put(Items.MELON_SEEDS,5);
        put(Items.PUMPKIN_SEEDS,4);
    }};
    Map<Item, Integer> flowerItems = new HashMap<Item, Integer>() {{
        put(Items.LILY_OF_THE_VALLEY,6);
        put(Items.WHITE_TULIP,5);
        put(Items.OXEYE_DAISY,7);
        put(Items.AZURE_BLUET,4);
    }};
    Map<Item, Integer> combatItems = new HashMap<Item, Integer>() {{
        put(Items.IRON_SWORD,1);
        put(Items.IRON_AXE,1);
        put(Items.SHIELD,1);
    }};
    Map<Item, Integer> foodItems = new HashMap<Item, Integer>() {{
        put(Items.APPLE,4);
        put(Items.MUSHROOM_STEW,1);
        put(Items.HONEY_BOTTLE,6);
        put(Items.CARROT,5);
    }};
    Map<Item, Integer> bookItems = new HashMap<Item, Integer>() {{
        put(Items.BOOK,5);
        put(Items.BOOKSHELF,2);
        put(Items.CARTOGRAPHY_TABLE,1);
        put(Items.LECTERN,1);
    }};

    public GreeceCivilianEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
        this.goalSelector.add(1, new GreeceCivilianAttackGoal(this,1,true));
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
        GreeceCivilianEntity baby = ModEntities.GREECE_CIVILIAN.create(world);
        GreeceCivilianVariant variant = Util.getRandom(GreeceCivilianVariant.values(), this.random);
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
            DataTracker.registerData(GreeceCivilianEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                 SpawnReason spawnReason, @Nullable EntityData entityData,
                                 @Nullable NbtCompound entityNbt) {
        GreeceCivilianVariant variant = Util.getRandom(GreeceCivilianVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public GreeceCivilianVariant getVariant() {
        return GreeceCivilianVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(GreeceCivilianVariant variant) {
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
                    dropStack(new ItemStack(ModItems.GREECE_COIN,5));
                }else{
                    Random generator = new Random();
                    Map<Item, Integer> map = new HashMap<Item, Integer>();
                    switch(getTypeVariant()){
                        case 1, 7 -> map = foodItems;
                        case 2, 5 -> map = farmItems;
                        case 3 -> map = bookItems;
                        case 4 -> map = flowerItems;
                        case 6, 8 -> map = combatItems;
                    }
                    Object[] values = map.keySet().toArray();
                    Item randomItem = (Item) values[generator.nextInt(values.length)];
                    dropStack(new ItemStack(randomItem,map.get(randomItem)));
                }
                tradingDuration = 0;
                trading = 0;
        }
        super.mobTick();
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if(!this.getWorld().isClient()){
            ItemStack itemStack = player.getMainHandStack();
            Item item = itemStack.getItem();
            if ((item.equals(Items.GOLD_INGOT) || item.equals(ModItems.GREECE_COIN)) && trading == 0){
                trader = player;
                trading = 1;
                if(item.equals(Items.GOLD_INGOT)) trading = 2;
                itemStack.setCount(itemStack.getCount()-1);
                ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.HAPPY_VILLAGER,this.getX(),
                        this.getEyeY(), this.getZ(), 10, 0.2, 0.2, 0.2, 1);
                return ActionResult.SUCCESS;
            }
        }
        return super.interactMob(player, hand);
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }
}
