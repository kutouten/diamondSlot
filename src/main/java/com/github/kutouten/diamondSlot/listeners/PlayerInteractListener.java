package com.github.kutouten.diamondSlot.listeners;

import com.github.kutouten.diamondSlot.gui.handlers.JukeboxInteractionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    JukeboxInteractionHandler jukeboxInteractionHandler;

    public PlayerInteractListener(){
        jukeboxInteractionHandler = new JukeboxInteractionHandler();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        jukeboxInteractionHandler.handleRightClick(e);
    }
}
