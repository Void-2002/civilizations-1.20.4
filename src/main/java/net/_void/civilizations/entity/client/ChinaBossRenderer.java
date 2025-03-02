package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.ChinaBossEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ChinaBossRenderer extends MobEntityRenderer<ChinaBossEntity,ChinaBossModel<ChinaBossEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/china_boss.png");

    public ChinaBossRenderer(EntityRendererFactory.Context context) {
        super(context, new ChinaBossModel<>(context.getPart(ModModelLayers.CHINA_BOSS)),0.6f);
    }

    @Override
    public Identifier getTexture(ChinaBossEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ChinaBossEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
