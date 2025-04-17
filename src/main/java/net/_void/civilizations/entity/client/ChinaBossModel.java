package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.ChinaBossEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class ChinaBossModel<T extends ChinaBossEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public ChinaBossModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F))
                .uv(48, 16).cuboid(4.0F, 12.0F, -1.0F, 1.0F, 11.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F))
                .uv(16, 48).cuboid(-2.0F, -10.0F, 2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData beard = head.addChild("beard", ModelPartBuilder.create().uv(48, 31).cuboid(-3.0F, -24.0F, -4.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 50).cuboid(-2.0F, -23.0F, -4.0F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(52, 50).cuboid(-1.0F, -21.0F, -4.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(0, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(16, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData sword = leftarm.addChild("sword", ModelPartBuilder.create().uv(54, 18).cuboid(5.0F, -10.0F, -2.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(42, 52).cuboid(4.0F, -9.0F, -2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 48).cuboid(3.0F, -8.0F, -2.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 45).cuboid(2.0F, -7.0F, -2.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 43).cuboid(1.0F, -6.0F, -2.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 41).cuboid(0.0F, -5.0F, -2.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 39).cuboid(-1.0F, -4.0F, -2.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 37).cuboid(-2.0F, -3.0F, -2.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 35).cuboid(-3.0F, -2.0F, -2.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(54, 24).cuboid(-5.0F, -2.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 29).cuboid(-6.0F, -1.0F, -2.0F, 7.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 33).cuboid(-6.0F, 0.0F, -2.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(42, 50).cuboid(-5.0F, 1.0F, -2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(54, 22).cuboid(-3.0F, 3.0F, -2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 48).cuboid(-6.0F, 2.0F, -2.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(54, 16).cuboid(-7.0F, 3.0F, -2.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 53).cuboid(-8.0F, 4.0F, -2.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(54, 20).cuboid(-8.0F, 5.0F, -2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 9.0F, -7.0F, 1.5708F, 0.8727F, 1.5708F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(0, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(48, 0).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(ChinaBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.CHINA_BOSS_MOVE, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.EGYPT_CIVILIAN_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.CHINA_BOSS_ATTACK, ageInTicks, 1f);
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
