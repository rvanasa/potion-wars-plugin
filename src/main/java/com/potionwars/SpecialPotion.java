package com.potionwars;

import org.bukkit.potion.PotionData;

import java.util.EnumMap;
import java.util.Map;


public class SpecialPotion {

    private final Map<SpecialPotionStat, Float> stats = new EnumMap<>(SpecialPotionStat.class);

    private final int seed;
    SpecialPotion(int seed){
        this.seed = seed;
    }

    public int getSeed() {
        return seed;
    }

    public float getStat(SpecialPotionStat stat) {
        return stats.getOrDefault(stat,0f);
    }

    public void setStat(SpecialPotionStat stat, float value){
        stats.put(stat,value);
    }

}
