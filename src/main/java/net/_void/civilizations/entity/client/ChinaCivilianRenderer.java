package net._void.civilizations.entity.client;

import com.google.common.collect.Maps;
import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.ChinaCivilianEntity;
import net._void.civilizations.entity.custom.ChinaCivilianVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class ChinaCivilianRenderer extends MobEntityRenderer<ChinaCivilianEntity,ChinaCivilianModel<ChinaCivilianEntity>> {
    public static final Map<ChinaCivilianVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ChinaCivilianVariant.class), (map) -> {
                map.put(ChinaCivilianVariant.CIVILIAN1,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/china_civilian.png"));
                map.put(ChinaCivilianVariant.CIVILIAN2,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/china_civilian_2.png"));
                map.put(ChinaCivilianVariant.CIVILIAN3,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/china_civilian_3.png"));
                map.put(ChinaCivilianVariant.CIVILIAN4,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/china_civilian_4.png"));
                map.put(ChinaCivilianVariant.CIVILIAN5,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/china_civilian_5.png"));
                map.put(ChinaCivilianVariant.CIVILIAN6,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/china_civilian_6.png"));
                map.put(ChinaCivilianVariant.CIVILIAN7,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/china_civilian_7.png"));
                map.put(ChinaCivilianVariant.CIVILIAN8,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/china_civilian_8.png"));
            });

    public ChinaCivilianRenderer(EntityRendererFactory.Context context) {
        super(context, new ChinaCivilianModel<>(context.getPart(ModModelLayers.CHINA_CIVILIAN)),0.6f);
    }

    @Override
    public Identifier getTexture(ChinaCivilianEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(ChinaCivilianEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
