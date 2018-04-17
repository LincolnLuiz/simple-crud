package com.github.lincolnluiz.simplecrud_maven_plugin.util;

public class StringUtil {

	public static String toLowerFirst(String value) {
		if (value == null)
			return null;
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}
	
}
