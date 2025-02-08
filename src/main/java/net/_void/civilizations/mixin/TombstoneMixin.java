package net._void.civilizations.mixin;


import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.block.entity.TombstoneBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.inventory.Inventory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class TombstoneMixin{
    @Inject(at = @At(value = "HEAD"), method = "onDeath")
    private void onPlayerDeath(DamageSource source, CallbackInfo info) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        Inventory inv = player.getInventory();
        BlockPos pos = player.getBlockPos();
        World world = player.getWorld();
        BlockState stateInBlock = world.getBlockState(pos);
        BlockState state = stateInBlock.isOf(ModBlocks.TOMBSTONE) ? stateInBlock : ModBlocks.TOMBSTONE.getDefaultState();
        world.setBlockState(pos, state);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof TombstoneBlockEntity tombstoneBlockEntity) {
            for(int i=0;i<inv.size();i++){
                tombstoneBlockEntity.setStack(i,inv.getStack(i));
            }
            player.getInventory().clear();
        }
        // TODO fix tombstone when player dies to explosion
    }
}

