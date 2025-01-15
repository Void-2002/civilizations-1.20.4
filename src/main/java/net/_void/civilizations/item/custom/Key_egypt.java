package net._void.civilizations.item.custom;

import net._void.civilizations.Civilizations;
import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Key_egypt extends Item {
    public Key_egypt(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult r = ActionResult.FAIL;;
        if(!context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            BlockState state = context.getWorld().getBlockState(positionClicked);
            if(state.isIn(ModTags.Blocks.EGYPT_KEY_DOOR)){
                Block block = state.getBlock();
                DoorBlock door = (DoorBlock) block;
                door.setOpen(context.getPlayer(), context.getWorld(),state,positionClicked, !door.isOpen(state));

                r = ActionResult.SUCCESS;
            }
        }else{
            if(context.getPlayer()!=null){
                context.getPlayer().playSound(SoundEvents.BLOCK_IRON_DOOR_OPEN,1.0F,1.0F);
            }
        }
        return r;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.civilizations.egypt_key"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
