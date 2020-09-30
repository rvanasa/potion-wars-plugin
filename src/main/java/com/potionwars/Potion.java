package com.potionwars;

import java.util.EnumMap;
import java.util.Map;

public class Potion {

	private final Map<PotionStat, Float> stats = new EnumMap<>(PotionStat.class);

	private final int seed;

	Potion(int seed) {
		this.seed = seed;
	}

	public int getSeed() {
		return seed;
	}

	public Map<PotionStat, Float> getStats() {
		return stats;
	}

	public float getStat(PotionStat stat) {
		return stats.getOrDefault(stat, 0f);
	}

	public void setStat(PotionStat stat, float value) {
		stats.put(stat, value);
	}

}
