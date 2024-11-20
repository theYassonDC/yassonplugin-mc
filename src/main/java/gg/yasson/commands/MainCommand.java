package gg.yasson.commands;

import gg.yasson.YassonPlugin;
import gg.yasson.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender send, Command cmd, String alias, String[] args) {
        if (!(send instanceof Player)) {
            send.sendMessage(YassonPlugin.prefix+"Comando denegado");
            return true;
        };
        help(send);
        return true;
    }

    public void help(CommandSender send) {
        send.sendMessage(MessageUtils.getColoredMessage("&f----------- &l&5YassonPlugin commands list &f-----------"));
        send.sendMessage(MessageUtils.getColoredMessage("&f- /ysethome"));
        send.sendMessage(MessageUtils.getColoredMessage("&f- /yhome"));
        send.sendMessage(MessageUtils.getColoredMessage("&f- /yhome <home>"));
        send.sendMessage(MessageUtils.getColoredMessage("&f- /ydelhome <home>"));
        send.sendMessage(MessageUtils.getColoredMessage("&f- /ypoints"));
        send.sendMessage(MessageUtils.getColoredMessage("&f- &4/ypoint <add | remove> <user> <ammount>"));
        send.sendMessage(MessageUtils.getColoredMessage("&f-------------------------------------------------"));
    }
}
