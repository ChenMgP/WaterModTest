package com.kcn.mixin;

import com.kcn.util.AuthenticationData;
import com.kcn.util.IAuthenticationDataGetter;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(at = @At("HEAD"), method = "respawnPlayer")
    public void respawnPlayer(ServerPlayerEntity player, boolean alive, CallbackInfoReturnable<ServerPlayerEntity> cir) {
        if (alive) {
            NbtCompound authentication = new NbtCompound();
            authentication.putBoolean("is_authentication", true);
            authentication.putBoolean("is_child", false);
            authentication.putBoolean("is_teleport", true);
            authentication.putBoolean("is_tip", true);
            ((IAuthenticationDataGetter) player).setAuthentication(authentication);
        }
    }

}
