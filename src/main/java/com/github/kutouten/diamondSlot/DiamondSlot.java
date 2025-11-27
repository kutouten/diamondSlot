package com.github.kutouten.diamondSlot;

import com.github.kutouten.diamondSlot.commands.DsgiveCommand;
import com.github.kutouten.diamondSlot.commands.DsgiveCommandTabCompleter;
import com.github.kutouten.diamondSlot.gui.menus.SlotSetupMenu;
import com.github.kutouten.diamondSlot.items.manager.ItemManager;
import com.github.kutouten.diamondSlot.listeners.InventoryClickListener;
import com.github.kutouten.diamondSlot.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiamondSlot extends JavaPlugin {
    private static DiamondSlot plugin;
    private static SlotSetupMenu slotSetupMenu;

    @Override
    public void onEnable() {
        plugin = this;
        slotSetupMenu = new SlotSetupMenu();

        // commands
        this.getCommand("dsgive").setExecutor(new DsgiveCommand());
        // commandTabCompleter
        getCommand("dsgive").setTabCompleter(new DsgiveCommandTabCompleter());
        // event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(),this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
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

    public static SlotSetupMenu getSlotSetupMenu() {
        return slotSetupMenu;
    }
}
