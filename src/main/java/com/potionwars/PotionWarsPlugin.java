package com.potionwars;

import cauldren.*;

import cauldren.editor.Editor;
import cauldren.itemsname.Itemsname;
import cauldren.itemsname.Itemsname_1_16_R2;
import cauldren.listeners.AsyncPlayerChatListener;
import cauldren.listeners.BlockPistonExtendListener;
import cauldren.listeners.BlockPistonRetractListener;
import cauldren.listeners.ItemDropListener;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

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

	//cauldren
	//Editors working
	public static Map<UUID, Editor> editors = new HashMap<>();

	//Data folder
	public static File dataFolder = null;

	public ConfigUpdate configUpdate = new ConfigUpdate(this);
	public Config configUtils = new Config(this);
	public CraftStorage craftStorage = new CraftStorage(this);
	//public Editor editorUtils = new Editor(this);

	//Languages availables list
	public HashMap<String, String> languagesAvailable = new HashMap<String,String>();
	//End of cauldren

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

			File[] messageFiles = MESSAGE_DIR.listFiles();
			if(messageFiles != null) {
				for(File file : messageFiles) {
					file.delete();
				}
			}
			getServer().getScheduler().scheduleSyncRepeatingTask(getInstance(), () -> {
				File messageFile = new File(MESSAGE_DIR, "HALT");
				if(messageFile.exists()) {
					messageFile.delete();
					Bukkit.shutdown();
				}
			}, 0, 20);

			getServer().getPluginManager().registerEvents(new PotionListeners(), this);

			getLogger().info(NAME + " v" + VERSION + " has been enabled.");
		}
		catch(Exception e) {
			e.printStackTrace();
			getLogger().severe("Failed to initialize " + NAME + "!");
		}

		ConfigurationSerialization.registerClass(CraftArray.class);
		//Save data folder
		PotionWarsPlugin.dataFolder = getDataFolder();
		//LISTENERS - EVENTS register
		PluginManager plugman = getServer().getPluginManager();
		plugman.registerEvents(new AsyncPlayerChatListener(this), this);
		plugman.registerEvents(new ItemDropListener(this), this);
		plugman.registerEvents(new BlockPistonExtendListener(this), this);
		plugman.registerEvents(new BlockPistonRetractListener(this), this);
		//Setup languages
		languagesAvailable.put("en", "English");
		languagesAvailable.put("fr", "Français");
		languagesAvailable.put("ru", "Русский");
		languagesAvailable.put("nl", "Nederlands");
		languagesAvailable.put("de", "Deutsch");
		languagesAvailable.put("ja", "日本語");
		languagesAvailable.put("pl", "Polski");
		languagesAvailable.put("vi", "Tiếng Việt");
		languagesAvailable.put("es", "Español");
		languagesAvailable.put("pt", "Português");
		languagesAvailable.put("zh", "中文");
		languagesAvailable.put("hu", "Magyar");
		languagesAvailable.put("lv", "Latviešu valoda");
		languagesAvailable.put("ko", "한국어");
		languagesAvailable.put("cs", "Česky");
		//Defaults configs files (locales..)
		configUtils.setupDefaults();
		//Load defaults configs if empty
		saveDefaultConfig();
		//Updating config
		configUpdate.update();
		//nms class for items name utils
		if(setupItemsname()){
			nmsItemsName = true;
		} else {
			getLogger().severe(Language.getTranslation("updater_warn_1"));
			getLogger().severe(Language.getTranslation("updater_warn_2"));
			nmsItemsName = false;
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

	//Cauldren
	static Itemsname itemsname = null;
	public static boolean nmsItemsName = false;

	public static Itemsname getItemsname(){
		return itemsname;
	}

	private boolean setupItemsname(){
		String version;

		try{
			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		} catch (Exception e){
			return false;
		}

		//Getting itemsname class specific version
		switch (version) {
		case "v1_16_R2":
			itemsname = new Itemsname_1_16_R2();
			break;
		}

		return itemsname != null;
	}

	/*public HashMap<Player,Integer> editor = new HashMap<Player,Integer>();
	public HashMap<Player,CraftArray> craft = new HashMap<Player,CraftArray>();*/

	public ArrayList<UUID> craftProc = new ArrayList<UUID>();
	public HashMap<UUID, Location> caulLoc = new HashMap<UUID,Location>();
	public HashMap<UUID,ArrayList<ItemStack>> inCaulFin = new HashMap<UUID,ArrayList<ItemStack>>();

	public void sendDebug(Player player, String msg) {
		if(getConfig().getBoolean("debug_message")) {
			getLogger().info("CaulCrafting DEBUG " + player.getName() + ": " + msg);
		}
	}
}