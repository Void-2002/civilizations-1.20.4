package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.BossMercuryEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BossMercuryModel <T extends BossMercuryEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public BossMercuryModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(22, 64).cuboid(4.0F, -32.0F, -3.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(64, 15).cuboid(-5.0F, -32.0F, -3.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData r1 = hat.addChild("r1", ModelPartBuilder.create().uv(64, 20).cuboid(-6.0F, -33.0F, -3.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData r2 = hat.addChild("r2", ModelPartBuilder.create().uv(32, 61).cuboid(-8.0F, -34.0F, -3.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData r3 = hat.addChild("r3", ModelPartBuilder.create().uv(52, 54).cuboid(-11.0F, -35.0F, -2.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData l1 = hat.addChild("l1", ModelPartBuilder.create().uv(64, 25).cuboid(5.0F, -33.0F, -3.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData l2 = hat.addChild("l2", ModelPartBuilder.create().uv(0, 64).cuboid(6.0F, -34.0F, -3.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData l3 = hat.addChild("l3", ModelPartBuilder.create().uv(32, 56).cuboid(8.0F, -35.0F, -2.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(0, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(16, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData weapon = leftarm.addChild("weapon", ModelPartBuilder.create().uv(64, 33).cuboid(-0.5F, -1.4074F, 5.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 64).cuboid(-0.5F, -2.4074F, 3.5F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 32).cuboid(-0.5F, -3.4074F, 0.5F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(52, 47).cuboid(-0.5F, -4.4074F, 0.5F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(58, 59).cuboid(-0.5F, -5.4074F, 0.5F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(64, 10).cuboid(-0.5F, -6.4074F, 0.5F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(64, 30).cuboid(-0.5F, -1.4074F, -7.5F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(64, 5).cuboid(-0.5F, -2.4074F, -7.5F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 48).cuboid(-0.5F, -3.4074F, -7.5F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(52, 40).cuboid(-0.5F, -4.4074F, -6.5F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(46, 59).cuboid(-0.5F, -5.4074F, -5.5F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(64, 0).cuboid(-0.5F, -6.4074F, -4.5F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(66, 44).cuboid(-0.5F, -1.4074F, 0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(66, 42).cuboid(-0.5F, -0.4074F, 1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(66, 40).cuboid(-0.5F, 0.5926F, 0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 66).cuboid(-0.5F, -1.4074F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(36, 66).cuboid(-0.5F, -0.4074F, -2.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 66).cuboid(-0.5F, 0.5926F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 65).cuboid(-0.5F, 4.5926F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(60, 65).cuboid(-0.5F, 3.5926F, -2.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 65).cuboid(-0.5F, 2.5926F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(52, 65).cuboid(-0.5F, 2.5926F, 0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 65).cuboid(-0.5F, 3.5926F, 1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(44, 65).cuboid(-0.5F, 4.5926F, 0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 38).cuboid(-0.5F, 6.5926F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 36).cuboid(-0.5F, 6.5926F, 0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 40).cuboid(-0.5F, -7.4074F, -0.5F, 1.0F, 18.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 8.4074F, -11.5F, 1.5708F, 0.0F, 0.0F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(0, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 0).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(16, 48).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 16).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(BossMercuryEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.updateAnimation(entity.idleAnimationState, ModAnimations.MERCURY_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.MERCURY_ATTACK, ageInTicks, 1f);
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
