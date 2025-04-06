package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.BossAnubisEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BossAnubisRenderer  extends MobEntityRenderer<BossAnubisEntity,BossAnubisModel<BossAnubisEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/boss_anubis.png");

    public BossAnubisRenderer(EntityRendererFactory.Context context) {
        super(context, new BossAnubisModel<>(context.getPart(ModModelLayers.BOSS_ANUBIS)),0.6f);
    }

    @Override
    public Identifier getTexture(BossAnubisEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BossAnubisEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(2f,2f,2f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
