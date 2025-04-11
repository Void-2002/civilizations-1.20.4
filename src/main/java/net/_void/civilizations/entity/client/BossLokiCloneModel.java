package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.BossLokiCloneEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BossLokiCloneModel <T extends BossLokiCloneEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public BossLokiCloneModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(34, 0).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 41).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cape = body.addChild("cape", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -1.0F, 0.5F, 16.0F, 24.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, 1.5F));

        ModelPartData pot = body.addChild("pot", ModelPartBuilder.create().uv(64, 16).cuboid(-0.5F, -2.1F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(60, 20).cuboid(-1.5F, -1.1F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(68, 35).cuboid(1.5F, -0.1F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(68, 33).cuboid(-0.5F, -0.1F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 22).cuboid(-1.5F, 0.9F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 16).cuboid(-2.5F, 1.9F, -0.5F, 7.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 20).cuboid(-1.5F, 4.9F, -0.5F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 18).cuboid(-0.5F, 5.9F, -0.5F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 6.1F, 0.5F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 25).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F))
                .uv(32, 25).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData horns = head.addChild("horns", ModelPartBuilder.create().uv(64, 27).cuboid(-3.0F, -36.0F, -3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 37).cuboid(-3.0F, -38.0F, -2.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(60, 22).cuboid(-3.0F, -39.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(68, 27).cuboid(-3.0F, -39.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 32).cuboid(2.0F, -36.0F, -3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 62).cuboid(2.0F, -38.0F, -2.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(64, 66).cuboid(2.0F, -39.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(68, 29).cuboid(2.0F, -39.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(24, 41).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(40, 41).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData sword = rightarm.addChild("sword", ModelPartBuilder.create().uv(64, 22).cuboid(-10.0F, -4.0F, 4.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(64, 57).cuboid(-10.0F, -5.0F, 3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(34, 16).cuboid(-10.0F, -4.0F, -3.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(68, 31).cuboid(-10.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(9.0F, 12.0F, -7.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(56, 41).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 57).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(16, 57).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 57).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(48, 57).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(58, 0).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(BossLokiCloneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.updateAnimation(entity.attackAnimationState, ModAnimations.LOKI_ATTACK, ageInTicks, 1f);
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
