package com.github.kutouten.diamondSlot.items.manager;

import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ItemManager {
    public static ItemStack SLOT_SETTER;
    private final Plugin pl;

    public ItemManager(Plugin pl) {
        this.pl = pl;
        loadItems();
    }

    private void loadItems() {
        SLOT_SETTER = new ItemBuilder(pl, Material.STICK).setPdc("custom_id", "slot_setter").setName("slot_setter").addGlow().toItemStack();
    }
}
