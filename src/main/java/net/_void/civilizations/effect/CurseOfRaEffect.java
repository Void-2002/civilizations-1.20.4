package net._void.civilizations.effect;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class CurseOfRaEffect extends StatusEffect{
    protected CurseOfRaEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.getWorld().isClient()) {
            for(int i=0;i<3;i++){
                Random rand = new Random();
                int x = (int) pLivingEntity.getX() + rand.nextInt(11) - 5;
                int y = (int) pLivingEntity.getY() + rand.nextInt(3) + 4;
                int z = (int) pLivingEntity.getZ() + rand.nextInt(11) - 5;
                World world = pLivingEntity.getWorld();
                BlockPos pos = new BlockPos(x, y, z);
                BlockState stateInBlock = world.getBlockState(pos);
                if(stateInBlock.isOf(Blocks.AIR)){
                    BlockState state = stateInBlock.isOf(Blocks.SAND) ? stateInBlock : Blocks.SAND.getDefaultState();
                    world.setBlockState(pos, state);
                }
            }
        }
        super.applyUpdateEffect(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}
