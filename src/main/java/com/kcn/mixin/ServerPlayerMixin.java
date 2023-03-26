package com.kcn.mixin;

import com.kcn.util.IAuthenticationDataGetter;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class ServerPlayerMixin implements IAuthenticationDataGetter {

    private NbtCompound authentication;


    @Inject(at = @At("HEAD"), method = "readNbt")
    public void readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Authentication", 10)) {
            authentication = nbt.getCompound("Authentication");
        }
    }

    @Inject(at = @At("HEAD"), method = "writeNbt")
    public void writeNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if (authentication != null) {
            nbt.put("Authentication", authentication);
        }
    }

    @Override
    public NbtCompound getAuthentication() {
        if (authentication == null) {
            NbtCompound nbtCompound = new NbtCompound();
            nbtCompound.putBoolean("is_authentication", false);
            nbtCompound.putBoolean("is_tip", false);
            nbtCompound.putBoolean("is_child", false);
            nbtCompound.putBoolean("is_teleport", false);
            authentication = nbtCompound;
            return authentication;
        }
        return authentication;
    }

    @Override
    public void setAuthentication(NbtCompound compound) {
        authentication = compound;
    }


}
