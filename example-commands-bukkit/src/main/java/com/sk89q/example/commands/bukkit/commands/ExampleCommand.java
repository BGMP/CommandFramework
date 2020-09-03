package com.sk89q.example.commands.bukkit.commands;

import com.sk89q.minecraft.util.commands.annotations.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.annotations.CommandPermissions;
import com.sk89q.minecraft.util.commands.annotations.CommandScopes;
import com.sk89q.minecraft.util.commands.annotations.TabCompletion;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExampleCommand {

    @Command(
            aliases = {"example", "ex"},
            desc = "Example command.",
            max = 1
    )
    @CommandPermissions("example.command")
    @CommandScopes({"player"})
    public static void example(final CommandContext args, final CommandSender sender) {
        if (args.argsLength() == 1) {
            sender.sendMessage("I'm a " + args.getString(0));
        } else {
            sender.sendMessage("I'm an example");
        }
    }

    @TabCompletion
    public static class ExampleCommandTabCompleter implements TabCompleter {
        @Override
        public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
            switch (args.length) {
                case 1:
                    return Arrays.asList("1", "2", "3");
                default:
                    return Collections.emptyList();
            }
        }
    }
}
