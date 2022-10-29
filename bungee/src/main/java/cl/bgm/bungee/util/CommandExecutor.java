package cl.bgm.bungee.util;


public interface CommandExecutor<T> {
    void onCommand(T sender, String commandName, String[] args);
}
