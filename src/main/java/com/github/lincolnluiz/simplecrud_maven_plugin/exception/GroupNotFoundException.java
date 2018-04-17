package com.github.lincolnluiz.simplecrud_maven_plugin.exception;

public class GroupNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2517961548L;
	
	public GroupNotFoundException(String groupTitle) {
		super(String.format("Group %s not found", groupTitle));
	}
	
	public GroupNotFoundException() {
		super("Group(s) not found");
	}
	
}
