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
import cl.bgm.util.StringUtil;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

/**
* @author zml2008
*/
public class DynamicPluginCommand extends org.bukkit.command.Command implements PluginIdentifiableCommand {

    protected final CommandExecutor executor;
    protected final @Nullable TabCompleter completer;
    protected final Object registeredWith;
    protected final Plugin owningPlugin;
    protected String[] permissions = new String[0];

    public DynamicPluginCommand(String[] aliases, String desc, String usage, CommandExecutor executor, @Nullable TabCompleter completer, Object registeredWith, Plugin plugin) {
        super(aliases[0], desc, usage, Arrays.asList(aliases));
        this.executor = executor;
        this.completer = completer;
        this.owningPlugin = plugin;
        this.registeredWith = registeredWith;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        return executor.onCommand(sender, this, label, args);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if(completer != null) {
            final List<String> completions = completer.onTabComplete(sender, this, alias, args);
            if(completions != null) {
                return completions;
            }
        }

        return super.tabComplete(sender, alias, args);
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public TabCompleter getCompleter() {
        return completer;
    }

    public Object getRegisteredWith() {
        return registeredWith;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
        if (permissions != null) {
            super.setPermission(StringUtil.joinString(permissions, ";"));
        }
    }

    public String[] getPermissions() {
        return permissions;
    }

    @Override
    public Plugin getPlugin() {
        return owningPlugin;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean testPermissionSilent(CommandSender sender) {
        if (permissions == null || permissions.length == 0) {
            return true;
        }

        if (registeredWith instanceof CommandsManager<?>) {
            try {
                for (String permission : permissions) {
                    if (((CommandsManager<CommandSender>) registeredWith).hasPermission(sender, permission)) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable ignore) {
            }
        }
        return super.testPermissionSilent(sender);
    }
}
