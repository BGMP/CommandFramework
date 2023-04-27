package cl.bgm.bukkit.util;

import cl.bgm.minecraft.util.commands.CommandScope;
import cl.bgm.minecraft.util.commands.CommandsManager;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BukkitCommandsManager extends CommandsManager<CommandSender> {
    @Override
    public boolean hasPermission(CommandSender player, String perm) {
        return player.hasPermission(perm);
    }

    @Override
    public boolean scopeMatches(CommandSender player, CommandScope scope) {
        switch (scope) {
            case ANY:
                return true;
            case PLAYER:
                return player instanceof Player;
            case CONSOLE:
                return player instanceof ConsoleCommandSender;
            case BLOCK:
                return player instanceof BlockCommandSender;
            default:
                return false;
        }
    }
}
