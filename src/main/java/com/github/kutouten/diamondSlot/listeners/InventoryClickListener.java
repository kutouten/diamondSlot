package com.github.kutouten.diamondSlot.listeners;

import com.github.kutouten.diamondSlot.gui.handlers.ClickHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryClickListener implements Listener {
    ClickHandler clickHandler;
    public InventoryClickListener(){
        clickHandler = new ClickHandler();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        clickHandler.inventoryClickCancel(e);
    }
}
