package cl.bgm.minecraft.util.commands.exceptions;

import cl.bgm.minecraft.util.commands.CommandScope;

/**
 * Thrown when the command scope doesn't match the sender
 */
public class ScopeMismatchException extends CommandException {
    CommandScope[] scopes;

    public ScopeMismatchException(String message, CommandScope[] scopes) {
        super(message);
        this.scopes = scopes;
    }

    public CommandScope[] getScopes() {
        return scopes;
    }
}
