package net._void.civilizations.item.custom;

import net._void.civilizations.entity.custom.BossLokiEntity;
import net._void.civilizations.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
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

public class LokiCore extends Item {

    private int tick = -1;
    private int spawnTick = -1;
    private int x;
    private int y;
    private int z;

    public LokiCore(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.civilizations.loki_core"));
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
            if(spawnTick >= 0) spawnTick++;
            if(tick == 10){
                ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+0.5 , y+1 ,z+0.5 ,
                        5, 0, 0, 0, 1);
            }
            if(tick == 20){
                ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+0.5 , y+1 ,z+0.5 ,
                        10, 0, 0, 0, 1);
                for(int i=-1;i<=1;i+=2){
                    ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i+0.5 , y+1 ,z+0.5 ,
                            5, 0, 0, 0, 1);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+0.5 , y+1 ,z+i+0.5 ,
                            5, 0, 0, 0, 1);
                }
            }
            if(tick == 30){
                ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+0.5 , y+1 ,z+0.5 ,
                        5, 0, 0, 0, 1);
                for(int i=-1;i<=1;i+=2){
                    ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i+0.5 , y+1 ,z+0.5 ,
                            5, 0, 0, 0, 1);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+0.5 , y+1 ,z+i+0.5 ,
                            5, 0, 0, 0, 1);
                }
                for(int i=-1;i<=1;i+=2){
                    for(int j=-1;j<=1;j+=2){
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i+0.5 , y+1 ,z+j+0.5 ,
                                5, 0, 0, 0, 1);
                    }
                }
                world.setBlockState(new BlockPos(x , y+1 ,z), Blocks.REDSTONE_BLOCK.getDefaultState());
            }
            if(tick == 40){
                ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+0.5 , y+1 ,z+0.5 ,
                        5, 0, 0, 0, 1);
                for(int i=-1;i<=1;i+=2){
                    ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i+0.5 , y+1 ,z+0.5 ,
                            5, 0, 0, 0, 1);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+0.5 , y+1 ,z+i+0.5 ,
                            5, 0, 0, 0, 1);
                }
                for(int i=-1;i<=1;i+=2){
                    for(int j=-1;j<=1;j+=2){
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i+0.5 , y+1 ,z+j+0.5 ,
                                5, 0, 0, 0, 1);
                    }
                }
                for(int i=-2;i<=2;i+=4){
                    for(int j=-1;j<=1;j+=1){
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+i+0.5 , y+1 ,z+j+0.5 ,
                                5, 0, 0, 0, 1);
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+j+0.5 , y+1 ,z+i+0.5 ,
                                5, 0, 0, 0, 1);
                    }
                }
                for(int i=-1;i<=1;i+=2){
                    world.setBlockState(new BlockPos(x+i , y+1 ,z), Blocks.OBSIDIAN.getDefaultState());
                    world.setBlockState(new BlockPos(x , y+1 ,z+i), Blocks.OBSIDIAN.getDefaultState());
                }
            }
            if(tick == 50){
                for(int i=-2;i<=2;i++){
                    for(int j=-2;j<=2;j++){
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+j+0.5 , y+1 ,z+i+0.5 ,
                                5, 0, 0, 0, 1);
                    }
                }
                for(int i=-1;i<=1;i+=2){
                    for(int j=-1;j<=1;j+=2){
                        world.setBlockState(new BlockPos(x+i , y+1 ,z+j), Blocks.ANCIENT_DEBRIS.getDefaultState());
                    }
                }
            }
            if(tick == 60){
                for(int i=-2;i<=2;i++){
                    for(int j=-2;j<=2;j++){
                        ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD ,x+j+0.5 , y+1 ,z+i+0.5 ,
                                5, 0, 0, 0, 1);
                    }
                }
                for(int i=-2;i<=2;i+=4){
                    for(int j=-1;j<=1;j+=1){
                        world.setBlockState(new BlockPos(x+i , y+1 ,z+j), Blocks.POLISHED_BLACKSTONE_SLAB.getDefaultState());
                        world.setBlockState(new BlockPos(x+j , y+1 ,z+i), Blocks.POLISHED_BLACKSTONE_SLAB.getDefaultState());
                        world.setBlockState(new BlockPos(x+i , y ,z+j), Blocks.POLISHED_BLACKSTONE.getDefaultState());
                        world.setBlockState(new BlockPos(x+j , y ,z+i), Blocks.POLISHED_BLACKSTONE.getDefaultState());
                    }
                }
            }
            if(tick == 80){
                if(entity instanceof PlayerEntity player){
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 11, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600, 0, false, false));
                    for(PlayerEntity player2 : world.getPlayers()){
                        if(player.squaredDistanceTo(player2) <= (double) 100.0F && !player.equals(player2)){
                            player2.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 12, false, false));
                            player2.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600, 0, false, false));
                        }
                    }
                }
            }
            if(entity instanceof PlayerEntity player && player.getY() >= 202 && player.hasStatusEffect(StatusEffects.LEVITATION)){
                player.setStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 0, 0), player);
                player.setStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 0, 0), player);
                for(PlayerEntity player2 : world.getPlayers()){
                    if(player.squaredDistanceTo(player2) <= (double) 900.0F && !player.equals(player2)){
                        player2.setStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 0, 0), player);
                        player2.setStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 0, 0), player);
                        player2.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 100, 0, false, false));
                    }
                }
                spawnTick = 0;
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++) {
                        if (pow(i, 2) + pow(j, 2) <= pow(35, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            world.setBlockState(new BlockPos(x + (int) i, 199, z + (int) j), Blocks.LAVA.getDefaultState());
                        }
                        if (pow(i, 2) + pow(j, 2) >= pow(34, 2) && pow(i, 2) + pow(j, 2) <= pow(35, 2)) {
                            world.setBlockState(new BlockPos(x + (int) i, 199, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                        }
                    }
                }
            }
            if(spawnTick == 60){
                BossLokiEntity customEntity = ((EntityType<BossLokiEntity>) EntityType.get("civilizations:boss_loki").get()).create(world);
                customEntity.updatePosition(x + 0.5, 201, z + 0.5);
                customEntity.setCoords(x, y, z);
                world.spawnEntity(customEntity);
                if(entity instanceof PlayerEntity player){
                    player.getInventory().setStack(slot, new ItemStack(ModItems.NORDIC_CORE));
                }
            }
            if(tick == 100){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if (pow(i, 2) + pow(j, 2) >= pow(31, 2) && pow(i, 2) + pow(j, 2) <= pow(35, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            if((int)(Math.random() * 40 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.TNT.getDefaultState());
                            if((int)(Math.random() * 30 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.COBWEB.getDefaultState());
                        }
                    }
                }
            }
            if(tick == 130){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if (pow(i, 2) + pow(j, 2) >= pow(27, 2) && pow(i, 2) + pow(j, 2) <= pow(31, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            if((int)(Math.random() * 40 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.TNT.getDefaultState());
                            if((int)(Math.random() * 30 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.COBWEB.getDefaultState());
                        }
                    }
                }
            }
            if(tick == 160){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if (pow(i, 2) + pow(j, 2) >= pow(23, 2) && pow(i, 2) + pow(j, 2) <= pow(27, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            if((int)(Math.random() * 40 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.TNT.getDefaultState());
                            if((int)(Math.random() * 30 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.COBWEB.getDefaultState());
                        }
                    }
                }
            }
            if(tick == 190){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if (pow(i, 2) + pow(j, 2) >= pow(19, 2) && pow(i, 2) + pow(j, 2) <= pow(23, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            if((int)(Math.random() * 40 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.TNT.getDefaultState());
                            if((int)(Math.random() * 30 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.COBWEB.getDefaultState());
                        }
                    }
                }
            }
            if(tick == 210){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if (pow(i, 2) + pow(j, 2) >= pow(15, 2) && pow(i, 2) + pow(j, 2) <= pow(19, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            if((int)(Math.random() * 40 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.TNT.getDefaultState());
                            if((int)(Math.random() * 30 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.COBWEB.getDefaultState());
                        }
                    }
                }
            }
            if(tick == 240){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if (pow(i, 2) + pow(j, 2) >= pow(11, 2) && pow(i, 2) + pow(j, 2) <= pow(15, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            if((int)(Math.random() * 40 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.TNT.getDefaultState());
                            if((int)(Math.random() * 30 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.COBWEB.getDefaultState());
                        }
                    }
                }
            }
            if(tick == 270){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if (pow(i, 2) + pow(j, 2) >= pow(7, 2) && pow(i, 2) + pow(j, 2) <= pow(11, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            if((int)(Math.random() * 40 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.TNT.getDefaultState());
                            if((int)(Math.random() * 30 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.COBWEB.getDefaultState());
                        }
                    }
                }
            }
            if(tick == 300){
                for(double i = -50;i<=100;i++){
                    for(double j = -50;j<=100;j++){
                        if (pow(i, 2) + pow(j, 2) >= pow(5, 2) && pow(i, 2) + pow(j, 2) <= pow(7, 2)) {
                            if((int)(Math.random() * 3 + 1) >= 2){
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.CRYING_OBSIDIAN.getDefaultState());
                            }else{
                                world.setBlockState(new BlockPos(x + (int) i, 200, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                                world.setBlockState(new BlockPos(x + (int) i, 198, z + (int) j), Blocks.ORANGE_STAINED_GLASS.getDefaultState());
                            }
                            if((int)(Math.random() * 40 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.TNT.getDefaultState());
                            if((int)(Math.random() * 30 + 1) == 1)world.setBlockState(new BlockPos(x + (int) i, 201, z + (int) j), Blocks.COBWEB.getDefaultState());
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
