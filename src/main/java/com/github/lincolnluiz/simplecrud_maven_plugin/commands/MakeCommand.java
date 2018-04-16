package com.github.lincolnluiz.simplecrud_maven_plugin.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MakeCommand {

	@ShellMethod("Add two integers together.")
	public int add(int a, int b) {
		return a + b;
	}

}
