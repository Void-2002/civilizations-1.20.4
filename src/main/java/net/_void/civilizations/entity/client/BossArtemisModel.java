package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.BossArtemisEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BossArtemisModel <T extends BossArtemisEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public BossArtemisModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F))
                .uv(0, 16).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(32, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-5.0F, 2.0F, 0.0F, -0.6788F, -0.537F, -0.8152F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(40, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(5.0F, 2.0F, 0.0F, -0.5087F, 0.1298F, 0.228F));

        ModelPartData bow = leftarm.addChild("bow", ModelPartBuilder.create().uv(64, 27).cuboid(-0.5F, -1.1379F, 2.3448F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(64, 16).cuboid(-0.5F, -0.1379F, 3.3448F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(64, 22).cuboid(-0.5F, 2.8621F, 4.3448F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(52, 64).cuboid(-0.5F, 4.8621F, 4.3448F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(50, 67).cuboid(-0.5F, -2.1379F, -5.6552F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 67).cuboid(-0.5F, -1.1379F, -5.6552F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 65).cuboid(-0.5F, -0.1379F, -4.6552F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(34, 67).cuboid(-0.5F, 0.8621F, -4.6552F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 47).cuboid(-0.5F, 1.8621F, -3.6552F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(42, 67).cuboid(-0.5F, 2.8621F, -2.6552F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 50).cuboid(-0.5F, 3.8621F, -1.6552F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(46, 64).cuboid(-0.5F, 4.8621F, 0.3448F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(56, 10).cuboid(-0.5F, 5.8621F, 2.3448F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(40, 64).cuboid(-0.5F, 6.8621F, 5.3448F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(38, 67).cuboid(-0.5F, 7.8621F, 6.3448F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 41).cuboid(-0.5F, -2.1379F, 0.3448F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(16, 64).cuboid(-0.5F, -3.1379F, 0.3448F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-0.5F, -4.1379F, -2.6552F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(64, 53).cuboid(-0.5F, 0.8621F, -2.6552F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(64, 56).cuboid(-0.5F, -0.1379F, -1.6552F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(58, 64).cuboid(-0.5F, -1.1379F, -0.6552F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(64, 59).cuboid(-0.5F, -5.1379F, 3.3448F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(64, 62).cuboid(-0.5F, -6.1379F, 4.3448F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(56, 0).cuboid(-0.5F, -5.1379F, -6.6552F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F))
                .uv(56, 32).cuboid(-0.5F, -6.1379F, -7.6552F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(54, 67).cuboid(-0.5F, -3.1379F, -6.6552F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(58, 67).cuboid(-0.5F, -4.1379F, -6.6552F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(34, 64).cuboid(-0.5F, -7.1379F, -4.6552F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(26, 64).cuboid(-0.5F, -7.1379F, -8.6552F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 7.1379F, -1.3448F, 1.2266F, -0.0594F, 2.9774F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 16).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-1.9F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(32, 48).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 48).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(1.9F, 12.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(BossArtemisEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.CHINA_GUARD_WALK, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.shootingAnimationState, ModAnimations.CHINA_GUARD_SHOOT, ageInTicks, 1f);
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
