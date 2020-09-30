package com.potionwars;

import org.bukkit.Material;

import java.util.Random;

public final class PotionGenerator {

	private static final float STAT_SCALE = .8F;

	public static Potion generatePotion(Material material) {
		Random random = new Random(material.name().hashCode());

		Potion potion = new Potion(random.nextInt());

		PotionStat[] stats = PotionStat.values();
		PotionStat stat = stats[random.nextInt(stats.length)];

		potion.setStat(stat, random.nextFloat());

		return potion;
	}

	public static Potion generatePotion(Potion a, Potion b) {
		Random random = new Random(a.getSeed() ^ b.getSeed());

		Potion childPotion = new Potion(random.nextInt());

		// Loop through all stats and randomly select properties
		for(PotionStat stat : PotionStat.values()) {
			childPotion.setStat(stat, ((random.nextFloat() * a.getStat(stat) + random.nextFloat() * b.getStat(stat)) * STAT_SCALE)   /*RANDOM.nextBoolean() ? a.getStat(stat) : b.getStat(stat)*/);
		}

		return childPotion;
	}

}
