package net._void.civilizations.entity.client;

import com.google.common.collect.Maps;
import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.GreeceCivilianEntity;
import net._void.civilizations.entity.custom.GreeceCivilianVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class GreeceCivilianRenderer extends MobEntityRenderer<GreeceCivilianEntity,GreeceCivilianModel<GreeceCivilianEntity>> {
    public static final Map<GreeceCivilianVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GreeceCivilianVariant.class), (map) -> {
                map.put(GreeceCivilianVariant.CIVILIAN1,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/greece_civilian.png"));
                map.put(GreeceCivilianVariant.CIVILIAN2,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/greece_civilian_2.png"));
                map.put(GreeceCivilianVariant.CIVILIAN3,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/greece_civilian_3.png"));
                map.put(GreeceCivilianVariant.CIVILIAN4,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/greece_civilian_4.png"));
                map.put(GreeceCivilianVariant.CIVILIAN5,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/greece_civilian_5.png"));
                map.put(GreeceCivilianVariant.CIVILIAN6,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/greece_civilian_6.png"));
                map.put(GreeceCivilianVariant.CIVILIAN7,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/greece_civilian_7.png"));
                map.put(GreeceCivilianVariant.CIVILIAN8,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/greece_civilian_8.png"));
            });

    public GreeceCivilianRenderer(EntityRendererFactory.Context context) {
        super(context, new GreeceCivilianModel<>(context.getPart(ModModelLayers.GREECE_CIVILIAN)),0.6f);
    }

    @Override
    public Identifier getTexture(GreeceCivilianEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(GreeceCivilianEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
