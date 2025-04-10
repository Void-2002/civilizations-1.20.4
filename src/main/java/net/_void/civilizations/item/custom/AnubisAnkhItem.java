package net._void.civilizations.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AnubisAnkhItem extends Item {
    public AnubisAnkhItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()) {
            user.getItemCooldownManager().set(this, 1200);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 400, 2, false, true));
        }
        return super.use(world, user, hand);
    }
}
