package com.potionwars.build;

import com.potionwars.PotionWarsPlugin;
import com.potionwars.build.yml.YamlComponent;
import com.potionwars.build.yml.YamlFileBuilder;

import java.io.File;

public class BuildHandler {
	static final File DIR = new File(".");

	public static void main(String[] argv) throws Exception {

//		File messageDir = new File(argv[0], "messages");
//		messageDir.mkdir();
//		new File(messageDir, "HALT").createNewFile();

		new YamlFileBuilder()
				.add(new YamlComponent("name", PotionWarsPlugin.NAME))
				.add(new YamlComponent("version", PotionWarsPlugin.VERSION))
				.add(new YamlComponent("api-version", argv[1]))
				.add(new YamlComponent("main", PotionWarsPlugin.class.getName()))
				//				.add(new YamlComponent("commands", getCommandYml().toArray(new YamlComponent[0])))
				.save(new File(DIR, "target/classes/plugin.yml"));
	}
}
