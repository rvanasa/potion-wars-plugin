package com.potionwars.util;

import org.bukkit.ChatColor;

public abstract class TextFormatter {
	public static String format(String value, Object... args) {
		if(value == null) {
			return null;
		}

		String s = value.replaceAll("(?<!/)&", String.valueOf(ChatColor.COLOR_CHAR)).replaceAll("/&", "&");

		for(int i = 0; i < args.length; i++) {
			s = s.replace("{" + i + "}", String.valueOf(args[i]));
		}

		return s;
	}
}
