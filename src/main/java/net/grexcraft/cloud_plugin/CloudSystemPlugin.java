package net.grexcraft.cloud_plugin;

import net.grexcraft.cloud_plugin.client.CloudWebClient;
import net.grexcraft.cloud_plugin.command.DevCommand;
import net.grexcraft.cloud_plugin.command.ModifyServerCommand;
import net.grexcraft.cloud_plugin.command.SendToProxyCommand;
import net.grexcraft.cloud_plugin.command.WhereamiCommand;
import net.grexcraft.cloud_plugin.enums.ServerState;
import net.grexcraft.cloud_plugin.listener.BungeePluginMessageListener;
import net.grexcraft.cloud_plugin.model.ModifyServerRequest;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class CloudSystemPlugin extends JavaPlugin {

    private final String serverName = System.getenv("GC_SERVER_NAME");

    @Override
    public void onEnable() {
        super.onEnable();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeePluginMessageListener());

        initCommands();

        Bukkit.getLogger().info(this.getClass().getName() + " has enabled for server: " + serverName);


        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(this, () -> {
            modifyServer(ServerState.RUNNING);
        }, 20L * 2L /*<-- after 2 seconds */);
    }



    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);

        modifyServer(ServerState.STOPPED);

    }

    private void initCommands() {
        getCommand("sendtoproxy").setExecutor(new SendToProxyCommand());
        getCommand("dev").setExecutor(new DevCommand(this));
        getCommand("modifyserver").setExecutor(new ModifyServerCommand(this));
        getCommand("whereami").setExecutor(new WhereamiCommand(serverName));
    }

    public void modifyServer(ServerState state) {
        if (serverName == null) return;
        CloudWebClient.modifyServer(new ModifyServerRequest(serverName, state));
    }
}