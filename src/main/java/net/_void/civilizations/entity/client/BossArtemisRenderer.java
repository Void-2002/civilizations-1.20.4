package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.BossArtemisEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BossArtemisRenderer  extends MobEntityRenderer<BossArtemisEntity,BossArtemisModel<BossArtemisEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/boss_artemis.png");

    public BossArtemisRenderer(EntityRendererFactory.Context context) {
        super(context, new BossArtemisModel<>(context.getPart(ModModelLayers.CHINA_GUARD)),0.6f);
    }

    @Override
    public Identifier getTexture(BossArtemisEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BossArtemisEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(2f,2f,2f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
