package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.RomeGuardEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class RomeGuardModel <T extends RomeGuardEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public RomeGuardModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(0, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData sword = rightarm.addChild("sword", ModelPartBuilder.create().uv(48, 44).cuboid(-0.5F, -7.125F, -7.1875F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(44, 53).cuboid(-0.5F, -6.125F, -7.1875F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 38).cuboid(-0.5F, -5.125F, -7.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(48, 32).cuboid(-0.5F, -4.125F, -6.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(32, 48).cuboid(-0.5F, -3.125F, -5.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(54, 53).cuboid(-0.5F, -2.125F, -4.1875F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(44, 48).cuboid(-0.5F, -1.125F, -3.1875F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 54).cuboid(-0.5F, -0.125F, -1.1875F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(54, 48).cuboid(-0.5F, 0.875F, -0.1875F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 8.125F, 0.1875F, 0.7854F, 0.0F, 0.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(16, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(0, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 0).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(16, 48).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 16).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(RomeGuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.CHINA_BOSS_MOVE, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.EGYPT_BOSS_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.ROME_BOSS_ATTACK, ageInTicks, 1f);
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
