package com.github.kutouten.diamondSlot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ItemBuilder {

    private final ItemStack is;
    private final Plugin plugin;

    public ItemBuilder(Plugin plugin, Material m) {
        this.plugin = plugin;
        this.is = new ItemStack(m, 1);
    }

    public ItemBuilder(Plugin plugin, ItemStack is) {
        this.plugin = plugin;
        this.is = is.clone();
    }

    public ItemBuilder(Plugin plugin, Material m, int amount) {
        this.plugin = plugin;
        this.is = new ItemStack(m, amount);
    }

    public ItemBuilder setDurability(int damage) {
        ItemMeta meta = is.getItemMeta();
        if (meta instanceof Damageable dmg) {
            dmg.setDamage(damage);
            is.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        is.removeEnchantment(ench);
        return this;
    }

//    public ItemBuilder setSkullOwner(String owner) {
//        ItemMeta meta = is.getItemMeta();
//        if (meta instanceof SkullMeta skull) {
//            PlayerProfile profile = Bukkit.createPlayerProfile(owner);
//            skull.setPlayerProfile(profile);
//            is.setItemMeta(skull);
//        }
//        return this;
//    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = is.getItemMeta();
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        ItemMeta im = is.getItemMeta();
        enchantments.forEach((ench, lvl) -> im.addEnchant(ench, lvl, true));
        is.setItemMeta(im);
        return this;
    }

    /** 削除された durability API の代替として最大値 damage=0 */
    public ItemBuilder setInfinityDurability() {
        ItemMeta meta = is.getItemMeta();
        if (meta instanceof Damageable dmg) {
            dmg.setDamage(0);
            is.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(String line) {
        ItemMeta im = is.getItemMeta();
        if (!im.hasLore()) return this;
        List<String> lore = new ArrayList<>(im.getLore());
        lore.remove(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(int index) {
        ItemMeta im = is.getItemMeta();
        if (!im.hasLore()) return this;
        List<String> lore = new ArrayList<>(im.getLore());
        if (index < 0 || index >= lore.size()) return this;
        lore.remove(index);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = im.hasLore() ? new ArrayList<>(im.getLore()) : new ArrayList<>();
        lore.add(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(String line, int index) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = im.hasLore() ? new ArrayList<>(im.getLore()) : new ArrayList<>();

        if (index < 0 || index > lore.size()) index = lore.size();
        lore.add(index, line);

        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    /** 旧 setDyeColor(data値) は廃止 — 最新版では色付き素材を使うべき */
    @Deprecated
    public ItemBuilder setDyeColor(Color color) {
        return this; // 1.21 では非推奨 → 何もしない
    }

    @Deprecated
    public ItemBuilder setWoolColor(Color color) {
        return this; // Material.WOOL は存在しないため非対応
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        ItemMeta meta = is.getItemMeta();
        if (meta instanceof LeatherArmorMeta lam) {
            lam.setColor(color);
            is.setItemMeta(lam);
        }
        return this;
    }

    public ItemBuilder addGlow() {
        ItemMeta meta = is.getItemMeta();
        // Dummy enchant: DAMAGE_UNDEAD は光るけど hideFlags で非表示
        meta.addEnchant(Enchantment.INFINITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS); // エンチャント名を非表示
        is.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setPdc(String key, String value) {
        ItemMeta meta = is.getItemMeta();
        if (meta == null) return this;
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
        is.setItemMeta(meta);
        return this;
    }

    public String getPdc(String key) {
        ItemMeta meta = is.getItemMeta();
        if (meta == null) return null;
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public ItemBuilder removePdc(String key) {
        ItemMeta meta = is.getItemMeta();
        if (meta == null) return this;
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.remove(new NamespacedKey(plugin, key));
        is.setItemMeta(meta);
        return this;
    }

    public ItemStack toItemStack() {
        return is.clone();
    }
}
