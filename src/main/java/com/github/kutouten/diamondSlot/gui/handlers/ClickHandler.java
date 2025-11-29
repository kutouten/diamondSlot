package com.github.kutouten.diamondSlot.gui.handlers;

import com.github.kutouten.diamondSlot.DiamondSlot;
import com.github.kutouten.diamondSlot.gui.menus.SlotSetupMenu;
import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickHandler {
    SlotSetupMenu slotSetupMenu;

    public ClickHandler() {
        slotSetupMenu = DiamondSlot.getSlotSetupMenu();
    }

    public void inventoryClickCancel(InventoryClickEvent e){
        if(e.getInventory().equals(slotSetupMenu.getInventory())){
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                ItemStack item = e.getCurrentItem();
                switch (new ItemBuilder(item).getPdc("gui")) {
                    case "uparupa":
                }
            }
        }
    }
}
