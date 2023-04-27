package cl.bgm.bungee.util;

import cl.bgm.minecraft.util.commands.CommandScope;
import cl.bgm.minecraft.util.commands.CommandsManager;
import net.md_5.bungee.api.CommandSender;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeCommandsManager extends CommandsManager<CommandSender> {
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
                return player instanceof ProxiedPlayer;
            case CONSOLE:
                return !(player instanceof ProxiedPlayer);
            default:
                return false;
        }
    }
}
