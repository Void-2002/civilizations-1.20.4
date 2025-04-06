package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.BossAnubisEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BossAnubisModel <T extends BossAnubisEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public BossAnubisModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F))
                .uv(40, 64).cuboid(-2.0F, -4.0F, -8.0F, 4.0F, 4.0F, 4.0F, new Dilation(-0.02F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(54, 0).cuboid(3.0F, -26.0F, -3.0F, 3.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 32).cuboid(-4.0F, -33.0F, -2.0F, 8.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(64, 14).cuboid(-4.0F, -32.0F, 4.0F, 8.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 0).cuboid(4.0F, -32.0F, -2.0F, 1.0F, 8.0F, 6.0F, new Dilation(0.0F))
                .uv(60, 29).cuboid(-5.0F, -32.0F, -2.0F, 1.0F, 8.0F, 6.0F, new Dilation(0.0F))
                .uv(16, 39).cuboid(-6.0F, -26.0F, -3.0F, 3.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 42).cuboid(1.0F, -38.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(54, 29).cuboid(1.0F, -36.0F, -3.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 13).cuboid(1.0F, -34.0F, -3.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 39).cuboid(-2.0F, -38.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 29).cuboid(-3.0F, -36.0F, -3.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 13).cuboid(-4.0F, -34.0F, -3.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(28, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 39).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData weapon = rightarm.addChild("weapon", ModelPartBuilder.create().uv(60, 43).cuboid(-0.5F, -0.6875F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 45).cuboid(-0.5F, -0.6875F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 50).cuboid(-0.5F, -5.6875F, -3.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(22, 64).cuboid(-0.5F, -4.6875F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-0.5F, -3.6875F, -5.0F, 1.0F, 3.0F, 10.0F, new Dilation(0.0F))
                .uv(64, 43).cuboid(-0.5F, -0.6875F, -3.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(54, 8).cuboid(-0.5F, 0.3125F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 64).cuboid(-0.5F, 1.3125F, -1.0F, 1.0F, 20.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 8.6875F, -13.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(44, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 13).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(48, 48).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 55).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(BossAnubisEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.updateAnimation(entity.attackAnimationState, ModAnimations.ANUBIS_ATTACK, ageInTicks, 1f);
        this.updateAnimation(entity.spellAnimationState, ModAnimations.ANUBIS_SPELL, ageInTicks, 1f);
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

