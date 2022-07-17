package boon4681.axoran.mixin;

import boon4681.axoran.imples.RainbowInterface;
import net.minecraft.entity.AngledModelEntity;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxolotlEntity.class)
public abstract class AxolotlEatAxolotlMixin extends AnimalEntity implements AngledModelEntity, Bucketable, RainbowInterface {

    private static final TrackedData<Boolean> RAINBOW = DataTracker.registerData(AxolotlEatAxolotlMixin.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected AxolotlEatAxolotlMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setRainbow(boolean playingDead) {
        this.dataTracker.set(RAINBOW, playingDead);
    }

    @Override
    public boolean isRainbow() {
        return this.dataTracker.get(RAINBOW);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initialize(CallbackInfo ci) {
        this.dataTracker.startTracking(RAINBOW, false);
    }

    @Redirect(method = "copyDataToStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getOrCreateNbt()Lnet/minecraft/nbt/NbtCompound;"))
    private NbtCompound writeDataToStack(ItemStack instance) {
        NbtCompound compound = instance.getOrCreateNbt();
        compound.putBoolean("Axoran.Rainbow", isRainbow());
        return compound;
    }

    @Inject(method = "copyDataFromNbt", at = @At("HEAD"))
    private void getDataFromStack(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Axoran.Rainbow")) setRainbow(nbt.getBoolean("Axoran.Rainbow"));
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeRainbowNBT(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("Axoran.Rainbow", isRainbow());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readRainbowNBT(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Axoran.Rainbow")) setRainbow(nbt.getBoolean("Axoran.Rainbow"));
    }

    @Inject(method = "interactMob",at = @At(value = "HEAD"), cancellable = true)
    private void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!this.isRainbow() && Ingredient.ofItems(Items.AXOLOTL_BUCKET).test(player.getStackInHand(hand))) {
            setRainbow(true);
            player.setStackInHand(hand, new ItemStack(Items.WATER_BUCKET));
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
