package com.github.lincolnluiz.simplecrud_maven_plugin.exception;

public class GroupNotFound extends RuntimeException {

	private static final long serialVersionUID = 2517961548L;
	
	@Override
	public String getMessage() {
		return "Group not found";
	}

}
