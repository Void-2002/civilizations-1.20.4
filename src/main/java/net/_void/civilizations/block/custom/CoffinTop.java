package net._void.civilizations.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class CoffinTop extends Block{
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public static final MapCodec<CoffinTop> CODEC = CoffinTop.createCodec(CoffinTop::new);

    public CoffinTop(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
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
}
