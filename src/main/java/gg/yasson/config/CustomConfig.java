package gg.yasson.config;

import gg.yasson.YassonPlugin;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private YassonPlugin plugin;

    private String fileName;
    private FileConfiguration fileConfiguration = null;
    private File file = null;
    private String folderName;
    private boolean newFile;

    public CustomConfig(String fileName, String folderName, YassonPlugin plugin, boolean newFile) {
        this.fileName = fileName;
        this.folderName = folderName;
        this.plugin = plugin;
        this.newFile = newFile;
    }

    public String getPath() { return this.fileName;}

    public void registerConfig() {
        if(folderName != null) {
            file = new File(plugin.getDataFolder()+ File.separator+folderName, fileName);
        } else {
            file = new File(plugin.getDataFolder(), fileName);
        }

        if(!file.exists()) {
            if (newFile) {
                try {
                    file.createNewFile();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                if (folderName != null) {
                    plugin.saveResource(folderName+File.separator+fileName, false);
                } else {
                    plugin.saveResource(fileName, false);
                }
            }
        }

        fileConfiguration = new YamlConfiguration();

        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        if (fileConfiguration == null) {
            reloadConfig();
        }
        return fileConfiguration;
    }

    public boolean reloadConfig() {
        if (fileConfiguration == null) {
            if (folderName != null) {
                file = new File(plugin.getDataFolder()+File.separator+folderName, fileName);
            } else {
                file = new File(plugin.getDataFolder(), fileName);
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);

        if (file != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(file);
            fileConfiguration.setDefaults(defConfig);
        }
        return true;
    }
}
