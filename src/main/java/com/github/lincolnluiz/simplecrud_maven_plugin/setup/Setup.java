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

	public List<Group> groups;
	
	public Setup(List<Group> groups) {
		this.groups = groups;
	}
	
	public static Setup get(String setupFile) {
		String path = System.getProperty("user.dir");
		
		File file = new File(path + "\\" + setupFile);
		ObjectMapper mapper = new ObjectMapper();
		
		List<Group> group = new ArrayList<Group>();
		
		try {
			group = mapper.readValue(file, new TypeReference<List<Group>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Setup(group);		
	}
	
}
