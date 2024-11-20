package gg.yasson.commands;

import gg.yasson.YassonPlugin;
import gg.yasson.models.PlayerDataManager;
import gg.yasson.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class RemoveCommand implements CommandExecutor {
    private YassonPlugin plugin;

    public RemoveCommand(YassonPlugin plugin) { this.plugin = plugin ;}

    @Override
    public boolean onCommand(CommandSender send, Command command, String s, String[] args) {
        if (!(send instanceof Player)) {
            send.sendMessage(YassonPlugin.prefix+"Comando denegado");
            return true;
        };

        Player player = (Player) send;
        List<String> homes;
        String data = null;
        PlayerDataManager playerDataManager = plugin.getPlayerDataManager();
        homes = playerDataManager.getHomePlayer(player);
        for (String home : homes) {
            String[] arr = home.split(" ");
            if (Objects.equals(arr[0], args[0])) {
                data = home;
            }
        }
        if (data != null) {
            playerDataManager.removeHome(player, data);
            send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&2Eliminaste la localizción "+args[0]+ " &2correctamente!"));
            return true;
        } else {
            send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&4No se encontro esta localización"));
            return true;
        }
    }
}
