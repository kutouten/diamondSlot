package com.github.kutouten.diamondSlot.gui.menus;


import com.github.kutouten.diamondSlot.items.manager.ItemManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class SlotSetupMenu {
    private final Inventory inv;

    public SlotSetupMenu(){
        Inventory inventory = Bukkit.createInventory(null, 9, Component.text("Slot Setup Menu"));
        inventory.setItem(4, ItemManager.GUI_SLOT_SETTER);
        inventory.setItem(8, ItemManager.GUI_CANCEL);
        this.inv = inventory;
    }
    public Inventory getInventory() {
        return inv;
    }
}
