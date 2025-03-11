package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.ChinaCivilianEntity;
import net._void.civilizations.entity.custom.NordicCivilianEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class NordicCivilianModel <T extends NordicCivilianEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public NordicCivilianModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(32, 0).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(16, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData axe = leftarm.addChild("axe", ModelPartBuilder.create().uv(32, 56).cuboid(-1.0F, -6.0333F, -3.9333F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 55).cuboid(-1.0F, -5.0333F, -3.9333F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(48, 40).cuboid(-1.0F, -4.0333F, -4.9333F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(48, 32).cuboid(-1.0F, -3.0333F, -4.9333F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(32, 48).cuboid(-1.0F, -2.0333F, -5.9333F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(48, 48).cuboid(-1.0F, -1.0333F, -5.9333F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(62, 52).cuboid(-1.0F, -0.0333F, -4.9333F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(62, 48).cuboid(-1.0F, -0.0333F, -1.9333F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(48, 61).cuboid(-1.0F, 0.9667F, -0.9333F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(40, 61).cuboid(-1.0F, 1.9667F, 0.0667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(32, 61).cuboid(-1.0F, 2.9667F, 1.0667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(60, 59).cuboid(-1.0F, 3.9667F, 2.0667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(60, 55).cuboid(-1.0F, 4.9667F, 3.0667F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(42, 56).cuboid(-1.0F, 5.9667F, 4.0667F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(56, 63).cuboid(-1.0F, -7.0333F, -2.9333F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.0333F, -7.0667F, 0.7854F, 0.0F, -3.1416F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(0, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(16, 48).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(NordicCivilianEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.EGYPT_CIVILIAN_MOVE, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.EGYPT_CIVILIAN_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.NORDIC_CIVILIAN_ATTACK, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }
}
