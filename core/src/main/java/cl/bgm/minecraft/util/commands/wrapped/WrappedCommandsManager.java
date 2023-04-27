package cl.bgm.minecraft.util.commands.wrapped;

import cl.bgm.minecraft.util.commands.CommandScope;
import cl.bgm.minecraft.util.commands.CommandsManager;

import static cl.bgm.minecraft.util.commands.CommandScope.BLOCK;
import static cl.bgm.minecraft.util.commands.CommandScope.CONSOLE;
import static cl.bgm.minecraft.util.commands.CommandScope.PLAYER;

public class WrappedCommandsManager extends CommandsManager<WrappedCommandSender> {
    @Override
    public boolean hasPermission(WrappedCommandSender player, String perm) {
        return player.hasPermission(perm);
    }

    @Override
    public boolean scopeMatches(WrappedCommandSender player, CommandScope scope) {
        switch (scope) {
            case ANY:
                return true;
            case PLAYER:
                return player.getType() == WrappedCommandSender.Type.PLAYER;
            case CONSOLE:
                return player.getType() == WrappedCommandSender.Type.CONSOLE;
            case BLOCK:
                return player.getType() == WrappedCommandSender.Type.BLOCK;
            default:
                return false; // When unknown
        }
    }
}
