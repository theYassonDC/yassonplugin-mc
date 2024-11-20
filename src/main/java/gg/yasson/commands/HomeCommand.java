package gg.yasson.commands;

import gg.yasson.YassonPlugin;
import gg.yasson.models.PlayerDataManager;
import gg.yasson.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;


public class HomeCommand  implements CommandExecutor {
    private YassonPlugin plugin;

    public HomeCommand(YassonPlugin plugin) { this.plugin = plugin ;}

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (!(send instanceof Player)) {
            send.sendMessage(YassonPlugin.prefix+"Comando denegado");
            return true;
        };
        Player player = (Player) send;
        List<String> homeData;
        //String arg = ;
        PlayerDataManager playerDataManager = plugin.getPlayerDataManager();
        homeData = playerDataManager.getHomePlayer(player);
        String world = "";
        double x = 0.0f;
        double y = 0.0f;
        double z = 0.0f;
        for (String home : homeData) {
            String[] arr = home.split(" ");
            if (Objects.equals(arr[0], args[0])) {
                world = arr[4];
                x = Double.parseDouble(arr[1]);
                y = Double.parseDouble(arr[2]);
                z = Double.parseDouble(arr[3]);
            }
        }
        if (Bukkit.getWorld(world) == null) {
            send.sendMessage(MessageUtils.getColoredMessage(YassonPlugin.prefix+"&4Localización no encontrada"));
            return true;
        }
        if (args.length <= 0) {
            send.sendMessage(MessageUtils.getColoredMessage(YassonPlugin.prefix+"&4Comando correcto es /yhome <name>"));
            return true;
        }
        Location location = new Location(Bukkit.getWorld(world), x, y, z);
        int points = playerDataManager.getPointsPlayer(player);
        if (points >= 0) {
            playerDataManager.removePoints(player, 1);
            player.teleport(location);
            send.sendMessage(
                    (
                            MessageUtils.getColoredMessage(
                                    plugin.prefix+"&aTe teletransportaste a "+args[0]+" puntos restantes "+points
                            )
                    )
            );
            return false;
        } else {
            send.sendMessage(MessageUtils.getColoredMessage(YassonPlugin.prefix+"&4Te quedaste sin puntos de teletransportación"));
            return true;
        }
    }
}
