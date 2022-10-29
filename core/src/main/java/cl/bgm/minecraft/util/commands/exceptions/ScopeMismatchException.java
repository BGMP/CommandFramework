package cl.bgm.minecraft.util.commands.exceptions;

/**
 * Thrown when the command scope doesn't match the sender
 */
public class ScopeMismatchException extends CommandException {
    String[] scopes;

    public ScopeMismatchException(String message, String[] scopes) {
        super(message);
        this.scopes = scopes;
    }

    public String[] getScopes() {
        return scopes;
    }
}
