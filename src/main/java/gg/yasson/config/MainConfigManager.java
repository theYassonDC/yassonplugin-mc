package gg.yasson.config;

import gg.yasson.YassonPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class MainConfigManager {
    private CustomConfig configFile;
    private YassonPlugin plugin;
    private int max_homes;
    private String message;


    public MainConfigManager(YassonPlugin plugin) {
        configFile = new CustomConfig("config.yml", null, plugin, false);
        configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = configFile.getConfig();
        max_homes = config.getInt("config.max_homes");
        message = config.getString("config.message");
    }

    public void reloadConfig() {
        configFile.reloadConfig();
        loadConfig();
    }

    public int getMax_homes() {
        return max_homes;
    }

    public String getMessage() {
        return message;
    }
}
