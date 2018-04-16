package com.github.lincolnluiz.simplecrud_maven_plugin.setup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Setup {

	private static Optional<List<Group>> groups;

	public Setup(List<Group> groups) {
		Setup.groups = Optional.of(groups);
	}

	public static Setup process(String setupFile) {
		String path = System.getProperty("user.dir");

		File file = new File(path + "\\" + setupFile);
		ObjectMapper mapper = new ObjectMapper();

		List<Group> group = new ArrayList<Group>();

		try {
			group = mapper.readValue(file, new TypeReference<List<Group>>() {
			});
			
		} catch (JsonParseException e) {
			System.out.println("Error parse file");
			System.out.println(e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Error mapping file");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(String.format("Setup file not found, path: %s", setupFile));
		}
		return new Setup(group);
	}

	public static Optional<List<Group>> getGroups() {
		return Setup.groups;
	}
	
	public static Optional<Group> getGroup(String groupTitle) {
		List<Group> groups = Setup.groups.orElseThrow(RuntimeException::new);
		return groups.stream().filter(group -> groupTitle.equalsIgnoreCase(group.title)).findAny();
	}

}
