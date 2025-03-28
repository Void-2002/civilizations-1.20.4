package net._void.civilizations.item.custom;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.entity.custom.RomeBossEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class RomeKey extends Item{
    public RomeKey(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult r = ActionResult.FAIL;;
        if(!context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            BlockState state = context.getWorld().getBlockState(positionClicked);
            if(state.getBlock().equals(ModBlocks.ROME_CHEST)){
                context.getWorld().setBlockState(positionClicked, Blocks.SAND.getDefaultState());
                RomeBossEntity customEntity = ((EntityType<RomeBossEntity>) EntityType.get("civilizations:rome_boss").get()).create(context.getWorld());
                customEntity.updatePosition(positionClicked.getX() + 0.5, positionClicked.getY() + 20, positionClicked.getZ() + 0.5);
                context.getWorld().spawnEntity(customEntity);
                r = ActionResult.SUCCESS;
            }
        }else{
            if(context.getPlayer()!=null){
                BlockPos positionClicked = context.getBlockPos();
                BlockState state = context.getWorld().getBlockState(positionClicked);
                if(state.getBlock().equals(ModBlocks.ROME_CHEST)){
                    context.getPlayer().playSound(SoundEvents.BLOCK_CHEST_OPEN,1.0F,1.0F);
                }
            }
        }
        return r;
    }
}

