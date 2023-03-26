package com.kcn.util;

import net.minecraft.nbt.NbtCompound;

public interface IAuthenticationDataGetter {

    NbtCompound getAuthentication();

    void setAuthentication(NbtCompound compound);

}
