package boon4681.axoran;

import boon4681.axoran.exts.b_ext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.util.Identifier;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class rainbow<T extends Entity, M extends EntityModel<T>> extends FeatureRenderer<T, M>{
    public rainbow(FeatureRendererContext<T, M> context) {
        super(context);
    }
    private final Identifier SKIN =  new Identifier("textures/entity/axolotl/axolotl_blue.png");


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(entity instanceof AxolotlEntity){
            if(((b_ext) entity).isRainbow()){
                float x = ((float)((entity.age + entity.getId()) % 90) + tickDelta) / 90.0F;
                Color d = Color.getHSBColor(x,0.9f,1);
                this.getContextModel().render(matrices,vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(SKIN)),light, OverlayTexture.DEFAULT_UV,d.getRed(),d.getBlue(),d.getGreen(),1);
            }
        }
    }
}
