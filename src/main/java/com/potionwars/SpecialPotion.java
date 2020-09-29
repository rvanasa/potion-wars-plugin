package com.potionwars;

import java.util.EnumMap;
import java.util.Map;

public class SpecialPotion {

	private final Map<SpecialPotionStat, Float> stats = new EnumMap<>(SpecialPotionStat.class);

	private final int seed;

	SpecialPotion(int seed) {
		this.seed = seed;
	}

	public int getSeed() {
		return seed;
	}

	public Map<SpecialPotionStat, Float> getStats() {
		return stats;
	}

	public float getStat(SpecialPotionStat stat) {
		return stats.getOrDefault(stat, 0f);
	}

	public void setStat(SpecialPotionStat stat, float value) {
		stats.put(stat, value);
	}

}
