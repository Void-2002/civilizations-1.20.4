package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.BossLokiCloneEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BossLokiCloneRenderer extends MobEntityRenderer<BossLokiCloneEntity,BossLokiCloneModel<BossLokiCloneEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/boss_loki.png");

    public BossLokiCloneRenderer(EntityRendererFactory.Context context) {
        super(context, new BossLokiCloneModel<>(context.getPart(ModModelLayers.BOSS_LOKI_CLONE)),0.6f);
    }

    @Override
    public Identifier getTexture(BossLokiCloneEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BossLokiCloneEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(2f,2f,2f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
