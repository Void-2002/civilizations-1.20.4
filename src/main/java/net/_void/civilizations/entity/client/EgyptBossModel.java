package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.EgyptBossEntity;
import net._void.civilizations.entity.custom.EgyptCivilianEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class EgyptBossModel <T extends EgyptBossEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public EgyptBossModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 27).cuboid(-4.0F, -26.0F, -2.0F, 8.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 0.0F));

        ModelPartData right_leg = body.addChild("right_leg", ModelPartBuilder.create().uv(24, 37).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -10.0F, 0.0F));

        ModelPartData left_leg = body.addChild("left_leg", ModelPartBuilder.create().uv(36, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -10.0F, 0.0F));

        ModelPartData right_arm = body.addChild("right_arm", ModelPartBuilder.create().uv(0, 44).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -22.0F, 0.0F));

        ModelPartData left_arm = body.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(6.0F, -22.0F, 0.0F));

        ModelPartData left_arm_r1 = left_arm.addChild("left_arm_r1", ModelPartBuilder.create().uv(40, 37).cuboid(-1.0F, -13.0F, -1.0F, 4.0F, 13.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.0F, 1.0F, 3.1416F, 0.0F, 0.0F));

        ModelPartData crook = left_arm.addChild("crook", ModelPartBuilder.create().uv(30, 54).cuboid(4.0F, -27.0F, -9.0F, 1.0F, 13.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 46).cuboid(4.0F, -28.0F, -8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 36).cuboid(4.0F, -31.0F, -7.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 49).cuboid(4.0F, -32.0F, -8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(52, 13).cuboid(4.0F, -32.0F, -10.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(56, 52).cuboid(4.0F, -32.0F, -11.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 41).cuboid(4.0F, -31.0F, -12.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 1.0F, 18.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 12).cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.0F))
                .uv(50, 54).cuboid(-1.0F, 0.0F, -4.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

        ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -37.0F, -4.0F, 10.0F, 4.0F, 8.0F, new Dilation(0.0F))
                .uv(48, 16).cuboid(-7.0F, -33.0F, -2.0F, 3.0F, 7.0F, 6.0F, new Dilation(0.0F))
                .uv(32, 17).cuboid(-6.0F, -36.0F, -2.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(52, 0).cuboid(4.0F, -33.0F, -2.0F, 3.0F, 7.0F, 6.0F, new Dilation(0.0F))
                .uv(16, 55).cuboid(5.0F, -36.0F, -2.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F))
                .uv(24, 28).cuboid(-5.0F, -33.0F, 4.0F, 10.0F, 7.0F, 2.0F, new Dilation(0.0F))
                .uv(48, 32).cuboid(-5.0F, -36.0F, 4.0F, 10.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(42, 54).cuboid(5.0F, -33.0F, 4.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 54).cuboid(-6.0F, -33.0F, 4.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 44).cuboid(-6.0F, -28.0F, -3.0F, 3.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(34, 54).cuboid(3.0F, -28.0F, -3.0F, 3.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 29).cuboid(-5.0F, -26.0F, 2.0F, 10.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(32, 12).cuboid(0.0F, -37.0F, -5.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(56, 55).cuboid(-1.0F, -36.0F, -5.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 26).cuboid(-1.0F, -36.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 26.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
    @Override
    public void setAngles(EgyptBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.EGYPT_BOSS_MOVE, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.EGYPT_BOSS_IDLE, ageInTicks, 1f);
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
