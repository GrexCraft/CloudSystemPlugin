package net.grexcraft.cloud_plugin.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class WhereamiCommand implements CommandExecutor {
    private final String serverName;

    public WhereamiCommand(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.spigot().sendMessage(
                    new ComponentBuilder("You are here: ").color(ChatColor.GRAY)
                            .append(serverName).color(ChatColor.YELLOW)
                            .event(
                                    new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                    new ComponentBuilder("Copy to clipboard!").create()
                            ))
                            .event(
                                    new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
                                    serverName
                            ))
                            .create()
            );
        }
        return true;
    }
}
