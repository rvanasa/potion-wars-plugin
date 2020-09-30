package com.potionwars;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.SplashPotion;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.io.CharConversionException;
import java.util.stream.Collectors;

public final class PotionStacker {

	private static final int DISPLAY_BARS = 10;

	public static ItemStack createPotionItemStack(Potion potion) {
		ItemStack item = new ItemStack(Material.SPLASH_POTION);

		item.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

		item.setLore(potion.getStats().entrySet().stream()
				.filter(kv -> kv.getValue() > 0)
				.map(kv -> getAttributeText(kv.getKey(), kv.getValue()))
				.collect(Collectors.toList()));

		PotionMeta meta = (PotionMeta)item.getItemMeta();
//		meta.addCustomEffect(new PotionEffect())
		meta.setLocalizedName("Fancy Potion #" + potion.getSeed());
		item.setItemMeta(meta);

		return item;
	}

	private static String getAttributeText(PotionStat stat, float value) {
		StringBuilder s = new StringBuilder()
				.append(ChatColor.GRAY)
				.append("[")
				.append(ChatColor.GREEN);

		for(int i = 0; i < DISPLAY_BARS; i++) {
			if(i > value * DISPLAY_BARS) {
				s.append(ChatColor.DARK_GRAY);
			}
			s.append("|");
		}
		s.append(ChatColor.GRAY)
				.append("] ")
				.append(ChatColor.WHITE)
				.append(stat.getDisplayName());
		return s.toString();
	}

}
