package net.grexcraft.cloud_plugin.command;

import net.grexcraft.cloud_plugin.CloudSystemPlugin;
import net.grexcraft.cloud_plugin.enums.ServerState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ModifyServerCommand implements CommandExecutor {

    CloudSystemPlugin plugin;

    public ModifyServerCommand(CloudSystemPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        plugin.modifyServer(ServerState.valueOf(strings[0]));
        return true;
    }
}
