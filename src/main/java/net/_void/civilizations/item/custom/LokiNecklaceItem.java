package net._void.civilizations.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LokiNecklaceItem extends Item{
    public LokiNecklaceItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()) {
            user.getItemCooldownManager().set(this, 400);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 0, false, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1, false, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 4, false, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0, false, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200, 0, false, true));
        }
        return super.use(world, user, hand);
    }
}
