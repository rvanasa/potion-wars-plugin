package com.potionwars;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PotionListeners implements Listener {

	@EventHandler
	public void onBrew(BrewEvent event) {
		event.getContents().setItem(0, new ItemStack(Material.COAL_BLOCK));
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		if(event.getMaterial() == Material.GLASS_BOTTLE && event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			Item[] items = event.getPlayer().getWorld().getNearbyEntitiesByType(Item.class, Objects.requireNonNull(event.getClickedBlock()).getLocation(), .8)
					.toArray(new Item[0]);

//			if(items.length >= 2) {
//				Item a = items[0];
//				Item b = items[1];
//
//				a.remove();
//				b.remove();
//
//				Objects.requireNonNull(event.getItem()).subtract(1);
//
//				//				SpecialPotion potion = new SpecialPotion(123);
//				//				potion.setStat(SpecialPotionStat.JUMP, 1);
//				//				potion.setStat(SpecialPotionStat.POISON, .2122345775F);
//				//				potion.setStat(SpecialPotionStat.EXPLOSIVE_RADIUS, 12.1235F);
//
//				SpecialPotion potion = PotionGenerator.generatePotion(event.getItem().getType());
//
//				//				SpecialPotion potion = PotionGenerator.generatePotion(a..., b...);
//
//				ItemStack item = new ItemStack(Material.POTION);
//				item.setLore(potion.getStats().entrySet().stream()
//						.filter(kv -> kv.getValue() > 0)
//						.map(kv -> String.format("%s: %.1f", kv.getKey().getDisplayName(), kv.getValue()))
//						.collect(Collectors.toList()));
//				item.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
//
//				event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), item);
//			}

			if(items.length >= 1) {
				Item source = items[0];
				source.remove();

				Objects.requireNonNull(event.getItem()).subtract(1);

				SpecialPotion potion = PotionGenerator.generatePotion(event.getItem().getType());

				ItemStack item = PotionStacker.createPotionItemStack(potion);

				event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), item);
			}
		}

	}
}
