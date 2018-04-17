package com.github.lincolnluiz.simplecrud_maven_plugin;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;

import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Setup;

@SpringBootApplication
public class Application {
	
	public static void main(String... args) {
		Setup.process(args[0]);
		SpringApplication.run(Application.class, "");
	}
	
	@Bean
	public PromptProvider myPromptProvider() {
		return () -> new AttributedString("SimpleCrud:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));
	}

}
