package com.potionwars;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;
import java.util.stream.Collectors;

public final class PotionStacker {

	public static ItemStack createPotionItemStack(SpecialPotion potion) {
		ItemStack item = new ItemStack(Material.SPLASH_POTION);

		item.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

		item.setLore(potion.getStats().entrySet().stream()
				.filter(kv -> kv.getValue() > 0)
				.map(kv -> String.format("%s: %.1f", kv.getKey().getDisplayName(), kv.getValue()))
				.collect(Collectors.toList()));

		ItemMeta meta = item.getItemMeta();

		meta.setLocalizedName("Fancy Potion");

		item.setItemMeta(meta);

		return item;
	}

}
