package com.github.kutouten.diamondSlot;

import com.github.kutouten.diamondSlot.commands.DscoinCommand;
import com.github.kutouten.diamondSlot.commands.DsgiveCommand;
import com.github.kutouten.diamondSlot.commands.DsgiveCommandTabCompleter;
import com.github.kutouten.diamondSlot.data.SlotManager;
import com.github.kutouten.diamondSlot.gui.menus.SlotSetupMenu;
import com.github.kutouten.diamondSlot.items.manager.ItemManager;
import com.github.kutouten.diamondSlot.listeners.InventoryClickListener;
import com.github.kutouten.diamondSlot.listeners.InventoryCloseListener;
import com.github.kutouten.diamondSlot.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DiamondSlot extends JavaPlugin {
    private static DiamondSlot plugin;
    private static SlotSetupMenu slotSetupMenu;
    private static SlotManager slotManager;

    @Override
    public void onEnable() {
        plugin = this;
        // manager
        new ItemManager();
        // gui
        slotSetupMenu = new SlotSetupMenu();
        // data
        slotManager = new SlotManager(this);


        // commands
        Objects.requireNonNull(this.getCommand("dsgive")).setExecutor(new DsgiveCommand());
        Objects.requireNonNull(this.getCommand("dscoin")).setExecutor(new DscoinCommand());
        // commandTabCompleter
        Objects.requireNonNull(getCommand("dsgive")).setTabCompleter(new DsgiveCommandTabCompleter());
        // event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(),this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
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

    public static SlotManager getSlotManager() { return slotManager; }
}
