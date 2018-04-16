package com.github.lincolnluiz.simplecrud_maven_plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.lincolnluiz.simplecrud_maven_plugin.setup.Setup;

@Mojo(name = "run")
public class RunMojo extends AbstractMojo {

	@Parameter(property = "setupFile", defaultValue = "\\src\\main\\resources\\simple-crud-setup.json")
	private String setupFile;

	public void execute() throws MojoExecutionException {
		Application.main("");
		Setup setup = Setup.get(setupFile);
//		
//		try {
//			ConsoleReader console = new ConsoleReader();
//			console.setPrompt("simple-crud> ");
//			String line = null;
//
//			List<Completer> completors = new LinkedList<Completer>();
//			
//			for (Group group : setup.groups) {
//				
//				List<String> variables = new ArrayList<String>();
//				for (String variable : group.variables) {
//					variables.add("--" + variable);
//				}
//				
//				completors.add(new AggregateCompleter(new ArgumentCompleter(
//						new StringsCompleter(group.title),
//						new StringsCompleter(variables),
//						new NullCompleter())));
//			}
//			
//			
//			for (Completer c : completors) {
//				console.addCompleter(c);
//			}
//
//			String pattern = "(\"[^\"]*\"|[^\"]+)(\\s+|$)";
//			
//			while ((line = console.readLine()) != null) {
//				console.println(line);
//				String[] split = line.split(pattern);
//				System.out.println(split.length);
//				System.out.println(split);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				TerminalFactory.get().restore();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		getLog().info("Hello " + setupFile);
	}

}