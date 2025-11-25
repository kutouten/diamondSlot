package com.github.kutouten.diamondSlot;

import com.github.kutouten.diamondSlot.commands.DsgiveCommand;
import com.github.kutouten.diamondSlot.commands.DsgiveCommandTabCompleter;
import com.github.kutouten.diamondSlot.items.manager.ItemManager;
import com.github.kutouten.diamondSlot.listeners.BlockRightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiamondSlot extends JavaPlugin {
    static DiamondSlot plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // commands
        this.getCommand("dsgive").setExecutor(new DsgiveCommand());
        // commandTabCompleter
        getCommand("dsgive").setTabCompleter(new DsgiveCommandTabCompleter());
        // event listeners
        Bukkit.getPluginManager().registerEvents(new BlockRightClickListener(),this);
        // items
        new ItemManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
