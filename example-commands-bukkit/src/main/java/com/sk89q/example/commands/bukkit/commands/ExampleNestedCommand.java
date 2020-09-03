package com.sk89q.example.commands.bukkit.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.CommandScopes;
import com.sk89q.minecraft.util.commands.NestedCommand;
import com.sk89q.minecraft.util.commands.TabCompletion;
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
        @NestedCommand(ExampleNestedCommand.class)
        public static void hello(final CommandContext args, final CommandSender sender) {
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
