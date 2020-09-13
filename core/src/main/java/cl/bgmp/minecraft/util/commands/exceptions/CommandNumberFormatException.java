package cl.bgmp.minecraft.util.commands.exceptions;

public class CommandNumberFormatException extends CommandException {

    private final String actualText;

    public CommandNumberFormatException(String actualText) {
        super("Number expected in place of '" + actualText + "'");
        this.actualText = actualText;
    }

    public String getActualText() {
        return actualText;
    }
}
