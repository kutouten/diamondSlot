package com.github.kutouten.diamondSlot.commands;

import com.github.kutouten.diamondSlot.data.CurrencyManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class DscoinCommand implements CommandExecutor {
    private final CurrencyManager currencyManager;

    public DscoinCommand() {
        this.currencyManager = new CurrencyManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args){
        if (args.length <= 2) {
            sender.sendMessage(Component.text("===== DS Coin Help =====").color(NamedTextColor.GOLD));
            sender.sendMessage(Component.text("/dscoin <player> add <amount> - コインを追加").color(NamedTextColor.YELLOW));
            sender.sendMessage(Component.text("/dscoin <player> remove <amount> - コインを減らす").color(NamedTextColor.YELLOW));
            sender.sendMessage(Component.text("/dscoin <player> set <amount> - コインをセット").color(NamedTextColor.YELLOW));
            return true;
        } else {
            // playerのオフライン判定
            if(Bukkit.getPlayer(args[0]) == null){
                sender.sendMessage(Component.text("プレイヤーがオフライン、もしくは存在しません。").color(NamedTextColor.RED));
                return false;
            }
            Player p = Bukkit.getPlayer(args[0]);

            // 数値チェック
            int amount = Integer.parseInt(args[2]);
            if(amount < 0){
                sender.sendMessage(Component.text("0未満の数値を指定することはできません。").color(NamedTextColor.RED));
                return false;
            }

            // 処理
            UUID uuid = Objects.requireNonNull(p).getUniqueId();
            switch (args[1]) {
                case "add":
                    currencyManager.addBalance(uuid, amount);
                    sender.sendMessage(Component.text(p.getName() + " のコインを " + amount + " 追加しました。").color(NamedTextColor.GREEN));
                    p.sendMessage(Component.text("あなたのコインが " + amount + " 増えました。").color(NamedTextColor.GREEN));
                    break;

                case "remove":
                    currencyManager.removeBalance(uuid, amount);
                    sender.sendMessage(Component.text(p.getName() + " のコインを " + amount + " 減らしました。").color(NamedTextColor.GREEN));
                    p.sendMessage(Component.text("あなたのコインが " + amount + " 減りました。").color(NamedTextColor.GREEN));
                    break;

                case "set":
                    currencyManager.setBalance(uuid, amount);
                    sender.sendMessage(Component.text(p.getName() + " のコインを " + amount + " に設定しました。").color(NamedTextColor.GREEN));
                    p.sendMessage(Component.text("あなたのコインが " + amount + " に設定されました。").color(NamedTextColor.GREEN));
                    break;

                default:
                    sender.sendMessage(Component.text("操作は add / remove / set のいずれかを指定してください。").color(NamedTextColor.GREEN));
                    break;
            }
        }

        return true;
    }
}
