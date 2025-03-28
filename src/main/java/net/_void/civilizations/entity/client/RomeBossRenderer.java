package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.RomeBossEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class RomeBossRenderer extends MobEntityRenderer<RomeBossEntity,RomeBossModel<RomeBossEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/rome_boss.png");

    public RomeBossRenderer(EntityRendererFactory.Context context) {
        super(context, new RomeBossModel<>(context.getPart(ModModelLayers.ROME_BOSS)),0.6f);
    }

    @Override
    public Identifier getTexture(RomeBossEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(RomeBossEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
