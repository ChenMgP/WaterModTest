package com.kcn.util;

import net.minecraft.nbt.NbtCompound;

public class AuthenticationData {

    public static boolean isTeleport(IAuthenticationDataGetter player) {
        return player.getAuthentication().getBoolean("is_teleport");
    }

    public static void setTeleport(IAuthenticationDataGetter player) {
        NbtCompound authentication = player.getAuthentication();
        authentication.putBoolean("is_teleport", true);
    }

    public static boolean isTip(IAuthenticationDataGetter player) {
        return player.getAuthentication().getBoolean("is_tip");
    }

    public static void setTip(IAuthenticationDataGetter player) {
        NbtCompound authentication = player.getAuthentication();
        authentication.putBoolean("is_tip", true);
    }

    public static void setChild(IAuthenticationDataGetter player) {
        NbtCompound authentication = player.getAuthentication();
        authentication.putBoolean("is_authentication", true);
        authentication.putBoolean("is_child", true);
    }

    public static void setAuthentication(IAuthenticationDataGetter player) {
        NbtCompound authentication = player.getAuthentication();
        authentication.putBoolean("is_authentication", true);
    }

    public static boolean isAuthentication(IAuthenticationDataGetter player) {
        NbtCompound authentication = player.getAuthentication();
        return authentication.getBoolean("is_authentication");
    }

    public static boolean isChild(IAuthenticationDataGetter player) {
        NbtCompound authentication = player.getAuthentication();
        return authentication.getBoolean("is_child");
    }

    public static void reload(IAuthenticationDataGetter player) {
        NbtCompound authentication = player.getAuthentication();
        authentication.putBoolean("is_authentication", true);
        authentication.putBoolean("is_child", false);
        authentication.putBoolean("is_teleport", true);
        authentication.putBoolean("is_teleport", true);
    }

}
