package boon4681.axoran.mixin;


import boon4681.axoran.render.RainbowRendererFeature;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.AxolotlEntityModel;
import net.minecraft.entity.passive.AxolotlEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AxolotlEntityRenderer.class)
public abstract class AxolotlRendererMixin extends MobEntityRenderer<AxolotlEntity, AxolotlEntityModel<AxolotlEntity>> {

    public AxolotlRendererMixin(EntityRendererFactory.Context context, AxolotlEntityModel<AxolotlEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method = "<init>",at = @At(value = "TAIL"))
    private void x(EntityRendererFactory.Context context, CallbackInfo ci){
        addFeature(new RainbowRendererFeature<>(this));
    }
}
