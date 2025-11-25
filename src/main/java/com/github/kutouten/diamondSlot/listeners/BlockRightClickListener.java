package com.github.kutouten.diamondSlot.listeners;

import com.github.kutouten.diamondSlot.DiamondSlot;
import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class BlockRightClickListener implements Listener {
    @EventHandler
    public void blockRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        // offhand無視
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;
        // 右クリックした対象のブロックが存在しない場合return
        if(b == null) return;

        // test
        ItemStack item = p.getInventory().getItemInMainHand();
        String custom_id = new ItemBuilder(DiamondSlot.getPlugin(), item).getPdc("custom_id");

        p.sendMessage(custom_id);
    }
}
