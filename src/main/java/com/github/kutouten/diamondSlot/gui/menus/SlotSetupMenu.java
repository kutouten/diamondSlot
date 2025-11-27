package com.github.kutouten.diamondSlot.gui.menus;


import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class SlotSetupMenu {
    private final Inventory inv;

    public SlotSetupMenu(){
        Inventory inventory = Bukkit.createInventory(null, 27, Component.text("Slot Setup Menu"));
        inventory.setItem(13, new ItemBuilder(Material.TROPICAL_FISH_BUCKET).setName("Uparupa").toItemStack());
        this.inv = inventory;
    }
    public Inventory getInventory() {
        return inv;
    }
}
