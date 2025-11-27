package com.github.kutouten.diamondSlot.commands;

import com.github.kutouten.diamondSlot.DiamondSlot;
import com.github.kutouten.diamondSlot.data.CurrencyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class DscoinCommand implements CommandExecutor {
    private final CurrencyManager currencyManager;

    public DscoinCommand() {
        this.currencyManager = new CurrencyManager(DiamondSlot.getPlugin());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (args.length <= 2) {
            sender.sendMessage(ChatColor.GOLD + "===== DS Coin Help =====");
            sender.sendMessage(ChatColor.YELLOW + "/dscoin <player> add <amount> - コインを追加");
            sender.sendMessage(ChatColor.YELLOW + "/dscoin <player> remove <amount> - コインを減らす");
            sender.sendMessage(ChatColor.YELLOW + "/dscoin <player> set <amount> - コインをセット");
            return true;
        } else {
            // playerのオフライン判定
            if(Bukkit.getPlayer(args[0]) == null){
                sender.sendMessage(ChatColor.RED + "プレイヤーがオフライン、もしくは存在しません。");
                return false;
            }
            Player p = Bukkit.getPlayer(args[0]);

            // 数値チェック
            int amount = Integer.parseInt(args[2]);
            if(amount < 0){
                sender.sendMessage(ChatColor.RED + "0未満の数値を指定することはできません。");
                return false;
            }

            // 処理
            UUID uuid = Objects.requireNonNull(p).getUniqueId();
            switch (args[1]) {
                case "add":
                    currencyManager.addBalance(uuid, amount);
                    sender.sendMessage(ChatColor.GREEN + p.getName() + " のコインを " + amount + " 追加しました。");
                    p.sendMessage(ChatColor.GREEN + "あなたのコインが " + amount + " 増えました。");
                    break;

                case "remove":
                    currencyManager.removeBalance(uuid, amount);
                    sender.sendMessage(ChatColor.GREEN + p.getName() + " のコインを " + amount + " 減らしました。");
                    p.sendMessage(ChatColor.GREEN + "あなたのコインが " + amount + " 減りました。");
                    break;

                case "set":
                    currencyManager.setBalance(uuid, amount);
                    sender.sendMessage(ChatColor.GREEN + p.getName() + " のコインを " + amount + " に設定しました。");
                    p.sendMessage(ChatColor.GREEN + "あなたのコインが " + amount + " に設定されました。");
                    break;

                default:
                    sender.sendMessage(ChatColor.RED + "操作は add / remove / set のいずれかを指定してください。");
                    break;
            }

        }


        return true;
    }
}
