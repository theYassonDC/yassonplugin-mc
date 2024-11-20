package gg.yasson.commands;

import gg.yasson.YassonPlugin;
import gg.yasson.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PointsCommand implements CommandExecutor {
    private YassonPlugin plugin;

    public PointsCommand(YassonPlugin plugin) { this.plugin = plugin ;}

    @Override
    public boolean onCommand(CommandSender send, Command command, String s, String[] args) {
        if(!send.hasPermission("yasson.commands.points")) {
            send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cNo tienes permisos para usar este comando."));
            return true;
        }
        int account = 0;
        Player player = null;
        try{
            account = Integer.parseInt(args[2]);
            if(account <= 0){
                send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cDebes usar una cantidad válida."));
                return true;
            }
        }catch (NumberFormatException e) {
            send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cDebes usar una cantidad válida."));
            return true;
        }

        if(args.length == 1){
                if(send instanceof Player){
                    player = (Player)send;
                }else{
                    send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cDebes usar: &7/ypoint <add | remove> <user> <ammount>"));
                    return true;
                }
        }else {
            player = Bukkit.getPlayer(args[1]);
            if(Objects.equals(args[0], "add")) {
                if (player == null) {
                    send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cEl jugador &7"+args[1]+" &cno esta conectado."));
                    return true;
                }
                plugin.getPlayerDataManager().addPoints(player, account);
                player.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&eFelicidades acabaste de recibir "+account+" puntos de teletransportación"));
            }
            if(Objects.equals(args[0], "remove")) {
                if (player == null) {
                    send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cEl jugador &7"+args[1]+" &cno esta conectado."));
                    return true;
                }
                plugin.getPlayerDataManager().removePoints(player, account);
                player.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&eTe eliminaron -"+account+" &epuntos de teletransportación"));
            }
        }
        return true;
    }
}
