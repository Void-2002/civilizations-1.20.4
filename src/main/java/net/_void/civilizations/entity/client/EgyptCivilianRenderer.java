package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.EgyptCivilianEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EgyptCivilianRenderer extends MobEntityRenderer<EgyptCivilianEntity,EgyptCivilianModel<EgyptCivilianEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/egypt_civilian.png");

    public EgyptCivilianRenderer(EntityRendererFactory.Context context) {
        super(context, new EgyptCivilianModel<>(context.getPart(ModModelLayers.EGYPT_CIVILIAN)),0.6f);
    }

    @Override
    public Identifier getTexture(EgyptCivilianEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(EgyptCivilianEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
