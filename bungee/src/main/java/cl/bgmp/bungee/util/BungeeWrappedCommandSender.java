package cl.bgmp.bungee.util;

import cl.bgmp.minecraft.util.commands.wrapped.WrappedCommandSender;
import net.md_5.bungee.api.CommandSender;

@SuppressWarnings("deprecation")
public class BungeeWrappedCommandSender implements WrappedCommandSender {
    public BungeeWrappedCommandSender(CommandSender wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getName() {
        return this.wrapped.getName();
    }

    @Override
    public void sendMessage(String message) {
        this.wrapped.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] messages) {
        this.wrapped.sendMessages(messages);
    }

    @Override
    public boolean hasPermission(String permission) {
        return this.wrapped.hasPermission(permission);
    }

    @Override
    public Type getType() {
        if (this.wrapped.getName().equals("CONSOLE")) { // hack because Bungee does not export ConsoleCommandSender
            return Type.CONSOLE;
        } else {
            return Type.PLAYER;
        }
    }

    @Override
    public Object getCommandSender() {
        return this.wrapped;
    }

    private final CommandSender wrapped;
}
