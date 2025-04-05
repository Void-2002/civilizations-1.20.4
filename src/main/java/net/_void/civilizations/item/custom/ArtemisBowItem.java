package net._void.civilizations.item.custom;

import java.util.function.Predicate;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ArtemisBowItem extends RangedWeaponItem implements Vanishable {
    public static final int TICKS_PER_SECOND = 20;
    public static final int RANGE = 15;

    public ArtemisBowItem(Item.Settings settings) {
        super(settings);
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getProjectileType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.ARROW);
                }

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!((double)f < 0.1)) {
                    boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                    if (!world.isClient) {
                        ArrowItem arrowItem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                        persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.0F, 1.0F);
                        persistentProjectileEntity.setOnFireFor(100);
                        persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)2.0F);
                        PersistentProjectileEntity persistentProjectileEntity2 = arrowItem.createArrow(world, itemStack, playerEntity);
                        Vec3d vec3d = playerEntity.getOppositeRotationVector(1.0F);
                        Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(10 * ((float)Math.PI / 180F)), vec3d.x, vec3d.y, vec3d.z);
                        Vec3d vec3d2 = playerEntity.getRotationVec(1.0F);
                        Vector3f vector3f = vec3d2.toVector3f().rotate(quaternionf);
                        persistentProjectileEntity2.setVelocity((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), f * 3.0F, 1.0F);
                        persistentProjectileEntity2.setOnFireFor(100);
                        persistentProjectileEntity2.setDamage(persistentProjectileEntity2.getDamage() + (double)2.0F);
                        PersistentProjectileEntity persistentProjectileEntity3 = arrowItem.createArrow(world, itemStack, playerEntity);
                        Quaternionf quaternionf2 = (new Quaternionf()).setAngleAxis((double)(-10 * ((float)Math.PI / 180F)), vec3d.x, vec3d.y, vec3d.z);
                        Vector3f vector3f2 = vec3d2.toVector3f().rotate(quaternionf2);
                        persistentProjectileEntity3.setVelocity((double)vector3f2.x(), (double)vector3f2.y(), (double)vector3f2.z(), f * 3.0F, 1.0F);
                        persistentProjectileEntity3.setOnFireFor(100);
                        persistentProjectileEntity3.setDamage(persistentProjectileEntity3.getDamage() + (double)2.0F);

                        if (f == 1.0F) {
                            persistentProjectileEntity.setCritical(true);
                            persistentProjectileEntity2.setCritical(true);
                            persistentProjectileEntity3.setCritical(true);
                        }

                        int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)j * (double)0.5F + (double)0.5F);
                            persistentProjectileEntity2.setDamage(persistentProjectileEntity.getDamage() + (double)j * (double)0.5F + (double)0.5F);
                            persistentProjectileEntity3.setDamage(persistentProjectileEntity.getDamage() + (double)j * (double)0.5F + (double)0.5F);
                        }

                        int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            persistentProjectileEntity.setPunch(k);
                            persistentProjectileEntity2.setPunch(k);
                            persistentProjectileEntity3.setPunch(k);
                        }

                        if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                            persistentProjectileEntity.setOnFireFor(200);
                            persistentProjectileEntity2.setOnFireFor(200);
                            persistentProjectileEntity3.setOnFireFor(200);
                        }

                        stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                        persistentProjectileEntity2.pickupType = PickupPermission.CREATIVE_ONLY;
                        persistentProjectileEntity3.pickupType = PickupPermission.CREATIVE_ONLY;
                        if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                            persistentProjectileEntity.pickupType = PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(persistentProjectileEntity);
                        world.spawnEntity(persistentProjectileEntity2);
                        world.spawnEntity(persistentProjectileEntity3);
                    }

                    world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            playerEntity.getInventory().removeOne(itemStack);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }

    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean bl = !user.getProjectileType(itemStack).isEmpty();
        if (!user.getAbilities().creativeMode && !bl) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    public int getRange() {
        return 15;
    }
}

