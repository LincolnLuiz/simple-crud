package com.github.lincolnluiz.simplecrud_maven_plugin.commands;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Group;
import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Setup;
import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Template;

@ShellComponent
public class MakeCommand {
	
	private static final Charset CHARSET = StandardCharsets.UTF_8;

	@ShellMethod("Make file(s) based a group.")
	public void make(String groupTitle, String entity) {

		Optional<Group> groupOptional = Setup.getGroup(groupTitle);
		Group group = groupOptional.orElseThrow(RuntimeException::new);
		processGroupTemplates(entity, group);
	}

	private void processGroupTemplates(String entity, Group group) {
		System.out.println(String.format("Generate templates from group %s with entity %s", group, entity));

		group.templates.forEach(template -> {
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -");
			System.out.println(String.format("Process template file %s", template.filePath));
			
			processFileTamplate(entity, template, group);
		});

		System.out.println("Done!");
	}

	private void processFileTamplate(String entity, Template template, Group group) {
		Path path = Paths.get(template.filePath);
		
		String contentParsed = replaceVariableByEntity(path, entity);

		String fileName = entity + template.suffixName;

		Path finalPathFile = Paths
				.get(getPathWithSlashEnds(group.rootPath) + getPathWithSlashEnds(template.dest) + fileName);
		
		if (!createFile(finalPathFile, contentParsed))
			return;
		
		System.out.println(String.format("*********** File '%s' was created success", fileName));
		System.out.println(String.format("*********** Location: '%s'", finalPathFile));
		System.out.println();
	}

	private String replaceVariableByEntity(Path path, String entity) {
		try {
			String content = new String(Files.readAllBytes(path), CHARSET);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getPathWithSlashEnds(String path) {
		return !path.endsWith("/") ? path + "/" : path;
	}
	
	private boolean createFile(Path path, String content) {
		File file = new File(path.toString());
		
		if (file.exists()) {
			System.out.println(String.format("*********** ALERT! File '%s' alredy exists!", path));
			System.out.println();
			return false;
		}
		
		try {
			Files.write(path, content.getBytes(CHARSET));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
