package net.grexcraft.cloud_plugin;

import net.grexcraft.cloud_plugin.command.DevCommand;
import net.grexcraft.cloud_plugin.command.SendToProxyCommand;
import net.grexcraft.cloud_plugin.listener.BungeePluginMessageListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class CloudSystemPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeePluginMessageListener());

        initCommands();

        Bukkit.getLogger().info(this.getClass().getName() + " has enabled!");
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    private void initCommands() {
        getCommand("sendtoproxy").setExecutor(new SendToProxyCommand());
        getCommand("dev").setExecutor(new DevCommand(this));
    }
}