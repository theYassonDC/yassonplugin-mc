package gg.yasson.config;

import gg.yasson.YassonPlugin;
import gg.yasson.models.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class PlayersConfigManager extends DataFolderConfigManager {
    public PlayersConfigManager(YassonPlugin plugin, String folderName) {
        super(plugin, folderName);
    }

    @Override
    public void loadConfigs() {
        Map<UUID, PlayerData> players = new HashMap<>();
        for (CustomConfig customConfig : configFiles) {
            FileConfiguration config = customConfig.getConfig();
            UUID uuid = UUID.fromString(customConfig.getPath().replace(".yml", ""));
            String name = config.getString("name");
            int points = config.getInt("points");
            List<String> homes = config.getStringList("homes");
            PlayerData playersData = new PlayerData(uuid, name, points, homes);
            players.put(uuid,playersData);
        }
        plugin.getPlayerDataManager().setPlayers(players);
    }

    @Override
    public void saveConfigs() {
        Map<UUID, PlayerData> players = plugin.getPlayerDataManager().getPlayers();
        for (Map.Entry<UUID, PlayerData> entry : players.entrySet()) {
            PlayerData playerData = entry.getValue();
            String pathName = playerData.getUuid().toString()+".yml";
            CustomConfig customConfig = getConfigFile(pathName);
            if (customConfig == null) {
                customConfig = registerConfigFile(pathName);
            }

            FileConfiguration config = customConfig.getConfig();
            config.set("name", playerData.getName());
            config.set("points", playerData.getPoints());
            config.set("homes", playerData.getHomes());
        }
        saveConfigFiles();
    }
}
