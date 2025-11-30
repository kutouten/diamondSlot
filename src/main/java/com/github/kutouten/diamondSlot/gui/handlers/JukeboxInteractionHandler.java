package com.github.kutouten.diamondSlot.gui.handlers;

import com.github.kutouten.diamondSlot.DiamondSlot;
import com.github.kutouten.diamondSlot.data.SlotManager;
import com.github.kutouten.diamondSlot.entity.SlotData;
import com.github.kutouten.diamondSlot.gui.menus.SlotSetupMenu;
import com.github.kutouten.diamondSlot.slot.managers.SlotService;
import com.github.kutouten.diamondSlot.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class JukeboxInteractionHandler {
    SlotSetupMenu slotSetupMenu;
    SlotManager slotManager;
    SlotService slotService;

    public JukeboxInteractionHandler(){
        slotSetupMenu = DiamondSlot.getSlotSetupMenu();
        slotManager = DiamondSlot.getSlotManager();
        slotService = new SlotService();
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
        Location loc = clicked.getLocation();
        String key = slotService.key_maker(loc);
        SlotData slotData = slotManager.getSlot(key);
        String item_custom_id = new ItemBuilder(mainHand).getPdc("custom_id");

        if(slotData == null){
            if(item_custom_id != null && item_custom_id.equals("slot_setter")){
                // 台がなく、手にセッターがある
                // setup GUIを開く
                slotService.pre_setup(loc, p);
                p.openInventory(slotSetupMenu.getInventory());

            } else {
                // 台がなく、手にセッターもない
                // 動作はデフォルトと同じ
                return;
            }
        } else if(item_custom_id != null && item_custom_id.equals("slot_setter")){
            // 台があり、セッターがある
            // 台の設定を開く

        } else {
            // 台があり、セッターがない
            // 台をプレイするGUIを開く

        }


    }
}
