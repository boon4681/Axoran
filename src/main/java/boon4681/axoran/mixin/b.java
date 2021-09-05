package boon4681.axoran.mixin;

import boon4681.axoran.exts.b_ext;
import net.minecraft.entity.AngledModelEntity;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(AxolotlEntity.class)
public abstract class b extends AnimalEntity implements AngledModelEntity, Bucketable, b_ext {
    private boolean rainbow = false;

    @Override
    public boolean isRainbow() {
        return rainbow;
    }

    protected b(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "interactMob",at = @At(value = "HEAD"))
    protected void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        boolean bl = Ingredient.ofItems(Items.AXOLOTL_BUCKET).test(stack);
        if (bl){
            rainbow = true;
            player.setStackInHand(hand,new ItemStack(Items.WATER_BUCKET));
            return;
        }
    }
}
