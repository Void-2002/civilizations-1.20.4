package net._void.civilizations.entity.client;

import com.google.common.collect.Maps;
import net._void.civilizations.Civilizations;
import net._void.civilizations.entity.custom.RomeCivilianEntity;
import net._void.civilizations.entity.custom.RomeCivilianVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class RomeCivilianRenderer extends MobEntityRenderer<RomeCivilianEntity,RomeCivilianModel<RomeCivilianEntity>> {
    public static final Map<RomeCivilianVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RomeCivilianVariant.class), (map) -> {
                map.put(RomeCivilianVariant.CIVILIAN1,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_civilian.png"));
                map.put(RomeCivilianVariant.CIVILIAN2,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_civilian_2.png"));
                map.put(RomeCivilianVariant.CIVILIAN3,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_civilian_3.png"));
                map.put(RomeCivilianVariant.CIVILIAN4,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_civilian_4.png"));
                map.put(RomeCivilianVariant.CIVILIAN5,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_civilian_5.png"));
                map.put(RomeCivilianVariant.CIVILIAN6,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_civilian_6.png"));
                map.put(RomeCivilianVariant.CIVILIAN7,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_civilian_7.png"));
                map.put(RomeCivilianVariant.CIVILIAN8,
                        new Identifier(Civilizations.MOD_ID, "textures/entity/rome_civilian_8.png"));
            });

    public RomeCivilianRenderer(EntityRendererFactory.Context context) {
        super(context, new RomeCivilianModel<>(context.getPart(ModModelLayers.ROME_CIVILIAN)),0.6f);
    }

    @Override
    public Identifier getTexture(RomeCivilianEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(RomeCivilianEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f,1f,1f);
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
