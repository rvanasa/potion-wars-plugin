package cauldren.listeners;

import java.util.UUID;

import com.potionwars.PotionWarsPlugin;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonRetractEvent;

import cauldren.CaulCrafting;

public class BlockPistonRetractListener implements Listener{
	
	private PotionWarsPlugin plugin;
	public BlockPistonRetractListener(PotionWarsPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPistonRetract(BlockPistonRetractEvent e) {
		//Check if there are cauldrons on activity on pushed blocks 
		for(Block block : e.getBlocks()) {
			Location loc = block.getLocation();
			for(UUID uuid : plugin.caulLoc.keySet()) {
				Location caul = plugin.caulLoc.get(uuid);
				if(caul.getBlock().getLocation().distance(loc) == 0) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}

}
