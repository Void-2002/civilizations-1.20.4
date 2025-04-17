package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.NordicBossEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class NordicBossModel<T extends NordicBossEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public NordicBossModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData beard = head.addChild("beard", ModelPartBuilder.create().uv(66, 34).cuboid(-3.0F, -24.0F, -4.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(68, 26).cuboid(-2.0F, -23.0F, -4.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(74, 47).cuboid(-1.0F, -22.0F, -4.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData hair = head.addChild("hair", ModelPartBuilder.create().uv(24, 16).cuboid(-2.0F, -33.0F, -5.0F, 4.0F, 2.0F, 10.0F, new Dilation(0.0F))
                .uv(72, 58).cuboid(-2.0F, -31.0F, 4.0F, 4.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(32, 0).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData axe = rightarm.addChild("axe", ModelPartBuilder.create().uv(52, 20).cuboid(-0.5F, -4.9783F, -3.0435F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(36, 55).cuboid(-0.5F, -3.9783F, -4.0435F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(48, 12).cuboid(-0.5F, -3.9783F, -8.0435F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(52, 10).cuboid(-0.5F, -2.9783F, -5.0435F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F))
                .uv(16, 49).cuboid(-0.5F, -2.9783F, -8.0435F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 46).cuboid(-0.5F, -1.9783F, 1.9565F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(48, 10).cuboid(-0.5F, -0.9783F, 2.9565F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(48, 0).cuboid(-0.5F, -1.9783F, -8.0435F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F))
                .uv(46, 28).cuboid(-0.5F, -0.9783F, -8.0435F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F))
                .uv(66, 28).cuboid(-0.5F, 3.0217F, -5.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(36, 64).cuboid(-0.5F, 1.0217F, -7.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(16, 65).cuboid(-0.5F, 2.0217F, -6.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(24, 28).cuboid(-0.5F, 0.0217F, -8.0435F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
                .uv(48, 73).cuboid(-0.5F, 1.0217F, -0.0435F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 73).cuboid(-0.5F, 2.0217F, 0.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(72, 69).cuboid(-0.5F, 3.0217F, 1.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(72, 65).cuboid(-0.5F, 4.0217F, 2.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(40, 70).cuboid(-0.5F, 5.0217F, 3.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(28, 65).cuboid(-0.5F, 6.0217F, 4.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(16, 43).cuboid(-0.5F, 7.0217F, 5.9565F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 67).cuboid(-0.5F, -5.9783F, -2.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(48, 67).cuboid(-0.5F, -6.9783F, -3.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(60, 67).cuboid(-0.5F, -7.9783F, -4.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 7.9783F, -9.9565F, 0.7854F, 0.0F, 0.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(22, 39).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData axe2 = leftarm.addChild("axe2", ModelPartBuilder.create().uv(0, 59).cuboid(-0.5F, -4.9783F, -3.0435F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
                .uv(54, 58).cuboid(-0.5F, -3.9783F, -4.0435F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(48, 14).cuboid(-0.5F, -3.9783F, -8.0435F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(54, 38).cuboid(-0.5F, -2.9783F, -5.0435F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F))
                .uv(16, 52).cuboid(-0.5F, -2.9783F, -8.0435F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(48, 64).cuboid(-0.5F, -1.9783F, 1.9565F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(66, 36).cuboid(-0.5F, -0.9783F, 2.9565F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(54, 48).cuboid(-0.5F, -1.9783F, -8.0435F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F))
                .uv(16, 55).cuboid(-0.5F, -0.9783F, -8.0435F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F))
                .uv(68, 0).cuboid(-0.5F, 3.0217F, -5.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(68, 20).cuboid(-0.5F, 1.0217F, -7.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(28, 70).cuboid(-0.5F, 2.0217F, -6.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 32).cuboid(-0.5F, 0.0217F, -8.0435F, 1.0F, 1.0F, 10.0F, new Dilation(0.0F))
                .uv(56, 73).cuboid(-0.5F, 1.0217F, -0.0435F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(64, 73).cuboid(-0.5F, 2.0217F, 0.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(72, 73).cuboid(-0.5F, 3.0217F, 1.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(74, 36).cuboid(-0.5F, 4.0217F, 2.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(40, 74).cuboid(-0.5F, 5.0217F, 3.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(74, 40).cuboid(-0.5F, 6.0217F, 4.9565F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(74, 44).cuboid(-0.5F, 7.0217F, 5.9565F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 71).cuboid(-0.5F, -5.9783F, -2.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(72, 6).cuboid(-0.5F, -6.9783F, -3.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(72, 12).cuboid(-0.5F, -7.9783F, -4.0435F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 7.9783F, -9.9565F, 0.7854F, 0.0F, 0.0F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(38, 39).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(0, 43).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(NordicBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.EGYPT_CIVILIAN_MOVE, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.EGYPT_CIVILIAN_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.NORDIC_BOSS_ATTACK, ageInTicks, 1f);
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
