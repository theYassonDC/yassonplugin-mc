package gg.yasson.listeners;

import gg.yasson.YassonPlugin;
import gg.yasson.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {
    private YassonPlugin plugin;

    public PlayerListeners(YassonPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getPlayerDataManager().updateName(player);
        if (player.getName().equals("YassonC")) {
            event.setJoinMessage(
                    MessageUtils.getColoredMessage(
                            YassonPlugin.prefix + plugin.getMainConfigManager().getMessage()
                    )
            );
        }
    }
}
