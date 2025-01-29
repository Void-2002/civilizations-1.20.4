package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.EgyptNpcEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EgyptNpcRenderer extends MobEntityRenderer<EgyptNpcEntity,EgyptNpcModel<EgyptNpcEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/egypt_npc.png");

    public EgyptNpcRenderer(EntityRendererFactory.Context context) {
        super(context, new EgyptNpcModel<>(context.getPart(ModModelLayers.EGYPT_NPC)),0.6f);
    }

    @Override
    public Identifier getTexture(EgyptNpcEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(EgyptNpcEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
