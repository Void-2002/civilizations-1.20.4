package net._void.civilizations.item.custom;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.entity.custom.EgyptBossEntity;
import net._void.civilizations.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class EgyptKey extends Item {
    public EgyptKey(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult r = ActionResult.FAIL;;
        if(!context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            BlockState state = context.getWorld().getBlockState(positionClicked);
            if(state.isIn(ModTags.Blocks.EGYPT_KEY_OPENABLE)){
                if(state.getBlock().equals(ModBlocks.COFFIN_TOP) || state.getBlock().equals(ModBlocks.COFFIN_BOTTOM)){
                    int x = positionClicked.getX();
                    int y = positionClicked.getY();
                    int z = positionClicked.getZ();
                    for(int i=-2;i<=2;i++){
                        for(int j=-2;j<=2;j++){
                            context.getWorld().setBlockState(new BlockPos(x + i, y, z + j), Blocks.AIR.getDefaultState());
                        }
                    }
                    EgyptBossEntity customEntity = ((EntityType<EgyptBossEntity>) EntityType.get("civilizations:egypt_boss").get()).create(context.getWorld());
                    customEntity.updatePosition(positionClicked.getX() + 0.5, positionClicked.getY() + 0.5, positionClicked.getZ() + 0.5);
                    context.getWorld().spawnEntity(customEntity);
                }else{
                    Block block = state.getBlock();
                    DoorBlock door = (DoorBlock) block;
                    door.setOpen(context.getPlayer(), context.getWorld(),state,positionClicked, !door.isOpen(state));
                }
                r = ActionResult.SUCCESS;
            }
        }else{
            if(context.getPlayer()!=null){
                BlockPos positionClicked = context.getBlockPos();
                BlockState state = context.getWorld().getBlockState(positionClicked);
                if(state.isIn(ModTags.Blocks.EGYPT_KEY_OPENABLE)){
                    context.getPlayer().playSound(SoundEvents.BLOCK_IRON_DOOR_OPEN,1.0F,1.0F);
                }
            }
        }
        return r;
    }
}
