package com.github.kutouten.diamondSlot.gui.menus;


import com.github.kutouten.diamondSlot.items.manager.ItemManager;
import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class SlotSetupMenu {
    private final Inventory inv;

    public SlotSetupMenu(){
        Inventory inventory = Bukkit.createInventory(null, 27, Component.text("Slot Setup Menu"));
        inventory.setItem(13, ItemManager.GUI_SLOT_SETTER);
        this.inv = inventory;
    }
    public Inventory getInventory() {
        return inv;
    }
}
