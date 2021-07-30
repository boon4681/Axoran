package boon4681.axoran;

import boon4681.axoran.mixin.a;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.AxolotlEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.awt.*;
import java.util.Optional;
import java.util.function.BiFunction;

@Environment(EnvType.CLIENT)
public class rainbow<T extends Entity, M extends EntityModel<T>> extends FeatureRenderer<T, M>{
    public rainbow(FeatureRendererContext<T, M> context) {
        super(context);
    }
    private final Identifier SKIN =  new Identifier("textures/entity/axolotl/axolotl_blue.png");

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        float x = ((float)(entity.getId() % 90) + tickDelta) / 90.0F;
        Color d = Color.getHSBColor(x,0.9f,1);
        this.getContextModel().render(matrices,vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(SKIN)),light, OverlayTexture.DEFAULT_UV,d.getRed(),d.getBlue(),d.getGreen(),1);
    }
}
