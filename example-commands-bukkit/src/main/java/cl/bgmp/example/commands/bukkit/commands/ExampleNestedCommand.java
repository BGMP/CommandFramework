package cl.bgmp.example.commands.bukkit.commands;

import cl.bgmp.minecraft.util.commands.annotations.Command;
import cl.bgmp.minecraft.util.commands.CommandContext;
import cl.bgmp.minecraft.util.commands.annotations.CommandPermissions;
import cl.bgmp.minecraft.util.commands.annotations.CommandScopes;
import cl.bgmp.minecraft.util.commands.annotations.NestedCommand;
import cl.bgmp.minecraft.util.commands.annotations.TabCompletion;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExampleNestedCommand {
    @Command(
            aliases = {"me"},
            desc = "Hello me command.",
            max = 1
    )
    @CommandPermissions("hello.command.me")
    @CommandScopes({"player"})
    public static void me(final CommandContext args, final CommandSender sender) {
        if (args.argsLength() == 0) {
            sender.sendMessage("hello me");
        } else {
            String to = args.getString(0);
            sender.sendMessage("hello me " + to);
        }
    }

    @Command(
            aliases = {"you"},
            desc = "Hello you command.",
            max = 1
    )
    @CommandPermissions("hello.command.you")
    @CommandScopes({"player"})
    public static void you(final CommandContext args, final CommandSender sender) {
        if (args.argsLength() == 0) {
            sender.sendMessage("hello you");
        } else {
            String to = args.getString(0);
            sender.sendMessage("hello you " + to);
        }
    }

    public static class ExampleNestedCommandNode {
        @Command(
                aliases = {"hello"},
                desc = "Hello node command."
        )
        @CommandPermissions("hello.command")
        @CommandScopes({"player"})
        @NestedCommand(value = ExampleNestedCommand.class, executeBody = true)
        public static void hello(final CommandContext args, final CommandSender sender) {
            sender.sendMessage("I'm the method body.");
        }
    }

    @TabCompletion
    public static class ExampleNestedCommandTabCompleter implements TabCompleter {
        @Override
        public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
            switch (args.length) {
                case 1:
                    return Arrays.asList("me", "you");
                case 2:
                    return Arrays.stream(EntityType.values()).map(Enum::toString).collect(Collectors.toList());
                default:
                    return Collections.emptyList();
            }
        }
    }
}
