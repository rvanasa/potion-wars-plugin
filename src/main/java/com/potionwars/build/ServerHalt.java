package com.potionwars.build;

import com.potionwars.PotionWarsPlugin;

import java.io.File;

public class ServerHalt {
	public static void main(String[] argv) throws Exception {

		File messageDir = new File(argv[0], "messages");
		messageDir.mkdir();

		File messageFile = new File(messageDir, "HALT");
		messageFile.createNewFile();

//		Thread.sleep(1000 * 5);
	}
}
