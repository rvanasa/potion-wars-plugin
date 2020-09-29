package com.potionwars;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class PotionWarsPlugin extends JavaPlugin {
	public boolean hasMetadata(Metadatable object, String key) {
		return object.hasMetadata(key);
	}

	public String getMetadata(Metadatable object, String key) {
		return String.valueOf(getMetadata(object, key, String.class));
	}

	public <T> T getMetadata(Metadatable object, String key, Class<T> cls) {
		for(MetadataValue meta : object.getMetadata(key)) {
			if(meta.getOwningPlugin() == this) {
				if(cls.isInstance(meta.value())) {
					return cls.cast(meta.value());
				}
			}
		}

		return null;
	}

	public void setMetadata(Metadatable object, String key, Object value) {
		object.setMetadata(key, new FixedMetadataValue(this, value));
	}

	public static final String NAME = "potion-wars";
	public static final String VERSION = "0.1.0";

	public static final File MESSAGE_DIR = new File("./messages");

	private static PotionWarsPlugin INSTANCE;

	public static PotionWarsPlugin getInstance() {
		return INSTANCE;
	}

	@Override
	public void onEnable() {
		INSTANCE = this;
		try {

			//			File[] messageFiles = MESSAGE_DIR.listFiles();
			//			if(messageFiles != null) {
			//				for(File file : messageFiles) {
			//					file.delete();
			//				}
			//			}
			//			getServer().getScheduler().scheduleSyncRepeatingTask(getInstance(), () -> {
			//				File messageFile = new File(MESSAGE_DIR, "HALT");
			//				if(messageFile.exists()) {
			//					Bukkit.shutdown();
			//				}
			//			}, 0, 20);

			getServer().getPluginManager().registerEvents(new PotionListeners(), this);

			getLogger().info(NAME + " v" + VERSION + " has been enabled.");
		}
		catch(Exception e) {
			e.printStackTrace();
			getLogger().severe("Failed to initialize " + NAME + "!");
		}
	}

	@Override
	public void onDisable() {
		try {
			INSTANCE = null;

			for(Player player : getServer().getOnlinePlayers()) {
				player.kickPlayer("The server is restarting.");
			}

			getLogger().info(NAME + " v" + VERSION + " has been disabled.");
		}
		catch(Exception e) {
			getLogger().warning("An exception occurred while disabling " + NAME + " v" + VERSION + "!");
		}
	}

	//	@Override
	//	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	//		String result = PotionWarsCommands.COMMAND_REGISTRY.onCommand(sender, command.getName(), args);
	//
	//		if(result != null) {
	//			sender.sendMessage(result);
	//		}
	//
	//		return true;
	//	}
}