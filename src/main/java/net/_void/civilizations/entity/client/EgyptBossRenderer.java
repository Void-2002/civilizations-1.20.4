package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.EgyptBossEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EgyptBossRenderer extends MobEntityRenderer<EgyptBossEntity,EgyptBossModel<EgyptBossEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/egypt_boss.png");

    public EgyptBossRenderer(EntityRendererFactory.Context context) {
        super(context, new EgyptBossModel<>(context.getPart(ModModelLayers.EGYPT_BOSS)),0.6f);
    }

    @Override
    public Identifier getTexture(EgyptBossEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(EgyptBossEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
