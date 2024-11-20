package gg.yasson.commands;

import gg.yasson.YassonPlugin;
import gg.yasson.models.PlayerDataManager;
import gg.yasson.utils.MessageUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SetHomeCommand implements CommandExecutor {

    private YassonPlugin plugin;

    public SetHomeCommand(YassonPlugin plugin) { this.plugin = plugin ;}

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (!(send instanceof Player)) {
            send.sendMessage(YassonPlugin.prefix+"Comando denegado");
            return true;
        };
        Player player = (Player)send;
        PlayerDataManager playerDataManager = plugin.getPlayerDataManager();
        Location location = player.getLocation();
        if (args.length <= 0) {
            send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&4 El comando se debe utilizar como: /ysethome <nombre>"));
            return true;
        }

        String format = MessageFormat.format("{0} {1} {2} {3} {4}",
                args[0],
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ(),
                location.getWorld().getName()
        );
        if (plugin.getMainConfigManager().getMax_homes() > playerDataManager.getHomePlayer(player).size()) {
            playerDataManager.addHome(player, format);
            send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&2Se guardo una localization llamada "+args[0]));
        } else {
            send.sendMessage(MessageUtils.getColoredMessage(plugin.prefix+"&4Alcanzaste el maximo de homes que puedes guardar!"));
            return true;
        }
        return true;
    }
}
