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

import com.github.lincolnluiz.simplecrud_maven_plugin.exception.FileNotFoundException;
import com.github.lincolnluiz.simplecrud_maven_plugin.exception.GroupNotFoundException;
import com.github.lincolnluiz.simplecrud_maven_plugin.exception.TemplateDontCreateException;
import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Group;
import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Setup;
import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Template;
import com.github.lincolnluiz.simplecrud_maven_plugin.util.FileUtil;
import com.github.lincolnluiz.simplecrud_maven_plugin.util.StringUtil;

@ShellComponent
public class MakeCommand {

	private static final Charset CHARSET = StandardCharsets.UTF_8;

	@ShellMethod("Make file(s) based a group.")
	public void make(String groupTitle, String entity) {

		Optional<Group> groupOptional = Setup.getGroup(groupTitle);
		Group group = groupOptional.orElseThrow(() -> new GroupNotFoundException(groupTitle));
		processGroupTemplates(entity, group);
	}

	private void processGroupTemplates(String entity, Group group) {
		System.out.println(String.format("Generate templates from group '%s' with entity '%s'", group, entity));

		group.templates.forEach(template -> {
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -");
			System.out.println(String.format("Processing template file %s", template.filePath));

			processFileTamplate(entity, template, group);
		});

		System.out.println("Done!");
	}

	private void processFileTamplate(String entity, Template template, Group group) {
		Path templatePath = Paths.get(FileUtil.getPathWithSlashEnds(group.rootPath) + template.filePath);

		String contentParsed = processTEmplateWithEntity(templatePath, entity);

		String fileName = entity + template.suffixName;

		Path destinationPath = Paths
				.get(FileUtil.getPathWithSlashEnds(group.rootPath) + FileUtil.getPathWithSlashEnds(template.dest));

		if (!createFile(destinationPath, fileName, contentParsed))
			return;

		System.out.println(String.format("*********** File '%s' was created with success", fileName));
		System.out.println(String.format("*********** Location: '%s'", destinationPath));
		System.out.println();
	}

	private String processTEmplateWithEntity(Path path, String entity) {
		try {
			String content = new String(Files.readAllBytes(path), CHARSET);
			return replaceAll(content, entity);
		} catch (IOException e) {
			throw new FileNotFoundException(path.toString());
		}
	}

	private boolean createFile(Path path, String fileName, String content) {
		try {
			Files.createDirectories(path);
		} catch (IOException e1) {
		}
		
		try {
			Path finalPathFile = Paths.get(FileUtil.getPathWithSlashEnds(path.toString()) + fileName);
			File file = new File(finalPathFile.toString());

			if (file.exists()) {
				System.out.println(String.format("*********** ALERT! File '%s' already exists!", path));
				return false;
			}
			
			Files.write(finalPathFile, content.getBytes(CHARSET));
			return true;
		} catch (IOException e) {
			throw new TemplateDontCreateException(fileName, path.toString());
		}
	}
	
	private String replaceAll(String originalContent, String entity) {
		String content = originalContent;
		content = content.replaceAll("{entity}", entity);
		content = content.replaceAll("{entity.lower}", entity.toLowerCase());
		content = content.replaceAll("{entity.upper}", entity.toUpperCase());
		content = content.replaceAll("{entity.lowerFirst}", StringUtil.toLowerFirst(entity));
		
		return content;
	}

}
