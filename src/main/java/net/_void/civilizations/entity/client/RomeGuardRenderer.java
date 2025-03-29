package net._void.civilizations.entity.client;

import com.google.common.collect.Maps;
import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.RomeGuardEntity;
import net._void.civilizations.entity.custom.RomeGuardVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class RomeGuardRenderer extends MobEntityRenderer<RomeGuardEntity,RomeGuardModel<RomeGuardEntity>> {
    public static final Map<RomeGuardVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RomeGuardVariant.class), (map) -> {
                map.put(RomeGuardVariant.CIVILIAN1,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_guard.png"));
                map.put(RomeGuardVariant.CIVILIAN2,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_guard_2.png"));
            });

    public RomeGuardRenderer(EntityRendererFactory.Context context) {
        super(context, new RomeGuardModel<>(context.getPart(ModModelLayers.ROME_GUARD)),0.6f);
    }

    @Override
    public Identifier getTexture(RomeGuardEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(RomeGuardEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
