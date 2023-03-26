package com.kcn.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerTickHandler implements ServerTickEvents.StartTick {


    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if (AuthenticationData.isChild(((IAuthenticationDataGetter) player))) {
                player.networkHandler.disconnect(new LiteralText(""));
            }
            if (!AuthenticationData.isAuthentication(((IAuthenticationDataGetter) player))) {
                player.requestTeleport(0, 1000, 0);
                if (!AuthenticationData.isTip(((IAuthenticationDataGetter) player))) {
                    player.sendMessage(new LiteralText("请使用 /idCard 命令,完成实名认证,本mod不会存储您的身份证信息,请放心输入." + "源码已在GitHub上存储,若有怀疑请按照以下网址查看源码" + "网址: https://github.com/ChenMgP/AuthenticationMod").formatted(Formatting.BLUE), false);
                    AuthenticationData.setTip(((IAuthenticationDataGetter) player));
                }
            } else {
                if (!AuthenticationData.isTeleport(((IAuthenticationDataGetter) player))) {
                    AuthenticationData.setTeleport(((IAuthenticationDataGetter) player));
                    land(player);
                }
            }
        }
    }

    public void land(PlayerEntity entity) {
        World world = entity.getWorld();
        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;
            BlockPos spawnPos = serverWorld.getSpawnPos();
            entity.requestTeleport(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
        }
    }
}
