package com.github.kutouten.diamondSlot.gui.handlers;

import com.github.kutouten.diamondSlot.DiamondSlot;
import com.github.kutouten.diamondSlot.gui.menus.SlotSetupMenu;
import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class JukeboxInteractionHandler {
    SlotSetupMenu slotSetupMenu;
    public JukeboxInteractionHandler(){
        slotSetupMenu = DiamondSlot.getSlotSetupMenu();
    }

    public void handleRightClick(PlayerInteractEvent e) {
        // 右クリじゃなかったらreturn
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        // mainHandじゃないなら受けreturn
        if(e.getHand() != EquipmentSlot.HAND) return;
        Block clicked = e.getClickedBlock();
        // jukeboxじゃないならreturn
        if (clicked == null || clicked.getType() != Material.JUKEBOX) return;

        Player p = e.getPlayer();
        ItemStack mainHand = p.getInventory().getItemInMainHand();

        if(new ItemBuilder(mainHand).getPdc("custom_id").equals("slot_setter")){
            p.openInventory(slotSetupMenu.getInventory());
        }
    }
}
