package com.potionwars;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;

import java.util.Random;

public final class PotionGenerator {

	private static final Random RANDOM = new Random();

	private static final float MIN_STAT = .1F;
	private static final float STAT_SCALE = .8F;

	public static SpecialPotion generatePotion(Material material) {
		Random random = new Random();

		SpecialPotion potion = new SpecialPotion(random.nextInt());

		SpecialPotionStat[] stats = SpecialPotionStat.values();
		SpecialPotionStat stat = stats[random.nextInt(stats.length)];

		potion.setStat(stat, (random.nextFloat() * (1 - MIN_STAT)) + MIN_STAT);

		return potion;
	}

	public static SpecialPotion generatePotion(SpecialPotion a, SpecialPotion b) {
		RANDOM.setSeed(a.getSeed() ^ b.getSeed());

		SpecialPotion childPotion = new SpecialPotion(RANDOM.nextInt());

		// Loop through all stats and randomly select properties
		for(SpecialPotionStat stat : SpecialPotionStat.values()) {
			childPotion.setStat(stat, ((RANDOM.nextFloat() * a.getStat(stat) + RANDOM.nextFloat() * b.getStat(stat)) * STAT_SCALE * (1 - MIN_STAT)) + MIN_STAT  /*RANDOM.nextBoolean() ? a.getStat(stat) : b.getStat(stat)*/);
		}



		return childPotion;
	}

}
