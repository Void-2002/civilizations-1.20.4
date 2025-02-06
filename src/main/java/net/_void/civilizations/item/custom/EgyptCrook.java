package net._void.civilizations.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EgyptCrook extends SwordItem {
    public EgyptCrook(int attackDamage, float attackSpeed, Settings settings) {
        super(ToolMaterials.NETHERITE, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Vec3d vec3d = user.getRotationVec(1.0F);
        double f = vec3d.x * (double)4.0F;
        double g = vec3d.y * (double)4.0F;
        double h = vec3d.z * (double)4.0F;
        world.syncWorldEvent((PlayerEntity)null, 1017, user.getBlockPos(), 0);
        FireballEntity fireballEntity = new FireballEntity(world, user, f * 4, g * 4, h * 4, 3);
        fireballEntity.setPosition(user.getX() + vec3d.x * (double)2.0F, user.getBodyY((double)0.5F), user.getZ() + vec3d.z * (double)2.0F);
        world.spawnEntity(fireballEntity);
        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100));
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean damage(DamageSource source) {
        return false;
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return false;
    }
}
