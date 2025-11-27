package com.github.kutouten.diamondSlot.listeners;

import com.github.kutouten.diamondSlot.gui.handlers.InventoryClickCancel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryClickListener implements Listener {
    InventoryClickCancel inventoryClickCancel;
    public InventoryClickListener(){
        inventoryClickCancel = new InventoryClickCancel();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        inventoryClickCancel.inventoryClickCancel(e);
    }
}
