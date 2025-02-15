package net._void.civilizations.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ChinaLanternBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(1, 0, 1, 15, 16, 15);

    public static final MapCodec<ChinaLanternBlock> CODEC = ChinaLanternBlock.createCodec(ChinaLanternBlock::new);

    public ChinaLanternBlock(Settings settings) {
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
