package net._void.civilizations.mixin;


import net._void.civilizations.block.ModBlocks;
import net._void.civilizations.block.entity.TombstoneBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
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

@Mixin(ServerPlayerEntity.class)
public abstract class TombstoneMixin {
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
        }

        MinecraftServer server = player.getServer();
        assert server != null;
        CommandManager commandManager = server.getCommandManager();
        // `player` can be anything that can issue commands (i.e. a `CommandSource`)
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        commandManager.execute(playerEntity, "clear "+player.getName());
    }
}

