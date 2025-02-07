package net._void.civilizations.mixin;

import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.block.entity.TombstoneBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class TombstoneHelperMixin {
    @Inject(at = @At(value = "HEAD"), method = "dropInventory")
    private void dontDropInventory(CallbackInfo info) {
        System.out.println("check");
        return;
    }
}
