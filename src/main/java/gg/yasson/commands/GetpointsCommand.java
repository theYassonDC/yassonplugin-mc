package gg.yasson.commands;

import gg.yasson.YassonPlugin;
import gg.yasson.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetpointsCommand implements CommandExecutor {
    private YassonPlugin plugin;

    public GetpointsCommand(YassonPlugin plugin) { this.plugin = plugin ;}

    @Override
    public boolean onCommand(CommandSender send, Command command, String s, String[] args) {
        if (!(send instanceof Player)) {
            send.sendMessage(YassonPlugin.prefix+"Comando denegado");
            return true;
        };
        if (args.length == 0) {
            send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cDebes usar: &7/ypoints <jugador>"));
            return true;
        }
        Player player = null;
        if(args.length == 1){
            if(send instanceof Player){
                player = (Player)send;
            }else{
                send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cDebes usar: &7/ypoints <jugador>"));
                return true;
            }
        }else{
            player = Bukkit.getPlayer(args[1]);
            if(player == null){
                send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&cEl jugador &7"+args[1]+" &cno esta conectado."));
                return true;
            }
        }
        player.sendMessage(
                MessageUtils.getColoredMessage(
                        "&2| Puntos de "+player.getName()+"\n&2| Total: &a"+plugin.getPlayerDataManager().getPointsPlayer(player)+"\n[! ] 1 punto = 100 server xp"
                )
        );
        return false;
    }
}
