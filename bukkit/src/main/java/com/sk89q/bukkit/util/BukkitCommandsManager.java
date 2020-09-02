package com.sk89q.bukkit.util;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;

import com.sk89q.minecraft.util.commands.CommandsManager;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BukkitCommandsManager extends CommandsManager<CommandSender> {
    @Override
    public boolean hasPermission(CommandSender player, String perm) {
        return player.hasPermission(perm);
    }

    @Override
    public boolean scopeMatches(CommandSender player, String scope) {
        switch (scope.toLowerCase()) {
            case "player":
                return player instanceof Player;
            case "console":
                return player instanceof ConsoleCommandSender;
            case "block":
                return player instanceof BlockCommandSender;
            default:
                return false;
        }
    }
}
