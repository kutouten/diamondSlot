package com.github.kutouten.diamondSlot.gui.handlers;

import com.github.kutouten.diamondSlot.DiamondSlot;
import com.github.kutouten.diamondSlot.data.SlotManager;
import com.github.kutouten.diamondSlot.gui.menus.SlotSetupMenu;
import com.github.kutouten.diamondSlot.slot.managers.SlotService;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CloseHandler {
    SlotSetupMenu slotSetupMenu;
    SlotService slotService;

    public CloseHandler() {
        slotSetupMenu = DiamondSlot.getSlotSetupMenu();
        slotService = new SlotService();
    }

    public void handleClose(InventoryCloseEvent e){
        // 基本的にcloseでbackやcanselの処理を行う
        if(e.getInventory().equals(slotSetupMenu.getInventory())){
            Player p = (Player) e.getPlayer();
            // pre_setupの内容の消去
            slotService.delete_pre_setup(p);
            // 離席
        }
    }
}
