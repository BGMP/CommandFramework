package cl.bgmp.bungee.util;


public interface CommandExecutor<T> {
    void onCommand(T sender, String commandName, String[] args);
}
