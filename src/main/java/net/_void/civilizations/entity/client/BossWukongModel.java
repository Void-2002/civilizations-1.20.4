package net._void.civilizations.entity.client;

import net._void.civilizations.entity.animation.ModAnimations;
import net._void.civilizations.entity.custom.BossWukongEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BossWukongModel <T extends BossWukongEntity> extends SinglePartEntityModel<T> {
    private final ModelPart body;
    private final ModelPart head;

    public BossWukongModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(28, 32).cuboid(-0.6667F, -0.4167F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 32).cuboid(0.3333F, 1.5833F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(28, 35).cuboid(-0.6667F, 4.5833F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 36).cuboid(0.3333F, 6.5833F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(28, 38).cuboid(-0.6667F, 8.5833F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 39).cuboid(-1.6667F, 9.5833F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.3333F, 11.4167F, 2.5F));

        ModelPartData cape = body.addChild("cape", ModelPartBuilder.create().uv(64, 61).cuboid(-7.0F, -1.5F, -1.0F, 14.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(40, 26).cuboid(-7.0F, -0.5F, 0.0F, 14.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.5F, 3.0F));

        ModelPartData a = cape.addChild("a", ModelPartBuilder.create().uv(40, 16).cuboid(-7.0F, -20.0F, 4.0F, 14.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.5F, -3.0F));

        ModelPartData b = cape.addChild("b", ModelPartBuilder.create().uv(40, 21).cuboid(-7.0F, -16.0F, 3.0F, 14.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.5F, -3.0F));

        ModelPartData c = cape.addChild("c", ModelPartBuilder.create().uv(40, 30).cuboid(-7.0F, -12.0F, 4.0F, 14.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.5F, -3.0F));

        ModelPartData d = cape.addChild("d", ModelPartBuilder.create().uv(40, 34).cuboid(-7.0F, -9.0F, 3.0F, 14.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.5F, -3.0F));

        ModelPartData e = cape.addChild("e", ModelPartBuilder.create().uv(40, 38).cuboid(-7.0F, -6.0F, 4.0F, 14.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.5F, -3.0F));

        ModelPartData f = cape.addChild("f", ModelPartBuilder.create().uv(40, 42).cuboid(-7.0F, -3.0F, 5.0F, 14.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.5F, -3.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(-0.02F))
                .uv(0, 16).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rightarm = body.addChild("rightarm", ModelPartBuilder.create().uv(24, 45).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(40, 45).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-5.0F, 2.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        ModelPartData weapon = rightarm.addChild("weapon", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.5F, -1.0F));

        ModelPartData weapon_r1 = weapon.addChild("weapon_r1", ModelPartBuilder.create().uv(32, 16).cuboid(-1.0F, -13.5F, -1.0F, 2.0F, 27.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

        ModelPartData leftarm = body.addChild("leftarm", ModelPartBuilder.create().uv(0, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(56, 0).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData rightleg = body.addChild("rightleg", ModelPartBuilder.create().uv(56, 45).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 61).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData leftleg = body.addChild("leftleg", ModelPartBuilder.create().uv(32, 61).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 61).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(BossWukongEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.updateAnimation(entity.idleAnimationState, ModAnimations.WUKONG_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.attackAnimationState, ModAnimations.WUKONG_ATTACK, ageInTicks, 1f);
        this.updateAnimation(entity.spellAnimationState, ModAnimations.WUKONG_SPELL, ageInTicks, 1f);
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
