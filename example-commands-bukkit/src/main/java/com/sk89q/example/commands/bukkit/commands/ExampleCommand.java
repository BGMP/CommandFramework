package com.sk89q.example.commands.bukkit.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import org.bukkit.command.CommandSender;

public class ExampleCommand {

    @Command(
            aliases = {"example", "ex"},
            desc = "Example command."
    )
    @CommandPermissions("example.command")
    public static void example(final CommandContext args, final CommandSender sender) {
        sender.sendMessage("I'm an example.");
    }
}
