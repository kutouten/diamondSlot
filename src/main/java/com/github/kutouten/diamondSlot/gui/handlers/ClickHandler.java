package com.github.kutouten.diamondSlot.gui.handlers;

import com.github.kutouten.diamondSlot.DiamondSlot;
import com.github.kutouten.diamondSlot.gui.menus.SlotSetupMenu;
import com.github.kutouten.diamondSlot.slot.managers.SlotService;
import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickHandler {
    SlotSetupMenu slotSetupMenu;
    SlotService slotService;

    public ClickHandler() {
        slotSetupMenu = DiamondSlot.getSlotSetupMenu();
        slotService = new SlotService();
    }

    public void inventoryClickCancel(InventoryClickEvent e){
        // slot setupのGUIを開いている場合
        if(e.getInventory().equals(slotSetupMenu.getInventory())){
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                ItemStack item = e.getCurrentItem();
                Player p = (Player) e.getWhoClicked();
                String type = new ItemBuilder(item).getPdc("gui");
                // itemにPDCがない場合return
                if(type == null) return;

                switch (type) {
                    case "uparupa":
                        slotService.setup(p, type);
                        p.closeInventory();
                    case "cancel":
                        p.closeInventory();
                }
            }
        }
    }
}
