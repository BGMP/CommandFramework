package cl.bgmp.minecraft.util.commands.wrapped;

import cl.bgmp.minecraft.util.commands.CommandsManager;

public class WrappedCommandsManager extends CommandsManager<WrappedCommandSender> {
    @Override
    public boolean hasPermission(WrappedCommandSender player, String perm) {
        return player.hasPermission(perm);
    }

    @Override
    public boolean scopeMatches(WrappedCommandSender player, String scope) {
        switch (scope.toLowerCase()) {
            case "player":
                return player.getType() == WrappedCommandSender.Type.PLAYER;
            case "console":
                return player.getType() == WrappedCommandSender.Type.CONSOLE;
            case "block":
                return player.getType() == WrappedCommandSender.Type.BLOCK;
            default:
                return false; // When unknown
        }
    }
}
