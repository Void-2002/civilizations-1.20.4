package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.ChinaGuardEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class ChinaGuardModel<T extends ChinaGuardEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public ChinaGuardModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(24, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 2.0F, 0.0F, -0.4556F, -0.4478F, -0.9828F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(0, 32).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, 2.0F, 0.0F, -0.582F, 0.1975F, 0.2898F));

        ModelPartData bow = leftarm.addChild("bow", ModelPartBuilder.create().uv(46, 38).cuboid(6.25F, -4.1042F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 35).cuboid(5.25F, -2.1042F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 46).cuboid(4.25F, -0.1042F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(44, 45).cuboid(0.25F, 3.8958F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(38, 45).cuboid(-1.75F, 4.8958F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(44, 43).cuboid(-3.75F, 5.8958F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 48).cuboid(2.25F, 2.8958F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(44, 41).cuboid(2.25F, 1.8958F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 44).cuboid(1.25F, 0.8958F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(38, 43).cuboid(0.25F, -0.1042F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(42, 33).cuboid(-0.75F, -1.1042F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(44, 47).cuboid(7.25F, -6.1042F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 24).cuboid(4.25F, -7.1042F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 36).cuboid(-1.75F, -5.1042F, -0.5F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 32).cuboid(0.25F, -6.1042F, -0.5F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 47).cuboid(-5.75F, -7.1042F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(42, 31).cuboid(-5.75F, -6.1042F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(38, 41).cuboid(-4.75F, -5.1042F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 41).cuboid(-3.75F, -4.1042F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 28).cuboid(-4.75F, -2.1042F, -0.5F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(32, 36).cuboid(-5.75F, -0.1042F, -0.5F, 3.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 20).cuboid(-6.75F, 3.8958F, -0.5F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(36, 47).cuboid(-5.75F, 6.8958F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.4514F, 7.6889F, 0.5F, 1.7063F, 0.8189F, -1.4672F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(32, 0).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(16, 32).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(ChinaGuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
