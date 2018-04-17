package com.github.lincolnluiz.simplecrud_maven_plugin.util;

import java.io.File;

public class FileUtil {
	
	public static String SEPARATOR = File.separator;

	public static String getPathWithSlashEnds(String path) {
		return !path.endsWith(SEPARATOR) ? path + SEPARATOR : path;
	}
	
}
