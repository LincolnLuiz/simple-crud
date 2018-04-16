package com.github.lincolnluiz.simplecrud_maven_plugin.setup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Setup {

	public static List<Group> groups;
	
	public Setup(List<Group> pgroups) {
		groups = pgroups;
	}
	
	public static Setup get(String setupFile) throws JsonParseException, JsonMappingException, IOException {
		String path = System.getProperty("user.dir");
		
		File file = new File(path + "\\" + setupFile);
		ObjectMapper mapper = new ObjectMapper();
		
		List<Group> group = new ArrayList<Group>();
		
		group = mapper.readValue(file, new TypeReference<List<Group>>() {});
		return new Setup(group);		
	}
	
}
