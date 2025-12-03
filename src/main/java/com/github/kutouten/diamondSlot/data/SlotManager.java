package com.github.kutouten.diamondSlot.data;

import com.github.kutouten.diamondSlot.entity.SlotData;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SlotManager {

    private final Plugin plugin;
    private final File file;
    private final YamlConfiguration config;
    private final List<SlotData> slotDataList = new ArrayList<>();

    public SlotManager(Plugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "slots.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        load();
    }

    /**
     * YAML からデータ読み込み
     */
    private void load() {
        if (!file.exists()) {
            plugin.getLogger().info("slots.yml が見つからないため新規作成されます");
            save();
            return;
        }

        for (String key : config.getKeys(false)) {
            String type = config.getString(key + ".type", null);
            int bbCount = config.getInt(key + ".bb_count", 0);
            int rbCount = config.getInt(key + ".rb_count", 0);
            int spinNow = config.getInt(key + ".spin_count_now", 0);
            int spinTotal = config.getInt(key + ".spin_count_total", 0);
            String playerName = config.getString(key + ".player_name", null);

            SlotData slot = new SlotData(key, type, bbCount, rbCount, spinNow, spinTotal, playerName);
            slotDataList.add(slot);
        }

        plugin.getLogger().info("Slot データ読込完了 (" + slotDataList.size() + " 件)");
    }

    /**
     * YAML へ保存
     */
    public void save() {
        try {
            for (SlotData d : slotDataList) {
                String key = d.key;
                config.set(key + ".type", d.type);
                config.set(key + ".bb_count", d.bb_count);
                config.set(key + ".rb_count", d.rb_count);
                config.set(key + ".spin_count_now", d.spin_count_now);
                config.set(key + ".spin_count_total", d.spin_count_total);
                config.set(key + ".player_name", d.player_uuid);
            }
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("slots.yml の保存に失敗しました: " + e.getMessage());
        }
    }

    /**
     * スロットデータ取得
     */
    public SlotData getSlot(String key) {
        for(SlotData d : slotDataList) {
            if (d.key.equals(key)) {
                return d;
            }
        }
        return null;
    }

    public SlotData getSlot(Player p) {
        for(SlotData d : slotDataList) {
            if (Objects.equals(d.player_uuid, p.getUniqueId().toString())) {
                return d;
            }
        }
        return null;
    }

    /**
     * スロットデータの新規登録
     */
    public void addSlot(SlotData data) {
        // key重複チェック
        for(SlotData d : slotDataList) {
            if (d.key.equals(data.key)) {
                plugin.getLogger().warning("keyが重複してます。");
                return;
            }
        }
        slotDataList.add(data);
        save();
    }

    /**
     * スロットデータの更新
     */
    public void updateSlot(SlotData data) {
        for (int i = 0; i < slotDataList.size(); i++) {
            if (slotDataList.get(i).key.equals(data.key)) {
                slotDataList.set(i, data); // 上書き
                save();
                return;
            }
        }
        plugin.getLogger().warning("updateの対象データが見つかりません。");
    }


    /**
     * スロットデータの削除
     */
    public void removeSlot(String key) {
        slotDataList.remove(getSlot(key));
        config.set(key, null);
        save();
    }

    /**
     * すべて取得（GUIなどで利用）
     */
    public List<SlotData> getAllSlots() {
        return slotDataList;
    }
}
