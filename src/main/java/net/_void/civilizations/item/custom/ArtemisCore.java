package net._void.civilizations.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static java.lang.Math.pow;

public class ArtemisCore extends Item {

    private int tick = -1;
    private int x;
    private int y;
    private int z;

    public ArtemisCore(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.civilizations.artemis_core"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult r = ActionResult.FAIL;
        if(!context.getWorld().isClient()){
            tick = 0;
            x = context.getBlockPos().getX();
            y = context.getBlockPos().getY();
            z = context.getBlockPos().getZ();
            if(context.getPlayer() != null) context.getPlayer().getItemCooldownManager().set(this, 3000);
            r = ActionResult.SUCCESS;
        }
        return r;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()){
            if(tick == 0){
                for(int i=-2;i<=2;i+=1){
                    for(int j=-2;j<=2;j+=1){
                        for(int k=1;k<=5;k+=1){
                            world.setBlockState(new BlockPos(x+i , y+k ,z+j), Blocks.AIR.getDefaultState());
                        }
                    }
                }
            }
            if(tick >= 0) tick++;
            if(tick == 10){
                ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x , y+1 ,z ,
                        10, 0, 0, 0, 1);
            }
            if(tick == 20){
                for(int i=-1;i<=1;i+=2){
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i , y+1 ,z ,
                                10, 0, 0, 0, 1);
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x , y+1 ,z+i ,
                                10, 0, 0, 0, 1);
                }
            }
            if(tick == 30){
                for(int i=-1;i<=1;i+=2){
                    for(int j=-1;j<=1;j+=2){
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i , y+1 ,z+j ,
                                10, 0, 0, 0, 1);
                    }
                }
                world.setBlockState(new BlockPos(x , y+1 ,z), Blocks.DIAMOND_BLOCK.getDefaultState());
            }
            if(tick == 40){
                for(int i=-2;i<=2;i+=4){
                    for(int j=-1;j<=1;j+=1){
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i , y+1 ,z+j ,
                                10, 0, 0, 0, 1);
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+j , y+1 ,z+i ,
                                10, 0, 0, 0, 1);
                    }
                }
                for(int i=-1;i<=1;i+=2){
                    world.setBlockState(new BlockPos(x+i , y+1 ,z), Blocks.EMERALD_BLOCK.getDefaultState());
                    world.setBlockState(new BlockPos(x , y+1 ,z+i), Blocks.EMERALD_BLOCK.getDefaultState());
                }
            }
            if(tick == 50){
                for(int i=-1;i<=1;i+=2){
                    for(int j=-1;j<=1;j+=2){
                        world.setBlockState(new BlockPos(x+i , y+1 ,z+j), Blocks.AZALEA_LEAVES.getDefaultState());
                        world.setBlockState(new BlockPos(x+i , y ,z+j), Blocks.OAK_WOOD.getDefaultState());
                    }
                }
            }
            if(tick == 60){
                for(int i=-2;i<=2;i+=4){
                    for(int j=-1;j<=1;j+=1){
                        world.setBlockState(new BlockPos(x+i , y+1 ,z+j), Blocks.QUARTZ_SLAB.getDefaultState());
                        world.setBlockState(new BlockPos(x+j , y+1 ,z+i), Blocks.QUARTZ_SLAB.getDefaultState());
                    }
                }
            }
            if(tick == 80){
                if(entity instanceof PlayerEntity player){
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 11, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600, 0, false, false));
                }
            }
            if(entity instanceof PlayerEntity player && player.getY() >= 205 && player.hasStatusEffect(StatusEffects.LEVITATION)){
                player.clearStatusEffects();
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if(pow(i,2) + pow(j,2) >= pow(4,2) && pow(i,2) + pow(j,2) <= pow(5,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) <= pow(50, 2)) {
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
            if(tick == 100){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if(pow(i,2) + pow(j,2) >= pow(46,2) && pow(i,2) + pow(j,2) <= pow(47,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if(pow(i,2) + pow(j,2) >= pow(45,2) && pow(i,2) + pow(j,2) <= pow(50,2)){
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
            if(tick == 130){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if(pow(i,2) + pow(j,2) >= pow(42,2) && pow(i,2) + pow(j,2) <= pow(43,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) >= pow(40, 2) && pow(i, 2) + pow(j, 2) <= pow(45, 2)) {
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
            if(tick == 160){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if(pow(i,2) + pow(j,2) >= pow(38,2) && pow(i,2) + pow(j,2) <= pow(39,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) >= pow(35, 2) && pow(i, 2) + pow(j, 2) <= pow(40, 2)) {
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
            if(tick == 190){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if(pow(i,2) + pow(j,2) >= pow(34,2) && pow(i,2) + pow(j,2) <= pow(35,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if(pow(i,2) + pow(j,2) >= pow(30,2) && pow(i,2) + pow(j,2) <= pow(31,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) >= pow(30, 2) && pow(i, 2) + pow(j, 2) <= pow(35, 2)) {
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
            if(tick == 210){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if(pow(i,2) + pow(j,2) >= pow(26,2) && pow(i,2) + pow(j,2) <= pow(27,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) >= pow(25, 2) && pow(i, 2) + pow(j, 2) <= pow(30, 2)) {
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
            if(tick == 240){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if(pow(i,2) + pow(j,2) >= pow(22,2) && pow(i,2) + pow(j,2) <= pow(23,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) >= pow(20, 2) && pow(i, 2) + pow(j, 2) <= pow(25, 2)) {
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
            if(tick == 270){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if(pow(i,2) + pow(j,2) >= pow(18,2) && pow(i,2) + pow(j,2) <= pow(19,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) >= pow(15, 2) && pow(i, 2) + pow(j, 2) <= pow(20, 2)) {
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
            if(tick == 300){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if(pow(i,2) + pow(j,2) >= pow(14,2) && pow(i,2) + pow(j,2) <= pow(15,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if(pow(i,2) + pow(j,2) >= pow(10,2) && pow(i,2) + pow(j,2) <= pow(11,2)){
                            world.setBlockState(new BlockPos(x+(int)i , 200 ,z+(int)j), Blocks.OAK_WOOD.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) >= pow(10, 2) && pow(i, 2) + pow(j, 2) <= pow(15, 2)) {
                            if(world.getBlockState(new BlockPos(x + (int) i, 200, z + (int) j)).getBlock().equals(Blocks.AIR)){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.AZALEA_LEAVES.getDefaultState());
                            }
                        }
                    }
                }
            }
        }else{
            if(tick == 1){
                if(entity instanceof PlayerEntity player){
                    player.playSound(SoundEvents.BLOCK_BEACON_AMBIENT,1.0F,1.0F);
                }
            }
            if(tick == 60){
                if(entity instanceof PlayerEntity player){
                    player.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN,1.0F,1.0F);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
