package com.github.lincolnluiz.simplecrud_maven_plugin.exception;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2517935947548L;
	
	public FileNotFoundException(String filePath) {
		super("File not found, path: " + filePath);
	}

}
