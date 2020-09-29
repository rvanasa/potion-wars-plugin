package com.potionwars.build.yml;

import org.yaml.snakeyaml.DumperOptions;

public class YamlComponent {
	private static final String LINE_BREAK = DumperOptions.LineBreak.getPlatformLineBreak().getString();

	private final String key, value;
	private final YamlComponent[] components;

	public YamlComponent(String key, String value, YamlComponent... components) {
		this.key = key;
		this.value = value;
		this.components = components;
	}

	public YamlComponent(String key, YamlComponent... components) {
		this(key, "", components);
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public YamlComponent[] getComponents() {
		return components;
	}

	public String getFormatted(int indent) {
		StringBuilder s = new StringBuilder();

		for(int i = 0; i < indent * 2; i++) {
			s.append(" ");
		}

		s.append(getKey()).append(": ").append(getValue()).append(LINE_BREAK);

		for(YamlComponent component : components) {
			s.append(component.getFormatted(indent + 1));
		}

		return s.toString();
	}
}
