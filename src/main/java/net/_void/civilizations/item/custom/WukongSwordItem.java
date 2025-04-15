package net._void.civilizations.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static java.lang.Math.pow;

public class WukongSwordItem extends SwordItem {
    public WukongSwordItem(int attackDamage, float attackSpeed, Settings settings) {
        super(ToolMaterials.NETHERITE, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 1800);
        world.syncWorldEvent((PlayerEntity)null, 1502, user.getBlockPos(), 0);
        for(double i = -7;i<=7;i++){
            for(double j = -7;j<=7;j++){
                if (pow(i, 2) + pow(j, 2) >= pow(4, 2) && pow(i, 2) + pow(j, 2) <= pow(6, 2)) {
                    int y = (int) user.getY();
                    int done = 0;
                    int limit = 0;
                    while(done == 0 && limit <= 7){
                        if(world.getBlockState(new BlockPos((int) user.getX() + (int) i, y - 1, (int) user.getZ() + (int) j)) != Blocks.AIR.getDefaultState() &&
                                world.getBlockState(new BlockPos((int) user.getX() + (int) i, y, (int) user.getZ() + (int) j)).equals(Blocks.AIR.getDefaultState())){
                            world.setBlockState(new BlockPos((int) user.getX() + (int) i, y, (int) user.getZ() + (int) j), Blocks.FIRE.getDefaultState());
                            done = 1;
                        }else if(world.getBlockState(new BlockPos((int) user.getX() + (int) i, y, (int) user.getZ() + (int) j)) != Blocks.AIR.getDefaultState()){
                            y += 1;
                            limit += 1;
                        }else if(world.getBlockState(new BlockPos((int) user.getX() + (int) i, y, (int) user.getZ() + (int) j)).equals(Blocks.AIR.getDefaultState())){
                            y -= 1;
                            limit += 1;
                        }
                    }
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity player){
            if(player.getMainHandStack().getItem().equals(this.getDefaultStack().getItem())){
                if(player.isOnFire()) player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 1));
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
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
