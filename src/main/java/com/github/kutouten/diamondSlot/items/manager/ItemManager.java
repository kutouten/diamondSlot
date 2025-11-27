package com.github.kutouten.diamondSlot.items.manager;

import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemManager {
    public static ItemStack SLOT_SETTER;

    public ItemManager() {
        loadItems();
    }

    private void loadItems() {
        // pl独自アイテムはすべてここに定義しておく
        SLOT_SETTER = new ItemBuilder(Material.STICK).setPdc("custom_id", "slot_setter").setName("slot_setter").addGlow().toItemStack();
    }
}
