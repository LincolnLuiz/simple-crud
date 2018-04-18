package com.github.lincolnluiz.simplecrud_maven_plugin.commands;

import java.util.List;
import java.util.Optional;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.github.lincolnluiz.simplecrud_maven_plugin.exception.GroupNotFoundException;
import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Group;
import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Setup;

@ShellComponent
public class GroupCommand {
	
	@ShellMethod(value = "List all available groups in your config file", key = "group ls")
	public void ls() {
		Optional<List<Group>> groupsOptional = Setup.getGroups();
		List<Group> groups = groupsOptional.orElseThrow(() -> new GroupNotFoundException());
		
		groups.forEach(System.out::println);
	}
	
	@ShellMethod(value = "Show templates in specific group", key = "group show")
	public void show(String groupTitle) {
		Optional<Group> groupOptional = Setup.getGroup(groupTitle);
		Group group = groupOptional.orElseThrow(() -> new GroupNotFoundException());
		
		group.templates.forEach(template -> {
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -");
			System.out.println("Template Path: " + template.templatePath);
			System.out.println("Dest: " + template.dest);
			System.out.println("Suffix Name: " + template.finalName);
		});
	}
	
}
