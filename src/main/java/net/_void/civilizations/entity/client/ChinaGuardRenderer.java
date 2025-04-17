package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.ChinaGuardEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ChinaGuardRenderer extends MobEntityRenderer<ChinaGuardEntity,ChinaGuardModel<ChinaGuardEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/china_guard.png");

    public ChinaGuardRenderer(EntityRendererFactory.Context context) {
        super(context, new ChinaGuardModel<>(context.getPart(ModModelLayers.CHINA_GUARD)),0.6f);
    }

    @Override
    public Identifier getTexture(ChinaGuardEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(ChinaGuardEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
