package net._void.civilizations.block.custom;

import com.mojang.serialization.MapCodec;
import net._void.civilizations.block.entity.ModBlockEntities;
import net._void.civilizations.block.entity.TombstoneBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TombstoneBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 5, 16, 16, 11);

    public static final MapCodec<TombstoneBlock> CODEC = TombstoneBlock.createCodec(TombstoneBlock::new);

    public TombstoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TombstoneBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TombstoneBlockEntity) {
                TombstoneBlockEntity tombstoneEntity = (TombstoneBlockEntity)blockEntity;
                for(int i = 0; i < tombstoneEntity.getItems().size(); ++i) {
                    double d = (double) EntityType.ITEM.getWidth();
                    double e = (double)1.0F - d;
                    double f = d / (double)2.0F;
                    double g = Math.floor(pos.getX()) + world.random.nextDouble() * e + f;
                    double h = Math.floor(pos.getY()) + world.random.nextDouble() * e;
                    double k = Math.floor(pos.getZ()) + world.random.nextDouble() * e + f;

                    while(!tombstoneEntity.getStack(i).isEmpty()) {
                        ItemEntity itemEntity = new ItemEntity(world, g, h, k, tombstoneEntity.getStack(i).split(world.random.nextInt(21) + 10));
                        float j = 0.05F;
                        itemEntity.setVelocity(world.random.nextTriangular((double)0.0F, 0.11485000171139836), world.random.nextTriangular(0.2, 0.11485000171139836), world.random.nextTriangular((double)0.0F, 0.11485000171139836));
                        itemEntity.setCovetedItem();
                        itemEntity.setInvulnerable(true);
                        world.spawnEntity(itemEntity);
                    }
                }
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((TombstoneBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.TOMBSTONE_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

}
