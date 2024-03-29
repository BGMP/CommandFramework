// $Id$
/*
 * WorldEdit
 * Copyright (C) 2011 sk89q <http://www.sk89q.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package cl.bgm.bukkit.util;

import cl.bgm.minecraft.util.commands.CommandsManager;
import cl.bgm.minecraft.util.commands.annotations.Command;
import cl.bgm.minecraft.util.commands.annotations.CommandPermissions;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.inject.Provider;

/**
 * @author zml2008
 */
public class CommandsManagerRegistration extends CommandRegistration {
    protected CommandsManager<?> commands;

    public CommandsManagerRegistration(Plugin plugin, CommandsManager<?> commands) {
        super(plugin);
        this.commands = commands;
    }

    public CommandsManagerRegistration(Plugin plugin, CommandExecutor executor, CommandsManager<?> commands) {
        super(plugin, executor);
        this.commands = commands;
    }

    public CommandsManagerRegistration(Plugin plugin, CommandExecutor executor, @Nullable TabCompleter completer, CommandsManager<?> commands) {
        super(plugin, executor, completer);
        this.commands = commands;
    }

    public boolean register(Class<?> clazz) {
        return register(clazz, null, null);
    }

    /* Allows for commands to specify aliases at registration time */
    public boolean register(Class<?> clazz, String[] aliases) {
        return register(clazz, null, aliases);
    }

    public <T> boolean register(Class<T> clazz, @Nullable Provider<? extends T> provider, String[] aliases) {
        return registerAll(commands.registerMethods(clazz, null, provider), aliases);
    }

    public boolean registerAll(List<Command> registered, String[] aliases) {
        List<CommandInfo> toRegister = new ArrayList<CommandInfo>();
        for (Command command : registered) {
            String[] permissions = null;
            Method cmdMethod = commands.getMethods().get(null).get(command.aliases()[0]);
            if (cmdMethod != null) {
                if (cmdMethod.isAnnotationPresent(CommandPermissions.class)) {
                    permissions = cmdMethod.getAnnotation(CommandPermissions.class).value();
                }
            }

            String[] cmdAliases = command.aliases();
            if (aliases != null) cmdAliases = (String[]) ArrayUtils.addAll(command.aliases(), aliases);

            toRegister.add(new CommandInfo(command.usage(), command.desc(), cmdAliases, commands, permissions));
        }

        return register(toRegister);
    }
}
