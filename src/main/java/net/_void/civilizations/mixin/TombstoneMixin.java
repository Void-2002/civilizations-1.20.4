package net._void.civilizations.mixin;


import net._void.civilizations.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net._void.civilizations.block.custom.TombstoneBlock;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public class TombstoneMixin {
    @Inject(method="onDeath", at=@At("TAIL"), cancellable = true)
    public void tombstoneOnDeath(DamageSource damageSource, CallbackInfo ci) {
        // TODO
        //BlockPos pos = Objects.requireNonNull(damageSource.getAttacker()).getBlockPos();
        //World world = damageSource.getAttacker().getWorld();
        //if(ci.)
        System.out.println("Works");
        //if(!world.isClient()){
        //}
        //BlockState stateInBlock = world.getBlockState(pos);
        //BlockState state = stateInBlock.isOf(ModBlocks.TOMBSTONE) ? stateInBlock : ModBlocks.TOMBSTONE.getDefaultState();
        //world.setBlockState(pos, state);
    }
}
