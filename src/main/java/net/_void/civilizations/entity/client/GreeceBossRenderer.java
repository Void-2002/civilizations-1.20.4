package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.GreeceBossEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GreeceBossRenderer extends MobEntityRenderer<GreeceBossEntity,GreeceBossModel<GreeceBossEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/greece_boss.png");

    public GreeceBossRenderer(EntityRendererFactory.Context context) {
        super(context, new GreeceBossModel<>(context.getPart(ModModelLayers.GREECE_BOSS)),0.6f);
    }

    @Override
    public Identifier getTexture(GreeceBossEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(GreeceBossEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
