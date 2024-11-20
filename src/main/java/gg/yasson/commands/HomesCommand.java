package gg.yasson.commands;

import gg.yasson.YassonPlugin;
import gg.yasson.models.PlayerDataManager;
import gg.yasson.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HomesCommand implements CommandExecutor {
    private YassonPlugin plugin;

    public HomesCommand(YassonPlugin plugin) { this.plugin = plugin ;}
    @Override
    public boolean onCommand(CommandSender send, Command command, String s, String[] args) {
        if (!(send instanceof Player)) {
            send.sendMessage(MessageUtils.getColoredMessage(YassonPlugin.prefix+"&4Comando denegado"));
            return true;
        };
        Player player = (Player) send;
        PlayerDataManager playerDataManager = plugin.getPlayerDataManager();
        List<String> homes = playerDataManager.getHomePlayer(player);
        if (homes == null) {
            send.sendMessage(MessageUtils.getColoredMessage("&4No tienes una lista de homes\n&eCrea con el comando /ysethome <name>"));
            return true;
        }
        send.sendMessage(MessageUtils.getColoredMessage("&f--------- &e&lLista de localizaciones &f---------"));
        for(String home : homes) {
            String[] data = home.split(" ");
            send.sendMessage(MessageUtils.getColoredMessage("&2-"+data[0]));
        }
        send.sendMessage(MessageUtils.getColoredMessage("-----------------------------------\n&eTeletransportate a la localización con el comando /yhome <home>"));
        return false;
    }
}
