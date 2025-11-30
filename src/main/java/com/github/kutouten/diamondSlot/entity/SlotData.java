package com.github.kutouten.diamondSlot.entity;

public class SlotData {
    public String key;
    public String type;
    public int bb_count;
    public int rb_count;
    public int spin_count_now;
    public int spin_count_total;
    public String player_name;

    public SlotData(String key, String type, int bb_count, int rb_count, int spin_count_now, int spin_count_total, String player_name) {
        this.key = key;
        this.type = type;
        this.bb_count = bb_count;
        this.rb_count = rb_count;
        this.spin_count_now = spin_count_now;
        this.spin_count_total = spin_count_total;
        this.player_name = player_name;
    }

}
