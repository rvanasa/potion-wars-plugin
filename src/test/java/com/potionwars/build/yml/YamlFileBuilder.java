package com.potionwars.build.yml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class YamlFileBuilder {
	private final List<YamlComponent> components = new ArrayList<>();

	public YamlFileBuilder() {

	}

	public List<YamlComponent> getComponents() {
		return components;
	}

	public YamlFileBuilder add(YamlComponent component) {
		getComponents().add(component);
		return this;
	}

	public YamlFileBuilder save(File file) throws IOException {
		try {
			if(!file.createNewFile()) {
				throw new IOException("Could not generate plugin.yml file at location: " + file.getAbsolutePath());
			}

			PrintWriter output = new PrintWriter(file);

			for(YamlComponent component : getComponents()) {
				output.print(component.getFormatted(0));
			}

			output.close();
		}
		catch(IOException e) {
			throw new IOException("Failed to save plugin.yml as file: " + file.getAbsolutePath(), e);
		}

		return this;
	}
}
