package gg.yasson.models;

import org.bukkit.entity.Player;

import java.util.*;

public class PlayerDataManager {
    private Map<UUID, PlayerData> players;
    private Map<String, UUID> playerNames;

    public PlayerDataManager () {
        players = new HashMap<>();
        playerNames = new HashMap<>();
    }

    public Map<UUID, PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(Map<UUID, PlayerData> players) {
        this.players = players;
        for(Map.Entry<UUID,PlayerData> entry : players.entrySet()){
            playerNames.put(entry.getValue().getName(),entry.getKey());
        }
    }

    public void addPlayer(PlayerData p){
        players.put(p.getUuid(),p);
        playerNames.put(p.getName(),p.getUuid());
    }

    public PlayerData getPlayer(Player player, boolean create){
        PlayerData playerData = players.get(player.getUniqueId());
        if(playerData == null && create){
            List<String> homes = new ArrayList<>();
            playerData = new PlayerData(player.getUniqueId(),player.getName(),3, homes);
            addPlayer(playerData);
        }
        return playerData;
    }

    public PlayerData getPlayerByName(String playerName){
        UUID uuid = playerNames.get(playerName);
        return players.get(uuid);
    }

    public void addPoints(Player player, int ammount) {
        PlayerData playerData = getPlayer(player, true);
        playerData.setPoints(playerData.getPoints()+ammount);
    }

    public void removePoints(Player player, int ammount) {
        PlayerData playerData = getPlayer(player, false);
        playerData.setPoints(playerData.getPoints()-ammount);
    }

    public void addHome(Player player, String home) {
        PlayerData playerData = getPlayer(player, true);
        playerData.getHomes().add(home);
    }

    public void removeHome(Player player, String home) {
        PlayerData playerData = getPlayer(player, true);
        playerData.getHomes().remove(home);
    }


    public int getPointsPlayer(Player player){
        PlayerData playerData = getPlayer(player,false);
        if(playerData != null){
            return playerData.getPoints();
        }
        return 0;
    }

    public List<String> getHomePlayer(Player player) {
        PlayerData playerData = getPlayer(player, false);
        if (playerData != null) {
            return playerData.getHomes();
        }
        return null;
    }

    public void updateName(Player player) {
        PlayerData playerData = getPlayer(player, false);
        if(playerData != null){
            String newName = player.getName();
            String oldName = playerData.getName();
            if(!newName.equals(oldName)){
                playerNames.remove(oldName);
                playerNames.put(newName,player.getUniqueId());
                playerData.setName(newName);
            }
        }
    }
}
