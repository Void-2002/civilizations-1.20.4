package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;

import net._void.civilizations.entity.custom.NordicBossEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class NordicBossRenderer extends MobEntityRenderer<NordicBossEntity,NordicBossModel<NordicBossEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/nordic_boss.png");

    public NordicBossRenderer(EntityRendererFactory.Context context) {
        super(context, new NordicBossModel<>(context.getPart(ModModelLayers.NORDIC_BOSS)),0.6f);
    }

    @Override
    public Identifier getTexture(NordicBossEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(NordicBossEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
