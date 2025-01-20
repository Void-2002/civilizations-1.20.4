package net._void.civilizations.entity.client;

import com.google.common.collect.Maps;
import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.EgyptCivilianEntity;
import net._void.civilizations.entity.custom.EgyptCivilianVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class EgyptCivilianRenderer extends MobEntityRenderer<EgyptCivilianEntity,EgyptCivilianModel<EgyptCivilianEntity>> {
    public static final Map<EgyptCivilianVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(EgyptCivilianVariant.class), (map) -> {
                map.put(EgyptCivilianVariant.CIVILIAN1,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/egypt_civilian.png"));
                map.put(EgyptCivilianVariant.CIVILIAN2,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/egypt_civilian_2.png"));
                map.put(EgyptCivilianVariant.CIVILIAN3,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/egypt_civilian_3.png"));
                map.put(EgyptCivilianVariant.CIVILIAN4,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/egypt_civilian_4.png"));
            });

    public EgyptCivilianRenderer(EntityRendererFactory.Context context) {
        super(context, new EgyptCivilianModel<>(context.getPart(ModModelLayers.EGYPT_CIVILIAN)),0.6f);
    }

    @Override
    public Identifier getTexture(EgyptCivilianEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(EgyptCivilianEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
