package com.github.lincolnluiz.simplecrud_maven_plugin.exception;

public class TemplateDontCreateException extends RuntimeException {

	private static final long serialVersionUID = 2517935947548L;
	
	public TemplateDontCreateException(String fileName, String pathTemplate) {
		super(String.format("File %s from template %s can't be created", fileName, pathTemplate));
	}

}
