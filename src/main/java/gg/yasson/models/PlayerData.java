package gg.yasson.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private String name;
    private int points;
    private List<String> homes;

    public PlayerData(UUID uuid, String name, int points, List<String> homes) {
        this.uuid = uuid;
        this.name = name;
        this.points = points;
        this.homes = homes;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public int getPoints() {
        return points;
    }

    public List<String> getHomes() {
        return homes;
    }

    public void setHomes(List<String> homes) {
        this.homes = homes;
    }
}
