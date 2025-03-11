package net._void.civilizations.entity.client;

import com.google.common.collect.Maps;
import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.EgyptCivilianEntity;
import net._void.civilizations.entity.custom.NordicCivilianEntity;
import net._void.civilizations.entity.custom.NordicCivilianVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class NordicCivilianRenderer extends MobEntityRenderer<NordicCivilianEntity,NordicCivilianModel<NordicCivilianEntity>> {
    public static final Map<NordicCivilianVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(NordicCivilianVariant.class), (map) -> {
                map.put(NordicCivilianVariant.CIVILIAN1,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/nordic_civilian.png"));
                map.put(NordicCivilianVariant.CIVILIAN2,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/nordic_civilian_2.png"));
                map.put(NordicCivilianVariant.CIVILIAN3,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/nordic_civilian_3.png"));
                map.put(NordicCivilianVariant.CIVILIAN4,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/nordic_civilian_4.png"));
            });

    public NordicCivilianRenderer(EntityRendererFactory.Context context) {
        super(context, new NordicCivilianModel<>(context.getPart(ModModelLayers.NORDIC_CIVILIAN)),0.6f);
    }

    @Override
    public Identifier getTexture(NordicCivilianEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(NordicCivilianEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
