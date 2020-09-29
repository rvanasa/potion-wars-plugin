package com.potionwars;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.inventory.ItemStack;

public class PotionListeners implements Listener {

	@EventHandler
	void onBrew(BrewEvent event){
		event.getContents().setItem(0, new ItemStack(Material.COAL_BLOCK));
	}
}
