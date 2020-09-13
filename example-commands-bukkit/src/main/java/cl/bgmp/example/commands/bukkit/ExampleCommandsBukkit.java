package cl.bgmp.example.commands.bukkit;

import cl.bgmp.bukkit.util.BukkitCommandsManager;
import cl.bgmp.bukkit.util.CommandsManagerRegistration;
import cl.bgmp.example.commands.bukkit.commands.ExampleNestedCommand;
import cl.bgmp.example.commands.bukkit.commands.ExampleCommand;
import cl.bgmp.minecraft.util.commands.exceptions.CommandException;
import cl.bgmp.minecraft.util.commands.exceptions.CommandPermissionsException;
import cl.bgmp.minecraft.util.commands.exceptions.CommandUsageException;
import cl.bgmp.minecraft.util.commands.CommandsManager;
import cl.bgmp.minecraft.util.commands.exceptions.MissingNestedCommandException;
import cl.bgmp.minecraft.util.commands.exceptions.ScopeMismatchException;
import cl.bgmp.minecraft.util.commands.annotations.TabCompletion;
import cl.bgmp.minecraft.util.commands.exceptions.WrappedCommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class ExampleCommandsBukkit extends JavaPlugin {
    @SuppressWarnings("rawtypes")
    private CommandsManager commandsManager;
    private CommandsManagerRegistration defaultRegistration;

    @SuppressWarnings("unchecked")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            this.commandsManager.execute(command.getName(), args, sender, sender);
        } catch (ScopeMismatchException exception) {
            String[] scopes = exception.getScopes();
            if (!Arrays.asList(scopes).contains("player")) {
                sender.sendMessage("You must use the console to execute this command.");
            } else {
                sender.sendMessage("You must be a player to execute this command.");
            }
        } catch (CommandPermissionsException exception) {
            sender.sendMessage("You do not have permission.");
        } catch (MissingNestedCommandException exception) {
            sender.sendMessage(ChatColor.YELLOW + "⚠ " + ChatColor.RED + exception.getUsage());
        } catch (CommandUsageException exception) {
            sender.sendMessage(ChatColor.RED + exception.getMessage());
            sender.sendMessage(ChatColor.RED + exception.getUsage());
        } catch (WrappedCommandException exception) {
            if (exception.getCause() instanceof NumberFormatException) {
                sender.sendMessage("Expected a number, string received instead.");
            } else {
                sender.sendMessage("An unknown error has occurred.");
                exception.printStackTrace();
            }
        } catch (CommandException exception) {
            sender.sendMessage(ChatColor.RED + exception.getMessage());
        }
        return true;
    }

    @Override
    public void onEnable() {
        commandsManager = new BukkitCommandsManager();
        defaultRegistration = new CommandsManagerRegistration(this, commandsManager);

        registerCommands(ExampleCommand.class, ExampleNestedCommand.class);
    }

    public void registerCommands(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            final Class<?>[] subclasses = clazz.getClasses();

            if (subclasses.length == 0) defaultRegistration.register(clazz);
            else {
                TabCompleter tabCompleter = null;
                Class<?> nestNode = null;
                for (Class<?> subclass : subclasses) {
                    if (subclass.isAnnotationPresent(TabCompletion.class)) {
                        try {
                            tabCompleter = (TabCompleter) subclass.newInstance();
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else nestNode = subclass;
                }

                if (tabCompleter == null) defaultRegistration.register(subclasses[0]);
                else {
                    CommandsManagerRegistration customRegistration = new CommandsManagerRegistration(this, this, tabCompleter, commandsManager);
                    if (subclasses.length == 1) customRegistration.register(clazz);
                    else customRegistration.register(nestNode);
                }
            }
        }
    }
}
