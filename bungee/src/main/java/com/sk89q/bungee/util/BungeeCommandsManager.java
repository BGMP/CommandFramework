package com.sk89q.bungee.util;

import net.md_5.bungee.api.CommandSender;

import com.sk89q.minecraft.util.commands.CommandsManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeCommandsManager extends CommandsManager<CommandSender> {
    @Override
    public boolean hasPermission(CommandSender player, String perm) {
        return player.hasPermission(perm);
    }

    @Override
    public boolean scopeMatches(CommandSender player, String scope) {
        switch (scope.toLowerCase()) {
            case "player":
                return player instanceof ProxiedPlayer;
            case "console":
                return !(player instanceof ProxiedPlayer);
            default:
                return false;
        }
    }
}
