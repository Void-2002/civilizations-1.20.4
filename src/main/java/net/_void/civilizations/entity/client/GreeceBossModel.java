package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.GreeceBossEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class GreeceBossModel <T extends GreeceBossEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public GreeceBossModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(26, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(24, 44).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(40, 44).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData shield = rightarm.addChild("shield", ModelPartBuilder.create().uv(72, 44).cuboid(-3.0F, 5.0F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(66, 39).cuboid(-3.0F, -6.0F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 60).cuboid(-3.0F, -5.0F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(54, 0).cuboid(-3.0F, 4.0F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(24, 32).cuboid(-3.0F, 2.0F, -5.0F, 1.0F, 2.0F, 10.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-3.0F, -4.0F, -5.0F, 1.0F, 2.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-3.0F, -2.0F, -6.0F, 1.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 9.0F, 0.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(0, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(50, 12).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData sword = leftarm.addChild("sword", ModelPartBuilder.create().uv(40, 12).cuboid(-0.5F, -7.75F, 5.8125F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(72, 0).cuboid(-0.5F, -6.75F, 3.8125F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 70).cuboid(-0.5F, -5.75F, 2.8125F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(48, 69).cuboid(-0.5F, -4.75F, 1.8125F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(66, 66).cuboid(-0.5F, -3.75F, 0.8125F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(66, 33).cuboid(-0.5F, -1.75F, -1.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(66, 27).cuboid(-0.5F, -0.75F, -2.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(66, 60).cuboid(-0.5F, -2.75F, -0.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(66, 15).cuboid(-0.5F, 1.25F, -4.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(66, 21).cuboid(-0.5F, 0.25F, -3.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(72, 54).cuboid(-0.5F, 3.25F, -5.1875F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(66, 9).cuboid(-0.5F, 2.25F, -5.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(46, 32).cuboid(-0.5F, 3.25F, -7.1875F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-0.5F, 4.25F, -7.1875F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
                .uv(72, 49).cuboid(-0.5F, 5.25F, -7.1875F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 12).cuboid(-0.5F, 6.25F, -7.1875F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, 9.75F, -8.8125F, 2.3562F, 0.0F, 0.0F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(50, 28).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(56, 44).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(16, 60).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 60).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(GreeceBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(ModAnimations.CHINA_BOSS_MOVE, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, ModAnimations.EGYPT_BOSS_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.GREECE_BOSS_ATTACK, ageInTicks, 1f);
        this.updateAnimation(entity.deffendAnimationState, ModAnimations.GREECE_BOSS_DEFFEND, ageInTicks, 1f);
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
