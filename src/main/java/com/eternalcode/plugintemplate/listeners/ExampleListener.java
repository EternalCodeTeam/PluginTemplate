package com.eternalcode.plugintemplate.listeners;

import com.eternalcode.plugintemplate.utils.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class ExampleListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(ChatUtils.component("Welcome " + event.getPlayer().getName()));
    }
}
