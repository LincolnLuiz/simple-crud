package com.github.lincolnluiz.simplecrud_maven_plugin.commands;

import java.util.Optional;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Group;
import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Setup;

@ShellComponent
public class MakeCommand {

	@ShellMethod("Make file(s) based a group.")
	public void make(String groupTitle, String entity) {
		
		Optional<Group> groupOptional = Setup.getGroup(groupTitle);
		Group group = groupOptional.orElseThrow(RuntimeException::new);
		processGroupTemplates(group);
	}
	
	private void processGroupTemplates(Group group) {
		System.out.println(String.format("Generate templates from group %s", group));
		
		group.templates.forEach(template -> {
			System.out.println(String.format("Process template file %s", template.filePath));
		});
	}
	
}
