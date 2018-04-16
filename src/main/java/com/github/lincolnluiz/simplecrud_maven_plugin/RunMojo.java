package com.github.lincolnluiz.simplecrud_maven_plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "run")
public class RunMojo extends AbstractMojo {

	@Parameter(property = "setupFile", defaultValue = "\\src\\main\\resources\\simple-crud-setup.json")
	private String setupFile;

	public void execute() throws MojoExecutionException {
		Application.main(setupFile);
	}

}