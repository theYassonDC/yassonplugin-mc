package gg.yasson;

import gg.yasson.commands.*;
import gg.yasson.config.MainConfigManager;
import gg.yasson.config.PlayersConfigManager;
import gg.yasson.listeners.PlayerListeners;
import gg.yasson.models.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class YassonPlugin extends JavaPlugin {

    public static String prefix = "&e[&lYASSON LOG] ";
    public MainConfigManager mainConfigManager;
    private PlayersConfigManager playersConfigManager;
    private PlayerDataManager playerDataManager;

    public void onEnable() {
        registerCommand();
        registerEvents();
        mainConfigManager = new MainConfigManager(this);
        playerDataManager = new PlayerDataManager();
        playersConfigManager = new PlayersConfigManager(this, "players");

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+"&fYassonPlugin started!"));
    }

    public void onDisable() {
        playersConfigManager.saveConfigs();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix+"&fYassonPlugin stop!"));
    }

    public void registerCommand() {
        getCommand("yhelp").setExecutor(new MainCommand());
        getCommand("yhome").setExecutor(new HomeCommand(this));
        getCommand("ysethome").setExecutor(new SetHomeCommand(this));
        getCommand("yhomes").setExecutor(new HomesCommand(this));
        getCommand("ypoint").setExecutor(new PointsCommand(this));
        getCommand("ydelhome").setExecutor(new RemoveCommand(this));
        getCommand("ypoints").setExecutor(new GetpointsCommand(this));
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerListeners(this), this);
    }

    public PlayersConfigManager getPlayersConfigManager() {
        return playersConfigManager;
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }
}
