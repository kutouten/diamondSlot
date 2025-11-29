package com.github.kutouten.diamondSlot.data;

import com.github.kutouten.diamondSlot.DiamondSlot;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class CurrencyManager {

    private final FileConfiguration config;
    private final File file;
    private final Plugin plugin;

    public CurrencyManager() {
        this.plugin = DiamondSlot.getPlugin();
        this.file = new File(plugin.getDataFolder(), "currency.yml");

        if (!file.exists()) {
            plugin.saveResource("currency.yml", false);
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    private String getKey(UUID uuid) {
        return "players." + uuid.toString() + ".balance";
    }

    public int getBalance(UUID uuid) {
        return config.getInt(getKey(uuid), 0);
    }

    public void setBalance(UUID uuid, int amount) {
        config.set(getKey(uuid), amount);
        save();
    }

    public void addBalance(UUID uuid, int amount) {
        int current = getBalance(uuid);
        config.set(getKey(uuid), current + amount);
        save();
    }

    public void removeBalance(UUID uuid, int amount) {
        int current = getBalance(uuid);
        config.set(getKey(uuid), Math.max(0, current - amount));
        save();
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to save currency.yml", e);
        }
    }
}
