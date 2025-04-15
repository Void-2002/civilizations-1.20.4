package net._void.civilizations.entity.client;

import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.BossWukongEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BossWukongRenderer extends MobEntityRenderer<BossWukongEntity,BossWukongModel<BossWukongEntity>> {
    private static final Identifier TEXTURE = new Identifier(Civilizations.MOD_ID,"textures/entity/boss_wukong.png");

    public BossWukongRenderer(EntityRendererFactory.Context context) {
        super(context, new BossWukongModel<>(context.getPart(ModModelLayers.BOSS_WUKONG)),0.6f);
    }

    @Override
    public Identifier getTexture(BossWukongEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BossWukongEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(2f,2f,2f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
