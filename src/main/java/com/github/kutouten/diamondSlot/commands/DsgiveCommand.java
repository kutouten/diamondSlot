package com.github.kutouten.diamondSlot.commands;

import com.github.kutouten.diamondSlot.items.manager.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DsgiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        Player player = (Player) sender;

        // アイテム指定の引数がない場合
        if(args.length == 0){
            player.sendMessage(ChatColor.RED + "アイテムを指定してください。");
            return false;
        } else {
            switch (args[0]){
                case "slot_setter":
                    player.give(ItemManager.SLOT_SETTER);
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "アイテム名が存在しません。");
            }
        }
        return  true;
    }
}
