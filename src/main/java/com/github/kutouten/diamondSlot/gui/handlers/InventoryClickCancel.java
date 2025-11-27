package com.github.kutouten.diamondSlot.gui.handlers;

import com.github.kutouten.diamondSlot.DiamondSlot;
import com.github.kutouten.diamondSlot.gui.menus.SlotSetupMenu;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickCancel {
    SlotSetupMenu slotSetupMenu;
    public InventoryClickCancel() {
        slotSetupMenu = DiamondSlot.getSlotSetupMenu();
    }

    public void inventoryClickCancel(InventoryClickEvent e){
        if(e.getInventory().equals(slotSetupMenu.getInventory())){
            e.setCancelled(true);
        }
    }
}
