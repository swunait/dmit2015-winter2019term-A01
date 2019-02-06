package dmit2015.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import org.omnifaces.util.Messages;

@Named("helloWorld")	// default name is helloWorldController
@RequestScoped	// @SessionScoped, @ApplicationScoped
public class HelloWorldController {
	
	@NotBlank(message="Input value is required")
	private String input;
	private String output;
	
	public void submit() {
		output = "Hello World! You have typed: " + input;
		
		Messages.addGlobalInfo(output);
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

}